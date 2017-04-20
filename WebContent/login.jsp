<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="title.jsp">
		<jsp:param name="title" value="Login" />
	</jsp:include>

	<form method="post" action="Servlet?action=login">
		<fieldset>
			<p>
				<label for="email">Email</label> 
				<input id="email" name="email" type="text" value="${email}" />
			</p>
			<p>
				<Label for="password">Password</Label> 
				<input id="password"name="password" type="password" />
			</p>
			<p>
				<label for="remember">remember</label> 
				<input id="remember"name="remember" type="checkbox" value="true" />
			</p>
		</fieldset>

	<p>
		<input id="login" name="login" type="submit" value="login" />	
	</p>
	
	</form>

</body>
</html>