package com.nabeeh.webmail;


import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.*;


public class SmartFolderProcess extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

    public SmartFolderProcess(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        super(request, response);
    }

    public void body() {
        if (session.getAttribute("user") == null) {
            try {
                response.sendRedirect("/login");
                return;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

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
            DBManager.deleteSmartFolders(deleteFolderList, serverName);
        }
        else if (request.getParameter("createSmartFolder") != null)
        {
            String folderName = request.getParameter("folderName");

            String selectedConstraint = request.getParameter("constraint");

            String sqlConstraint = null;

            if (selectedConstraint.equals("Is Unread")) {
                sqlConstraint = "tb_MailLink.MailLink_HasUnread=1";
            }
            else if (selectedConstraint.equals("From")) {
                String selectEmailAddr = request.getParameter("fromList");

                sqlConstraint = "tb_Mail.Mail_From=\\'" + selectEmailAddr + "\\'";

            }
            else if (selectedConstraint.equals("Subject")) {

                String selectEdit = request.getParameter("additionalText");

                sqlConstraint = "tb_Mail.Mail_Subject LIKE \\'%" + selectEdit + "%\\'";

            }
            else if (selectedConstraint.equals("Content")) {
                String selectEdit = request.getParameter("additionalText");

                sqlConstraint = "tb_Mail.Mail_Content LIKE \\'%" + selectEdit + "%\\'";
            }

            DBManager.createSmartFolder(folderName, serverName, sqlConstraint);
        }

        try {
            response.sendRedirect("./smartfolderlist");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}