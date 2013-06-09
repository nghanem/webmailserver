package com.nabeeh.webmail;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

import org.antlr.stringtemplate.StringTemplate;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.Hits;





import java.util.*;

public class MultimailsOperation extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

	PrintWriter out;
	
	public MultimailsOperation(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		super(request, response);
		out = response.getWriter();
	}

	public void body() {
		
		try {
			if (session.getAttribute("user") == null) {
				try {
					response.sendRedirect("/login");
					return;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			int checkboxAmount = Integer.parseInt(request.getParameter("boxAmount"));
			
			
			
			StringTemplate pageST = templates.getInstanceOf("page");
			StringTemplate bodyST = templates.getInstanceOf("inbox");
			pageST.setAttribute("body", bodyST);
			
			String serverName = session.getAttribute("serverName").toString();
			String userName = session.getAttribute("user").toString();
			String folderName = session.getAttribute("folderName").toString();
			
			ArrayList<String> serverNames = DBManager.getServerNames(userName);
			
			ArrayList<Integer> processMailList = new ArrayList<Integer>();
			int value = 0;
			for (int i = 1; i <= checkboxAmount; ++i) {
				if (request.getParameter("select" + i) != null) {
					value = Integer.parseInt(request.getParameterValues("select" + i)[0]);
					processMailList.add(value);
				}
			}
		
			if (request.getParameter("search") != null) {
				
				String searchBy = request.getParameter("searchOption");
				
				LuceneSearch lucene = (LuceneSearch) session.getAttribute("luceneForMail");
				
				Hits hits=lucene.mailSearch(request.getParameter("queryString"), searchBy);

				Mail mail;
				ArrayList<Mail> mailList = new ArrayList<Mail>();
				
				for (int i = 0; i < hits.length(); ++i) {
					Document doc;
					try {
						doc = hits.doc(i);
						mail = new Mail();
						
						mail.setMailId(Integer.parseInt(doc.get("id")));
						mail.setMailFrom(doc.get("from"));
						mail.setMailTo(doc.get("to"));
						mail.setMailCc(doc.get("cc"));
						mail.setMailBcc(doc.get("bcc"));
						mail.setMailSubject(doc.get("subject"));
						mail.setMailContent(doc.get("content"));
						mail.setMailDate(doc.get("date"));
						mail.setMailSize(Integer.parseInt(doc.get("size")));
						mail.setHasUnread(Boolean.getBoolean(doc.get("hasUnread")));
//						mail.setIsRead(Boolean.getBoolean(doc.get("isRead")));
						mail.setHasAttachment(Boolean.getBoolean(doc.get("hasAttachment")));
						
						mailList.add(mail);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}

				bodyST.setAttribute("serverItem", serverNames);
				
				session.setAttribute("folderName", folderName);

				String strPageNum = request.getParameter("page");
				int pageNum = 0;
				if (strPageNum != null) {
					pageNum = Integer.parseInt(strPageNum);
				}
				
				Iterator<Mail> iterMail = mailList.iterator();
				while (iterMail.hasNext()) {
					bodyST.setAttribute("mails", iterMail.next());
				}
				
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
				
				bodyST.setAttribute("folderName", folderName);
				bodyST.setAttribute("previousPage", pageNum - 1);
				bodyST.setAttribute("isFirstPage", -1);
				bodyST.setAttribute("isLastPage", -1);
				bodyST.setAttribute("nextPage", pageNum + 1);

				lucene.close();

				out.println(pageST);
				
				return;
			}
			else if (request.getParameter("deleteMutiMails") != null) {
				DBManager.deleteMails(processMailList);
			}
			else if (request.getParameter("markRead") != null) {
				DBManager.setMailInfo(processMailList, 0);
			}
			else if (request.getParameter("markUnread") != null) {
				DBManager.setMailInfo(processMailList, 1);
			}
			
			else if (request.getParameter("reportSpam") != null) {
				
				SpamFilter spamFilter = new SpamFilter();
				
				spamFilter.adjustData(processMailList, "spam");
				
				DBManager.moveMails(processMailList, "Spam");	
			}
			else if (request.getParameter("notSpam") != null) {
				
				SpamFilter spamFilter = new SpamFilter();
				
				spamFilter.adjustData(processMailList, "ham");
				
				DBManager.moveMails(processMailList, "Inbox");	
			} 
			//above may we can handle to move to inbox
			else if (request.getParameter("empty") != null) {
				DBManager.emptyTrash(serverName);
			}
			else {
				String targetFolder = request.getParameter("targetFolder");
				
				DBManager.moveMails(processMailList, targetFolder);	
			}

			try {
				response.sendRedirect("/mail/inbox?foldername=" + folderName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
