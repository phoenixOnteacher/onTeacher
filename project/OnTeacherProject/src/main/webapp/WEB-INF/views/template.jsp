<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
String id = request.getParameter("page");
%>
<html>
<head>
<meta charset="UTF-8">
<title>ON TEACHER</title>
<c:set var="path" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${path}/resources/css/template.css" />
</head>
<body>
	<div id="wrap">
		<div id="header">
			<jsp:include page="header.jsp" />
		</div>
		<div id="contents"><jsp:include page="${page }.jsp" /></div>
	</div>
	<div id="footer">
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>

