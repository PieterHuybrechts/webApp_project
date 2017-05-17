<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">ChattApp</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<c:forEach var="action" items="${menuItems}">
					<li><a href="Servlet?action=${action.action}"
						id="${action.action}">${action.description}</a></li>
				</c:forEach>

			</ul>
		</div>
	</div>
</nav>

<div class="container">
	<h1>${param.title}</h1>
	<div id="errors">
		<c:forEach var="error" items="${errorMessages}">
			<p class="alert-danger">${error}</p>
		</c:forEach>
	</div>
</div>





