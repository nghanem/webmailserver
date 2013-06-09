package com.nabeeh.webmail;


import java.io.IOException;
import javax.servlet.http.*;



public class LoginProcess extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

	public LoginProcess(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		super(request, response);
	}

	public void body() {

		try {
			if (request.getParameter("signin") != null)
			{
				String user_name = request.getParameter("user_name");

				String password = request.getParameter("password");
				
				if (DBManager.loginVerification(user_name, password)) {

					UserManager.login(request.getSession(), user_name);
					
					response.sendRedirect("/mail/inbox?foldername=Inbox");

				} else {
					response.sendRedirect("/login");
				}
			} else if (request.getParameter("signup") != null) {
				response.sendRedirect("/signup");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
