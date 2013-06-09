package com.nabeeh.webmail;



import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.apache.commons.fileupload.FileItem;


public class SmtpHandler implements Smtp{
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

    private static final String SMTP_HOST_NAME = "localhost";
    private static final int SMTP_HOST_PORT = 25;
    private String userName;
    private String password;

    String from;

    String bcc;

    String cc;

    String recipient;

    String subject;

    String content;
    ArrayList<File> fList = null;

    
    public void setUserPass(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

   
    public void SetBcc(String bcc) {
    	
        this.bcc = bcc;
    }

  
    public void setCc(String cc) {
        
    	
        this.cc = cc;
    }

  
    public void setAttachment(ArrayList<File> fileList) {
        
        this.fList = fileList;
    }

    
    public void setFrom(String from) {
        this.from = from;
    }

    public void send() {

        Properties props = new Properties();

        props.setProperty("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        Session mailSession = Session.getDefaultInstance(props);
       //mailSession.setDebug(true);
        Transport transport;
        try {
            transport = mailSession.getTransport();

            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(userName));
            message.setSubject(subject);
           
            
            
            
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            
            if (!cc.equals("")){
            message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
            }
            if(!bcc.equals("")){
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
            }
            
             // create the message part
            MimeBodyPart messageBodyPart =
              new MimeBodyPart();

            //fill message
            messageBodyPart.setText(content);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            if(fList != null){
            Iterator<File> i = fList.iterator();
                // part two is attachment
                while (i.hasNext()) {
                    File file = (File)i.next();
                    messageBodyPart = new MimeBodyPart();
                    DataSource source =
                      new FileDataSource(file);
                    messageBodyPart.setDataHandler(
                      new DataHandler(source));
                    messageBodyPart.setFileName(file.getName());
                    multipart.addBodyPart(messageBodyPart);
                }
            }
            // Put parts in message
            message.setContent(multipart);

            Transport.send(message);


            transport.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  
    public void setAncestorId(int ancestorId) {
        

    }

  
    public void setParentId(int parentId) {
        

    }


}