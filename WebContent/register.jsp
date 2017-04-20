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
		<jsp:param name="title" value="Register" />
	</jsp:include>

	<form method="post" action="Servlet?action=register">
		<fieldset>
			<p>
				<label>name</label> 
				<input name="name" id="name" type="text" value="${param.name}"/> 
			</p>
			<p>
				<label>email</label>
				<input name="email" id="email" type="text" value="${param.email}"/> 
			</p>
			<p>
				<label>password</label>
				<input name="password" id="password" type="password"/> 
			</p>
		</fieldset>
		<p>
			<input name="save" id="save" type="submit" value="save" />
		</p>	
	</form>
</body>
</html>