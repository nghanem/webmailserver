package com.nabeeh.webmail;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutPage extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/
	
	public LogoutPage(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		super(request, response);
	}
	
	public void body() {
		UserManager.logout(request.getSession());
		try {
			response.sendRedirect("/login");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}