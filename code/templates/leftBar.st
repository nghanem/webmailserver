	<div></div>
	<li><a href="./check">Check Mail</a></li>
	<li><a href="./compose">Compose</a></li>
	<div><HR color="blue"></div>
	<div id="commonItem">
	 	<li><strong><a href="./folderlist">Folders</a></strong></li><br>
	    $folder:{
			$if(it.isCurrentFolder)$
				$if(it.hasUnreadMail)$
		     		<li><strong><a href="./inbox?foldername=$it.folderName$">$it.folderName$($it.unreadMailAmount$)</a></strong></li>
		     	$else$
		     		<li><strong><a href="./inbox?foldername=$it.folderName$">$it.folderName$</a></strong></li>
		     	$endif$
		   $else$	    
		    	$if(it.hasUnreadMail)$
		     		<li><a href="./inbox?foldername=$it.folderName$">$it.folderName$($it.unreadMailAmount$)</a></li>
		     	$else$
		     		<li><a href="./inbox?foldername=$it.folderName$">$it.folderName$</a></li>
		     	$endif$
		    $endif$
	    }$
   </div>
   <div><HR color="blue" ></div>
   <div id="smartFolder">
     	<li><strong><a href="./smartfolderlist">Smart Folders</a></strong></li><br>
     	$smartFolder:{
	     <li><a href="./inbox?smartfoldername=$it$">$it$</a></li>
	    }$
	</div>
	<div><HR color="blue"></div>
   <div id="contactList">
     	<li><strong><a href="./contactlist" >Contacts</a></strong></li>
     	$contact:{
	     <li><a href="./compose?to=$it.contactEmail$">$it.contactName$</a></li>
	    }$
	</div>
	