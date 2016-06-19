<%-- 
    Document   : editUser
    Created on : May 22, 2016, 4:49:41 PM
    Author     : sharmila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../views/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User Page</title>
        <link href="${URL}/static/css/myForm.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="mdl-layout mdl-layout__content ">
            <c:url var="edit" value="admin/editUser"/>
            <form action="${edit}"  class="mdl-layout" modelAttribute="userAdd" method="POST" >
                <h6>Edit User</h6>
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="hidden" name="id" value="${userRoles.id}"/>
                <input type="hidden" name="accountNonExpired" value="${user.accountNonExpired}"/>
                <input type="hidden" name="accountNonLocked" value="${user.accountNonLocked}"/>
                <input type="hidden" name="credentialsNonExpired" value="${user.credentialsNonExpired}"/>


                <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                    <input class="mdl-textfield__input" type="text" id="username" name="userName" value="${user.userName}"/>

                </div>

                <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                    <input class="mdl-textfield__input" type="email" id="email" name="email" value="${user.email}"/>
                </div>


                <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                    <input class="mdl-textfield__input" type="text" id="address" name="address" value="${user.address}"/>
                </div>


                <input class="mdl-textfield__input" type="hidden" id="password" name="password" value="${user.password}"/>


                <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                    <input class="mdl-textfield__input" type="text" id="status"  name="status" value="${user.status}"/>
                </div>
                <div class="mdl-layout mdl-textfield mdl-js-textfield" >
                    <select name="role" class="mdl-layout mdl-textfield">
                        <c:forEach var="u" items="${user.role}">
                            <option value="${u.role}">
                                ${u.role}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div></div>
                <div>
                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent" type="submit">
                        Edit
                    </button>
                </div>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
            </form>
        </div>
    </body>
</html>
