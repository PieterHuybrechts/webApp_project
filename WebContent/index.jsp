<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/css/bootstrap.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/custom.css"
	rel="stylesheet">

</head>
<body class="" onload="openSocket()">
	<jsp:include page="title.jsp">
		<jsp:param name="title" value="Home" />
	</jsp:include>

	<div class=container>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>Blog Subject</h2>
			</div>
			<div class="panel-body">
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed
					mattis quam non lacinia lobortis. Nunc aliquet lorem lacus, non
					blandit libero dignissim sit amet. Donec eros ex, malesuada sit
					amet diam ut, tincidunt elementum lorem. Ut sodales elit accumsan,
					vestibulum dolor et, rutrum augue. Pellentesque bibendum ante eu
					felis semper euismod. Nullam venenatis neque id lacinia dapibus.
					Integer sed ultrices libero. Maecenas ut sem elit. Nunc aliquam
					posuere vulputate. Quisque in felis at purus hendrerit molestie
					quis eu libero. Pellentesque habitant morbi tristique senectus et
					netus et malesuada fames ac turpis egestas. Phasellus vel ante
					tellus.</p>
				<p>Nunc faucibus quam a fermentum pellentesque. Lorem ipsum
					dolor sit amet, consectetur adipiscing elit. Duis dictum sapien a
					molestie fermentum. Mauris at nulla quis est pretium scelerisque et
					vitae libero. Nullam quis est lacus. Pellentesque molestie
					consequat pharetra. Ut tristique auctor ligula tempor faucibus. Sed
					condimentum sed lacus ut faucibus. In congue mauris ligula, quis
					suscipit lorem tincidunt eu.</p>
			</div>
			<div class="panel-footer">
				<!-- <input type="text" id="messageinput2" />
				<button type="button" >Send</button>-->
				<div class="row">
					<div class="col-lg-4">
						<div class="input-group">
							<input type="text" id="messageinput1" class="form-control"
								placeholder="Message" /> <span class="input-group-btn">
								<button class="btn btn-default" type="button" onclick="send(1);">Send</button>
							</span>
						</div>
					</div>
				</div>


				<div class="row">
					<div  class="col-lg-4">
						<ul class="list-group" id="messages1">
							<c:forEach var="message" items="${messageMap['1']}">
								<li class="list-group-item">${message}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>Blog Subject</h2>
			</div>
			<div class="panel-body">
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed
					mattis quam non lacinia lobortis. Nunc aliquet lorem lacus, non
					blandit libero dignissim sit amet. Donec eros ex, malesuada sit
					amet diam ut, tincidunt elementum lorem. Ut sodales elit accumsan,
					vestibulum dolor et, rutrum augue. Pellentesque bibendum ante eu
					felis semper euismod. Nullam venenatis neque id lacinia dapibus.
					Integer sed ultrices libero. Maecenas ut sem elit. Nunc aliquam
					posuere vulputate. Quisque in felis at purus hendrerit molestie
					quis eu libero. Pellentesque habitant morbi tristique senectus et
					netus et malesuada fames ac turpis egestas. Phasellus vel ante
					tellus.</p>
				<p>Nunc faucibus quam a fermentum pellentesque. Lorem ipsum
					dolor sit amet, consectetur adipiscing elit. Duis dictum sapien a
					molestie fermentum. Mauris at nulla quis est pretium scelerisque et
					vitae libero. Nullam quis est lacus. Pellentesque molestie
					consequat pharetra. Ut tristique auctor ligula tempor faucibus. Sed
					condimentum sed lacus ut faucibus. In congue mauris ligula, quis
					suscipit lorem tincidunt eu.</p>
			</div>
			<div class="panel-footer">
				<!-- <input type="text" id="messageinput2" />
				<button type="button" >Send</button>-->
				<div class="row">
					<div class="col-lg-4">
						<div class="input-group">
							<input type="text" id="messageinput2" class="form-control"
								placeholder="Message" /> <span class="input-group-btn">
								<button class="btn btn-default" type="button" onclick="send(2);">Send</button>
							</span>
						</div>
					</div>
				</div>


				<div class="row">
					<div  class="col-lg-4">
						<ul class="list-group" id="messages2">
							<c:forEach var="message" items="${messageMap['2']}">
								<li class="list-group-item">${message}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<h2>Blog Subject</h2>
			</div>
			<div class="panel-body">
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed
					mattis quam non lacinia lobortis. Nunc aliquet lorem lacus, non
					blandit libero dignissim sit amet. Donec eros ex, malesuada sit
					amet diam ut, tincidunt elementum lorem. Ut sodales elit accumsan,
					vestibulum dolor et, rutrum augue. Pellentesque bibendum ante eu
					felis semper euismod. Nullam venenatis neque id lacinia dapibus.
					Integer sed ultrices libero. Maecenas ut sem elit. Nunc aliquam
					posuere vulputate. Quisque in felis at purus hendrerit molestie
					quis eu libero. Pellentesque habitant morbi tristique senectus et
					netus et malesuada fames ac turpis egestas. Phasellus vel ante
					tellus.</p>
				<p>Nunc faucibus quam a fermentum pellentesque. Lorem ipsum
					dolor sit amet, consectetur adipiscing elit. Duis dictum sapien a
					molestie fermentum. Mauris at nulla quis est pretium scelerisque et
					vitae libero. Nullam quis est lacus. Pellentesque molestie
					consequat pharetra. Ut tristique auctor ligula tempor faucibus. Sed
					condimentum sed lacus ut faucibus. In congue mauris ligula, quis
					suscipit lorem tincidunt eu.</p>
			</div>
			<div class="panel-footer">
				<!-- <input type="text" id="messageinput2" />
				<button type="button" >Send</button>-->
				<div class="row">
					<div class="col-lg-4">
						<div class="input-group">
							<input type="text" id="messageinput3" class="form-control"
								placeholder="Message" /> <span class="input-group-btn">
								<button class="btn btn-default" type="button" onclick="send(3);">Send</button>
							</span>
						</div>
					</div>
				</div>


				<div class="row">
					<div  class="col-lg-4">
						<ul class="list-group" id="messages3">
							<c:forEach var="message" items="${messageMap['3']}">
								<li class="list-group-item">${message}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>

	</div>




	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>');
	</script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/blog.js"></script>
</body>
</html>