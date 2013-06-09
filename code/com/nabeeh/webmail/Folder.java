package com.nabeeh.webmail;


public class Folder {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/
	
	private String folderName;
	
	private int unreadMailAmount;
	
	private boolean isCurrentFolder;
	
	private boolean hasUnreadMail;

	public Folder(String name, int i, boolean isCurrentFolder, boolean hasUnreadMail) {
		folderName = name;
		unreadMailAmount = i;
		this.isCurrentFolder = isCurrentFolder;
		this.hasUnreadMail = hasUnreadMail;
	}

	public boolean getHasUnreadMail() {
		return hasUnreadMail;
	}

	public void setHasUnreadMail(boolean hasUnreadMail) {
		this.hasUnreadMail = hasUnreadMail;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public int getUnreadMailAmount() {
		return unreadMailAmount;
	}

	public void setUnreadMailAmount(int unreadMailAmount) {
		this.unreadMailAmount = unreadMailAmount;
	}

	public boolean getIsCurrentFolder() {
		return isCurrentFolder;
	}

	public void setCurrentFolder(boolean isCurrentFolder) {
		this.isCurrentFolder = isCurrentFolder;
	}
}
