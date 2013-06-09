package com.nabeeh.webmail;


import javax.servlet.http.HttpSession;

/**
 * 
 * @author parrt
 * http://www.cs.usfca.edu/~parrt/course/601/lectures/sessions.html
 */

public class UserManager {
	
    /** Session variable pointing to a user object */
    public static final String SESSION_MEMBER = "user";

    /** Indicates currently logged in (could be just visiting site) */
    public static final String SESSION_LOGGEDIN = "loggedin";

	static public void login(HttpSession session, String user) {
		session.setAttribute(SESSION_LOGGEDIN, "true");
		session.setAttribute(SESSION_MEMBER, user);
	}
	
	static public void logout(HttpSession session) {
	    session.removeAttribute(SESSION_LOGGEDIN);
	    session.removeAttribute(SESSION_MEMBER);
	    session.invalidate();
	}
}