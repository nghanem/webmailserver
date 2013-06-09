/*
<!--JavaScript & Ajax-->
<!--Tom Negrino Dori Smith-->
<!--Chap7-7-->
*/
/*
 window.onload = initForms;

function initForms() {
     for (var i=0; i< document.forms.length; i++) {
         document.forms[i].onsubmit = function() {return validForm();}
     }
}

function validForm() {
     var allGood = true;
     var allTags = document.getElementsByTagName ("*");

     for (var i=0; i<allTags.length; i++) {
        if (!validTag(allTags[i])) {
           allGood = false;
        }
     }
     return allGood;

     function validTag(thisTag) {
        var outClass = "";
        var allClasses = thisTag.className.split (" ");

        for (var j=0; j<allClasses.length; j++) {
           outClass += validBasedOnClass(allClasses[j]) + " ";
        }

        thisTag.className = outClass;

        if (outClass.indexOf("invalid") > -1) {
           invalidLabel(thisTag.parentNode);
           thisTag.focus();
           if (thisTag.nodeName == "INPUT") {
              thisTag.select();
           }
           return false;
        }
           return true;

           function validBasedOnClass(thisClass) {
              var classBack = "";

              switch(thisClass) {
                 case "":
                 case "invalid":
                    break;
                 case "email":
                    if (allGood && !validEmail (thisTag.value)) classBack = "invalid ";
                 default:
                    classBack += thisClass;
              }
              return classBack;
           }

           function validEmail(email) {
              var re = /^\w+([\.-]?\w+)*@\w+  ([\.-]?\w+)*(\.\w{2,3})+$/;

              return re.test(email);
           }

           function invalidLabel(parentTag) {
              if (parentTag.nodeName == "LABEL") {
                 parentTag.className += " invalid";
              }
          }
      }
}

 */
		window.onload = initForms;

		function initForms() {
			var rform = document.getElementById("register_form");
			rform.onsubmit = function() {
				if (!validForm()) {
					var existErrorTag = document.body.getElementsByTagName("p");
					if (existErrorTag.length == 0) {
						var oP = document.createElement("p");
						var oText = document.createTextNode("Incorrect Information !");
						oP.appendChild(oText);
						document.body.appendChild(oP);
					}
				}
				return validForm();
			}			
		}

		function validForm() {
			var allGood = true;
			var allTags = document.getElementsByTagName("input");

			for (var i=0; i<allTags.length; i++) {
				if (!validTag(allTags[i])) {
					allGood = false;
				}
			}
			return allGood;

			function validTag(thisTag) {
				var outClass = "";
				var allClasses = thisTag.className.split(" ");
	
				for (var j=0; j<allClasses.length; j++) {
					outClass += validBasedOnClass(allClasses[j]) + " ";
				}
	
				thisTag.className = outClass;
	
				if (outClass.indexOf("invalid") > -1) {
					return false;
				}
				return true;
		
				function validBasedOnClass(thisClass) {
					var classBack = "";
		
					switch(thisClass) {
						case "":
						case "invalid":
							break;
						case "reqd":
							if (thisTag.value == "") classBack = "invalid ";
							classBack += thisClass;
							break;
						default:
							if (!crossCheck(thisTag,thisClass)) classBack = "invalid ";
							classBack += thisClass;
					}
					return classBack;
				}
				
				function crossCheck(inTag,otherFieldID) {
					if (!document.getElementById(otherFieldID)) return false;
					return (inTag.value == document.getElementById(otherFieldID).value);
				}
			}
		}
