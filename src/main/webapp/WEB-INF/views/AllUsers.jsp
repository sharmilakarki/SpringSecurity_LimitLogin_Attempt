<%-- 
    Document   : AllUsers
    Created on : May 22, 2016, 12:38:46 PM
    Author     : sharmila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../views/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
         <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="A page that uses Material Design Lite.">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">

        <!-- Tile icon for Win8 (144x144 + tile color) -->
        <meta name="msapplication-TileImage" content="images/touch/ms-touch-icon-144x144-precomposed.png">
        <meta name="msapplication-TileColor" content="#3372DF">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.cyan-light_blue.min.css">
        <link rel="stylesheet" href="${URL}/static/css/styles.css" />

        <style>
            #view-source {
                position: fixed;
                display: block;
                right: 0;
                bottom: 0;
                margin-right: 40px;
                margin-bottom: 40px;
                z-index: 900;
            }
        </style>
        <link href="${URL}/static/css/myForm.css" rel="stylesheet" type="text/css"/>
        
        <title>All User Page</title>
    </head>
    <body>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
                <header class="mdl-layout__header">
                    <div class="mdl-layout__header-row">
                        <span class="mdl-layout__title">DashBoard</span>
                        <div class="mdl-layout-spacer"></div>
                        <nav class="mdl-navigation mdl-typography--body-1-force-preferred-font">
                            <a class="mdl-navigation__link" href="#">Option 1</a>
                            <a class="mdl-navigation__link" href="#">Option 2</a>
                            <a class="mdl-navigation__link" href="#">About</a>
                            <a class="mdl-navigation__link" href="${URL}/login/?logout">logout</a>
                        </nav>
                    </div>
                </header>
                <div class="mdl-layout__drawer">
                    <span class="mdl-layout__title">${username}</span>
                    <nav class="mdl-navigation mdl-typography--body-1-force-preferred-font">
                        <a class="mdl-navigation__link"  id="usersLink" <a class="mdl-navigation__link" href="${URL}/admin/AllUsers"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">people</i>Users</a>
                            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">home</i>Home</a>
                            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">inbox</i>Inbox</a>
                            <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">delete</i>Trash</a>
                    </nav>
                </div>
                <div class="container">
                    <table class="mdl-data-table mdl-js-data-table mdl-data-table-selectable mdl-shadow--2dp" id="tblData" align="center">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>USER NAME</th>
                                <th>EMAIL</th>
                                <th>ADDRESS</th> 
                                <th>ROLE</th> 
                                <th>ADDED DATE</th>                   
                                <th>MODIFIED DATE</th>
                                <th>STATUS</th>
                                <th>ACTION</th>
                            </tr>
                        </thead>
                        <tbody>


                            <c:forEach var="user" items="${user}">
                                <tr>
                                    <td class="mdl-data-table__cell--non-numeric">${user.id}</td>
                                    <td class="mdl-data-table__cell--non-numeric">${user.userName}</td>
                                    <td class="mdl-data-table__cell--non-numeric">${user.email}</td>
                                    <td class="mdl-data-table__cell--non-numeric">${user.address}</td>
                                    <td class="mdl-data-table__cell--non-numeric">
                                        <c:forEach var="u" items="${user.role}">
                                            ${u.role}
                                        </c:forEach>
                                    </td>

                                    <td class="mdl-data-table__cell--non-numeric">${user.addedDate}</td>
                                    <td class="mdl-data-table__cell--non-numeric">${user.modifiedDate}</td>
                                    <td class="mdl-data-table__cell--non-numeric">${user.status}</td>

                                    <td class="mdl-data-table__cell--non-numeric"><a href="${URL}/edit?id=${user.id}" class="btn btn-sm btn-success"><span class="glyphicon glyphicon-edit">Edit  </span></a>
                                        &nbsp;&nbsp;
                                        <a href="${URL}/delete?id=${user.id}" id="deleteBtn" class="btn btn-sm btn-danger" onclick="return check();"><span class="glyphicon glyphicon-remove">Delete</span></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </div>
            </sec:authorize>
    </body>
</html>
