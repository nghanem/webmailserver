package com.nabeeh.webmail;




import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import java.io.*;



public class DBManager {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/
    public static Connection connection;

    private static String driver = "com.mysql.jdbc.Driver";

    private static String url = "jdbc:mysql://localhost/WebMail";
   // private static String url = "jdbc:mysql://localhost:3307/WebMail";//?useUnicode=true&characterEncoding=utf8";

    private static String user = "root";

    private static String password = "";

    private static String uploadPath = "./upload/";

    static public void ConnectMySQL() throws ClassNotFoundException,
            SQLException {

        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        if (!connection.isClosed()) {
            System.out.println("DB connection successful!");
        } else {
            System.out.println("DB connection fail!");
        }
    }

    static public int getUserAmount() {
        Statement statement;
        ResultSet resultSet;
        int amount = 0;
        try {
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("SELECT MAX(tb_User.User_ID) FROM `tb_User`");

            while (resultSet.next()) {
                amount = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return amount;
    }

    static public int getUploadFileAmount() {
        Statement statement;
        ResultSet resultSet;
        int amount = 0;
        try {
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("SELECT MAX(tb_WebDisc.WebDisc_ID) FROM `tb_WebDisc`");

            while (resultSet.next()) {
                amount = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return amount;
    }

    private static int getFolderAmount() {
        Statement statement;
        ResultSet resultSet;
        int amount = 0;
        try {
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("SELECT MAX(tb_Folder.Folder_ID) FROM `tb_Folder`");

            while (resultSet.next()) {
                amount = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return amount;
    }

    private static int getSmartFolderAmount() {
        Statement statement;
        ResultSet resultSet;
        int amount = 0;
        try {
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("SELECT MAX(tb_SmartFolder.SmartFolder_ID) FROM `tb_SmartFolder`");

            while (resultSet.next()) {
                amount = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        return amount;
    }

    static public int getMailAmount() {
        Statement statement;
        ResultSet resultSet;
        int amount = 0;
        try {
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("SELECT MAX(tb_Mail.Mail_ID) FROM `tb_Mail`");

            while (resultSet.next()) {
                amount = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return amount;
    }

    static public int getServerAmount() {
        Statement statement;
        ResultSet resultSet;
        int amount = 0;
        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT MAX(tb_Server.Server_ID) FROM `tb_Server`");

            while (resultSet.next()) {
                amount = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return amount;
    }
  
    
    static public int getSecurityAmount() {
        Statement statement;
        ResultSet resultSet;
        int amount = 0;
        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT MAX(tb_Security.Security_ID) FROM `tb_Security`");

            while (resultSet.next()) {
                amount = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return amount;
    }
    
    

    static public int getContactAmount() {
        Statement statement;
        ResultSet resultSet;
        int amount = 0;
        try {
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("SELECT MAX(tb_Contact.Contact_ID) FROM `tb_Contact`");

            while (resultSet.next()) {
                amount = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return amount;
    }

    static public int getAttachmentAmount() {
        Statement statement;
        ResultSet resultSet;
        int amount = 0;
        try {
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("SELECT MAX(tb_Attachment.Attachment_ID) FROM `tb_Attachment`");

            while (resultSet.next()) {
                amount = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return amount;
    }

   
    static public boolean isUserVerification(String userName) {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tb_User WHERE User_Name='"
                            + userName + "'");

            if (resultSet.next()) {
            	System.out.println("inside DB is unread true");
                resultSet.close();
                statement.close();
                return true;
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }
    
    
    
    static public boolean loginVerification(String userName, String password) {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tb_User WHERE User_Name='"
                            + userName + "' AND User_Password='" + password
                            + "'");

            if (resultSet.next()) {
                resultSet.close();
                statement.close();
                return true;
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }
    
    
    static public boolean forgottenPasswordVerification(String sequrityQ, String sequrityA, String userName) {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tb_security WHERE securityQ='"
                            + sequrityQ + "' AND securityA='" + sequrityA + "' AND userName='" + userName
                            + "'");

            if (resultSet.next()) {
                resultSet.close();
                statement.close();
                return true;
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }
    
   

    static public boolean existEmailVerification(String emailAddress) {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement
                    .executeQuery("SELECT * FROM tb_Server WHERE Server_Name='"
                            + emailAddress + "'");
            if (resultSet.next()) {
                resultSet.close();
                statement.close();
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }

        static public boolean addAccount(String userName, String password,String emailAccount, String emailPassword,String securityQ, String securityA) {
        	System.out.println("securityq "+securityQ);
        	System.out.println("securitya "+securityA);
        	try {
            if (!loginVerification(userName, password)) {

                Statement statement;
                statement = connection.createStatement();

                statement.executeUpdate("INSERT INTO tb_User VALUES ("
                        + (getUserAmount() + 1) + ",'" + userName + "' , '"
                        + password + "')");

                statement.executeUpdate("INSERT INTO tb_Server VALUES ("
                        + (getServerAmount() + 1) + ",'" + emailAccount
                        + "' , '" + emailPassword + "', " + getUserAmount()
                        + ")");
                
                
                statement.executeUpdate("INSERT INTO tb_security VALUES ("
                        + (getSecurityAmount() + 1) + ",'" + securityQ
                        + "' , '" + securityA + "', " + userName
                        + ")");
                
                
            }
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static int getUserId(String userName) {
        Statement statement;
        ResultSet resultSet;
        int id = 0;
        try {
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("SELECT tb_User.User_ID FROM `tb_User`"
                            + " WHERE User_Name='" + userName + "'");

            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return id;
    }

    public static int getServerId(String serverName) {
        Statement statement;
        ResultSet resultSet;
        int id = 0;
        try {
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("SELECT tb_Server.Server_ID FROM `tb_Server`"
                            + " WHERE Server_Name='" + serverName + "'");

            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return id;
    }

    public static int getFolderId(String folderName) {
        Statement statement;
        ResultSet resultSet;
        int id = 0;
        try {
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("SELECT tb_Folder.Folder_ID FROM `tb_Folder`"
                            + " WHERE Folder_Name='" + folderName + "'");

            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return id;
    }

    public static ArrayList<String> getServerNames(String userName) {
        Statement statement;
        ResultSet resultSet;
        ArrayList<String> nameList = new ArrayList<String>();
        try {
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("SELECT tb_Server.Server_Name FROM `tb_Server`"
                            + " WHERE fk_Server_User_id=" + getUserId(userName));

            while (resultSet.next()) {
                nameList.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return nameList;
    }

    static public ArrayList<Mail> getMail(String serverName) {
        ArrayList<Mail> mailList = new ArrayList<Mail>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM `tb_Mail`,`tb_Server`, `tb_MailLink`"
                + " WHERE tb_Mail.fk_Mail_Server_id=tb_Server.Server_ID"
                + " AND tb_Server.Server_Name='"
                + serverName
                + "'"
                + " AND tb_Mail.Mail_ID=tb_MailLink.MailLink_StartId";
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM `tb_Mail`,`tb_Server`, `tb_MailLink`"
                            + " WHERE tb_Mail.fk_Mail_Server_id=tb_Server.Server_ID"
                            + " AND tb_Server.Server_Name='"
                            + serverName
                            + "'"
                            + " AND tb_Mail.Mail_ID=tb_MailLink.MailLink_StartId");

            Mail mail;
            while (resultSet.next()) {
                mail = new Mail();
                mail.setMailId(resultSet.getInt("tb_Mail.Mail_ID"));
                mail.setMailFrom(resultSet.getString("tb_Mail.Mail_From"));
                mail.setMailTo(resultSet.getString("tb_Mail.Mail_To"));
                mail.setMailCc(resultSet.getString("tb_Mail.Mail_Cc"));
                mail.setMailBcc(resultSet.getString("tb_Mail.Mail_Bcc"));
                mail.setMailSubject(resultSet.getString("tb_Mail.Mail_Subject"));
                mail.setMailContent(resultSet.getString("tb_Mail.Mail_Content"));
                mail.setMailDate(resultSet.getString("tb_Mail.Mail_Date"));
                mail.setMailSize(resultSet.getInt("tb_Mail.Mail_Size"));
                mail.setHasUnread(resultSet.getBoolean("tb_MailLink.MailLink_HasUnread"));
                mail.setHasAttachment(resultSet.getBoolean("tb_Mail.Mail_HasAttachment"));
                mail.setAncestorId(resultSet.getInt("tb_Mail.Mail_AncestorId"));
                mail.setParentId(resultSet.getInt("tb_Mail.Mail_ParentId"));
                mail.setChildId(resultSet.getInt("tb_Mail.Mail_ChildId"));
                mail.setMailLinkLength(resultSet
                        .getInt("tb_MailLink.MailLink_Length"));

                mailList.add(mail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mailList;
    }

    public static String getEmailPassword(String serverName) {
        Statement statement;
        ResultSet resultSet;

        int serverId = getServerId(serverName);
       
        System.out.println("serverID inside dbmanager getemailpassword="+serverId);

        String accountPassword = null;
        try {
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("SELECT * FROM `tb_Server` WHERE Server_ID="
                            + serverId);

            if (resultSet.next()) {
                accountPassword = resultSet.getString("Server_Password");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return accountPassword;
    }

    public static void addNewMails(ArrayList<Mail> mailList, String serverName, String folderName) {

        try {

            int serverId = getServerId(serverName);
            int folderId = getFolderId(folderName);

            Statement statement = connection.createStatement(), statForStartId, statForEndId, statForUpdate;

            Iterator<Mail> iter = mailList.iterator();
            Mail m;
            while (iter.hasNext()) {
                statForStartId = connection.createStatement();
                statForEndId = connection.createStatement();
                statForUpdate = connection.createStatement();
                ResultSet resForStartId, resForEndId;

                int mailId = getMailAmount() + 1;

                m = (Mail) iter.next();

                if (m.isHasAttachment()) {
                    int attachId = getAttachmentAmount() + 1;

                    ArrayList<File> fileList = m.getAttachment();
                    Iterator<File> iterFile = fileList.iterator();
                    while (iterFile.hasNext()) {
                        File file = (File) iterFile.next();
                        FileInputStream fis;

                        try {
                            fis = new FileInputStream(file);

                            PreparedStatement ps = DBManager.connection
                                    .prepareStatement("insert into tb_Attachment values ("
                                            + (attachId++)
                                            + ",'"
                                            + file.getName()
                                            + "',?,"
                                            + mailId
                                            + ")");

                            ps.setString(1, file.getName());
                            ps.setBinaryStream(1, fis, (int) file.length());
                            ps.executeUpdate();
                            ps.close();
                            fis.close();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                

                String subject = m.getMailSubject();
                if (subject != null) {
                    if (subject.contains("'")) {
                        subject = subject.replace("'", "\\'");
                    }
                    if (subject.startsWith("RE: ") || subject.startsWith("re: ") || subject.startsWith("Re: ")) {
                        subject = subject.substring("RE: ".length());

                        if (m.getAncestorId() == 0) {
                            Statement statForReply = connection.createStatement();

                            ResultSet resForReply = statForReply.executeQuery("SELECT * FROM tb_Mail, tb_MailLink"
                                    + " WHERE tb_Mail.Mail_Subject='" + subject + "'"
                                    + " AND tb_Mail.Mail_To='" + m.getMailFrom() + "'"
                                    + " AND tb_Mail.Mail_ID=tb_MailLink.MailLink_StartId"
                                    + " AND tb_Mail.fk_Mail_Server_id=" + serverId);
                            if (resForReply.next()) {
                                m.setAncestorId(resForReply.getInt("tb_Mail.Mail_AncestorId"));
                                m.setParentId(resForReply.getInt("tb_MailLink.MailLink_EndId"));
                            }
                        }



                    }
                }
                String content = m.getMailContent();
                if (content != null && content.contains("'")) {
                    content = content.replace("'", "\\'");
                }

                int startId = 0, endId = 0, linkLength = 0;
                if (m.getAncestorId() != 0) {
                    startId = m.getAncestorId();
                    endId = m.getParentId();
                    resForEndId = statForEndId.executeQuery("SELECT MailLink_Length FROM tb_MailLink WHERE MailLink_StartId="   + startId);
                    if (resForEndId.next()) {
                        linkLength = resForEndId.getInt("MailLink_Length");
                    }
                }
                else {

                    resForStartId = statForStartId
                            .executeQuery("SELECT MIN(Mail_ID) mid FROM tb_Mail"
                                    + " WHERE tb_Mail.Mail_Subject='"
                                    + subject + "'"
                                    + " AND tb_Mail.Mail_From='" + m.getMailFrom() + "'"
                                    + " AND tb_Mail.fk_Mail_Server_id=" + serverId);

                    if (resForStartId.next()) {
                        startId = resForStartId.getInt("mid");

                        resForEndId = statForEndId
                                .executeQuery("SELECT MailLink_EndId, MailLink_Length FROM tb_MailLink WHERE MailLink_StartId="
                                        + startId);
                        if (resForEndId.next()) {
                            endId = resForEndId.getInt("MailLink_EndId");
                            linkLength = resForEndId.getInt("MailLink_Length");
                        }
                    }
                }
                if (startId == 0) {
                    m.setAncestorId(mailId);
                    m.setParentId(mailId);
                    m.setChildId(mailId);
                    statForUpdate
                            .executeUpdate("INSERT INTO tb_MailLink VALUES("
                                    + m.getParentId() + ", " + m.getChildId()
                                    + ", 1," + m.isHasUnread() + ")");
                    statForUpdate
                            .executeUpdate("INSERT INTO tb_LinkMapFolder VALUES("
                                    + mailId + ", " + folderId + ")");
                } else {
                    m.setAncestorId(startId);
                    m.setParentId(endId);
                    m.setChildId(mailId);
                    statForUpdate
                            .executeUpdate("UPDATE tb_MailLink SET MailLink_EndId="
                                    + mailId
                                    + ", MailLink_Length="
                                    + (++linkLength)
                                    + ", MailLink_HasUnread="
                                    + m.isHasUnread()
                                    + " WHERE MailLink_StartId=" + startId);
                    statForUpdate
                            .executeUpdate("UPDATE tb_Mail SET Mail_ChildId="
                                    + mailId + " WHERE Mail_ID=" + endId);

                    if (!folderContainsLink(folderId, startId)) {
                        statForUpdate
                                .executeUpdate("INSERT INTO tb_LinkMapFolder VALUES("
                                        + startId + ", " + folderId + ")");
                    }
                }

                statement.execute("INSERT INTO tb_Mail VALUES(" + (mailId)
                        + ",'" + m.getMailFrom() + "','" + m.getMailTo()
                        + "','" + m.getMailCc() + "', '" + m.getMailBcc()
                        + "', '" + m.getMailSubject() + "','" + content + "','"
                        + m.getMailDate() + "'," + m.getMailSize() + ","
                        + m.isHasAttachment() + "," + folderId + "," + serverId
                        + "," + m.getAncestorId() + "," + m.getParentId() + ","
                        + m.getChildId() + ")");
            }
            statement.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static boolean folderContainsLink(int folderId, int startId) {

        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM tb_LinkMapFolder"
                            + " WHERE LinkMapFolder_LinkId=" + startId
                            + " AND LinkMapFolder_FolderId=" + folderId);

            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    private static boolean emotionExist(String name) {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement
                    .executeQuery("SELECT * FROM tb_Emotion WHERE Emotion_Name='"
                            + name + "'");

            if (resultSet.next()) {
                resultSet.close();
                statement.close();
                return true;
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkCurrentView(int mailId, String currentServer) {

        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement
                    .executeQuery("SELECT * FROM tb_Mail WHERE Mail_ID="
                            + mailId + " AND fk_Mail_Server_id="
                            + getServerId(currentServer));
            if (resultSet.next()) {
                resultSet.close();
                statement.close();
                return true;
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }
    public static ArrayList<Mail> getMailLink(int mailId) {
        ArrayList<Mail> mailLink = new ArrayList<Mail>();
        Mail mail;
        HashMap<Integer, String> attachmentIdName = new HashMap<Integer, String>();
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM `tb_Mail`"
                            + " WHERE tb_Mail.Mail_AncestorId=" + mailId
                            + " ORDER BY tb_Mail.Mail_ID ASC");
            String s = "SELECT * FROM `tb_Mail`"
                + " WHERE tb_Mail.Mail_AncestorId=" + mailId
                + " ORDER BY tb_Mail.Mail_ID ASC";
            //System.out.println("query getMailLink: " + s);
            String mailSubject;
            while (resultSet.next()) {
                mail = new Mail();
                int id = resultSet.getInt("tb_Mail.Mail_ID");
                mail.setMailId(id);
                mail.setMailFrom(resultSet.getString("tb_Mail.Mail_From"));
                mail.setMailTo(resultSet.getString("tb_Mail.Mail_To"));
                mail.setMailCc(resultSet.getString("tb_Mail.Mail_Cc"));
                if (!mail.getMailCc().equals("")) {
                    mail.setHasCc(true);
                }
                mail.setMailBcc(resultSet.getString("tb_Mail.Mail_Bcc"));
                if (!mail.getMailBcc().equals("")) {
                    mail.setHasBcc(true);
                }

                mailSubject = resultSet.getString("tb_Mail.Mail_Subject");
                if (mailSubject.contains("<")) {
                    mailSubject = mailSubject.replace("<", " ");
                    mailSubject = mailSubject.replace(">", " ");
                }

                mail.setMailSubject(mailSubject);
                mail.setMailContent(resultSet.getString("tb_Mail.Mail_Content"));
                mail.setMailDate(resultSet.getString("tb_Mail.Mail_Date"));
                mail.setMailSize(resultSet.getInt("tb_Mail.Mail_Size"));


                if (resultSet.getBoolean("tb_Mail.Mail_HasAttachment")) {
                    attachmentIdName = getAttachmentByMail(id);
                    mail.setAttachmentIdName(attachmentIdName);
                    mail.setHasAttachment(true);
                } else {
                    mail.setHasAttachment(false);
                }

                mailLink.add(mail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setMailLinkRead(mailId);

        return mailLink;
    }

    public static String getSingleMailContent(int mailId) {
        String content = null;
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM `tb_Mail`"
                            + " WHERE tb_Mail.Mail_ID=" + mailId);

            if (resultSet.next()) {
                content = resultSet.getString("tb_Mail.Mail_Content");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return content;
    }

    private static void setMailLinkRead(int id) {
        try {
            Statement statement;
            statement = connection.createStatement();
            statement
                    .executeUpdate("UPDATE tb_MailLink SET MailLink_HasUnread=0 WHERE MailLink_StartId="
                            + id);

            return;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static HashMap<Integer, String> getAttachmentByMail(int mailId) {
        HashMap<Integer, String> attachmentIdName = new HashMap<Integer, String>();
        Statement statement;
        try {
            statement = DBManager.connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("select * from tb_Attachment where fk_Attachment_Mail_id = "
                            + mailId);
            while (resultSet.next()) {
                attachmentIdName.put(resultSet.getInt("Attachment_ID"),
                        resultSet.getString("Attachment_Name"));
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return attachmentIdName;
    }

    public static ArrayList<Character> getAttachmentByName(String emotionName) {
        ArrayList<Character> emotion = new ArrayList<Character>();

        Statement statement;
        try {
            statement = DBManager.connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("select * from tb_Emotion where Emotion_Name = '"
                            + emotionName + "'");
            if (resultSet.next()) {

                Blob blob = resultSet.getBlob("Emotion_Content");

                InputStream inputStream = blob.getBinaryStream();
                int c;
                while ((c = inputStream.read()) != -1)
                    emotion.add((char) c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emotion;
    }

    public static ArrayList<Character> getAttachmentById(int attachmentId) {
        ArrayList<Character> attachment = new ArrayList<Character>();

        Statement statement;
        try {
            statement = DBManager.connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("select * from tb_Attachment where Attachment_ID = "
                            + attachmentId);
            if (resultSet.next()) {

                Blob blob = resultSet.getBlob("Attachment_Content");

                InputStream inputStream = blob.getBinaryStream();
                // File fileOutput = new File("");
                // FileOutputStream fo = new FileOutputStream(fileOutput);
                int c;
                while ((c = inputStream.read()) != -1)
                    // fo.write(c);
                    attachment.add((char) c);
                // fo.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return attachment;
    }

    public static ArrayList<String> getFolderListWithoutCommon(String serverName) {
        ArrayList<String> folderList = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM `tb_Folder`,`tb_Server`"
                            + " WHERE tb_Folder.fk_Folder_Server_id=tb_Server.Server_ID"
                            + " AND tb_Server.Server_Name='" + serverName + "'");

            while (resultSet.next()) {
                folderList.add(resultSet.getString("tb_Folder.Folder_Name"));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return folderList;
    }

    public static ArrayList<Folder> getFolderList(String serverName,
            String folderName) {
        ArrayList<Folder> folderList = new ArrayList<Folder>();
        ArrayList<String> existFolder = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement(), statEmptyFolder = connection
                    .createStatement();
            ResultSet resultSet, resultSetEmptyFolder;

            int serverId = getServerId(serverName);

            String sql = "SELECT tb_Folder.Folder_Name, sum(tb_MailLink.MailLink_HasUnread) sumOfUnread"
                    + " from tb_Folder, tb_Mail, tb_MailLink "
                    + " WHERE tb_MailLink.MailLink_StartId=tb_Mail.Mail_ID"
                    + " AND tb_Folder.fk_Folder_Server_id=0"
                    + " AND tb_Mail.fk_Mail_Folder_id=tb_Folder.Folder_ID"
                    + " AND tb_Mail.fk_Mail_Server_id=" + serverId
                    + " GROUP BY tb_Folder.Folder_Name";



            resultSet = statement.executeQuery(sql);

            String fName;
            int sumOfUnread;
            boolean isCurrentFolder = false, hasUnreadMail = false;
            while (resultSet.next()) {
                fName = resultSet.getString("Folder_Name");
                if (fName.equals("Sent")) {
                    sumOfUnread = 0;
                } else {
                    sumOfUnread = resultSet.getInt("sumOfUnread");
                }
                existFolder.add(fName);

                isCurrentFolder = false;
                if (fName.equals(folderName)) {
                    isCurrentFolder = true;
                }
                hasUnreadMail = false;
                if (sumOfUnread != 0) {
                    hasUnreadMail = true;
                }
                folderList.add(new Folder(fName, sumOfUnread, isCurrentFolder,
                        hasUnreadMail));
            }

            resultSet.close();
            statement.close();

            resultSetEmptyFolder = statEmptyFolder
                    .executeQuery("select distinct(Folder_Name) from tb_Folder where fk_Folder_Server_id=0");
            while (resultSetEmptyFolder.next()) {
                fName = resultSetEmptyFolder.getString("Folder_Name");
                if (!existFolder.contains(fName)) {
                    existFolder.add(fName);
                    folderList.add(new Folder(fName, 0, fName
                            .equals(folderName), false));
                }
            }
            resultSetEmptyFolder.close();
            statEmptyFolder.close();

            statement = connection.createStatement();
            statEmptyFolder = connection.createStatement();

            sql = "SELECT tb_Folder.Folder_Name, sum(tb_MailLink.MailLink_HasUnread) sumOfUnread"
                    + " FROM tb_Folder, tb_Mail, tb_MailLink "
                    + " WHERE tb_MailLink.MailLink_StartId=tb_Mail.Mail_ID"
                    + " AND tb_Folder.fk_Folder_Server_id="
                    + serverId
                    + " AND tb_Mail.fk_Mail_Folder_id=tb_Folder.Folder_ID"
                    + " GROUP BY tb_Folder.Folder_Name";

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                fName = resultSet.getString("Folder_Name");
                sumOfUnread = resultSet.getInt("sumOfUnread");
                // sumOfMail = resultSet.getInt("sumOfMail");
                existFolder.add(fName);

                isCurrentFolder = false;
                if (fName.equals(folderName)) {
                    isCurrentFolder = true;
                }
                hasUnreadMail = false;
                if (sumOfUnread != 0) {
                    hasUnreadMail = true;
                }
                folderList.add(new Folder(fName, sumOfUnread, isCurrentFolder,
                        hasUnreadMail));

            }

            resultSetEmptyFolder = statEmptyFolder
                    .executeQuery("select distinct(Folder_Name) from tb_Folder where fk_Folder_Server_id="
                            + serverId);
            while (resultSetEmptyFolder.next()) {
                fName = resultSetEmptyFolder.getString("Folder_Name");
                if (!existFolder.contains(fName)) {
                    existFolder.add(fName);
                    folderList.add(new Folder(fName, 0, fName
                            .equals(folderName), false));
                }
            }
            resultSetEmptyFolder.close();
            statEmptyFolder.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return folderList;
    }

    

    public static ArrayList<Mail> getCertainFolderMail(String serverName,
            String folderName, String smartFolderName, String orderby, int pageNum) {
        ArrayList<Mail> mailList = new ArrayList<Mail>();

        Mail mail;

        int serverId = getServerId(serverName);
        int folderId = getFolderId(folderName);
        try {
            Statement statement;

            statement = connection.createStatement();

            ResultSet resultSet, resultSetConstraint;
            String sql;

            int fifteenMailsPerPage = 15;
            int startIndex = pageNum * fifteenMailsPerPage;
            String orderbtString = "tb_MailLink.MailLink_EndId";
            if(orderby != null && orderby.equalsIgnoreCase("from")){
                orderbtString ="tb_Mail.Mail_From";
            }else if(orderby != null && orderby.equalsIgnoreCase("subject")){
                orderbtString = "tb_Mail.Mail_Subject";
            }else if(orderby != null && orderby.equalsIgnoreCase("date")){
                orderbtString = "tb_Mail.Mail_Date";
            }else if(orderby != null && orderby.equalsIgnoreCase("size")){
                orderbtString = "tb_Mail.Mail_Size";
            }
            if (smartFolderName == null) {

                sql = ("SELECT * FROM tb_MailLink, tb_Mail, tb_Folder, tb_LinkMapFolder"
                        + " WHERE tb_MailLink.MailLink_StartId=tb_LinkMapFolder.LinkMapFolder_LinkId"
                        + " AND tb_LinkMapFolder.LinkMapFolder_FolderId=" + folderId
                        + " AND tb_LinkMapFolder.LinkMapFolder_FolderId=tb_Folder.Folder_ID"
                        + " AND tb_Mail.fk_Mail_Server_id=" + serverId
                        + " AND tb_Mail.Mail_ID=tb_MailLink.MailLink_StartId"
                        + " ORDER BY " + orderbtString + " DESC"
                        //+ " ORDER BY tb_MailLink.MailLink_EndId DESC"
                        + " Limit " + startIndex + ", 15"
                        );

//                System.out.println("query getCertainFolderMail : " + sql);

                resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {

                    mail = new Mail();

                    mail.setMailId(resultSet.getInt("tb_Mail.Mail_ID"));
                    mail.setMailFrom(resultSet.getString("tb_Mail.Mail_From"));
                    mail.setMailTo(resultSet.getString("tb_Mail.Mail_To"));
                    mail.setMailCc(resultSet.getString("tb_Mail.Mail_Cc"));
                    mail.setMailBcc(resultSet.getString("tb_Mail.Mail_Bcc"));
                    mail.setMailSubject(resultSet.getString("tb_Mail.Mail_Subject"));
                    mail.setMailContent(resultSet.getString("tb_Mail.Mail_Content"));
                    mail.setMailDate(resultSet.getString("tb_Mail.Mail_Date"));
                    mail.setMailSize(resultSet.getInt("tb_Mail.Mail_Size"));
                    mail.setHasAttachment(resultSet.getBoolean("tb_Mail.Mail_HasAttachment"));
                    mail.setAncestorId(resultSet.getInt("tb_Mail.Mail_AncestorId"));
                    mail.setParentId(resultSet.getInt("tb_Mail.Mail_ParentId"));
                    mail.setChildId(resultSet.getInt("tb_Mail.Mail_ChildId"));
                    mail.setHasUnread(resultSet.getBoolean("tb_MailLink.MailLink_HasUnread"));
                    mail.setMailLinkLength(resultSet.getInt("tb_MailLink.MailLink_Length"));
                    if (mail.getMailLinkLength() > 1) {
                        mail.setHasMoreThanOne(true);
                    }

                    mailList.add(mail);
                }
            }
            else {
                String q =  "SELECT * FROM tb_SmartFolder"
                    + " WHERE tb_SmartFolder.SmartFolder_Name='" + smartFolderName + "'"
                    + " AND tb_SmartFolder.fk_SmartFolder_Server_id=" + serverId;
                //System.out.println("query getCertainFolderMail else :"+ q);
                 resultSetConstraint = statement.executeQuery("SELECT * FROM tb_SmartFolder"
                            + " WHERE tb_SmartFolder.SmartFolder_Name='" + smartFolderName + "'"
                            + " AND tb_SmartFolder.fk_SmartFolder_Server_id=" + serverId);

                 String constraint = null;
                 if (resultSetConstraint.next()) {
                     constraint = resultSetConstraint.getString("tb_SmartFolder.SmartFolder_Constraint");
                 }
                String query = "SELECT * FROM tb_MailLink, tb_Mail, tb_LinkMapFolder"
                    + " WHERE tb_Mail.fk_Mail_Server_id=" + serverId
//                  + " AND tb_MailLink.MailLink_StartId=tb_LinkMapFolder.LinkMapFolder_LinkId"
                    + " AND tb_LinkMapFolder.LinkMapFolder_FolderId=1" //inbox
                    + " AND tb_LinkMapFolder.LinkMapFolder_LinkId=tb_MailLink.MailLink_StartId"
                    + " AND tb_Mail.Mail_ID=tb_MailLink.MailLink_StartId"
                    + " AND " + constraint
                    + " ORDER BY tb_MailLink.MailLink_StartId DESC"
                    + " Limit " + startIndex + ", 15";
//                System.out.println("query getCertainFolderMail xxxx:"+ query);
                 resultSet = statement.executeQuery("SELECT * FROM tb_MailLink, tb_Mail, tb_LinkMapFolder"
                        + " WHERE tb_Mail.fk_Mail_Server_id=" + serverId
//                      + " AND tb_MailLink.MailLink_StartId=tb_LinkMapFolder.LinkMapFolder_LinkId"
                        + " AND tb_LinkMapFolder.LinkMapFolder_FolderId=1" //inbox
                        + " AND tb_LinkMapFolder.LinkMapFolder_LinkId=tb_MailLink.MailLink_StartId"
                        + " AND tb_Mail.Mail_ID=tb_MailLink.MailLink_StartId"
                        + " AND " + constraint
                        + " ORDER BY tb_MailLink.MailLink_StartId DESC"
                        + " Limit " + startIndex + ", 15"
                        );

                 while (resultSet.next()) {
                    mail = new Mail();

                    mail.setMailId(resultSet.getInt("tb_Mail.Mail_ID"));
                    mail.setMailFrom(resultSet.getString("tb_Mail.Mail_From"));
                    mail.setMailTo(resultSet.getString("tb_Mail.Mail_To"));
                    mail.setMailCc(resultSet.getString("tb_Mail.Mail_Cc"));
                    mail.setMailBcc(resultSet.getString("tb_Mail.Mail_Bcc"));
                    mail.setMailSubject(resultSet.getString("tb_Mail.Mail_Subject"));
                    mail.setMailContent(resultSet.getString("tb_Mail.Mail_Content"));
                    mail.setMailDate(resultSet.getString("tb_Mail.Mail_Date"));
                    mail.setMailSize(resultSet.getInt("tb_Mail.Mail_Size"));
                    mail.setHasAttachment(resultSet.getBoolean("tb_Mail.Mail_HasAttachment"));
                    mail.setAncestorId(resultSet.getInt("tb_Mail.Mail_AncestorId"));
                    mail.setParentId(resultSet.getInt("tb_Mail.Mail_ParentId"));
                    mail.setChildId(resultSet.getInt("tb_Mail.Mail_ChildId"));
                    mail.setHasUnread(resultSet.getBoolean("tb_MailLink.MailLink_HasUnread"));
                    mail.setMailLinkLength(resultSet.getInt("tb_MailLink.MailLink_Length"));
                    if (mail.getMailLinkLength() > 1) {
                        mail.setHasMoreThanOne(true);
                    }

                    mailList.add(mail);
                 }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mailList;
    }

    public static void createFolder(String userName, String folderName,
            String serverName) {

        int serverId = getServerId(serverName);
        int folderId = getFolderAmount() + 1;
        Statement statement;
        try {
            statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO tb_Folder VALUES (" + folderId
                    + ",'" + folderName + "' , " + serverId + ")");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void deleteFolders(ArrayList<String> deleteFolderList,
            String serverName) {
        int serverId = getServerId(serverName);
        Statement statement;
        try {
            statement = connection.createStatement();

            Iterator<String> iter = deleteFolderList.iterator();

            int fId = 0;
            while (iter.hasNext()) {
                fId = getFolderId(iter.next());
                statement
                        .executeUpdate("DELETE FROM tb_Folder WHERE tb_Folder.Folder_ID="
                                + fId
                                + " AND tb_Folder.fk_Folder_Server_id="
                                + serverId);
                statement
                        .executeUpdate("UPDATE tb_Mail SET fk_Mail_Folder_id=4 WHERE fk_Mail_Folder_id="
                                + fId);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void deleteMails(ArrayList<Integer> deleteMailList) {
        Statement statement, statMailFolder;
        ResultSet resMailFolder;
        String folderName = null;
        try {
            statement = connection.createStatement();
            statMailFolder = connection.createStatement();

            Iterator<Integer> iter = deleteMailList.iterator();
            while (iter.hasNext()) {
                int ancestorId = iter.next();

                resMailFolder = statMailFolder.executeQuery("SELECT * FROM tb_LinkMapFolder, tb_Folder"
                        + " WHERE tb_LinkMapFolder.LinkMapFolder_LinkId=" + ancestorId
                        + " AND tb_LinkMapFolder.LinkMapFolder_FolderId=tb_Folder.Folder_ID");


                while(resMailFolder.next()) {
                    folderName = resMailFolder.getString("tb_Folder.Folder_Name");
                }

                if (folderName.equals("Trash")) {

                    statement.executeUpdate("DELETE FROM tb_Mail WHERE tb_Mail.Mail_AncestorId=" + ancestorId);

                    ArrayList<Integer> childrenId = getChildrenId(ancestorId);
                    Iterator<Integer> iterChildId = childrenId.iterator();
                    while (iterChildId.hasNext()) {
                        statement.executeUpdate("DELETE FROM tb_Attachment WHERE tb_Attachment.fk_Attachment_Mail_id=" + iterChildId.next());
                    }

                    statement.executeUpdate("DELETE FROM tb_LinkMapFolder WHERE tb_LinkMapFolder.LinkMapFolder_LinkId=" + ancestorId);
                    statement.executeUpdate("DELETE FROM tb_MailLink WHERE tb_MailLink.MailLink_StartId=" + ancestorId);

                }
                else {
                    statement.executeUpdate("UPDATE tb_Mail, tb_Folder SET "
                            + " tb_Mail.fk_Mail_Folder_id=tb_Folder.Folder_ID"
                            + " WHERE tb_Folder.Folder_Name='Trash' "
                            + " AND tb_Mail.Mail_AncestorId=" + ancestorId);
                    statement.executeUpdate("DELETE FROM tb_LinkMapFolder WHERE tb_LinkMapFolder.LinkMapFolder_LinkId=" + ancestorId);
                    statement.executeUpdate("INSERT INTO tb_LinkMapFolder VALUES (" + ancestorId + ", 5)");
                }
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static ArrayList<Integer> getChildrenId(int mailId) {
        ArrayList<Integer> childrenList = new ArrayList<Integer>();

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM `tb_Mail`"
                            + " WHERE tb_Mail.Mail_AncestorId=" + mailId);

            while (resultSet.next()) {
                childrenList.add(resultSet.getInt("tb_Mail.Mail_ID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return childrenList;
    }

    public static HashMap<Integer, String> getServerIdName(String userName,
            String serverName) {
        HashMap<Integer, String> emailAccounts = new HashMap<Integer, String>();
        Statement statement;

        try {
            int userId = getUserId(userName);
            statement = DBManager.connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("select * from tb_Server where fk_Server_User_id = "
                            + userId);
            while (resultSet.next()) {
 
                emailAccounts.put(resultSet.getInt("Server_ID"), resultSet
                        .getString("Server_Name"));
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return emailAccounts;
    }

    public static void setBasicInfo(String userName, String newPassword) {
        int userId = getUserId(userName);
        try {
            Statement statement;
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE tb_User SET User_Password=" + "'"
                    + newPassword + "' WHERE User_ID=" + userId);
            return;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void setExistInfo(int serverId, String newPassword) {
        try {
            Statement statement;
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE tb_Server SET Server_Password="
                    + "'" + newPassword + "' WHERE Server_ID=" + serverId);

            return;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void addNewEmailAccount(String userName, String emailAccount,
            String newPassword) {
        int userId = getUserId(userName);
        int serverAmount = getServerAmount() + 1;
        try {
            Statement statement;
            statement = connection.createStatement();
 

            statement.executeUpdate("INSERT INTO tb_Server VALUES("
                    + serverAmount + ",'" + emailAccount + "','" + newPassword
                    + "'," + userId + ")");
            return;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static int getMaxPage(String serverName, String folderName) {
        Statement statement;
        ResultSet resultSet;
        int amount = 0;
        int serverId = getServerId(serverName);
        int folderId = getFolderId(folderName);

        int fifteenMailsPerPage = 16;
        try {
            statement = connection.createStatement();




            resultSet = statement
                    .executeQuery("SELECT DISTINCT(COUNT(tb_LinkMapFolder.LinkMapFolder_LinkId))"
                            + " FROM tb_Folder, tb_LinkMapFolder, tb_Mail"
                            + " WHERE tb_Folder.Folder_ID=" + folderId
//                          + "tb_Folder.fk_Folder_Server_id=" + serverId
                            + " AND tb_Mail.Mail_ID=tb_LinkMapFolder.LinkMapFolder_LinkId"
                            + " AND tb_Mail.fk_Mail_Server_id=" + serverId
                            + " AND tb_Folder.Folder_ID=tb_LinkMapFolder.LinkMapFolder_FolderId");

            while (resultSet.next()) {
                amount = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return (amount / fifteenMailsPerPage) + 1;
    }

    public static ArrayList<String> getSmartFolderList(String serverName) {
        ArrayList<String> folderList = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet;

            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("SELECT * FROM `tb_SmartFolder`,`tb_Server`"
                            + " WHERE tb_SmartFolder.fk_SmartFolder_Server_id=tb_Server.Server_ID"
                            + " AND tb_Server.Server_Name='" + serverName + "'"
                            + " ORDER BY tb_SmartFolder.SmartFolder_id");

            while (resultSet.next()) {
                folderList.add(folderList.size(), resultSet
                        .getString("tb_SmartFolder.SmartFolder_Name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return folderList;
    }

    public static void deleteSmartFolders(ArrayList<String> deleteFolderList,
            String serverName) {
        int serverId = getServerId(serverName);
        Statement statement;
        try {
            statement = connection.createStatement();

            Iterator<String> iter = deleteFolderList.iterator();
            while (iter.hasNext()) {
                statement
                        .executeUpdate("DELETE FROM tb_SmartFolder WHERE tb_SmartFolder.SmartFolder_Name='"
                                + iter.next().toString()
                                + "'"
                                + " AND tb_SmartFolder.fk_SmartFolder_Server_id="
                                + serverId);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void createSmartFolder(String folderName, String serverName,
            String sqlConstraint) {
        int serverId = getServerId(serverName);
        int folderId = getSmartFolderAmount() + 1;
        Statement statement;
        try {
            statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO tb_SmartFolder VALUES ("
                    + folderId + ",'" + folderName + "' , '" + sqlConstraint
                    + "', " + serverId + ")");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public static String  getSmartFolderSqlConstraint(String folderName, 
    		String serverName) {
        String s = "";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            statement = connection.createStatement();
String sss = "SELECT tb_SmartFolder.SmartFolder_Constraint FROM `tb_SmartFolder`,`tb_Server`"
    + " WHERE tb_SmartFolder.fk_SmartFolder_Server_id=tb_Server.Server_ID"
    + " AND tb_Server.Server_Name='" + serverName 
    + "' AND tb_SmartFolder.folderName='"+ folderName + "'";

System.out.println("sssss :" + sss);
            resultSet = statement
                    .executeQuery("SELECT tb_SmartFolder.SmartFolder_Constraint FROM `tb_SmartFolder`,`tb_Server`"
                            + " WHERE tb_SmartFolder.fk_SmartFolder_Server_id=tb_Server.Server_ID"
                            + " AND tb_Server.Server_Name='" + serverName 
                            + "' AND tb_SmartFolder.SmartFolder_Name='"+ folderName + "'");

            while (resultSet.next()) {
                 s = resultSet.getString("tb_SmartFolder.SmartFolder_Constraint");
                        
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;

    }

    public static void moveMails(ArrayList<Integer> processMailList,
            String targetFolder) {
        Statement statement;
        int folderId = getFolderId(targetFolder);
        try {
            statement = connection.createStatement();

            Iterator<Integer> iter = processMailList.iterator();
            while (iter.hasNext()) {
                int ancestorId = iter.next();

                statement.executeUpdate("UPDATE tb_Mail, tb_Folder SET "
                        + " tb_Mail.fk_Mail_Folder_id=tb_Folder.Folder_ID"
                        + " WHERE tb_Folder.Folder_Name='" + targetFolder + "'"
                        + " AND tb_Mail.Mail_AncestorId=" + ancestorId);

                statement.executeUpdate("DELETE FROM tb_LinkMapFolder WHERE tb_LinkMapFolder.LinkMapFolder_LinkId=" + ancestorId);
                statement.executeUpdate("INSERT INTO tb_LinkMapFolder VALUES (" + ancestorId + ", " + folderId + ")");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void setMailInfo(ArrayList<Integer> processMailList, int i) {
        Statement statement;
        try {
            statement = connection.createStatement();

            Iterator<Integer> iter = processMailList.iterator();
            while (iter.hasNext()) {
                int mailId = iter.next();

                statement.executeUpdate("UPDATE tb_MailLink SET "
                        + " tb_MailLink.MailLink_HasUnread=" + i
                        + " WHERE tb_MailLink.MailLink_StartId=" + mailId);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static ResultSet constructLuceneDB(String sql) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void deleteEmailAccount(int serverId) {
        try {
            Statement statement, statementMail;
            statement = connection.createStatement();
            statementMail = connection.createStatement();
            statement.executeUpdate("DELETE FROM tb_Server WHERE Server_ID="
                    + serverId);

            ResultSet resultSet = statementMail
                    .executeQuery("SELECT * FROM tb_Mail WHERE fk_Mail_Server_id="
                            + serverId);

            int mailId = 0;
            while (resultSet.next()) {
                mailId = resultSet.getInt("tb_Mail.Mail_ID");

                statement
                        .executeUpdate("DELETE FROM tb_Attachment WHERE fk_Attachment_Mail_id="
                                + mailId);
            }
            statement
                    .executeUpdate("DELETE FROM tb_Mail WHERE fk_Mail_Server_id="
                            + serverId);

            return;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void saveTrainFreq(HashMap<String, Integer> table,
            String tableName) {
        Statement statement;
        try {
            statement = connection.createStatement();

            Iterator iter = table.keySet().iterator();
            String word;
            int freq;
            while (iter.hasNext()) {
                word = iter.next().toString();
                freq = table.get(word);

                statement.executeUpdate("INSERT INTO " + tableName
                        + " VALUES('" + word + "', " + freq + ")");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void saveTrainData(HashMap table, String tableName) {
        Statement statement;
        try {
            statement = connection.createStatement();

            Iterator iter = table.keySet().iterator();
            String word;
            while (iter.hasNext()) {
                word = iter.next().toString();

                if (!Double.isNaN((Double)table.get(word))) {
                    statement.executeUpdate("INSERT INTO " + tableName
                            + " VALUES('" + word + "', " + table.get(word) + ")");
                }
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static HashMap<String, Integer> readFreqTable(String tableName) {

        HashMap<String, Integer> table = new HashMap<String, Integer>();

        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "
                    + tableName);

            String wordAttribute = "tb_HamFreq.HamFreq_Word", freqAttribute = "tb_HamFreq.HamFreq_Freq";
            if (tableName.equals("tb_SpamFreq")) {
                wordAttribute = "tb_SpamFreq.SpamFreq_Word";
                freqAttribute = "tb_SpamFreq.SpamFreq_Freq";
            }
            String word;
            int freq;
            while (resultSet.next()) {
                word = resultSet.getString(wordAttribute);
                freq = resultSet.getInt(freqAttribute);

                table.put(word, freq);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return table;
    }

    public static HashMap readDataTable(String tableName) {

        HashMap<String, Integer> table = new HashMap<String, Integer>();

        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "
                    + tableName);

            String wordAttribute = "tb_SpamData.SpamData_Word", freqAttribute = "tb_SpamData.SpamData_Frequency";

            String word;
            int freq;
            while (resultSet.next()) {
                word = resultSet.getString(wordAttribute);
                freq = resultSet.getInt(freqAttribute);

                table.put(word, freq);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return table;
    }

    public static double getTrainSize(String column) {

        Statement statement;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM tb_TrainSize");
            while (resultSet.next()) {
                return resultSet.getDouble(column);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }

    public static void truncateTable(String table) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE " + table);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return;
    }

    public static void updateTrainDataSize(double size, String column) {
        Statement statement;
        try {
            statement = connection.createStatement();

            statement.executeUpdate("UPDATE tb_TrainSize SET " + column + "="
                    + size + " WHERE tb_TrainSize.TrainSize_ID=1");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return;
    }

    public static int getContactType(String mailFrom, String userName) {
        Statement statement;
        ResultSet resultSet;
        int userId = getUserId(userName);
        int type = 0;
        try {
            statement = connection.createStatement();

            resultSet = statement
                    .executeQuery("SELECT * FROM tb_Contact WHERE Contact_EmailAddress='"
                            + mailFrom
                            + "'"
                            + " AND fk_Contact_User_id="
                            + userId);

            if (resultSet.next()) {
                type = resultSet.getInt("Contact_Type");
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return type;
    }

    public static void insertContact(String contactName, String mailFrom,
            String userName, int type) {
        Statement statement;
        int contactId = getContactAmount();
        int userId = getUserId(userName);
        try {
            statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO tb_Contact VALUES("
                    + (++contactId) + ", '" + contactName + "', '" + mailFrom
                    + "', " + type + ", " + userId + ")");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return;
    }

    public static void importContact(ArrayList<Contact> contactList,
            String userName) {

        try {
            Statement statement = connection.createStatement();

            int contactId = getContactAmount();

            int userId = getUserId(userName);

            Iterator<Contact> iterContact = contactList.iterator();
            Contact contact;
            while (iterContact.hasNext()) {
                contact = iterContact.next();
                if (!contactIsExist(contact.getContactEmail())) {
                    statement.executeUpdate("INSERT INTO tb_Contact VALUES("
                            + (++contactId) + ", '" + contact.getContactName()
                            + "', '" + contact.getContactEmail() + "', 3, "
                            + userId + ")");
                }
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static boolean contactIsExist(String contactEmail) {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement
                    .executeQuery("SELECT * FROM tb_Contact WHERE Contact_EmailAddress='"
                            + contactEmail + "'");

            if (resultSet.next()) {
                resultSet.close();
                statement.close();
                return true;
            }
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<Contact> getContactList(String userName) {
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet;

            int userId = getUserId(userName);

            resultSet = statement.executeQuery("SELECT * FROM `tb_Contact` "
                    + " WHERE fk_Contact_User_id=" + userId);

            while (resultSet.next()) {
                contactList.add(new Contact(resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name"), resultSet
                                .getString("Contact_EmailAddress"), resultSet
                                .getInt("Contact_Type")));
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactList;
    }

    public static void deleteContacts(ArrayList<String> deleteContactList) {

        Statement statement;
        try {
            statement = connection.createStatement();

            Iterator<String> iter = deleteContactList.iterator();
            while (iter.hasNext()) {
                String id = iter.next().toString();
//              System.out.println("id: " + id);
                statement
                        .executeUpdate("DELETE FROM tb_Contact WHERE tb_Contact.Contact_ID="
                                + id);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static ArrayList<UploadFile> getUploadFileList(String userName) {
        ArrayList<UploadFile> uploadFileList = new ArrayList<UploadFile>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet;

            int userId = getUserId(userName);

            resultSet = statement.executeQuery("SELECT * FROM `tb_WebDisc` "
                    + " WHERE fk_WebDisc_User_id=" + userId);

            while (resultSet.next()) {
                uploadFileList.add(new UploadFile(resultSet
                        .getInt("WebDisc_ID"), resultSet
                        .getString("WebDisc_Name")));
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return uploadFileList;
    }

    public static void deleteUploadFiles(ArrayList<String> deleteUploadFileList) {
        Statement statement;
        try {
            statement = connection.createStatement();

            Iterator<String> iter = deleteUploadFileList.iterator();
            while (iter.hasNext()) {
                String id = iter.next().toString();
//              System.out.println("id: " + id);
                statement
                        .executeUpdate("DELETE FROM tb_WebDisc WHERE tb_WebDisc.WebDisc_ID="
                                + id);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void uploadFilesToWebDisc(ArrayList<File> fileList,
            String userName) {

        Iterator<File> iterFile = fileList.iterator();
        int uploadId = getUploadFileAmount();
        int userId = getUserId(userName);

        while (iterFile.hasNext()) {
            File file = (File) iterFile.next();
            FileInputStream fis;

            try {
                fis = new FileInputStream(file);

//              System.out.println(file.getName() + " " + uploadId);

                PreparedStatement ps = DBManager.connection
                        .prepareStatement("insert into tb_WebDisc values ("
                                + (++uploadId) + ",'" + file.getName() + "',?,"
                                + userId + ")");

                ps.setString(1, file.getName());
                ps.setBinaryStream(1, fis, (int) file.length());
                ps.executeUpdate();
                ps.close();
                fis.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static ArrayList getUploadFileById(int fileId) {
        ArrayList<Character> file = new ArrayList<Character>();

        byte[] Buffer = new byte[4096];
        Statement statement;
        try {
            statement = DBManager.connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("select * from tb_WebDisc where WebDisc_ID = "
                            + fileId);
            if (resultSet.next()) {

                Blob blob = resultSet.getBlob("WebDisc_Content");

                InputStream inputStream = blob.getBinaryStream();

                int c;
                while ((c = inputStream.read()) != -1) {
                    file.add((char) c);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }

    public static File getUploadFileAsAttachment(int fileId) {

        byte[] Buffer = new byte[4096];
        File file = null;
        Statement statement;
        try {
            statement = DBManager.connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("select * from tb_WebDisc where WebDisc_ID = "
                            + fileId);
            String fileName = null;
            if (resultSet.next()) {

                Blob blob = resultSet.getBlob("WebDisc_Content");

                InputStream inputStream = blob.getBinaryStream();

                fileName = resultSet.getString("WebDisc_Name");

                file = new File(uploadPath, fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileOutputStream fos = new FileOutputStream(file);

                int size;
                while ((size = inputStream.read(Buffer)) != -1) {
                    fos.write(Buffer, 0, size);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }

    public static String getSingleSubject(int mailId) {
        String subject = null;
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM `tb_Mail`"
                            + " WHERE tb_Mail.Mail_ID=" + mailId);

            if (resultSet.next()) {
                subject = resultSet.getString("tb_Mail.Mail_Subject");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subject;
    }

    public static ArrayList<Mail> getChildren(int mailId) {
        ArrayList<Mail> childrenList = new ArrayList<Mail>();
        Mail mail;

        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM `tb_Mail`"
                            + " WHERE tb_Mail.Mail_AncestorId=" + mailId);

            while (resultSet.next()) {
                mail = new Mail();
                mail.setMailId(resultSet.getInt("tb_Mail.Mail_ID"));
                mail.setMailFrom(resultSet.getString("tb_Mail.Mail_From"));
                mail.setMailTo(resultSet.getString("tb_Mail.Mail_To"));
                mail.setMailCc(resultSet.getString("tb_Mail.Mail_Cc"));
                mail.setMailBcc(resultSet.getString("tb_Mail.Mail_Bcc"));
                mail.setMailSubject(resultSet.getString("tb_Mail.Mail_Subject"));
                mail.setMailContent(resultSet.getString("tb_Mail.Mail_Content"));
                mail.setAncestorId(resultSet.getInt("tb_Mail.Mail_AncestorId"));
                mail.setParentId(resultSet.getInt("tb_Mail.Mail_ParentId"));
                mail.setChildId(resultSet.getInt("tb_Mail.Mail_ChildId"));
                mail.setHasAttachment(resultSet.getBoolean("tb_Mail.Mail_HasAttachment"));
                childrenList.add(mail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return childrenList;
    }

    public static void deleteSingleMail(int id) {
        Statement statement, statForUpdate, statForDelSize;
        try {
            statement = connection.createStatement();
            statForUpdate = connection.createStatement();
            statForDelSize = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM tb_Mail WHERE tb_Mail.Mail_ID=" + id);
            int ancestorId, parentId, childId;
            if (resultSet.next()) {
                ancestorId = resultSet.getInt("tb_Mail.Mail_AncestorId");
                parentId = resultSet.getInt("tb_Mail.Mail_ParentId");
                childId = resultSet.getInt("tb_Mail.Mail_ChildId");

                if (ancestorId == parentId && parentId == childId && childId == id) {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(id);
                    deleteMails(list);
                }
                else {
                    ResultSet resForDel = statForDelSize.executeQuery("SELECT * FROM tb_MailLink WHERE tb_MailLink.MailLink_StartId=" + ancestorId);
                    while (resForDel.next()) {
                        int size = resForDel.getInt("tb_MailLink.MailLink_Length");
                        statForUpdate.executeUpdate("UPDATE tb_MailLink SET tb_MailLink.MailLink_Length=" + (--size)
                                + " WHERE tb_MailLink.MailLink_StartId=" + ancestorId);
                    }
                    if (ancestorId == id) {
                        statForUpdate.executeUpdate("UPDATE tb_Mail SET tb_Mail.Mail_AncestorId=" + childId
                                + " WHERE tb_Mail.Mail_AncestorId=" + id);
                        statForUpdate.executeUpdate("UPDATE tb_Mail SET tb_Mail.Mail_ParentId=" + childId
                                + " WHERE tb_Mail.Mail_ParentId=" + id);
                        statForUpdate.executeUpdate("UPDATE tb_LinkMapFolder SET tb_LinkMapFolder.LinkMapFolder_LinkId=" + childId
                                + " WHERE tb_LinkMapFolder.LinkMapFolder_LinkId=" + id);
                        statForUpdate.executeUpdate("UPDATE tb_MailLink SET tb_MailLink.MailLink_StartId=" + childId
                                + " WHERE tb_MailLink.MailLink_StartId=" + id);
                        statForUpdate.executeUpdate("UPDATE tb_Mail SET tb_Mail.Mail_AncestorId=" + id
                                + ", tb_Mail.fk_Mail_Folder_id=5"
                                + ", tb_Mail.Mail_ParentId=" + id
                                + ", tb_Mail.Mail_ChildId=" + id
                                + " WHERE tb_Mail.Mail_ID=" + id);
                    }
                    else if (childId == id){
                        statForUpdate.executeUpdate("UPDATE tb_Mail SET tb_Mail.Mail_ChildId=" + parentId
                                + " WHERE tb_Mail.Mail_ChildId=" + id);
                        statForUpdate.executeUpdate("UPDATE tb_MailLink SET tb_MailLink.MailLink_EndId=" + parentId
                                + " WHERE tb_MailLink.MailLink_EndId=" + id);
                        statForUpdate.executeUpdate("UPDATE tb_Mail SET tb_Mail.Mail_AncestorId=" + id
                                + ", tb_Mail.Mail_ParentId=" + id
                                + ", tb_Mail.Mail_ChildId=" + id
                                + ", tb_Mail.fk_Mail_Folder_id=5"
                                + " WHERE tb_Mail.Mail_ID=" + id);
                    }
                    else {
                        statForUpdate.executeUpdate("UPDATE tb_Mail SET tb_Mail.Mail_ParentId=" + parentId
                                + " WHERE tb_Mail.Mail_ParentId=" + id);
                        statForUpdate.executeUpdate("UPDATE tb_Mail SET tb_Mail.Mail_ChildId=" + childId
                                + " WHERE tb_Mail.Mail_ChildId=" + id);
                        statForUpdate.executeUpdate("UPDATE tb_Mail SET tb_Mail.Mail_AncestorId=" + id
                                + ", tb_Mail.Mail_ParentId=" + id
                                + ", tb_Mail.Mail_ChildId=" + id
                                + ", tb_Mail.fk_Mail_Folder_id=5"
                                + " WHERE tb_Mail.Mail_ID=" + id);
                    }
                    statForUpdate.executeUpdate("INSERT INTO tb_LinkMapFolder VALUES(" + id + ", 5)");
                    statForUpdate.executeUpdate("INSERT INTO tb_MailLink VALUES(" + id + ", " + id + ", 1, 0)");
                    statForUpdate.executeUpdate("DELETE FROM tb_MailLink WHERE tb_MailLink.MailLink_Length=0");
                }
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }

    public static ArrayList<File> getAttachmentForFwd(int id) {
        ArrayList<File> fileList = new ArrayList<File>();
        byte[] Buffer = new byte[4096];
        File file = null;
        Statement statement;
        try {
            statement = DBManager.connection.createStatement();

            ResultSet resultSet = statement
                    .executeQuery("select * from tb_Attachment where fk_Attachment_Mail_id=" + id);
            String fileName = null;
            while (resultSet.next()) {

                Blob blob = resultSet.getBlob("Attachment_Content");

                InputStream inputStream = blob.getBinaryStream();

                fileName = resultSet.getString("Attachment_Name");

                file = new File(uploadPath, fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileOutputStream fos = new FileOutputStream(file);

                int size;
                while ((size = inputStream.read(Buffer)) != -1) {
                    fos.write(Buffer, 0, size);
                }
                fileList.add(file);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileList;
    }

    public static void emptyTrash(String serverName) {
        Statement statForList;
        ResultSet resForList;
        ArrayList<Integer> mailIdList = new ArrayList<Integer>();
        int serverId = getServerId(serverName);
        try {
            statForList = connection.createStatement();

            resForList = statForList.executeQuery("SELECT DISTINCT(tb_Mail.Mail_ID) mid FROM tb_Mail, tb_LinkMapFolder"
                    + " WHERE tb_Mail.Mail_ID=tb_LinkMapFolder.LinkMapFolder_LinkId"
                    + " AND tb_LinkMapFolder.LinkMapFolder_FolderId=5"
                    + " AND tb_Mail.fk_Mail_Server_id=" + serverId);

            while (resForList.next()) {
                mailIdList.add(resForList.getInt("mid"));
            }

            deleteMails(mailIdList);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}


