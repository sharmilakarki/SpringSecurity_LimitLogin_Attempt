<%-- 
    Document   : header
    Created on : May 21, 2016, 7:08:17 PM
    Author     : sharmila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="URL" value="${pageContext.request.contextPath}"/>
<%@taglib  prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.indigo-pink.min.css">
        <script defer src="${URL}/static/extraJS/1.1.3.material.min.js"></script>
         <link href="${URL}/static/mdl/material.min.css" rel="stylesheet" type="text/css"/>
         <script src="${URL}/static/mdl/material.min.js" type="text/javascript"></script>
         <script src="${URL}/static/extraJS/jquery.min.js" type="text/javascript"></script>
    </head>
   
    
</html>
