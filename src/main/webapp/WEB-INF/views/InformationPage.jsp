<%-- 
    Document   : InformationPage
    Created on : May 26, 2016, 12:17:04 PM
    Author     : sharmila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../views/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>Information Page</title>
         <script src="/static/extraJS/jquery.min.js" type="text/javascript"></script>
  
    </head>
    <body>
        <h3>User Information</h3>
        <div class="result" id="resultDiv">
            St
        </div>
        <button id="loadBtn" >Load</button>
        <a href="../../json/User.json"></a>
        <script>
            $(document).ready(function (){
//                alert("works");
               $("#loadBtn").click(function (){
                   $.getJSON("../../json/User.json",function (ur){
                       $("#result").text(ur.name);
                   });
               });
            });
        </script>
    </body>
</html>
