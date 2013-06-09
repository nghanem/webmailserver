package com.nabeeh.webmail;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;


public class EmailAttachAgentFNOL{
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

    Properties emailProperty;
    Vector msgQueue;
    String acessoryCode;
    String collisionCode;
    String repairtimeEstCode;
    String dmsRoot;
    Vector fileAttachPath;
    String accessoryEstimate;
    String collisionEsitmate;
    String repairtimeEsitmate;
    final static String NOOP = "NoMessage";

 

    private String readInboxMsg() throws Exception
    {
        String msgResponse = NOOP;
        //get popHost from property
        String emailServerHost = emailProperty.getProperty("EmailServerHost");
        String emailPort = emailProperty.getProperty("EmailServerPort");
        int emailServerPort = -1;
        if(emailPort != null)
            emailServerPort = Integer.parseInt(emailPort);
        //get popHost user and password
        String userName = emailProperty.getProperty("EmailUserID");
        String passWord = emailProperty.getProperty("EmailUserPWD");
        String userEmailFolder = emailProperty.getProperty("EmailUserFolder");
        String emailProtocol = emailProperty.getProperty("EmailProtocol"); // imap or pop3

        Properties props = new Properties();
        Session session = Session.getInstance(props,null);
        Store store = session.getStore(emailProtocol);
        System.out.println("username :" + userName);
        System.out.println("passWord :" + passWord);
        System.out.println("emailServerHost :" + emailServerHost);
        System.out.println("emailServerPort :" + emailServerPort);
        if(emailServerPort != -1)
            store.connect(emailServerHost,emailServerPort,userName.trim(),passWord.trim());
        else
            store.connect(emailServerHost,userName,passWord);
        System.out.println("SUCCESSFULLY Connected to MailServer:"+ emailServerHost);

        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
        int msgCount = folder.getMessageCount();
        System.out.println("No. of Messages in INBOX are: "+msgCount);
        if(msgCount > 0)
        {

            Message message[] = folder.getMessages();
            for(int i=0;i<message.length;i++)
            {

                msgQueue.addElement(message[i]);
                System.out.println("message : "+message[i].getContent());
                        //message[i].setFlag(Flags.Flag.DELETED , true);
            }
            //msgResponse = processMessage();
            folder.close(true);
            store.close();

        }else{
            folder.close(false);
            store.close();
        }
        return msgResponse;
    }

    private String processMessage() throws Exception
    {
        String msgContent = null;
        int i=0;
        while (msgQueue.size() > 0)
        {
            Message msg = (Message)msgQueue.remove(i);
            i++;
            msg.getSubject();
            if(msg.getContent() instanceof String){
                msgContent = (String)msg.getContent();
            }else{
                Multipart m = (Multipart)msg.getContent();
                int partCnt = m.getCount();
                for(int j = 0; j< partCnt ; j++)
                {
                    Part bp = m.getBodyPart(j);
                    String disposition = bp.getDisposition();
                    // read attachment
                    if((disposition != null) && ((disposition.equals(BodyPart.ATTACHMENT)) || (disposition.equals(BodyPart.INLINE))))
                    {
                        File file = new File(dmsRoot+bp.getFileName());
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                        BufferedInputStream bis = new BufferedInputStream(bp.getInputStream());
                        int aByte;
                        while ((aByte=bis.read()) !=-1){
                            bos.write(aByte);
                        }
                        bos.flush();
                        bos.close();
                        bis.close();
                        fileAttachPath.add(file.getPath());
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
            }
            //if(msgContent != null)
            //    return parseMessage(msgContent);
        }
        return NOOP;
    }


    private String parseMessage(String msgString) throws Exception
    {

        StringTokenizer st = new StringTokenizer(msgString,";\n");
        String tokens[] = new String[st.countTokens()];
        int count = 0;
        while(st.hasMoreTokens()){
            tokens[count] = st.nextToken().trim();
            count++;
        }
        boolean accessValueFound = false;
        boolean collisionValueFound = false;
        boolean repairValueFound = false;
        for(int i=0;i < tokens.length ;i++)
        {
            if(tokens[i] != null || !tokens[i].equals(" "))
            {
                String inresponse = tokens[i].toLowerCase();
                if(inresponse.indexOf("*"+acessoryCode.toLowerCase()) != -1){
                    if(accessoryEstimate != null && accessoryEstimate.length() > 0){
                        accessValueFound = true;
                    } else {
                        accessoryEstimate = inresponse.substring(inresponse.indexOf("=")+1).trim();
                    }
                }
                if(inresponse.indexOf("*"+collisionCode.toLowerCase()) != -1){
                    if(collisionEsitmate != null && collisionEsitmate.length() > 0){
                        collisionValueFound = true;
                    } else {
                        collisionEsitmate = inresponse.substring(inresponse.indexOf("=")+1).trim();
                    }
                }
                if(inresponse.indexOf("*"+repairtimeEstCode.toLowerCase()) != -1){
                    if(repairtimeEsitmate != null && repairtimeEsitmate.length() > 0){
                        repairValueFound = true;
                    } else {
                        repairtimeEsitmate = inresponse.substring(inresponse.indexOf("=")+1).trim();
                    }
                }
            }
            if(accessValueFound && collisionValueFound && repairValueFound){
                break;
            }
        }
        if(accessValueFound && collisionValueFound && repairValueFound){
            return "found";
        } else {
            return NOOP;
        }
    }


    // Following methods only for the Debugging purposes --
    public String invokeService() throws Exception
    {
        msgQueue = new Vector();
        fileAttachPath = new Vector();
        emailProperty = new Properties();
        emailProperty.load(new FileInputStream("D://Temp//emailFNOLAgent.properties"));
        // read the choices
        acessoryCode = emailProperty.getProperty("AcessoryEstCode");
        collisionCode = emailProperty.getProperty("CollisionEstCode");
        dmsRoot = emailProperty.getProperty("DMSRoot");
        // read Inbox for new msgs
        String msgResponse = readInboxMsg();
        System.out.println("msgResponse:"+msgResponse);

        System.out.println("Accessory Estimate="+accessoryEstimate);
        System.out.println("Collision Estimate="+collisionEsitmate);
        for(int i=0; i<fileAttachPath.size(); i++){
            System.out.println("Attachment paths Are:"+fileAttachPath.get(i));
        }
        //System.out.println("Return with NO Choice");
        return ""; // this will make agent to retry, based on the retry interval

    }

    public static void main(String args[]) throws Exception
    {
        EmailAttachAgentFNOL eAgent = new EmailAttachAgentFNOL();
        eAgent.invokeService();
    }

}