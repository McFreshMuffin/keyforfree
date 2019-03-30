<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">Login</jsp:attribute>
   
    <jsp:attribute name="nav_log">
        <c:choose>
            <c:when test="${empty user}">
                <a class="nav-link" href="login">Login</a>
            </c:when>
            <c:otherwise>
                <a class="nav-link" href="logout">Logout</a>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
    
    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/style/login.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="nav_log">
        <c:choose>
            <c:when test="${empty sessionScope.user}">
                 <ul class="nav navbar-nav navbar-right mt-2 mt-lg-0">
                        <li class="nav-item active">
                            <a href="register" class="nav-link" style="text-decoration: none; color: white;">
                                <i class="fas fa-user-plus"></i>
                            </a>
                        </li>
                        <li class="nav-item active">
                            <a href="login" class="nav-link" style="text-decoration: none; color: white;">
                                <i class="fas fa-sign-in-alt"></i>
                            </a>
                        </li>
                    </ul>
            </c:when>
            <c:otherwise>
                <ul class="nav navbar-nav navbar-right mt-2 mt-lg-0">
                        <li class="nav-item active">
                            <a href="cart" class="nav-link">
                                <i class="fas fa-shopping-cart"></i>
                            </a>
                        </li>
                        <li class="nav-item active">
                            <a href="logout" class="nav-link">
                                <i class="fas fa-sign-out-alt"></i>
                            </a>
                        </li>
                    </ul>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
        
    <jsp:attribute name="main">

        <div class="row pt-4">
            <div class="login-form col-6">
                <form action="/WebProjekt/login" method="post">
                    <h2>Einloggen</h2>
                    <p class="hint-text">Bitte die E-Mail-Adresse und Passwort eingeben um weitere Funktionen nutzen zu können.</p>
                    <div class="form-group">
                        <input type="email" name="email" class="form-control" placeholder="E-Mail" required>
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" class="form-control" placeholder="Passwort" required>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <a href="#" class="pull-right">Passwort vergessen?</a>
                        </div>
                    </div> 
                    <div class="form-group">
                        <button type="submit" class="btn btn-success btn-lg btn-block">Einloggen</button>
                    </div>
                    <div class="text-center">Noch keinen Account? <a href="register">Hier Registrieren</a></div>

                    <div class="mt-4">
                    <c:choose>
                        <c:when test="${!empty result}">
                            <c:choose>
                                <c:when test="${result}">
                                    <div class="alert alert-success" role="alert">
                                        Der Login war Erfolgreich.
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="alert alert-warning" role="alert">
                                        <h4 class="alert-heading">Login fehlgeschlagen</h4>
                                        <p>Der eingegebene Benutzername (E-Mail) wurde nicht gefunden oder das zugehörige Passwort ist falsch.</p>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose>
                        </div>

                </form>
            </div>
        </div>

    </jsp:attribute>
</template:base>