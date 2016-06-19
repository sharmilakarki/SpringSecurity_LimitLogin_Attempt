<%-- 
    Document   : adminDashboard
    Created on : May 21, 2016, 8:56:48 PM
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
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.cyan-light_blue.min.css">
        <link rel="stylesheet" href="${URL}/static/styles.css" />
       
        <title>Admin Page</title>
    <body
        <!-- The drawer is always open in large screens. The header is always shown,
          even in small screens. -->
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
            <header class="mdl-layout__header">
                <div class="mdl-layout__header-row">
                    <span class="mdl-layout__title">Simple portfolio website</span>
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
                <span class="mdl-layout__title">Admin Panel</span>
                <nav class="mdl-navigation mdl-typography--body-1-force-preferred-font">
                    <a class="mdl-navigation__link" href="${URL}/admin/AllUsers">Users</a>
                    <a class="mdl-navigation__link" href="#">News</a>
                    <a class="mdl-navigation__link" href="#">Other</a>
                    <a class="mdl-navigation__link" href="#">Contact</a>
                </nav>
            </div>
            <main class="mdl-layout__content">
                <div class="mdl-grid">
                    <div class="mdl-cell mdl-cell--12-col"><p>This</p></div>
                    <div class="mdl-cell mdl-cell--12-col-tablet">Cell 1</div>
                    <div class="mdl-cell mdl-cell--4-col-tablet">Cell 2</div>
                    <div class="mdl-cell mdl-cell--4-col-tablet">Cell 3</div>
                </div>
                <footer class="mdl-mini-footer">
                    <div class="mdl-mini-footer_left-section">
                        <div class="mdl-logo">This footer</div>
                    </div>
                    <div class="mdl-mini-footer__right-section">
                        <ul class="mdl-mini-footer__link-list">
                            <li><a href="#">Help</a></li>
                            <li><a href="#">Privacy and terms</a></li>
                        </ul>
                    </div>

                </footer>
            </main>
        </div>
        <script src="${URL}/static/extraJS/1.0.6.material.min.js"></script>


    </body>
</html>
