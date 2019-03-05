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

    <jsp:attribute name="main">

        <div class="row pt-4">
            <div class="login-form col-6">
                <form action="/WebProjekt/login" method="post">
                    <h2>Log in</h2>
                    <p class="hint-text">Bitte die E-Mail-Adresse und Passwort eingeben um weitere Funktionen nutzen zu können.</p>
                    <div class="form-group">
                        <input type="email" name="email" class="form-control" placeholder="E-Mail" required>
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" class="form-control" placeholder="Passwort" required>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label class="checkbox-inline">
                                <input type="checkbox"> Eingeloggt bleiben
                            </label>
                        </div>
                        <div class="form-group col-md-6 text-right">
                            <a href="#" class="pull-right">Passwort vergessen?</a>
                        </div>
                    </div> 
                    <div class="form-group">
                        <button type="submit" class="btn btn-success btn-lg btn-block">Einloggen</button>
                    </div>
                    <div class="text-center">Noch keinen Account? <a href="login?type=register">Hier Registrieren</a></div>

                    <div class="mt-4">
                    <c:choose>
                        <c:when test="${!empty result}">
                            <c:choose>
                                <c:when test="${result}">
                                    <div class="alert alert-success" role="alert">
                                        This is a success alert—check it out!
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