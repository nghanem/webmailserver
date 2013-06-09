	<div align="left">
		<form name="form_ServerChoose" method="post" action="">
		  <input type="hidden" name="savedServer" id="savedServer">
		  <select name="server_choose" id="server_choose" onchange="serverSelectd(this.value); form.submit();">
      $serverItem:{
	      <option name="server$i$">$it$</option>
      }$
      </select>
		</form>
	</div>
