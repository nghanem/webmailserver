package com.nabeeh.webmail;


import java.io.IOException;
import javax.servlet.http.*;


import java.util.*;

public class SingleMailProcess extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

	public SingleMailProcess(HttpServletRequest request,
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

		try {

			int mailId = Integer.parseInt(session.getAttribute("mailId")
					.toString());

			ArrayList<Integer> processMailList = new ArrayList<Integer>();

			processMailList.add(mailId);
	
			if (request.getParameter("delete") != null) {
				DBManager.deleteMails(processMailList);
			} else if (request.getParameter("markRead") != null) {
				DBManager.setMailInfo(processMailList, 1);
			} else if (request.getParameter("markUnread") != null) {
				DBManager.setMailInfo(processMailList, 0);
			} 
			else if (request.getParameter("reportSpam") != null) {

				SpamFilter spamFilter = new SpamFilter();

				spamFilter.adjustData(processMailList, "spam");
				
				spamFilter = new SpamFilter();
				session.removeAttribute("spamChecker");
				session.setAttribute("spamChecker", spamFilter);

				DBManager.moveMails(processMailList, "Spam");
				
			} 
			
			else if (request.getParameter("notSpam") != null) {

				SpamFilter spamFilter = new SpamFilter();

				spamFilter.adjustData(processMailList, "ham");
				
				spamFilter = new SpamFilter();
				
				session.removeAttribute("spamChecker");
				session.setAttribute("spamChecker", spamFilter);
                    
			
				DBManager.moveMails(processMailList, "Inbox");
			} 
			
			else if (!request.getParameter("targetFolder").equals("move")) {
								
				String targetFolder = request.getParameter("targetFolder");

				DBManager.moveMails(processMailList, targetFolder);
			} else {
				ArrayList<Mail> childrenId = DBManager.getChildren(mailId);
				Iterator<Mail> iterChild = childrenId.iterator();
				while (iterChild.hasNext()) {
					Mail mail = iterChild.next();
					int id = mail.getMailId();

					if (request.getParameter("reply" + id) != null) {
						session.setAttribute("to", mail.getMailFrom());
						if (mail.getMailSubject().startsWith("Re:")) {
							session.setAttribute("subject", mail.getMailSubject());
						}
						else {
							session.setAttribute("subject", "Re: " + mail.getMailSubject());
						}
						session.setAttribute("content", "-----original message-----<br>" + mail.getMailContent());
						session.setAttribute("ancestorId", mailId);
						session.setAttribute("parentId", id);
						response.sendRedirect("./compose");
						return;
					}
					if (request.getParameter("replyAll" + id) != null) {
						session.setAttribute("to", mail.getMailFrom());
						session.setAttribute("cc", mail.getMailCc());
						if (mail.getMailSubject().startsWith("Re:")) {
							session.setAttribute("subject", mail.getMailSubject());
						}
						else {
							session.setAttribute("subject", "Re: " + mail.getMailSubject());
						}
						session.setAttribute("content", "-----original message-----<br>" + mail.getMailContent());
						session.setAttribute("ancestorId", mailId);
						session.setAttribute("parentId", id);
						response.sendRedirect("./compose");
						return;
					}
					if (request.getParameter("forward" + id) != null) {
						session.setAttribute("subject", "Fwd: " + mail.getMailSubject());
						session.setAttribute("content", mail.getMailContent());
						session.setAttribute("fwdId", id);
						session.setAttribute("hasAttachment", mail.isHasAttachment());
						session.setAttribute("ancestorId", mailId);
						session.setAttribute("parentId", id);
						response.sendRedirect("./compose");
						return;
					}
					if (request.getParameter("delete" + id) != null) {
						DBManager.deleteSingleMail(id);
						
					}
				}

			}

			response.sendRedirect("/mail/inbox?foldername=inbox");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
