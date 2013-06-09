package com.nabeeh.webmail;

import org.mortbay.http.HttpContext;
import org.mortbay.http.HttpServer;
import org.mortbay.http.NCSARequestLog;
import org.mortbay.http.RequestLog;
import org.mortbay.http.SocketListener;
import org.mortbay.http.handler.ResourceHandler;
import org.mortbay.jetty.servlet.*;

public class Server{
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/
    public static void main(String[] args) throws Exception{
    	
    	HttpServer server = new HttpServer();
    	SocketListener listener = new SocketListener();
    	listener.setPort(8080);
    	server.addListener(listener);

    	HttpContext context = new HttpContext();
    	context.setContextPath("/");
    	server.addContext(context);
    	
    	ServletHandler servlets = new ServletHandler();
    	context.addHandler(servlets);
    	
    	servlets.addServlet("/neo.com","DispatchServlet");
    	servlets.addServlet("/login", "DispatchServlet");
    	servlets.addServlet("/forgot", "DispatchServlet");
    	servlets.addServlet("/exelogin", "DispatchServlet"); 
    	servlets.addServlet("/signup", "DispatchServlet");
    	servlets.addServlet("/signupexe", "DispatchServlet");
    	servlets.addServlet("/mail/inbox", "DispatchServlet");
    	servlets.addServlet("/logout", "DispatchServlet");
    	
    	servlets.addServlet("/mail/compose", "DispatchServlet");
    	
    	servlets.addServlet("/mail/check", "DispatchServlet");
    	servlets.addServlet("/mail/*", "DispatchServlet");
    	
    	
    	servlets.addServlet("/mark", "DispatchServlet");
    	
    	
    	servlets.addServlet("/mail/proc_send", "DispatchServlet");
    	servlets.addServlet("/mail/view", "DispatchServlet");
    	servlets.addServlet("/mail/attachment", "DispatchServlet");
    	servlets.addServlet("/mail/preview", "DispatchServlet");
    	servlets.addServlet("/mail/proc_reply_forward", "DispatchServlet");
    	servlets.addServlet("/mail/proc_singlemail", "DispatchServlet");
    	servlets.addServlet("/mail/proc_multimails", "DispatchServlet");
    	servlets.addServlet("/mail/folderlist", "DispatchServlet");
    	servlets.addServlet("/mail/smartfolderlist", "DispatchServlet");
    	servlets.addServlet("/mail/proc_folder", "DispatchServlet");
    	servlets.addServlet("/mail/proc_smartFolder", "DispatchServlet");
    	servlets.addServlet("/mail/setting", "DispatchServlet");
    	servlets.addServlet("/mail/proc_compose", "DispatchServlet");
    	servlets.addServlet("/mail/contactlist", "DispatchServlet");
    	//servlets.addServlet("/mail/check_spelling", "server.DispatchServlet");
    	servlets.addServlet("/mail/proc_contact", "DispatchServlet");
    	//servlets.addServlet("/mail/proc_setting", "server.DispatchServlet");
    	
    	servlets.addServlet("/logout", "DispatchServlet");
    	servlets.addServlet("/forgotHandler", "DispatchServlet");
    	servlets.addServlet("/newpassword", "DispatchServlet");
    	servlets.addServlet("/newpasswordHandler", "DispatchServlet");
    	
    	
    	servlets.addServlet("/mail/uploadfile", "DispatchServlet");   
    	
    	
    	servlets.addServlet("/mail/addressbook", "DispatchServlet");
    	servlets.addServlet("/mail/proc_addressbook", "DispatchServlet");
    	
    	
    	String home = System.getProperty("user.dir");

        context.setResourceBase(home);
        context.addHandler(new ResourceHandler());

        server.setRequestLog(getServerLogging());
        DBManager.ConnectMySQL();
    	server.start();
    }
   
    private static RequestLog getServerLogging() throws Exception {
		NCSARequestLog a = new NCSARequestLog("./request.log");
		a.setRetainDays(90);
		a.setAppend(true);
		a.setExtended(false);
		a.setLogTimeZone("GMT");
		a.start();
		return a;
	}
    
	
}
