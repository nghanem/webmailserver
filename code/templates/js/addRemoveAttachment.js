var textNumber = 1;
function AddOne(form, afterElement) {
	var oP = document.createElement("input");
	oP.setAttribute("type", "file");
	oP.setAttribute("name", "attachement");
	oP.setAttribute("id","attachment"+(++textNumber));

	var oTr = document.createElement("tr");
	oTr.appendChild(oP);
	
	afterElement.appendChild(oTr);
}

function removeOne(form) {
	
	if (textNumber > 1) {
		
		var oP = document.getElementById("attachement" + textNumber);
		
		oP.parentNode.removeChild(oP);
		textNumber--;
	}
}
