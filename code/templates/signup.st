<div id="signup"><img src="../image/neo.gif" border="0">
<form id="register_form" action="/signupexe" method="POST">
	<table border="0">
	     
		<tr>
			<td>User Name:</td>
			<td><input type="text" name="user_name" class="reqd"></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="password" name="passw1" id="passw1" class="reqd"></td>
		</tr>
		<tr>
			<td>Re-enter Password:</td>
			<td><input type="password" name="passw2" id="passw2" class="reqd passw1"></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><input type="text" name="email_address" class="reqd"></td>
		</tr>
		<tr>
			<td>Email Password:</td>
			<td><input type="password" name="email_passw1" id="email_passw1" class="reqd"></td>
		</tr>
		<tr>
			<td>Re-enter Email Password:</td>
			<td><input type="password" name="email_passw2" id="email_passw2" class="reqd email_passw1"></td>
		</tr>
		<tr><td><hr color="blue"></td></tr>
		<tr><td>Security Question :</td>
		<td>
		<select name="secq" id="secquestion" class="reqd" >
	
	<option value="What is the last name of your favorite musician?" SELECTED >What is the last name of your favorite musician?</option>
	<option value="What was the last name of your favorite teacher?" >What was the last name of your favorite teacher?</option>
	<option value="What was the last name of your best childhood friend?" >What was the last name of your best childhood friend?</option>
	<option value="What is the name of the hospital where you were born?" >What is the name of the hospital where you were born?</option>
	<option value="What is your main frequent flier number?" >What is your main frequent flier number?</option>

	<option value="What is the name of the street on which you grew up?" > What is the name of the street on which you grew up?</option>
	<option value="What is the name of your favorite book?" >What is the name of your favorite book?</option>
	<option value="Who is your favorite author?" >Who is your favorite author?</option>
	<option value="Where did you spend your childhood summers?" >Where did you spend your childhood summers?</option></select>
		</td>
		</tr>
		<tr>
			<td>Your Answer:</td>
			<td><input type="text" name="seca" id="secutiy" class="reqd"></td>
		</tr>
		
	</table>
	<hr color="blue">
<input type="submit" name="submit" value="Create Account">
<input type="reset" value="Reset">
</form></div>