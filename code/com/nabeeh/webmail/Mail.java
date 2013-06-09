package com.nabeeh.webmail;


import java.io.*;
import java.util.*;

public class Mail {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/
	
	private int mailId;
	
	private String mailFrom;
	
	private String mailTo;
	
	private String mailCc;
	
	private String mailBcc;
	
	private String mailSubject;
	
	private String mailContent;
	
	private int mailLinkLength;
	
	private ArrayList<File> attachment;
	
	private String mailDate;
	
	private int mailSize;
	
	private boolean hasUnread = true;
	
	private boolean hasAttachment;

	private boolean hasEmotion = false;
	
	private boolean hasCc = false;
	
	private boolean hasBcc = false;
	
	private int parentId;
	
	private int childId;
	
	private int ancestorId;
	
	private boolean hasMoreThanOne = false;
	
	
	
	
	
	public boolean isHasMoreThanOne() {
		return hasMoreThanOne;
	}

	public void setHasMoreThanOne(boolean hasMoreThanOne) {
		this.hasMoreThanOne = hasMoreThanOne;
	}

	public boolean isHasCc() {
		return hasCc;
	}

	public void setHasCc(boolean hasCc) {
		this.hasCc = hasCc;
	}

	public boolean isHasBcc() {
		return hasBcc;
	}

	public void setHasBcc(boolean hasBcc) {
		this.hasBcc = hasBcc;
	}

	public int getAncestorId() {
		return ancestorId;
	}

	public void setAncestorId(int ancestorId) {
		this.ancestorId = ancestorId;
	}

	public int getMailLinkLength() {
		return mailLinkLength;
	}

	public void setMailLinkLength(int mailLinkLength) {
		this.mailLinkLength = mailLinkLength;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getChildId() {
		return childId;
	}

	public void setChildId(int childId) {
		this.childId = childId;
	}

	private ArrayList<File> emotion;

	public boolean isHasEmotion() {
		return hasEmotion;
	}

	public void setHasEmotion(boolean hasEmotion) {
		this.hasEmotion = hasEmotion;
	}

	public ArrayList<File> getEmotion() {
		return emotion;
	}

	public void setEmotion(ArrayList<File> emotion) {
		this.emotion = emotion;
	}

	private HashMap<Integer, String> attachmentIdName = new HashMap<Integer, String>();
	
	public HashMap<Integer, String> getAttachmentIdName() {
		return attachmentIdName;
	}

	public void setAttachmentIdName(HashMap<Integer, String> attachmentIdName) {
		this.attachmentIdName = attachmentIdName;
	}

	public Mail() {}
	
	public int getMailId() {
		return mailId;
	}

	public void setMailId(int mailId) {
		this.mailId = mailId;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	
	public String getMailCc() {
		return mailCc;
	}

	public void setMailCc(String mailCc) {
		
		this.mailCc = mailCc;
	}

	public String getMailBcc() {
		
		return mailBcc;
	}

	public void setMailBcc(String mailBcc) {
		
		this.mailBcc = mailBcc;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public ArrayList<File> getAttachment() {
		return attachment;
	}

	public void setAttachment(ArrayList<File> attachment) {
		this.attachment = attachment;
	}

	public String getMailDate() {
		return mailDate;
	}

	public void setMailDate(String mailDate) {
		this.mailDate = mailDate;
	}

	public int getMailSize() {
		return mailSize;
	}

	public void setMailSize(int mailSize) {
		this.mailSize = mailSize;
	}



	public boolean isHasUnread() {
		return hasUnread;
	}

	public void setHasUnread(boolean hasUnread) {
		this.hasUnread = hasUnread;
	}

	public boolean isHasAttachment() {
		return hasAttachment;
	}

	public void setHasAttachment(boolean hasAttachment) {
		this.hasAttachment = hasAttachment;
	}
}