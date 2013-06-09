package com.nabeeh.webmail;


import java.util.ArrayList;

public interface Pop {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/
	
	public void setUserPass(String username, String password);
	
	public ArrayList<Mail> getNewMessage(String user, String flag);
	
	public void connect();
	
	public void openFolder(String folderName);
	
	public void disconnect();
}
