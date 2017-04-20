<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<p>
	<nav>
	<c:forEach var="action" items="${menuItems}">
		<a href="Servlet?action=${action.action}" id="${action.action}">${action.description}</a>
	</c:forEach>
	</nav>
</p>
<h1>${param.title}</h1>
<div id="errors">
	<c:forEach var="error" items="${errorMessages}">
		<p class="alert-danger">${error}</p>
	</c:forEach>
</div>
