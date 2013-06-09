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
		<form action="/mail/proc_folder" method="POST" style="float:left">
		  <input type="button" name="butSelectAll" id="butSelectAll" value="selectAll" onclick="checkAll()">
			<input type="button" name="butDeselectAll" id="butDeselectAll" value="deselectAll" onclick="clearAll()">
			<input type="submit" name="deleteFolder" id="deleteFolder" value="delete" onclick="checkboxAmount()">
			<div id="folderList">
		   $folderList()$
		  </div>
		  <input type="hidden" name="boxAmount" id="boxAmount">
		  <input type="text" name="folderName" id="folderName">
		  <input type="submit" name="createFolder" id="createFolder" value="create">
		</form>
	</div>
</div>
