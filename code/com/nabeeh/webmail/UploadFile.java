package com.nabeeh.webmail;


public class UploadFile {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/
	private int id;
	
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public UploadFile() {}
	
	public UploadFile(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
}
