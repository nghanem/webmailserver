package com.nabeeh.webmail;
 

import java.io.*;

import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.*;



public class SendProcess extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

    public SendProcess(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
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
        System.out.println("server name inside send process "+serverName);
        System.out.println("username inside send process "+userName);
        System.out.println("password inside send process "+password);
        System.out.println("inside send preocess 44");
        Smtp se = null;
        se = new SmtpHandler();
        

        se.setUserPass(serverName, password);

        Mail mail = (Mail) session.getAttribute("mail");

        String content = mail.getMailContent();
        if (session.getAttribute("preview") != null) {
            session.removeAttribute("preview");

            HashMap modifiedWords = (HashMap) session.getAttribute("modifiedWords");

            Iterator iter = modifiedWords.keySet().iterator();
            String key, newWord;
            while (iter.hasNext()) {
                key = iter.next().toString();

                newWord = request.getParameter(key);

                content = content.replace(key, newWord);
            }
        }

        se.setFrom(mail.getMailFrom());

        se.setRecipient(mail.getMailTo());

        se.setCc(mail.getMailCc());

        se.SetBcc(mail.getMailBcc());

        se.setSubject(mail.getMailSubject());

        se.setContent(content);

        if (mail.getAncestorId() != 0) {
            se.setAncestorId(mail.getAncestorId());

            se.setParentId(mail.getParentId());
        }

        if (mail.getAttachment() != null) {
            se.setAttachment(mail.getAttachment());
        }
        
        se.send();

        try {
            response.sendRedirect("/mail/inbox?foldername=Inbox");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}