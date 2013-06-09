package com.nabeeh.webmail;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antlr.stringtemplate.StringTemplate;





public class Preview extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

	PrintWriter out;

	public Preview(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		super(request, response);
		out = response.getWriter();
		// TODO Auto-generated constructor stub
	}

	public void body() {

		StringTemplate pageST = templates.getInstanceOf("page");
		StringTemplate bodyST = templates.getInstanceOf("preview");
		pageST.setAttribute("body", bodyST);

		if (session.getAttribute("user") == null) {
			try {
				response.sendRedirect("/login");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		

		Mail mail = (Mail) session.getAttribute("mail");

		String content = mail.getMailContent();
		
		bodyST.setAttribute("mail", mail);
         
		if (mail.isHasAttachment()) {
			ArrayList<String> attachNames = (ArrayList<String>) session.getAttribute("attachNamesForPreview");
			Iterator<String> iterName = attachNames.iterator();
			while (iterName.hasNext()) {
				bodyST.setAttribute("attachName", iterName.next());
			}
		}

		bodyST.setAttribute("content", content);

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
		
		session.setAttribute("mail", mail);
		
		session.setAttribute("preview", "yes");

		out.println(pageST);
	}
}
