<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">Übersicht</jsp:attribute>

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
        <h1>Warenkorb wurde erfolgreich ausgelöst</h1>
        <h2>Email mit ihrer Bestellung wurde versandt</h2>
        <a href="/WebProjekt/index.html">Zurück zur Startseite</a>
    </jsp:attribute>
</template:base>