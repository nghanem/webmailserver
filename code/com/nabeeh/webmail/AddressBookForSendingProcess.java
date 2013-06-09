package com.nabeeh.webmail;


import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.*;

public class AddressBookForSendingProcess extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

    public AddressBookForSendingProcess(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        super(request, response);
    }

    public void body() {
        if (session.getAttribute("user") == null) {
            try {
                response.sendRedirect("/login");
                return;
            } catch (IOException e) {
               
                e.printStackTrace();
            }
        }
        String userName = session.getAttribute("user").toString();
        String serverName = session.getAttribute("serverName").toString();

        if (request.getParameter("confirm") != null)
        {
            int checkboxAmount = Integer.parseInt(request.getParameter("boxAmount")) / 3;
//          ArrayList<String> toList = new ArrayList<String>(),
//                      ccList = new ArrayList<String>(),
//                      bccList = new ArrayList<String>();
            String toList = "", ccList = "", bccList = "";
            for (int i = 1; i <= checkboxAmount; ++i) {
                if (request.getParameter("to" + i) != null) {
                    toList += (request.getParameterValues("to" + i)[0]) + " ";
                }
                if (request.getParameter("cc" + i) != null) {
                    ccList += (request.getParameterValues("cc" + i)[0]) + " ";
                }
                if (request.getParameter("bcc" + i) != null) {
                    bccList += (request.getParameterValues("bcc" + i)[0]) + " ";
                }
            }
            session.setAttribute("to", toList);
            session.setAttribute("cc", ccList);
            session.setAttribute("bcc", bccList);
        }

        try {
            response.sendRedirect("./compose");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}