package com.nabeeh.webmail;


import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.*;


public class FolderProcess extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

	public FolderProcess(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		super(request, response);
	}

	public void body() {
		if (session.getAttribute("user") == null) {
			try {
				response.sendRedirect("/login");
				return;
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		String userName = session.getAttribute("user").toString();
		
		String serverName = session.getAttribute("serverName").toString();

		if (request.getParameter("deleteFolder") != null)
		{
			int checkboxAmount = Integer.parseInt(request.getParameter("boxAmount"));
			ArrayList<String> deleteFolderList = new ArrayList<String>();
			for (int i = 1; i <= checkboxAmount; ++i) {
				if (request.getParameter("selFolder" + i) != null) {
					deleteFolderList.add(request.getParameterValues("selFolder" + i)[0]);
				}
			}
			DBManager.deleteFolders(deleteFolderList, serverName);
		}
		else if (request.getParameter("createFolder") != null)
		{
			String folderName = request.getParameter("folderName");
			
			DBManager.createFolder(userName, folderName, serverName);
		}
		
		try {
			response.sendRedirect("./folderlist");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}