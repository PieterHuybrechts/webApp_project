var xHRObject = new XMLHttpRequest();

function addFriend(email) {
	xHRObject.open("POST", "FriendServlet?action=addFriend&email=" + email,
			true);
	xHRObject.onreadystatechange = getFriendData;
	xHRObject.send(null);
}

function refreshList() {
	xHRObject.open("POST", "FriendServlet?action=getAllFriends", true);
	xHRObject.onreadystatechange = getFriendData;
	xHRObject.send(null);
	setTimeout("refreshList()", 5000);
}

/*
 * function getFriendData() { if (xHRObject.readyState == 4) { if
 * (xHRObject.status == 200) { var serverResponse =
 * JSON.parse(xHRObject.responseText); var name = serverResponse.username; var
 * status = serverResponse.status;
 * 
 * if(serverResponse!=""){ var friendsDiv = document.getElementById("friends");
 * var friendsList = document.getElementById("friendsL");
 * 
 * var friendText = document.createTextNode(name + " - " + status); var
 * friendListing = document.createElement("li");
 * friendListing.appendChild(friendText) friendsList.appendChild(friendListing); } } } }
 */

function getFriendData() {
	if (xHRObject.readyState == 4) {
		if (xHRObject.status == 200) {
			var response = JSON.parse(xHRObject.responseText);
			var friendsDiv = document.getElementById("friends");
			friendsDiv.innerHTML = '';

			var friendsList = document.createElement("ul");
			friendsDiv.appendChild(friendsList);

			for (var i = 0; i < Object.keys(response).length; i++) {
				var name = response[i].username;
				var status = response[i].status;
				var email = response[i].email;
				var friendText = document.createTextNode(name + " - " + status);
				
				var chatButton = document.createElement("button");
				chatButton.id=email;
				chatButton.onclick=openChat;
				var buttonText = document.createTextNode("Chat");
			
				var friendListing = document.createElement("li");
				friendListing.appendChild(friendText);
				chatButton.appendChild(buttonText);
				friendListing.appendChild(chatButton);
				friendsList.appendChild(friendListing);
			}
		}
	}

	
}

/*function getRefreshData() {
	if (xHRObject.readyState == 4) {
		if (xHRObject.status == 200) {
			var response = JSON.parse(xHRObject.responseText);
			var friendsDiv = document.getElementById("friends");
			friendsDiv.innerHTML = '';

			var friendsList = document.createElement("ul");
			friendsDiv.appendChild(friendsList);

			for (var i = 0; i < Object.keys(response).length; i++) {
				var name = response[i].username;
				var status = response[i].status;
				var email = response[i].email;
				var friendText = document.createTextNode(name + " - " + status);
				
				var chatButton = document.createElement("button");
				chatButton.id=email;
				chatButton.onclick=openChat;
				var buttonText = document.createTextNode("Chat");
			
				var friendListing = document.createElement("li");
				friendListing.appendChild(friendText);
				chatButton.appendChild(buttonText);
				friendListing.appendChild(chatButton);
				friendsList.appendChild(friendListing);
			}
		}
	}

	setTimeout("refreshList()", 5000);
}*/