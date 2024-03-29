<html>
<head>
<title>$title$</title>
<script type="text/javascript" src="../jquery.js"></script> 
<script type="text/javascript" src="../tablesorter/jquery.tablesorter.js"></script>
<script type="text/javascript" src="../templates/js/sortTitle.js"></script>
<script type="text/javascript" src="../templates/js/hiddenReplyForward.js"></script>
<script type="text/javascript" src="../templates/js/allCheckbox.js"></script>
<script type="text/javascript" src="../templates/js/hiddenServer.js"></script>
<script type="text/javascript" src="../templates/js/addRemoveAttachment.js"></script>
<script type="text/javascript" src="../templates/js/registerCheck.js"></script>
<script type="text/javascript" src="../mail/KindEditor.js"></script>


<script type="text/javascript" src="../mail/mootools-1.2-core-yc.js"></script>
<script type="text/javascript" src="../mail/spell_checker.js"></script>

<script type="text/javascript">
function setSelectionRange(input, selectionStart, selectionEnd) {
  if (input.setSelectionRange) {
    input.focus();
    input.setSelectionRange(selectionStart, selectionEnd);
  }
  else if (input.createTextRange) {
    var range = input.createTextRange();
    range.collapse(true);
    range.moveEnd('character', selectionEnd);
    range.moveStart('character', selectionStart);
    range.select();
  }
}

function replaceSelection (input, replaceString) {
	if (input.setSelectionRange) {
		var selectionStart = input.selectionStart;
		var selectionEnd = input.selectionEnd;
		input.value = input.value.substring(0, selectionStart)+ replaceString + input.value.substring(selectionEnd);
    
		if (selectionStart != selectionEnd){ 
			setSelectionRange(input, selectionStart, selectionStart + 	replaceString.length);
		}else{
			setSelectionRange(input, selectionStart + replaceString.length, selectionStart + replaceString.length);
		}

	}else if (document.selection) {
		var range = document.selection.createRange();

		if (range.parentElement() == input) {
			var isCollapsed = range.text == '';
			range.text = replaceString;

			 if (!isCollapsed)  {
				range.moveStart('character', -replaceString.length);
				range.select();
			}
		}
	}
}


// We are going to catch the TAB key so that we can use it, Hooray!
function catchTab(item,e){
	if(navigator.userAgent.match("Gecko")){
		c=e.which;
	}else{
		c=e.keyCode;
	}
	if(c==9){
		replaceSelection(item,String.fromCharCode(9));
		setTimeout("document.getElementById('"+item.id+"').focus();",0);	
		return false;
	}
		    
}
</script>


<link href="../templates/css/view.css" rel="stylesheet" type="text/css">
</head>
<body>

$body$

</body>
</html>
