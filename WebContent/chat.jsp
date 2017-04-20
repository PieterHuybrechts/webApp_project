<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="js/status.js"></script>
</head>
<body>
	<jsp:include page="title.jsp">
		<jsp:param name="title" value="Chat" />
	</jsp:include>

	<article>
		<h2>Status</h2>
		<h3>Name:</h3>
		<p>${user.username}</p>
		<h3>Current Status:</h3>
		<div id="status">
			<p id="statusP">${user.currentStatus}</p>
		</div>
		<h3>Change Status</h3>
		<p>
			<input id="onlineButton" type="button" value="Go online"
				onClick="goOnline()" />
		</p>
		<p>
			<input id="offlineButton" type="button" value="Go offline"
				onClick="goOffline()" />
		</p>
		<p>
			<input id="awayButton" type="button" value="Go away"
				onClick="goAway()" />
		</p>
		<p>
		<form>
			<input id="customStatusInput" type="text" /> <input
				id="customStatusButton" type="button" value="Custom Status"
				onclick="goCustom()" />
		</form>

	</article>

	<article>
		<h2>Friends</h2>
	</article>

	<article>
		<h2>All users</h2>
		<c:forEach var="user" items="${users}">
			<p>
			${user.email}
			<input id="add${user.username}Button" value="Add friend" type="button" onclick="addFriend(${user.email})"/>  
			</p>
		</c:forEach>
	</article>
</body>
</html>