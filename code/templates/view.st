<div id="body">	
	<div id="topBar">
		$topBar()$
	</div>
	<div>
		<div id="leftBar" style="float:left">
			$leftBar()$
		</div>
	
		<div id="viewMail" style="float:left">
			<form action="/mail/proc_singlemail" method="GET">
			<div id="mailOperation">
				<input type="submit" name="delete" value="Delete">
				<select name="targetFolder" onchange="form.submit();">
					<option name="move" value="move">Move to...</option>
					$folder: {
						<option name="$it.folderName$">$it.folderName$</option>
					}$
				</select>
				<input type="submit" name="markRead" value="Mark As Read">
				<input type="submit" name="markUnread" value="Mark As Unread">
				$if(spam)$
						<input type="submit" name="notSpam" value="Not Spam">
				$else$
						<input type="submit" name="reportSpam" value="Report As Spam">
				$endif$
			</div>
					
			<div class="tableMail">
				<table>
			  		<tbody>
			  			<tr>
			  				<td><strong>Subject: $subject$</strong></td>
			  			</tr>
			  			<tr>
			  				<td><HR color="blue"></td>
			  			</tr>
			  			$mail:{						
							<table border="1" width="70%" color="blue">
								<tr>
									<td>
								<table border="0">
									<tr>
										<td></td>
										<td>
											<input type="submit" name="reply$it.mailId$" id="reply" value="Reply">
											<input type="submit" name="replyAll$it.mailId$" id="replyAll" value="Reply All">
											<input type="submit" name="forward$it.mailId$" id="forward" value="Forward">
											<input type="submit" name="delete$it.mailId$" id="dele" value="Delete">
										</td>
									</tr>
									<tr class="addressStyle">
										<td>From:</td>
										<td>$it.mailFrom$</td>
									</tr>
									<tr class="addressStyle">
										<td>To: </td>
										<td>$it.mailTo$</td>
									</tr>
									<tr class="addressStyle">
										<td>$if(it.hasCc)$Cc: $endif$</td>
										<td>$if(it.hasCc)$$it.mailCc$$endif$</td>
									</tr>
									<tr class="addressStyle">
										<td>$if(it.hasBcc)$Bcc: $endif$</td>
										<td>$if(it.hasBcc)$$it.mailBcc$$endif$</td>
									</tr>
									<tr class="addressStyle">
										<td>Date: </td>
										<td>$it.mailDate$</td>
									</tr>
									<tr class="addressStyle">
										<td>$if(it.hasAttachment)$Attachment$endif$</td>
										<td>$if(it.hasAttachment)$$it.attachmentIdName.keys:{k| <a href="./attachment?aid=$k$">$it.attachmentIdName.(k)$</a><br>}$$endif$</td>
									</tr>
									<tr>
										<td></td>
										<td><HR color="blue" ></td>
									</tr>
									<tr>
										<td></td>
										<td>$it.mailContent$</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>						
						}$
						<tr>
					</tbody>
				</table>
			</div>
			</form>
		</div>
	</div>
</div>
