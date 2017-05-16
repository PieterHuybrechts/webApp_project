<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="openSocket()">
	<jsp:include page="title.jsp">
		<jsp:param name="title" value="Home" />
	</jsp:include>

	<h2>Blog Subject</h2>
	<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed
		mattis quam non lacinia lobortis. Nunc aliquet lorem lacus, non
		blandit libero dignissim sit amet. Donec eros ex, malesuada sit amet
		diam ut, tincidunt elementum lorem. Ut sodales elit accumsan,
		vestibulum dolor et, rutrum augue. Pellentesque bibendum ante eu felis
		semper euismod. Nullam venenatis neque id lacinia dapibus. Integer sed
		ultrices libero. Maecenas ut sem elit. Nunc aliquam posuere vulputate.
		Quisque in felis at purus hendrerit molestie quis eu libero.
		Pellentesque habitant morbi tristique senectus et netus et malesuada
		fames ac turpis egestas. Phasellus vel ante tellus.</p>
	<p>Nunc faucibus quam a fermentum pellentesque. Lorem ipsum dolor
		sit amet, consectetur adipiscing elit. Duis dictum sapien a molestie
		fermentum. Mauris at nulla quis est pretium scelerisque et vitae
		libero. Nullam quis est lacus. Pellentesque molestie consequat
		pharetra. Ut tristique auctor ligula tempor faucibus. Sed condimentum
		sed lacus ut faucibus. In congue mauris ligula, quis suscipit lorem
		tincidunt eu.</p>
	<input type="text" id="messageinput2" />
	<button type="button" onclick="send(2);">Send</button>
	<div id="messages2">
		<c:forEach var="message" items="${messageMap['2']}">
			${message}<br/>
		</c:forEach>
	</div>

	<h2>Blog Subject</h2>
	<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed
		mattis quam non lacinia lobortis. Nunc aliquet lorem lacus, non
		blandit libero dignissim sit amet. Donec eros ex, malesuada sit amet
		diam ut, tincidunt elementum lorem. Ut sodales elit accumsan,
		vestibulum dolor et, rutrum augue. Pellentesque bibendum ante eu felis
		semper euismod. Nullam venenatis neque id lacinia dapibus. Integer sed
		ultrices libero. Maecenas ut sem elit. Nunc aliquam posuere vulputate.
		Quisque in felis at purus hendrerit molestie quis eu libero.
		Pellentesque habitant morbi tristique senectus et netus et malesuada
		fames ac turpis egestas. Phasellus vel ante tellus.</p>
	<p>Nunc faucibus quam a fermentum pellentesque. Lorem ipsum dolor
		sit amet, consectetur adipiscing elit. Duis dictum sapien a molestie
		fermentum. Mauris at nulla quis est pretium scelerisque et vitae
		libero. Nullam quis est lacus. Pellentesque molestie consequat
		pharetra. Ut tristique auctor ligula tempor faucibus. Sed condimentum
		sed lacus ut faucibus. In congue mauris ligula, quis suscipit lorem
		tincidunt eu.</p>

	<input type="text" id="messageinput3" />
	<button type="button" onclick="send(3);">Send</button>
	<div id="messages3"></div>



	<h2>Blog Subject</h2>
	<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed
		mattis quam non lacinia lobortis. Nunc aliquet lorem lacus, non
		blandit libero dignissim sit amet. Donec eros ex, malesuada sit amet
		diam ut, tincidunt elementum lorem. Ut sodales elit accumsan,
		vestibulum dolor et, rutrum augue. Pellentesque bibendum ante eu felis
		semper euismod. Nullam venenatis neque id lacinia dapibus. Integer sed
		ultrices libero. Maecenas ut sem elit. Nunc aliquam posuere vulputate.
		Quisque in felis at purus hendrerit molestie quis eu libero.
		Pellentesque habitant morbi tristique senectus et netus et malesuada
		fames ac turpis egestas. Phasellus vel ante tellus.</p>
	<p>Nunc faucibus quam a fermentum pellentesque. Lorem ipsum dolor
		sit amet, consectetur adipiscing elit. Duis dictum sapien a molestie
		fermentum. Mauris at nulla quis est pretium scelerisque et vitae
		libero. Nullam quis est lacus. Pellentesque molestie consequat
		pharetra. Ut tristique auctor ligula tempor faucibus. Sed condimentum
		sed lacus ut faucibus. In congue mauris ligula, quis suscipit lorem
		tincidunt eu.</p>

	<input type="text" id="messageinput1" />
	<button type="button" onclick="send(1);">Send</button>
	<div id="messages1"></div>


	<script type="text/javascript" src="js/blog.js"></script>
</body>
</html>