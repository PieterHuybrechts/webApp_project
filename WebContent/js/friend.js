var xHRObject = new XMLHttpRequest();

function addFriend(email) {
	xHRObject.open("POST", "FriendServlet?action=addFriend&email=" + email,
			true);
	xHRObject.onreadystatechange = getFriendData;
	xHRObject.send(null);
}

function getFriendData() {
	if (xHRObject.readyState == 4) {
		if (xHRObject.status == 200) {
			var serverResponse = JSON.parse(xHRObject.responseText);
			var name = serverResponse.username;
			var status = serverResponse.status;
			
			if(serverResponse!=""){
				var friendsDiv = document.getElementById("friends");
				// var statusParagraph = statusDiv.childNodes[0];
				var friendsList = document.getElementById("friendsL");
				
				var friendText = document.createTextNode(name + " - " + status);
				var friendListing = document.createElement("li");
				friendListing.appendChild(friendText)
				friendsList.appendChild(friendListing);				
			}
			
		}
	}
}
