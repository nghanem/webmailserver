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
	
	<div id="contactFrame">
		<form action="/mail/proc_addressbook" method="POST" style="float:left" onclick="checkboxAmount()">
			<div id="contactList">
		   $bookList()$
		  </div>
		  <input type="hidden" name="boxAmount" id="boxAmount">
		  <div>
		  <input type="submit" name="confirm" id="confirm" value="confirm">
		  </div>
		</form>
	</div>
</div>
