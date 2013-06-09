function serverSelectd(value) {
  var serverName;
  var server = document.getElementById("server_choose");
  var savedServerName = document.getElementById("savedServer");
  for (i = 0; i < server.length; ++i) {
    if (server[i].selected == true) {
      savedServerName.value = server[i].text;
    }
  }
}


function selectConstraint(value) {
	var editArea = document.getElementById("divAdditionalText");
	var fList = document.getElementById("divFromList");
	if (value == "Is Unread") {
		editArea.style.display = "none";
		fList.style.display = "none";
	}
	else if (value == "From") {
		editArea.style.display = "none";
		fList.style.display = "block";
	}
	else {
		editArea.style.display = "block";
		fList.style.display = "none";
	}
}