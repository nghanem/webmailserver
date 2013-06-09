function toggle(value) {

	var oDivResponse = document.getElementById("divResponse");
	
	if (value == "reply") {
		oDivResponse.style.display = "block";
	}
	if (value == "forward") {
		oDivResponse.style.display = "block";
	}
	
	
		var tMail = document.getElementById("tableMail");
  		var tMailBody = tMail.tBodies[0];
	  	var cFrom = tMailBody.rows[0].cells[1].firstChild.nodeValue;
		var cSubject = tMailBody.rows[5].cells[1].firstChild.nodeValue;
		var cContent = tMailBody.rows[7].cells[1].firstChild.nodeValue;

	  if (value == "reply") {	
			document.getElementById("responseTo").value = cFrom;
			document.getElementById("responseSubject").value = "Re: " + cSubject;
	  }
	  else if (value == "forward") {
	  		document.getElementById("responseTo").value = "";
		  	document.getElementById("responseSubject").value = "Forward: " + cSubject;
	  }
}





