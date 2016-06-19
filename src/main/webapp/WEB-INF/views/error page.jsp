<%-- 
    Document   : error page
    Created on : May 24, 2016, 5:16:11 PM
    Author     : sharmila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../views/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bad login credentials</h1>
        <a href="/">Back</a>
        <c:choose>
		<c:when test="${empty username}">
		  <h2>You do not have permission to access this page!</h2>
		</c:when>
		<c:otherwise>
		  <h2>Username : ${username} <br/>
                    You do not have permission to access this page!</h2>
		</c:otherwise>
	</c:choose>
    </body>
</html>
