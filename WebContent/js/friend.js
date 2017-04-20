function addFriend(email){
	xHRObject.open("POST", "FriendServlet?action=addFriend&email="+email, true);
	xHRObject.onreadystatechange = getData;
	xHRObject.send(null);
}

function getData(){
	
}