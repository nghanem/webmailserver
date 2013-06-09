package com.nabeeh.webmail;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.*;
import org.antlr.stringtemplate.*;

public abstract class Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/
	// My template library 
	protected static StringTemplateGroup templates = new StringTemplateGroup("mygroup", "./templates");

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected StringTemplate pageST;
	protected HttpSession session;

	static {
		templates.setRefreshInterval(0); // don't cache templates
	}

	public Page(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.request = request;
		this.response = response;

		pageST = templates.getInstanceOf("page");
		this.session = request.getSession();
	}

	public void generate() {
		header();
		body();
		footer();
	}

	public void header() {
	};
    
    public void body() {
    }
    
    public void footer() {};
}
