<%-- 
    Document   : login
    Created on : May 21, 2016, 5:02:53 PM
    Author     : sharmila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../views/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="${URL}/static/css/myForm.css" rel="stylesheet" type="text/css"/>
    </head>

    <body onload="noback();">

        <div class="mdl-layout">
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
            <c:if test="${not empty message}">
                <div class="message">${message}</div>
            </c:if> 
            
            <c:url var="loginUrl" value="/login" />
            <form   action="${loginUrl}" class="mdl-layout" modelAttribute="user" method="POST">
                <h3>Login Form</h3>
                <input type="hidden" name="id"/>
                <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                    <label class="mdl-textfield__label" for="username">Username</label>
                    <input class="mdl-textfield__input" type="text" id="username" name="username"/>

                </div>
                </br>
                <div class="mdl-layout mdl-textfield mdl-js-textfield">
                    <label class="mdl-textfield__label" for="password">Password</label>
                    <input class="mdl-textfield__input" type="password" id="password" name="password"/>

                </div>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />

                <div>
                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent" type="submit">
                        Login
                    </button>
                </div>
            </form>
            <div class="mdl-layout">
                <div>
                    </br>
                    <p>Not a member???</p>
                </div>
                <div>
                    <a href="${URL}/user/SignupPage" class="mdl-button mdl-js-button mdl-button--raised  mdl-button--accent">Signup</a>
                    <div>
                    </div>

                    <script type="text/javascript">

                        $(document).ready(function () {
                           
                            function disableBack() {
                                window.history.go(1);
                            };
                            

                            window.onload = disableBack();
                            window.onpageshow = function (evt) {
                                if (evt.persisted)
                                    disableBack();
                            };
                        });
                    </script>
                    </body>
                    </html>
