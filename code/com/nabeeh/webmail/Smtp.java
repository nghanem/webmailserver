package com.nabeeh.webmail;


import java.util.*;
import java.io.*;

public interface Smtp {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/
	
	public void setUserPass(String username, String password);
	
	public void send();
	
	public void setFrom(String recipient);

	public void setRecipient(String recipient);
	
	public void setCc(String cc);
	
	public void SetBcc(String bcc);

	public void setSubject(String subject);
	
	public void setContent(String content);
	
	public void setAttachment(ArrayList<File> fileList);

	public void setAncestorId(int ancestorId);

	public void setParentId(int parentId);
	
}
