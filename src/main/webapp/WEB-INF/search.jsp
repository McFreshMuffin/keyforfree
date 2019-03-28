<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>

    <jsp:attribute name="title">Suchergebnisse</jsp:attribute>
    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="style/shop.css"/>">
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
        <div class="row col-md-6">
            <c:choose>
                <c:when test="${!empty sucheTreffer}">
                    <div class="message" style="font-weight: bold;">
                        Ihre Suche nach "${suchbegriff}" hat ${anzItems} Treffer ergeben: 
                    </div>
                    <table class="table table-striped table-bordered table-sm" style="table-layout:fixed; margin-top: 5px;">
                        <tr>
                            <th style="width:309px;">Bild</th>
                            <th style="width:400px;">Name</th>
                            <th style="width:70px;">Preis</th>
                            <th style="width:120px;">Veröffentlicht</th>
                            <th style="width:50px;">Alter</th>
                            <th style="width:100px;"></th>
                        </tr>

                        <c:forEach items="${sucheTreffer}" var="item">
                            <tr>
                                <td> <a href="detail?gameid=${item.getGameId()}">
                                        <img src="${item.getImage()}" alt="${item.getName()}">
                                    </a>
                                </td>    
                                <td>${item.getName()}</td>
                                <td>${item.getPrice()} €</td>
                                <td>${item.getReleaseYear()}</td>
                                <td>${item.getFsk()}</td>
                                <td align="center">
                                    <form action="/WebProjekt/cart" method="post">
                                        <input type="hidden" name="currentUrl" value="${requestScope['javax.servlet.forward.query_string']}">
                                        <input type="hidden" name="action" value="add">
                                        <input type="hidden" name="id" value="${item.getGameId()}">
                                        <button type="submit" class="btn btn-success btn-lg btn-block">Kaufen</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <div class="message" style="font-weight: bold;">
                        Ihre Suche nach "${suchbegriff}" hat leider keine Treffer ergeben.
                    </div>
                </c:otherwise>
            </c:choose>
        </div>


    </jsp:attribute>
</template:base>
