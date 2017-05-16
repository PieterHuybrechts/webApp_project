var webSocket;
var xHRObject = new XMLHttpRequest();

var path = "ChatApp";

function openSocket() {
	xHRObject.open("POST", "BlogServlet",true);
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

	var messages = document.getElementById("messages"+subject);
	messages.innerHTML += "<br/>" + message;
}