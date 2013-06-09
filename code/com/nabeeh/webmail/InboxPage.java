package com.nabeeh.webmail;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antlr.stringtemplate.StringTemplate;



public class InboxPage extends Page {
	/**
	* Webmail server Application for 601 class USF
	* @author Nabeeh Ghanem
	* @version 1.0
	*/

    PrintWriter out;

    public InboxPage(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        super(request, response);
        out = response.getWriter();
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
        StringTemplate pageST = templates.getInstanceOf("page");
        StringTemplate bodyST = templates.getInstanceOf("inbox");
        pageST.setAttribute("body", bodyST);

        String userName = session.getAttribute("user").toString();
        
        String folderName = request.getParameter("foldername");
      
        String smartFolderName = request.getParameter("smartfoldername");
        
        String serverName = request.getParameter("savedServer");
       
        ArrayList<String> serverNames = DBManager.getServerNames(userName);

        if (serverName == null) {
            if (session.getAttribute("serverName") != null) {
                serverName = session.getAttribute("serverName").toString();
                serverNames.remove(serverName);
                serverNames.add(0, serverName);
            }
            else {
                serverName = serverNames.get(0);
            }
        }
        else {
            serverNames.remove(serverName);
            serverNames.add(0, serverName);
        }

        session.setAttribute("serverName", serverName);

        if (request.getParameter("smartfoldername") != null) {
            session.setAttribute("folderName", "Inbox");
        }
        else {
            session.setAttribute("folderName", folderName);
        }

        LuceneSearch luceneForMail = new LuceneSearch(serverName, folderName);
        System.out.println("serverName : " + serverName + "folderName :" + folderName);
        luceneForMail.MailIndex();

        session.setAttribute("luceneForMail", luceneForMail);
        //+++++
        if (session.getAttribute("spellChecker") == null) {
            SpellCheck spellCheck = new SpellCheck();

            session.setAttribute("spellChecker", spellCheck);
        }
          //++++
        String strPageNum = request.getParameter("page");
        String orderby = request.getParameter("orderby");
        int pageNum = 0;

        if (strPageNum != null) {
            pageNum = Integer.parseInt(strPageNum);
        }

        int maxPage = DBManager.getMaxPage(serverName, folderName);
        bodyST.setAttribute("currentfolder", folderName);
        ArrayList<Mail> mailList = DBManager.getCertainFolderMail(serverName, folderName, smartFolderName, orderby, pageNum);
        Iterator<Mail> iterMail = mailList.iterator();
        while (iterMail.hasNext()) {
            bodyST.setAttribute("mails", iterMail.next());
        }

        ArrayList<Folder> folderList = DBManager.getFolderList(serverName, folderName);
        Iterator<Folder> iterFolder = folderList.iterator();
        while (iterFolder.hasNext()) {
            bodyST.setAttribute("folder", iterFolder.next());
        }

        ArrayList<String> smartFolderList = DBManager.getSmartFolderList(serverName);
        Iterator<String> iterSmartFolder = smartFolderList.iterator();
        while (iterSmartFolder.hasNext()) {
            bodyST.setAttribute("smartFolder", iterSmartFolder.next());
        }

        ArrayList<Contact> contactList = DBManager.getContactList(userName);
        Iterator<Contact> iterContact = contactList.iterator();
        while (iterContact.hasNext()) {
            bodyST.setAttribute("contact", iterContact.next());
        }

        if (folderName != null) {
            bodyST.setAttribute("spam", folderName.equals("Spam"));
            bodyST.setAttribute("trash", folderName.equals("Trash"));
        }
        else {
            bodyST.setAttribute("spam", false);
            bodyST.setAttribute("trash", false);
        }


        bodyST.setAttribute("serverItem", serverNames);
        bodyST.setAttribute("folderName", folderName);
        bodyST.setAttribute("previousPage", pageNum - 1);
        bodyST.setAttribute("isFirstPage", 0 == pageNum);
        bodyST.setAttribute("isLastPage", maxPage == pageNum + 1);
        bodyST.setAttribute("nextPage", pageNum + 1);

        out.println(pageST);
    }
}
