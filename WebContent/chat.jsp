<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/custom.css"
	rel="stylesheet">

</head>
<body onload="refreshList()">
	<jsp:include page="title.jsp">
		<jsp:param name="title" value="Chat" />
	</jsp:include>


	<div class="container">
		<div class="row">

			<div class="col-md-9" >

				<h2>Friends</h2>
				<div id="friends">
					<ul id="friendsL">
						<c:forEach var="friend" items="${friends}">
							<li>${friend.username}-${friend.status}
								<button onclick="openChat(${friend.email})">Chat</button>
							</li>
						</c:forEach>
					</ul>
				</div>

			</div>

			<div class="col-md-3" >
				<h4>Name:</h4>
				<p>${user.username}</p>
				<h4>Current Status:</h4>
				<div id="status">
					<p id="statusP">${user.status}</p>
				</div>

				<h4>Change Status</h4>
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
			</div>



		</div>

		<article></article>

		<article>
			<h2>All users</h2>
			<c:forEach var="user" items="${users}">
				<p>
					${user.email} <input id="add${user.username}Button"
						value="Add friend" type="button"
						onclick="addFriend('${user.email}')" />
				</p>
			</c:forEach>
		</article>

	</div>


	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>');
	</script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/status.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/friend.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/chat.js"></script>

</body>
</html>