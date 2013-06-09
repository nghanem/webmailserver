<div id="body">	
	<div id="topBar">
		$topBar()$
	</div>
	<div id="mainFrame">
		<div id="leftBar" style="float:left">
			<div id="leftBar">
				$leftBar()$
			</div>
		</div>

	<div id="compose">
		<form action="/mail/proc_send" method="POST" style="float:left">
				$mail:{
					<table id="tableMail" border="0">
			  		<tbody>
						<tr class="addressStyle">
							<td>From</td>
							<td>$it.mailFrom$</td>
						</tr>
						<tr class="addressStyle">
							<td>To</td>
							<td>$it.mailTo$</td>
						</tr>
						<tr class="addressStyle">
							<td>Cc</td>
							<td>$it.mailCc$</td>
						</tr>
						<tr class="addressStyle">
							<td>Bcc</td>
							<td>$it.mailBcc$</td>
						</tr>
						<tr class="addressStyle">
							<td>Date</td>
							<td>$it.mailDate$</td>
						</tr>
						<tr class="addressStyle">
							<td>Subject</td>
							<td>$it.mailSubject$</td>
						</tr>
						<tr class="addressStyle">
							<td>$if(it.hasAttachment)$Attachment$endif$</td>
							<td>$if(it.hasAttachment)$$attachName: {$it$<br>}$$endif$</td>
						</tr>
						<tr>
							<td></td>
							<td><div><HR ></div></td>
						</tr>
						<tr>
							<td>Content</td>
							<td>$content$</td>
						<tr>
							<td></td>
							<td><input type="submit" name="send" value="send"></td>
						</tr>
						</tbody>
					</table>
					}$	
		</form>
		</div>
	</div>
</div>
