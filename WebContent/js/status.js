var xHRObject = new XMLHttpRequest();

function goOnline(){
	xHRObject.open("POST", "StatusServlet?action=goOnline", true);
	xHRObject.onreadystatechange = getStatusData;
	xHRObject.send(null);
}

function goOffline(){
	xHRObject.open("POST", "StatusServlet?action=goOffline", true);
	xHRObject.onreadystatechange = getStatusData;
	xHRObject.send(null);
}

function goAway(){
	xHRObject.open("POST", "StatusServlet?action=goAway", true);
	xHRObject.onreadystatechange = getStatusData;
	xHRObject.send(null);
}

function goCustom(){
	var input = document.getElementById("customStatusInput").value;
	xHRObject.open("POST", "StatusServlet?action=goCustom&status="+input, true);
	xHRObject.onreadystatechange = getStatusData;
	xHRObject.send(null);
}

function getStatusData(){
	if(xHRObject.readyState == 4){
		if(xHRObject.status == 200){
			var serverResponse = JSON.parse(xHRObject.responseText);
			var status = serverResponse.status;
			
			var statusParagraph = document.getElementById("statusP");
			
			var statusText = document.createTextNode(status);
			statusParagraph.removeChild(statusParagraph.childNodes[0]);
			statusParagraph.appendChild(statusText);
			
		}
	}
}