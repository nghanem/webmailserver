package com.nabeeh.webmail;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.*;

//import other.ContactAPI;



public class SettingProcess extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

	public SettingProcess(HttpServletRequest request,
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
		
		String userName = session.getAttribute("user").toString();
		String serverName = session.getAttribute("serverName").toString();
		
		if (request.getParameter("setBasicInfo") != null) {
			String newPassword = request.getParameter("password1");
			DBManager.setBasicInfo(userName, newPassword);
		}
		else if (request.getParameter("addEmail") != null) {
			String newEmail = request.getParameter("newEmailAddress");
			String newPassword = request.getParameter("newEmailPass1");
			DBManager.addNewEmailAccount(userName, newEmail, newPassword);
		}
		else {
			for (int i = 0; ; ++i) {
				if (request.getParameter("setEmail" + i) != null) {
					String newPassword = request.getParameter("emailPass" + i).toString();
					DBManager.setExistInfo(i, newPassword);
					break;					
				}
				if (request.getParameter("deleteEmail" + i) != null) {
					if (DBManager.getServerId(serverName) == i) {
						session.removeAttribute("serverName");
					}
					DBManager.deleteEmailAccount(i);
					break;					
				}
				
			}
		}
		try {
			response.sendRedirect("/mail/inbox?foldername=Inbox");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}