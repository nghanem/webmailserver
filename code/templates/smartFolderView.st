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
	
	<div id="folderFrame">
		<form action="/mail/proc_smartFolder" method="POST" style="float:left">
		  <input type="button" name="butSelectAll" id="butSelectAll" value="selectAll" onclick="checkAll()">
			<input type="button" name="butDeselectAll" id="butDeselectAll" value="deselectAll" onclick="clearAll()">
			<input type="submit" name="deleteFolder" id="deleteFolder" value="delete" onclick="checkboxAmount()">
			<div id="folderList">
		   $folderList()$
		  </div>
		  <input type="hidden" name="boxAmount" id="boxAmount">
		  <div><HR color="blue"></div>
		  <div>
		  Folder Name: <input type="text" name="folderName" id="folderName"><br>
		  </div>
		  <div>
		   <select name="constraint" id="constraint" onchange="selectConstraint(this.value)">
			  <option name="from">From</option>
			  <option name="subject">Subject</option>
			  <option name="content">Content</option>
			  <option name="unread">Is Unread</option>
			</select>
			<div id="divFromList">
			<select name="fromList" id="fromList">
			  	$contact:{
	      		<option name="fromList$i$">$it.contactEmail$</option>
     			}$
			</select>
			</div>
			<div id="divAdditionalText" style="display:none">
		  		<input type="text" name="additionalText"  id="additionalConstraint">
		  	</div>
		  <input type="submit" name="createSmartFolder" id="createSmartFolder" value="create">
		  </div>
		</form>
	</div>
</div>
