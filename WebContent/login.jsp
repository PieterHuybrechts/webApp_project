<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<body>
	<jsp:include page="title.jsp">
		<jsp:param name="title" value="" />
	</jsp:include>

	<div class="container">
		<form class="form-signin" method="post" action="Servlet?action=login">
			<h2 class="form-signin-heading">Please sign in</h2>
			<label for="email" class="sr-only">Email</label>  
			<input id="email" name="email" type="text" value="${email}" class="form-control" placeholder="Email address" required autofocus/>
			<Label for="password" class="sr-only">Password</Label>
			<input id="password" name="password" type="password" class="form-control" placeholder="Password" required/>
			<div class="checkbox">
				<label> 
					<input id="remember" name="remember" type="checkbox" value="true" >
					Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
		</form>
	</div>

	<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>');
	</script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>


</body>
</html>