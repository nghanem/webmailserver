package com.nabeeh.webmail;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.*;




public class ForgotHandler extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/
    
	public ForgotHandler(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		super(request, response);
	}

	public void body() {
		
		String userName = request.getParameter("user_name");

		String securityq = request.getParameter("secq");

		String securitya = request.getParameter("seca");
		System.out.println("user "+userName);
		System.out.println("secq "+securityq);
		System.out.println("seca "+securitya);
		
		
		try {
			if (DBManager.forgottenPasswordVerification(securityq,securitya,userName)) {
				
			
				response.sendRedirect("/newpassword");
			} else {
				
				response.sendRedirect("/forgot");
			}

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}