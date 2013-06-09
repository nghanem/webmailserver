package com.nabeeh.webmail;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.*;




public class SignUPHandler extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

    public SignUPHandler(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        super(request, response);
    }

    public void body() {
       
        String userName = request.getParameter("user_name");

        String userPassword = request.getParameter("passw1");

        String serverName = request.getParameter("email_address");
       

        String emailPassword = request.getParameter("email_passw1");
       
        String securityq = request.getParameter("secq");

        String securitya = request.getParameter("seca");
        

            
        try {
           
            if (DBManager.addAccount(userName, userPassword, serverName, emailPassword, securityq, securitya)) {
                response.sendRedirect("/login");
            } else {

                response.sendRedirect("/signup");
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}