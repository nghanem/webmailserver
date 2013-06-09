function checkAll()
{
	var box = document.getElementsByTagName('input');
	var len = box.length; 
	for(var i=0; i<len; i++)
	{
		if ((box[i].type=="checkbox")) 
		{
			box[i].checked = true;
		}
	}
}
		
function clearAll()
{
	var box = document.getElementsByTagName('input');
	var len = box.length; 
	for(var i=0; i<len; i++)
	{
		if ((box[i].type=="checkbox")) 
		{
			box[i].checked = false;
		}
	}
}

function checkboxAmount()
{
	var box = document.getElementsByTagName('input');
	var len = box.length; 
	var amount = 0;
	for(var i=0; i<len; i++)
	{
		if ((box[i].type=="checkbox")) 
		{
			amount++;
		}
	}
	var textAmount = document.getElementById("boxAmount");
	textAmount.value = amount;
}