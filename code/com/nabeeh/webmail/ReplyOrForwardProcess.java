package com.nabeeh.webmail;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



public class ReplyOrForwardProcess extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

    private File tempPathFile;

    private String uploadPath = "./upload/";

    public ReplyOrForwardProcess(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        super(request, response);
    }

    public void body() {

        if (session.getAttribute("user") == null) {
            try {
                response.sendRedirect("/login");
                return;
            } catch (IOException e) {
                
                e.printStackTrace();
            }
        }

        String option = null;

        String serverName = session.getAttribute("serverName").toString();

        Mail mail = new Mail();

        String content = null;

        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();

            factory.setSizeThreshold(4096);
            factory.setRepository(tempPathFile);

            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setSizeMax(4194304);// 4MB

            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> i = items.iterator();
            ArrayList<File> fileList = new ArrayList<File>();

            mail.setMailFrom(serverName);
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();

                if (fi.isFormField()) {
                    if (fi.getFieldName().equals("responseTo")) {
                        mail.setMailTo(fi.getString());
                    }
                    if (fi.getFieldName().equals("cc")) {
                        mail.setMailCc(fi.getString());
                    }
                    if (fi.getFieldName().equals("bcc")) {
                        mail.setMailBcc(fi.getString());
                    }
                    if (fi.getFieldName().equals("responseSubject")) {
                        mail.setMailSubject(fi.getString());
                    }
                    if (fi.getFieldName().equals("responseContent")) {
                        content = fi.getString();
                        mail.setMailContent(content);
                    }
                    if (fi.getFieldName().equals("send")) {
                        option = "send";
                    }
                    if (fi.getFieldName().equals("check")) {
                        option = "check";
                    }

                } else {
                    String fileName = fi.getName();

                    if (fileName != null) {
//                      File fullFile = new File(fi.getName());
                        File savedFile = new File(uploadPath, fileName);
                        if (!savedFile.isDirectory()) {
                            fileList.add(savedFile);
                            fi.write(savedFile);
                        }
                    }
                }
            }
            if (fileList.size() != 0) {
                mail.setAttachment(fileList);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        session.setAttribute("mail", mail);

        try {
            if (option.equals("check")) {
                response.sendRedirect("./preview");
            } else {
                response.sendRedirect("./proc_send");
            }

        } catch (IOException e) {
           
            e.printStackTrace();
        }

    }
}