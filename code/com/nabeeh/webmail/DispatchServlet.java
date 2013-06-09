package com.nabeeh.webmail;




import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;



public class DispatchServlet extends HttpServlet {

	
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		Page p = lookupPage(uri, request, response);
		p.generate();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public Page lookupPage(String uri, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		if (uri.equals("/neo.com")) {
			return new LoginPage(request, response);
		}
		
		if (uri.equals("/login")) {
			return new LoginPage(request, response);
		}
		
		if (uri.equals("/exelogin")) {
			return new LoginProcess(request, response);
		}
		if (uri.equals("/signup")) {
			return new SignUP(request, response);
		}
		if (uri.equals("/signupexe")) {
			return new SignUPHandler(request, response);
		}
		if (uri.equals("/mail/inbox")) {
			return new InboxPage(request, response);
		}
		if (uri.equals("/mail/check")) {
			return new CheckMailPage(request, response);
		}
		if (uri.equals("/mail/compose")) {
			return new ComposePage(request, response);
		}
		if (uri.equals("/mail/proc_send")) {
			return new SendProcess(request, response);
		}
		if (uri.equals("/logout")) {
			return new LogoutPage(request, response);
		}
		if (uri.equals("/mail/view")) {
			return new ViewMail(request, response);
		}
		if (uri.equals("/mail/preview")) {
			return new Preview(request, response);
		}
		if (uri.equals("/mail/attachment")) {
			return new ViewAttachment(request, response);
		}
		if (uri.equals("/mail/proc_reply_forward")) {
			return new ReplyOrForwardProcess(request, response);
		}
		if (uri.equals("/mail/proc_multimails")) {
			return new MultimailsOperation(request, response);
		}
		if (uri.equals("/mail/proc_singlemail")) {
			return new SingleMailProcess(request, response);
		}
		if (uri.equals("/mail/folderlist")) {
			return new FolderView(request, response);
		}
		if (uri.equals("/mail/smartfolderlist")) {
			return new SmartFolderView(request, response);
		}
		if (uri.equals("/mail/proc_folder")) {
			return new FolderProcess(request, response);
		}
		if (uri.equals("/mail/proc_smartFolder")) {
			return new SmartFolderProcess(request, response);
		}
		
		if (uri.equals("/mail/setting")) {
			return new SettingPage(request, response);
		}
		if (uri.equals("/mail/proc_setting")) {
			return new SettingProcess(request, response);
		}
		
		if (uri.equals("/mail/proc_compose")) {
			return new ComposeProcess(request, response);
		}
		if (uri.equals("/mail/contactlist")) {
			return new ContactView(request, response);
		}
		if (uri.equals("/mail/proc_contact")) {
			return new ContactProcess(request, response);
		}
		
		if (uri.equals("/mail/uploadfile")) {
			return new ViewUploadFile(request, response);
		}
		
		if (uri.equals("/mail/addressbook")) {
			return new AddressBookForSending(request, response);
		}
		if (uri.equals("/mail/proc_addressbook")) {
			return new AddressBookForSendingProcess(request, response);
		}
		if (uri.equals("/logout")) {
			return new LogoutPage(request, response);
		}
		
		if (uri.equals("/forgot")) {
			return new Forgot(request, response);
		}
		if (uri.equals("/forgotHandler")) {
			return new ForgotHandler(request, response);
		}
		if (uri.equals("/newpassword")) {
			return new NewPasswordP(request, response);
		}
		if (uri.equals("/newpasswordHandler")) {
			return new NewPasswordHandler(request, response);
		}
		
		else {
			return null;
		}
	}
}


