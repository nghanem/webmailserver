package com.nabeeh.webmail;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antlr.stringtemplate.StringTemplate;





public class ViewMail extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/
	
	PrintWriter out;
	
	public ViewMail(HttpServletRequest request, HttpServletResponse response) throws IOException  {
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
		StringTemplate bodyST = templates.getInstanceOf("view");
		pageST.setAttribute("body", bodyST);
		
		String serverName = session.getAttribute("serverName").toString();
		String folderName = session.getAttribute("folderName").toString();
		String userName = session.getAttribute("user").toString();
		
		
		int mailId = Integer.parseInt(request.getParameter("mid"));
		session.setAttribute("mailId", mailId);
		
		if (!DBManager.checkCurrentView(mailId, serverName)) {
			try {
				response.sendRedirect("./inbox");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		ArrayList<Mail> mailLink = DBManager.getMailLink(mailId);
		Iterator<Mail> iterMail = mailLink.iterator();
		while (iterMail.hasNext()) {
			bodyST.setAttribute("mail", iterMail.next());
		}
		String subject = DBManager.getSingleSubject(mailId);
		bodyST.setAttribute("subject", subject);
		
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
		
		bodyST.setAttribute("spam", folderName.equals("Spam"));
		//bodyST.setAttribute("mail", mail);
		out.println(pageST);
		
	}
}