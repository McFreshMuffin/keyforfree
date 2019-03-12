<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>

    <jsp:attribute name="title">${game.getName()}</jsp:attribute>

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

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/style/detail.css"/>">
    </jsp:attribute>


    <jsp:attribute name="main">
        <c:set var="test1" value="True"></c:set>
        <h1 class="title">${game.getName()}</h1>
        <div class="text-center">
            <img src="${game.getImage()}" class="rounded">
        </div>

        <div class="accordion" id="accordionExample">
            <div class="card">
                <div class="card text-center">
                    <div class="card-header" id="cardHeaderOne">
                        <ul class="nav nav-tabs card-header-tabs">
                            <li class="nav-item">
                                <a class="nav-link beschreibungActive" data-toggle="collapse show" href="#collapseOne" role="button" aria-expanded="true" aria-controls="headingOne">
                                    Beschreibung
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="collapse" href="#collapseTwo" role="button" aria-expanded="false" aria-controls="headingTwo">
                                    Informationen
                                </a>
                            </li>
                            <c:choose>
                                <c:when test="${game.getPlatformWindows() == test1}">
                                    <li class="nav-item">
                                        <a class="nav-link" data-toggle="collapse" href="#collapseThree" role="button" aria-expanded="false" aria-controls="headingThree">
                                            Systemvoraussetzungen-Windows
                                        </a>
                                    </li>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${game.getPlatformLinux() == test1}">
                                    <li class="nav-item">
                                        <a class="nav-link" data-toggle="collapse" href="#collapseFour" role="button" aria-expanded="false" aria-controls="headingFour">
                                            Systemvoraussetzungen-Linux
                                        </a>
                                    </li>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${game.getPlatformMac() == test1}">
                                    <li class="nav-item">
                                        <a class="nav-link" data-toggle="collapse" href="#collapseFive" role="button" aria-expanded="false" aria-controls="headingFive">
                                            Systemvoraussetzungen-Mac
                                        </a>
                                    </li>
                                </c:when>
                            </c:choose>
                        </ul>
                    </div>
                    <div class="card-body" id="cardBodyOne">
                        <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                            <div class="card-body">
                                ${game.getDetailedDescrip()}
                            </div>
                        </div>
                        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                            <div class="card-body">
                                ${game.getReleaseYear()}
                            </div>
                        </div>
                        <!--Systemvoraussetzungen Windows-->
                        <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                            <div class="card-body">
                                <c:choose>
                                    <c:when test="${game.getPCReqsHaveMin() == test1}">
                                        Minimum:
                                        <br>
                                        ${game.getPCMinReqsText()}
                                        <br>
                                    </c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${game.getPCReqsHaveRec() == test1}">
                                        Empfohlen:
                                        <br>
                                        ${game.getPCRecReqsText()}
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                        <!--Systemvoraussrtzungen Linux-->
                        <div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordionExample">
                            <div class="card-body">
                                <c:set var="test1" value="True"></c:set>
                                <c:choose>
                                    <c:when test="${game.getLinuxReqsHaveMin() == test1}">
                                        Minimum:
                                        <br>
                                        ${game.getLinuxMinReqsText()}
                                        <br>
                                    </c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${game.getLinuxReqsHaveRec() == test1}">
                                        Empfohlen:
                                        <br>
                                        ${game.getLinuxRecReqsText()}
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                        <!--Systemvoraussetzungen Mac-->
                        <div id="collapseFive" class="collapse" aria-labelledby="headingFive" data-parent="#accordionExample">
                            <div class="card-body">
                                <c:set var="test1" value="True"></c:set>
                                <c:choose>
                                    <c:when test="${game.getMacReqsHaveMin() == test1}">
                                        Minimum:
                                        <br>
                                        ${game.getMacMinReqsText()}
                                        <br>
                                    </c:when>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${game.getMacReqsHaveRec() == test1}">
                                        Empfohlen:
                                        <br>
                                        ${game.getMacRecReqsText()}
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</template:base>
