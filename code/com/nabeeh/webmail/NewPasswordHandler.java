package com.nabeeh.webmail;


import java.io.IOException;
import javax.servlet.http.*;



public class NewPasswordHandler extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

	public NewPasswordHandler(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		super(request, response);
	}

	public void body() {

		try {
			System.out.println("inside new password ");
			if (request.getParameter("change") != null)
			{
				System.out.println("inside new password  change ");
				String user_name = request.getParameter("usern");

				String password = request.getParameter("passw");
				
				if(DBManager.isUserVerification(user_name))
				{
					System.out.println("insidenew password handler and is true");
					DBManager.setBasicInfo(user_name, password);
                     UserManager.login(request.getSession(), user_name);
					
					response.sendRedirect("/mail/inbox?foldername=Inbox");
				}
				else{
					response.sendRedirect("/newpassword");
				
				     }
				
				
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
