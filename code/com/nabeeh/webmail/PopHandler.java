package com.nabeeh.webmail;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

import javax.mail.*;
import javax.mail.Folder;
import javax.mail.Flags.Flag;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;



public class PopHandler implements Pop {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

	private Session session = null;
	private Store store = null;
	private String username, password;
	Mail mail=new Mail();
	//private Folder folder;
    String accessoryEstimate;
    String collisionEsitmate;
    String repairtimeEsitmate;
    final static String NOOP = "NoMessage";

	
	public void setUserPass(String username, String password) {
		this.username = username;
		this.password = password;
	}

	
	public void connect() {

		Properties pop3Props = new Properties();

		session = Session.getInstance(pop3Props, null);

		try {
			store = session.getStore("pop3");
			System.out.println("username inside connect="+username);
			System.out.println("password inside connect="+password);
			store.connect("localhost", username, password);
			
		} catch (NoSuchProviderException e) {
			
			e.printStackTrace();
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
	}

	
	public void disconnect() {
		try {
			store.close();
		 
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
	}

	
	public ArrayList<Mail> getNewMessage(String user, String flag)  {
		ArrayList<Mail> mailList = new ArrayList<Mail>();
		Folder folder;

		try {
			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_WRITE );
			Message message[] = folder.getMessages();
			System.out.println("length :" + message.length);
			for (int i=0, n=message.length; i<n; i++) {
				
				
				mail.setMailSubject(message[i].getSubject());
				mail.setMailSize(message[i].getSize());
				mail.setMailDate(message[i].getSentDate().toString());
				mail.setMailFrom(message[i].getFrom()[0].toString());
				if (message[i].getRecipients(Message.RecipientType.TO) != null) {
					// user1@cs.com; user2@cs.com
					String to = "";
					for (Address a : message[i].getRecipients(Message.RecipientType.TO)) {
						if (!to.equals("")) {
							to += "; ";
						}
						to += a.toString();
					}
					mail.setMailTo(to);
				}
				if (message[i].getRecipients(Message.RecipientType.CC) != null) {
					for (Address a : message[i].getRecipients(Message.RecipientType.CC)) {
						mail.setMailCc(a.toString());
						
					}
				}
				if (message[i].getRecipients(Message.RecipientType.BCC) != null) {
					for (Address a : message[i].getRecipients(Message.RecipientType.BCC)) {
						mail.setMailBcc(a.toString());
						
					}
					
				}
				
				getAttachment(message[i], mail);
				mailList.add(mail);
				}
			for (int i=0, n=message.length; i<n; i++) {
				System.out.println("inside delete");
			message[i].setFlag(Flags.Flag.DELETED, true);
			}
			folder.close(true);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return mailList;
	}
	
	
	public void openFolder(String folderName) {
		// TODO Auto-generated method stub


//		}
	}
	
    private void getAttachment(Message msg, Mail mail) throws Exception
    {
        	String msgContent = null;
            msg.getSubject();
            if(msg.getContent() instanceof String){
                msgContent = (String)msg.getContent();
            }else{
                Multipart m = (Multipart)msg.getContent();
                int partCnt = m.getCount();
                ArrayList<File> attList = new ArrayList<File>();
            	HashMap<Integer, String> attachmentIdName = new HashMap<Integer, String>();

                for(int j = 0; j< partCnt ; j++)
                {
                    Part bp = m.getBodyPart(j);
                    String disposition = bp.getDisposition();
                    // read attachment
                    if((disposition != null) && ((disposition.equals(BodyPart.ATTACHMENT)) || (disposition.equals(BodyPart.INLINE))))
                    {
                    	mail.setHasAttachment(true);
                    	File file = new File(bp.getFileName());
                    	attList.add(file);
                    	attachmentIdName.put(j, bp.getFileName());
                    }
                    else
                    {
                        MimeBodyPart p = (MimeBodyPart) bp;
                        if (p.isMimeType("multipart/alternative")) { // read body message with attachment
                            MimeMultipart multipart2 = (MimeMultipart) p.getContent();
                            for (int k = 0; k < multipart2.getCount(); k++) {
                                MimeBodyPart p2 = (MimeBodyPart) multipart2.getBodyPart(k);
                                if (p2.isMimeType("text/plain")) {
                                    msgContent = (String) p2.getContent();
                                    break;
                                }
                            }
                        }
                        else if(p.isMimeType("text/plain")) // read body message with no attachment
                        {
                            msgContent = (String) bp.getContent();
                        }
                    }
                }
                mail.setAttachment(attList);
                mail.setAttachmentIdName(attachmentIdName);
                mail.setMailContent(msgContent);
            }
    }

}
	