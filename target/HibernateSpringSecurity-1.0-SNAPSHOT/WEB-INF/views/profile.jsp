<%-- 
    Document   : profile
    Created on : May 21, 2016, 8:55:39 PM
    Author     : sharmila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../views/header.jsp" %>
<%@taglib  prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <script src="http://maps.googleapis.com/maps/api/js"></script>
        <script>
        function getMap(){
            var mapProp={center:new google.maps.LatLng(51.508742,-0.120850),
                zoom:5,
                mapTypeId:google.maps.MapTypeId.ROADMAP};
            var map=new google.maps.Map(document.getElementById("mapDiv"),mapProp);
        }
        google.maps.event.addDomListener(window, 'load', getMap);
            </script>
    </head>
    <body>
        
        <sec:authorize access="hasRole('ROLE_USER')">
            <h1>User profile</h1>
            <div id="mapDiv" style="width:500px;height:380px;"></div>
           <c:url value="/logout" var="logoutUrl" />

	<!-- csrt for log out-->
	<form action="${logoutUrl}" method="POST" id="logoutForm">
	  <input type="hidden" 
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
	
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>

                    
        </sec:authorize>
    </body>
</html>
