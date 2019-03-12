<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">Übersicht</jsp:attribute>
    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="style/index.css"/>">
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
        <div class="container">
            <div class="row">
                <h1>Willkommen ${sessionScope.username}</h1>
            </div>
            <br>
            <br>
            <div class="row">
                <div class="col-md-12">
                    <div id="NewestGamesCarousel" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <c:choose>
                                <c:when test="${!empty newestGames}">
                                    <c:forEach items="${newestGames}" var="game" varStatus="status">
                                        <c:choose> 
                                            <c:when test="${status.first }" >
                                                <div class="carousel-item active">
                                                    <a href="detail?gameid=${game.getGameId()}">
                                                    <img class="imageitem" src="${game.getImage()}" alt="${game.getName()}" style="max-width:100%;">
                                                    </a>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="carousel-item">
                                                    <a href="detail?gameid=${game.getGameId()}">
                                                    <img class="imageitem" src="${game.getImage()}" alt="${game.getName()}" style="max-width:100%;">
                                                    </a>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Next</span>
                                    </a>
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