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
			var response = xHRObject.responseText;
			var statusDiv = document.getElementById("status");
			//var statusParagraph = statusDiv.childNodes[0];
			var statusParagraph = document.getElementById("statusP");
			
			var statusText = document.createTextNode(response);
			statusParagraph.removeChild(statusParagraph.childNodes[0]);
			statusParagraph.appendChild(statusText);
			
		}
	}
}

function addFriend(email){
	console.log("add Friend");
	xHRObject.open("POST", "FriendServlet?action=addFriend&email="+email, true);
	xHRObject.onreadystatechange = getData;
	xHRObject.send(null);
}
