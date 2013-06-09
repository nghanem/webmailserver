package com.nabeeh.webmail;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.lucene.analysis.*;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;

public class LuceneSearch {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

    ResultSet rs;

    String serverName, folderName;

    String mailPath = "./Lucene/Mail/";

    String spamDataPath = "./Lucene/Spam/";
    
    IndexSearcher is = null;

    public LuceneSearch(String serverName, String folderName) {
    	super();
        this.serverName = serverName;
        this.folderName = folderName;
    }

    public LuceneSearch() {
        try {
			is = new IndexSearcher(mailPath);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }

    public void close() {
    	try {
			is.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }

    public void MailIndex() {
        try {
            String sql = "SELECT * FROM tb_Mail, tb_Server, tb_Folder, tb_MailLink"
                    + " WHERE tb_Server.Server_ID=tb_Mail.fk_Mail_Server_id"
                    + " AND tb_Folder.Folder_ID=tb_Mail.fk_Mail_Folder_Id"
                    + " AND tb_Server.Server_Name='" + this.serverName + "'"
                    + " AND tb_Folder.Folder_Name='" + this.folderName + "'"
                    + " AND tb_Mail.Mail_ID=tb_MailLink.MailLink_StartId";

            rs = DBManager.constructLuceneDB(sql);
            System.out.println("mailPath : " + mailPath);
            synchronized(this){
            IndexWriter writer = new IndexWriter(mailPath, getAnalyzer(), true);
            while (this.rs.next()) {
                Document doc = new Document();
                doc.add(Field.Keyword("id", rs.getString("tb_Mail.Mail_ID")));
                doc.add(Field.Text("from", rs.getString("tb_Mail.Mail_From")));
                doc.add(Field.Text("to", rs.getString("tb_Mail.Mail_To")));
                doc.add(Field.Text("cc", rs.getString("tb_Mail.Mail_Cc")));
                doc.add(Field.Text("bcc", rs.getString("tb_Mail.Mail_Bcc")));
                doc.add(Field.Text("subject", rs
                        .getString("tb_Mail.Mail_Subject")));
                doc.add(Field.Text("content", rs
                        .getString("tb_Mail.Mail_Content")));

                doc.add(Field.Text("hasUnread", rs
                        .getString("tb_MailLink.MailLink_HasUnread")));
                doc.add(Field.Text("hasAttachment", rs
                        .getString("tb_Mail.Mail_HasAttachment")));
                doc.add(Field.Text("date", rs.getString("tb_Mail.Mail_Date")));
                doc.add(Field.Text("size", rs.getString("tb_Mail.Mail_Size")));
                writer.addDocument(doc);
            }
            writer.optimize();
            writer.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void SpamDataIndex() {
        try {
            String sql = "SELECT * FROM tb_SpamData";

            rs = DBManager.constructLuceneDB(sql);

            IndexWriter writer = new IndexWriter(spamDataPath, getAnalyzer(),
                    true);
            while (this.rs.next()) {
                Document doc = new Document();
                doc.add(Field.Text("word", rs
                        .getString("tb_SpamData.SpamData_Word")));
                doc.add(Field.Text("frequency", rs
                        .getString("tb_SpamData.SpamData_Frequency")));

                writer.addDocument(doc);
            }
            writer.optimize();
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Analyzer getAnalyzer() {
        // return new StandardAnalyzer();
        return new SimpleAnalyzer();
    }

    public Hits mailSearch(String queryString, String searchBy) {
        Hits hits = null;
        try {
			is = new IndexSearcher(mailPath);
            Query query = QueryParser.parse(queryString, searchBy,
                    getAnalyzer());

            hits = is.search(query);

        } catch (Exception e) {
        	e.printStackTrace();
        }
        return hits;
    }

    public double spamSearch(String[] words) {
        Hits hits = null;
        double spamProb = 1;
        double hamProb = 1;
        IndexSearcher is =null;
        for (String word : words) {
            try {
                is = new IndexSearcher(spamDataPath);

                Query query = QueryParser.parse(word, "word", getAnalyzer());

                hits = is.search(query);

                if (hits.length() == 0) {
                    spamProb *= 0.4;

                    hamProb *= 0.6;
                } else {

                    Document doc = hits.doc(0);

                    spamProb *= Double.parseDouble(doc.get("frequency"));

                    hamProb *= (1 - Double.parseDouble(doc.get("frequency")));
                }
                is.close();
            }

            catch (Exception e) {
                System.out.print(e);
            }
        }

        return (spamProb / (spamProb + hamProb));
    }
}
