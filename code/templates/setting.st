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

		<div id="container">
			<form action="./proc_setting" method="POST" style="float:left">
			<table>
			  <tr><td><strong>Change Password:</strong></td></tr>
			  <tr>
			    <td>Password: </td>
			    <td><input type="password" name="password1"></td>
			  </tr>
			  
			  <tr>
			    <td></td>
			    <td><input type="submit" name="setBasicInfo" value="confirm"></td>
			  </tr>
			  <tr><td><HR color="blue"></td></tr>
			  <tr><td><strong>Exist Email Account:</strong></td></tr>
			  $settingEmailAccount()$
			  	 <tr><td><HR color="blue"></td></tr> 
			  <tr><td><strong>Add New Email Account:</strong></td></tr>
			  <tr>
		      <td>Email Address:</td>
		      <td><input type="text" name="newEmailAddress"></td>
		    </tr>
		    <tr>
		      <td>Password: </td>
		      <td><input type="password" name="newEmailPass1"></td>
		    </tr>
		    
		    <tr>
		      <td></td>
		      <td><input type="submit" name="addEmail" value="Confirm"></td>
		    </tr>	  
			</table>  
			</form>
		</div>
	</div>
</div>
