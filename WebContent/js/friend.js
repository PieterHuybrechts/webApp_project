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

function getFriendData() {
	if (xHRObject.readyState == 4) {
		if (xHRObject.status == 200) {
			var response = JSON.parse(xHRObject.responseText);
			friendsList = document.getElementById("friendsList");
			friendsList.innerHTML = '';

			for (var i = 0; i < Object.keys(response).length; i++) {
				var name = response[i].username;
				var status = response[i].status;
				var email = response[i].email;
				var friendText = document.createTextNode(name + " - " + status);
				
				var chatButton = document.createElement("button");
				chatButton.id="chatbutton"+email.replace("@","");
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