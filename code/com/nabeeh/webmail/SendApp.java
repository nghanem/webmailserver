package com.nabeeh.webmail;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
    
public class SendApp {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/
  
    public static void send(String smtpHost, int smtpPort,
                            String from, String to,
                            String subject, String content)
                throws AddressException, MessagingException {

        // Create a mail session
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", ""+smtpPort);
        Session session = Session.getDefaultInstance(props, null);
    
        // Construct the message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msg.setText(content);
    
        // Send the message
        Transport.send(msg);
        System.out.println("message sent");
    }
    
    public static void main(String[] args) throws Exception {
        // Send a test message
        send("localhost", 25, "user", "paswword",
             "Hello", "Hello, \n\n How are you ?");
    }
}  
