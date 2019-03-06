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
        <link rel="stylesheet" href="<c:url value="/style/register.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="main">

        <div class="row pt-4">
            <div class="signup-form col-6">
                <form action="/WebProjekt/register" method="post">
                    <h2>Registrierung</h2>
                    <input type="hidden" name="type" value="register">
                    <p class="hint-text">Hier kann ihr Account erstellt werden.</p>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <input type="text" class="form-control" name="first_name" placeholder="Vorname" required="required">
                        </div>

                        <div class="form-group col-md-6">
                            <input type="text" class="form-control" name="last_name" placeholder="Nachname" required="required">
                        </div>
                    </div> 
                    <div class="form-group">
                        <input type="address" class="form-control" name="address" placeholder="Addresse" required="required">
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control" name="email" placeholder="Email" required="required">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="Passwort" required="required">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="confirm_password" placeholder="Passwort bestätigen" required="required">
                    </div>        
                    <div class="form-group">
                        <label class="checkbox-inline"><input type="checkbox" required="required"> Ich akzeptiere <a href="#">Nutzungsbedingungen</a> &amp; <a href="#">Datenschutzbestimmungen</a></label>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-success btn-lg btn-block">Jetzt Registrieren</button>
                    </div>
                    <div class="text-center">Bereits einen Account? <a href="login">Einloggen</a></div>
                </form>

                <c:choose>
                    <c:when test="${!empty result}">
                        <c:choose>
                            <c:when test="${result}">
                                <p>Registrierung war Erfolgreich!</p>
                            </c:when>
                            <c:otherwise>
                                <p>Registrierung fehlgeschlagen!</p>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>

</jsp:attribute>
</template:base>