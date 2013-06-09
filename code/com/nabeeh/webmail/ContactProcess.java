package com.nabeeh.webmail;


import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.*;


public class ContactProcess extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

	public ContactProcess(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
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

		if (request.getParameter("deleteContact") != null)
		{
			System.out.println("hello");
			int checkboxAmount = Integer.parseInt(request.getParameter("boxAmount"));
			System.out.println("hello11");
			ArrayList<String> deleteContactList = new ArrayList<String>();
			for (int i = 1; i <= checkboxAmount; ++i) {
				if (request.getParameter("selContact" + i) != null) {
					System.out.println("hello1");
					deleteContactList.add(request.getParameterValues("selContact" + i)[0]);
				}
			}
			DBManager.deleteContacts(deleteContactList);
		}
		else if (request.getParameter("createContact") != null)
		{
			String contactName = request.getParameter("newContactName");
			
			String contactEmail = request.getParameter("newContactEmail");
			
			String contactType = request.getParameter("newContactType");
			
			int type = 0;
			
			if (contactType.equals("normal")) {
				type = 3;
			}
			else if (contactType.equals("white")) {
				type = 2;
			}
			else if (contactType.equals("black")) {
				type = 1;
			}
			
			DBManager.insertContact(contactName, contactEmail, userName, type);
		}
		
		try {
			response.sendRedirect("./contactlist");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}