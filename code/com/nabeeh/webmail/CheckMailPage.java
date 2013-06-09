package com.nabeeh.webmail;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class CheckMailPage extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

    final int NOT_EXIST = 0;
    final int BLACK_LIST = 1;
    final int WHITE_LIST = 2;
    final int NORMAL_LIST = 3;

    public CheckMailPage(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        super(request, response);
    }

    public void body() {
        if (session.getAttribute("user") == null) {
            try {
                response.sendRedirect("/login");
                return;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        String userName = session.getAttribute("user").toString();
        String serverName = session.getAttribute("serverName").toString();

        String password = DBManager.getEmailPassword(serverName);
        //System.out.println("username inside connect="+username);
        System.out.println("password inside chm after caling getpassword="+password);

        fetchMail(userName, serverName, password);
    }

    public void fetchMail(String userName, String serverName, String password) {
        ArrayList<Mail> mailList = new ArrayList<Mail>();
        ArrayList<Mail> spamList = new ArrayList<Mail>();
        ArrayList<Mail> hamList = new ArrayList<Mail>();

        
        Pop px =new PopHandler();
        
        try {
            //px.setUserPass(serverName, password);//++++++
        	/*ArrayList<String> sfolders = DBManager.getSmartFolderList(serverName);
        	HashMap<String, String> sfolderMap = new HashMap<String, String>();
        	HashMap<String, ArrayList<Mail>> fmailList = new HashMap<String, ArrayList<Mail>>();
        	Iterator<String> niter = sfolders.iterator();
        	while (niter.hasNext()) {
                String fName = niter.next();
                String sqlConstraint = DBManager.getSmartFolderSqlConstraint(fName,serverName);
                sfolderMap.put(fName, sqlConstraint);
                ArrayList<Mail> fmail = new ArrayList<Mail>();
                fmailList.put(fName, fmail);
        	}*/
        	
            px.setUserPass(userName, password);
            System.out.println("username inside chkm ="+userName);
            System.out.println("password inside chkm="+password);

            px.connect();

            px.openFolder("INBOX");

            mailList = px.getNewMessage(userName, "UNSEEN");

            px.disconnect();

            Iterator<Mail> iter = mailList.iterator();
            SpamFilter spamFilter = new SpamFilter();
            session.setAttribute("spamChecker", spamFilter);

            while (iter.hasNext()) {
                Mail mail = iter.next();
                int contactType = DBManager.getContactType(mail.getMailFrom(), userName);
                if (contactType == BLACK_LIST) {
                	System.out.println("inside if contact is balck list");
                    spamList.add(mail);
                    continue;
                }
                else if (contactType == WHITE_LIST) {
                	System.out.println("inside if contact is white list");
                    hamList.add(mail);
                    continue;
                }
                boolean isSpam = spamFilter.spamChecker(mail.getMailContent());

                if (isSpam) {
                	System.out.println("inside if contact is spam list");
                    spamList.add(mail);
                }
                else {
                    hamList.add(mail);
                    if (contactType == NOT_EXIST) {
                    	System.out.println("inside if contact not exist");
                        DBManager.insertContact(mail.getMailFrom(), mail.getMailFrom(), userName, NORMAL_LIST);
                    }
                }
            }
            System.out.println("before dbmanage add new mails");

            DBManager.addNewMails(hamList, serverName, "Inbox");

            DBManager.addNewMails(spamList, serverName, "Spam");


        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            response.sendRedirect("/mail/inbox?foldername=Inbox");
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
}