<div id="body">
	<div id="topBar">
		$topBar()$
	</div>

	<div id="mainFrame">
		
		<div id="leftBar" style="float:left">
			<div id="serverItem">
				$serverChoose()$
			</div>
			<div id="leftBar">
				$leftBar()$
			</div>
		</div>
	
		<div id="mailFrame">
		  <form id="multimails" action="./proc_multimails" method="POST" onclick="checkboxAmount()">
		  	<input type="hidden" name="boxAmount" id="boxAmount"> 				
				<div>
					<div>
						<input type="button" name="butSelectAll" id="butSelectAll" value="SelectAll" onclick="checkAll()">
						<input type="button" name="butDeselectAll" id="butDeselectAll" value="DeselectAll" onclick="clearAll()">
						<input type="submit" name="deleteMutiMails" value="Delete">
						<input type="submit" name="markRead" value="Mark As Read">
						<input type="submit" name="markUnread" value="Mark As Unread">
						$if(spam)$
							<input type="submit" name="notSpam" value="Not Spam">
						$else$
							<input type="submit" name="reportSpam" value="Report Spam">
						$endif$
						$if(trash)$
							<input type="submit" name="empty" value="empty">
						$endif$
						<select name="targetFolder" onchange="form.submit();">
							<option name="move" value="move">Move to...</option>
						$folder: {
							<option name="$it.folderName$">$it.folderName$</option>
						}$
						</select>
						Search By: 
						<select name="searchOption">
							<option name="searchByFrom" value="from">From</option>
							<option name="searchByTo" value="to">To</option>
							<option name="searchByCc" value="cc">Cc</option>
							<option name="searchByBcc" value="bcc">Bcc</option>
							<option name="searchBySubject" value="subject">Subject</option>
							<option name="searchByContent" value="content">Content</option>					
						</select>
						<input type="text" name="queryString">
						<input type="submit" name="search" value="search">
					</div>
					<div>
						  <div>
						  $inboxMail()$
						  </div>
						  <div align="right">
								$if(isFirstPage)$
								$else$
									<a href="./inbox?foldername=$folderName$&page=$previousPage$">Newer</a>
								$endif$
								$if(isLastPage)$
								$else$
						  		<a href="./inbox?foldername=$folderName$&page=$nextPage$">Older</a>
								$endif$
							</div>
					</div>
				</div>
			</form>
		</div>
</div>