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
                <a class="nav-link" href="login">Login</a>
            </c:when>
            <c:otherwise>
                <a class="nav-link" href="logout">Logout</a>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>

    <jsp:attribute name="main">
        <div class="container">
            <div class="row">
                <h1>Willkommen ${sessionScope.username}</h1>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div id="NewestGamesCarousel" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <c:choose>
                                <c:when test="${!empty newestGames}">
                                    <c:forEach items="${newestGames}" var="game">
                                        <div class="carousel-item">
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <a href="#">
                                                        <img src="http://placehold.it/250x250" alt="Image" style="max-width:100%;">
                                                    </a>
                                                    <div class="carousel-caption d-none d-md-block">
                                                        <h5>...</h5>
                                                        <p>...</p>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <a href="#">
                                                        <img src="http://placehold.it/250x250" alt="Image" style="max-width:100%;">
                                                    </a>
                                                    <div class="carousel-caption d-none d-md-block">
                                                        <h5>...</h5>
                                                        <p>...</p>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <a href="#">
                                                        <img src="http://placehold.it/250x250" alt="Image" style="max-width:100%;">
                                                    </a>
                                                    <div class="carousel-caption d-none d-md-block">
                                                        <h5>...</h5>
                                                        <p>...</p>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <a href="#">
                                                        <img src="http://placehold.it/250x250" alt="Image" style="max-width:100%;">
                                                    </a>
                                                    <div class="carousel-caption d-none d-md-block">
                                                        <h5>...</h5>
                                                        <p>...</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    <div class="message">
                                        Kein Game gefunden
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <a class="carousel-control-prev" href="#NewestGamesCarousel" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#NewestGamesCarousel" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</template:base>