var xHRObject = new XMLHttpRequest();

function addFriend(email) {
	console.log("add Friend 1 "+ email);
	xHRObject.open("POST", "FriendServlet?action=addFriend&email=" + email,
			true);
	xHRObject.onreadystatechange = getFriendData;
	xHRObject.send(null);
}

function getFriendData() {
	if (xHRObject.readyState == 4) {
		if (xHRObject.status == 200) {
			var response = xHRObject.responseText;
			
			if(response!=""){
				var friendsDiv = document.getElementById("friends");
				// var statusParagraph = statusDiv.childNodes[0];
				var friendsList = document.getElementById("friendsL");
				
				var friendText = document.createTextNode(response);
				var friendListing = document.createElement("li");
				friendListing.appendChild(friendText)
				friendsList.appendChild(friendListing);				
			}
			
		}
	}
}
