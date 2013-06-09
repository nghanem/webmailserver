package com.nabeeh.webmail;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.*;

import org.antlr.stringtemplate.StringTemplate;






public class ComposePage extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/
	
	PrintWriter out;
	
	public ComposePage(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		super(request, response);
		out = response.getWriter();
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
		StringTemplate pageST = templates.getInstanceOf("page");
		StringTemplate bodyST = templates.getInstanceOf("compose");
		pageST.setAttribute("body", bodyST);
		
		String userName = session.getAttribute("user").toString();
		
		String serverName = session.getAttribute("serverName").toString();
		
		String folderName = session.getAttribute("folderName").toString();
		
		ArrayList<Folder> folderList = DBManager.getFolderList(serverName, folderName);
		Iterator<Folder> iterFolder = folderList.iterator();
		while (iterFolder.hasNext()) {
			bodyST.setAttribute("folder", iterFolder.next());
		}
		
		ArrayList<String> smartFolderList = DBManager.getSmartFolderList(serverName);
		Iterator<String> iterSmartFolder = smartFolderList.iterator();
		while (iterSmartFolder.hasNext()) {
			bodyST.setAttribute("smartFolder", iterSmartFolder.next());
		}
		
		ArrayList<Contact> contactList = DBManager.getContactList(userName);
		Iterator<Contact> iterContact = contactList.iterator();
		while (iterContact.hasNext()) {
			bodyST.setAttribute("contact", iterContact.next());
		}
		
		ArrayList<UploadFile> uploadFileList = DBManager.getUploadFileList(userName);
		Iterator<UploadFile> iterUploadFileList = uploadFileList.iterator();
		while (iterUploadFileList.hasNext()) {
			bodyST.setAttribute("uploadFile", iterUploadFileList.next());
		}
		
		String directTo = request.getParameter("to");
		if (directTo != null) {
			bodyST.setAttribute("to", directTo);
		}
		
		if (session.getAttribute("to") != null) {
			String addressTo = session.getAttribute("to").toString();
			bodyST.setAttribute("to", addressTo);
			session.removeAttribute("to");
		}
		if (session.getAttribute("cc") != null) {
			String addressCc = session.getAttribute("cc").toString();
			bodyST.setAttribute("cc", addressCc);
			session.removeAttribute("cc");
		}
		if (session.getAttribute("bcc") != null) {
			String addressBcc = session.getAttribute("bcc").toString();
			bodyST.setAttribute("bcc", addressBcc);
			session.removeAttribute("bcc");
		}
		if (session.getAttribute("subject") != null) {
			String sub = session.getAttribute("subject").toString();
			bodyST.setAttribute("subject", sub);
			session.removeAttribute("subject");
		}
		if (session.getAttribute("content") != null) {
			String cont = session.getAttribute("content").toString();
			bodyST.setAttribute("content", cont);
			session.removeAttribute("content");
		}
	
		out.println(pageST);
	}
}