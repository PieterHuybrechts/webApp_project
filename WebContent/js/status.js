var xHRObject = new XMLHttpRequest();

function goOnline(){
	
	console.log("going online");
	xHRObject.open("POST", "StatusServlet?action=goOnline", true);
	xHRObject.onreadystatechange = getData;
	xHRObject.send(null);
}

function goOffline(){
	console.log("going offline");
	xHRObject.open("POST", "StatusServlet?action=goOffline", true);
	xHRObject.onreadystatechange = getData;
	xHRObject.send(null);
}

function goAway(){
	console.log("going away");
	xHRObject.open("POST", "StatusServlet?action=goAway", true);
	xHRObject.onreadystatechange = getData;
	xHRObject.send(null);
}

function goCustom(){
	console.log("going custom");
	var input = document.getElementById("customStatusInput").value;
	xHRObject.open("POST", "StatusServlet?action=goCustom&status="+input, true);
	xHRObject.onreadystatechange = getData;
	xHRObject.send(null);
}

function getData(){
	if(xHRObject.readyState == 4){
		if(xHRObject.status == 200){
			var response = xHRObject.responseText;
			console.log(response);
			var statusDiv = document.getElementById("status");
			//var statusParagraph = statusDiv.childNodes[0];
			var statusParagraph = document.getElementById("statusP");
			
			var statusText = document.createTextNode(response);
			statusParagraph.removeChild(statusParagraph.childNodes[0]);
			statusParagraph.appendChild(statusText);
			
		}
	}
}