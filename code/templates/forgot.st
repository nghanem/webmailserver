<div id="forgotp">

	<img src="../image/neo.gif" border="0">
	
	<div>
		<form action="/forgotHandler" method="post">
		<table>
		  <tr><td>Enter your User Name:</td></tr>
		  <tr>
		    
		    <td><input type="text" name="user_name"></td>
		  </tr>
		  <tr><td>Security Question :</td></tr>
		  <tr>
		<td>
		<select name="secq" id="secquestion">
	
	<option value="What is the last name of your favorite musician?" SELECTED>What is the last name of your favorite musician?</option>
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
			</tr>
			<tr>
			<td><input type="text" name="seca" id="secutiy" ></td>
		</tr>
		
		  </table>
		  <tr>	
		<td><input type="submit" name="enter" value="Enter"></td>
		
		</tr>
		</form>
	</div>
</div>
