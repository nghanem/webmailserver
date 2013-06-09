package com.nabeeh.webmail;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



public class ComposeProcess extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

    private File tempPathFile;

    private String uploadPath = "./upload/";

    public ComposeProcess(HttpServletRequest request,
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

        String option = null;

        String serverName = session.getAttribute("serverName").toString();

        Mail mail = new Mail();

        mail.setMailDate((new Date()).toString());

        String content = null;

        ArrayList<String> attachNames = new ArrayList<String>();

        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();

            factory.setSizeThreshold(594304);
            factory.setRepository(new File(""));

            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setSizeMax(594304);// 4MB

            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> i = items.iterator();
            ArrayList<File> fileList = new ArrayList<File>();
            mail.setMailFrom(serverName);

            ArrayList<Integer> discFileList = new ArrayList<Integer>();
            String nameAndId = null, tempName = null, tempId = null;
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();

                if (fi.isFormField()) {
                    if (fi.getFieldName().contains("selUploadFile")) {
                        nameAndId = fi.getString();
                        tempName = nameAndId.substring(0, nameAndId.indexOf("|"));
                        tempId = nameAndId.substring(nameAndId.indexOf("|") + 1, nameAndId.length());
                        attachNames.add(tempName);
                        discFileList.add(Integer.parseInt(tempId));
                    }
                    if (fi.getFieldName().equals("recipient")) {
                        mail.setMailTo(fi.getString());
                    }
                    if (fi.getFieldName().equals("cc")) {
                        mail.setMailCc(fi.getString());
                    }
                    if (fi.getFieldName().equals("bcc")) {
                        mail.setMailBcc(fi.getString());
                    }
                    if (fi.getFieldName().equals("subject")) {
                        mail.setMailSubject(fi.getString());
                    }
                    if (fi.getFieldName().equals("content")) {
                        content = fi.getString();
                        mail.setMailContent(content);
                    }
                    if (fi.getFieldName().equals("saveDraft")) {
                        option = "draft";
                    }
                    if (fi.getFieldName().equals("addressBook")) {
                        option = "addressBook";
                    }
                    if (fi.getFieldName().equals("send")) {
                        option = "send";
                    }
                    if (fi.getFieldName().equals("check")) {
                        option = "check";
                    }
                } else {
                    String fileName = fi.getName();
                    if ((fileName != null) && (fileName.trim().length() > 0)) {
                        File savedFile = new File(fileName);
                        if (!savedFile.isDirectory()) {
                            fileList.add(savedFile);
                            fi.write(savedFile);
                            attachNames.add(fileName);
                        }
                    }
                }
            }
            if (session.getAttribute("hasAttachment") != null) {
                if (session.getAttribute("hasAttachment").equals(true)) {
                    int id = Integer.parseInt(session.getAttribute("fwdId").toString());
                    ArrayList<File> fwdAttachment = DBManager.getAttachmentForFwd(id);
                    Iterator<File> iterfwdAttachment = fwdAttachment.iterator();
                    while (iterfwdAttachment.hasNext()) {
                        fileList.add(iterfwdAttachment.next());
                    }
                }
            }

            if (discFileList.size() != 0) {
                Iterator<Integer> iterDiscFile = discFileList.iterator();
                while (iterDiscFile.hasNext()) {
                    fileList.add(DBManager.getUploadFileAsAttachment(iterDiscFile.next()));
                }
            }

            if (fileList.size() != 0) {
                mail.setAttachment(fileList);
                mail.setHasAttachment(true);

                session.setAttribute("attachNamesForPreview", attachNames);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        if (session.getAttribute("ancestorId") != null) {

            mail.setAncestorId(Integer.parseInt(session.getAttribute("ancestorId").toString()));
            session.removeAttribute("ancestorId");

            mail.setParentId(Integer.parseInt(session.getAttribute("parentId").toString()));
            session.removeAttribute("parentId");

            mail.setChildId(mail.getMailId());
        }

        session.setAttribute("mail", mail);

        try {
            if (option.equals("draft")) {
                ArrayList<Mail> mailList = new ArrayList<Mail>();
                mailList.add(mail);
                session.removeAttribute("mail");
                DBManager.addNewMails(mailList, serverName, "Draft");
                response.sendRedirect("./inbox?foldername=inbox");

            }
            else if (option.equals("addressBook")) {

                response.sendRedirect("./addressbook");
            }
            else if (option.equals("check")) {

                response.sendRedirect("./preview");
            } else {
                response.sendRedirect("./proc_send");
            }
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
}
