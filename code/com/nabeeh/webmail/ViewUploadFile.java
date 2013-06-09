package com.nabeeh.webmail;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ViewUploadFile extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

	PrintWriter out;
	
	public ViewUploadFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		super(request, response);
		out = response.getWriter();
	}

	public void body() {
		int fileId = Integer.parseInt(request.getParameter("fid"));

		ArrayList attachmentStream = DBManager.getUploadFileById(fileId);
		Iterator iter = attachmentStream.iterator();
		while (iter.hasNext()) {
			out.print((iter.next()));
		}
	}
}