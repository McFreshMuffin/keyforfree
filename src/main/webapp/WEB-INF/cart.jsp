<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>

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
        <div class="container">
            <table id="cart" class="table table-hover table-condensed">
                <thead>
                    <tr>
                        <th style="width:20%">Produkt</th>
                        <th style="width:20%"></th>
                        <th style="width:10%" class="text-center">Menge</th>
                        <th style="width:10%" class="text-center">Preis</th>
                        <th style="width:20%" class="text-center">Gesamtpreis</th>
                        <th style="width:20%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="total" value="0"></c:set>
                    <c:forEach var="list" items="${sessionScope.shoppingCart}">
                        <c:set var="length" value="${fn:length(list.value)}"></c:set>
                        <c:choose>
                            <c:when test="${length != 0}">
                                <c:set var="gesamtpreis" value="${length * list.value.get(0).getPrice()}"></c:set>
                            </c:when>
                        </c:choose>
                        <c:set var="endpreis" value="${endpreis + gesamtpreis}"></c:set>    
                            <tr>
                                <td><img src="${list.value.get(0).getImage()}" width="120" class="img-responsive"/></td>
                            <td><h4 class="nomargin">${list.value.get(0).getName()}</h4></td>
                            <td data-th="Menge" class="text-center">${length}</td>
                            <td data-th="Preis" class="text-center roundPriceId">${list.value.get(0).getPrice()}</td>
                            <td data-th="Gesamtpreis" class="text-center roundPriceId">${gesamtpreis}</td>
                            <td class="actions" data-th="">
                                <form action="/WebProjekt/cart" method="post">
                                    <input type="hidden" name="id" value="${list.value.get(0).getGameId()}"/>
                                    <input type="hidden" name="action" value="delete"/>
                                    <button type="submit" class="btn btn-danger btn-sm text-center"><i class="far fa-trash-alt"></i></button>
                                </form>							
                            </td>
                        </tr>    
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${!empty sessionScope.shoppingCart}">
                                    <c:choose>
                                        <c:when test="${source == 'shop'}">
                                            <a href="shop?recordsPerPage=${requestScope.recordsPerPage}&currentPage=${requestScope.currentPage}" class="btn btn-warning"><i class="fa fa-angle-left"></i> Weiter Einkaufen</a>
                                        </c:when>
                                        <c:when test="${source == 'detail'}">
                                            <a href="detail?gameid=${requestScope.gameid}" class="btn btn-warning"><i class="fa fa-angle-left"></i> Weiter Einkaufen</a>
                                        </c:when>    
                                    </c:choose>
                                </c:when>
                            </c:choose>
                        </td>
                        <td colspan="3" class="hidden-xs"></td>
                        <c:choose>
                            <c:when test="${!empty sessionScope.shoppingCart}">
                                <td class="text-center">Endpreis <strong class="roundPriceId">${endpreis}</strong></td>
                                <td>
                                    <form action="/WebProjekt/cart" method="post">
                                        <input type="hidden" name="totalPrice" value="${endpreis}"/>
                                        <input type="hidden" name="action" value="buy"/>
                                        <button type="submit" class="btn btn-success">Kaufen <i class="fa fa-angle-right"></i></button>
                                    </form>
                                </td>
                            </c:when>
                        </c:choose>
                    </tr>
                </tfoot>
            </table>
        </div>
    </jsp:attribute>
</template:base>