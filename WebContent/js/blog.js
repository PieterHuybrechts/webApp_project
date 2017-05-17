var webSocket;

var path = "ChatApp";

function openSocket() {
	webSocket = new WebSocket("ws://localhost:8080/" + path + "/echo");
	

	webSocket.onopen = function(event) {
		// writeResponse("Connection opened")
	};

	webSocket.onmessage = function(event) {
		writeResponse(event.data);
	};

	webSocket.onclose = function(event) {
		// writeResponse("Connection closed");
	};
}

function getFriendData() {
	if (xHRObject.readyState == 4) {
		if (xHRObject.status == 200) {
			
		}
	}
}

function send(subject) {
	var input = document.getElementById("messageinput" + subject);
	var text = input.value;
	input.value="";
	
	var message = `{ "subject" : "`+subject+`", "message" : "`+text+`" }`;
	webSocket.send(message);
}

function closeSocket() {
	webSocket.close();
}

function writeResponse(text) {
	var response = JSON.parse(text);
	var subject = response.subject;
	var message = response.message;

	var messageList = document.getElementById("messages"+subject);
	var messageListing = document.createElement("li");
	var messageText = document.createTextNode(message);
	
	messageListing.className+="list-group-item";
	messageListing.appendChild(messageText);
	messageList.appendChild(messageListing);
	
	//message.innerHtml += "<li class=\"list-group-item\">"+message+"</li>";
	//messages.innerHTML += message+"<br/>";
}