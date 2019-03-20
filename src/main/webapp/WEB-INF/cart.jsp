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
        <table cellpadding="2" cellspacing="2" border="1">
            <tr>
                <th>Bild</th>
                <th>Name</th>
                <th>Preis</th>
                <th>Option</th>
            </tr>
            <c:set var="total" value="0"></c:set>
            <c:forEach var="item" items="${cart}">
                <c:set var="total" value="${total + item.getPrice()}"></c:set>
                    <tr>
                        <td>
                            <img src="${item.getImage()}" width="120">
                    </td>
                    <td>${item.getName()}</td>
                    <td>${item.getPrice()}</td>
                    <td align="center">
                        <a href="${pageContext.request.contextPath }/cart?action=remove&id=${item.getGameId()}"
                           onclick="return confirm('Are you sure?')">Entfernen</a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="6" align="right">Gesamtpreis</td>
                <td>${total}</td>
            </tr>
        </table>
        <br>
        <form action="/WebProjekt/cart" method="post">
            <input type="hidden" name="totalPrice" value="${total}">
            <input type="hidden" name="action" value="buy">
            <button type="submit" class="btn btn-success btn-lg btn-block">Kaufen</button>
        </form>
        <c:choose>
            <c:when test="${!empty requestScope.currentPage}">
                <a href="shop?${requestScope.currentPage}">Weiter Einkaufen</a>
            </c:when>
        </c:choose>
    </jsp:attribute>
</template:base>