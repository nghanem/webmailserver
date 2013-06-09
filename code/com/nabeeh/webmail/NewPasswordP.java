package com.nabeeh.webmail;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;
import org.antlr.stringtemplate.StringTemplate;

public class NewPasswordP extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/
	
	PrintWriter out;
	
	public NewPasswordP(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		super(request, response);
		out = response.getWriter();
	}
	
	public void body() {
		StringTemplate pageST = templates.getInstanceOf("page");
		StringTemplate bodyST = templates.getInstanceOf("newpassword");
		pageST.setAttribute("body", bodyST);			
		out.println(pageST);
	}
}
