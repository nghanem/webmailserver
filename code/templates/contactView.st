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
		<form action="/mail/proc_contact" method="POST" style="float:left">
		  <input type="button" name="butSelectAll" id="butSelectAll" value="selectAll" onclick="checkAll()">
			<input type="button" name="butDeselectAll" id="butDeselectAll" value="deselectAll" onclick="clearAll()">
			<input type="submit" name="deleteContact" id="deleteContact" value="delete" onclick="checkboxAmount()">
			<div id="contactList">
		   $contactList()$
		  </div>
		  <input type="hidden" name="boxAmount" id="boxAmount">
		  <div><HR color="blue"></div>
		  <div>
		  <table>
		  		<tr>
		  			<td>Name</td>
		  			<td>Email</td>
		  			<td>Type</td>
		  		</tr>
		  		<tr>
		  			<td><input type="text" name="newContactName" id="newContactName"></td>
		  			<td><input type="text" name="newContactEmail" id="newContactEmail"></td>
		  			<td><select name="newContactType" id="contactType">
							  <option name="normalList" value="normal">Normal</option>
							  <option name="blackList" value="black">Black List</option>
							  <option name="whiteList" value="white">White List</option>
							</select>
		  			</td>
		  		</tr>
		  	</table>
		  </div>
		  <div>
		  <input type="submit" name="createContact" id="createContact" value="create">
		  </div>
		</form>
	</div>
</div>
