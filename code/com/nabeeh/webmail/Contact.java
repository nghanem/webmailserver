package com.nabeeh.webmail;


public class Contact {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/
	
	private int contactId;
	
	private String contactName;
	
	private String contactEmail;
	
	private int contactType;
	
	public Contact() {}
	
	public Contact(int id, String name, String email, int type) {
		contactId = id;
		contactName = name;
		contactEmail = email;
		contactType = type;
	}
	
	
	public int getContactType() {
		return contactType;
	}

	public void setContactType(int contactType) {
		this.contactType = contactType;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}	
}
