<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
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
        <div class="container">
            <table id="cart" class="table table-hover table-condensed">
                <thead>
                    <tr>
                        <th style="width:50%">Produkt</th>
                        <th style="width:8%"></th>
                        <th style="width:17%" class="text-center"></th>
                        <th style="width:10%">Preis</th>
                        <th style="width:15%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="total" value="0"></c:set>
                    <c:forEach var="item" items="${cart}">
                        <c:set var="total" value="${total + item.getPrice()}"></c:set>
                            <tr>
                                <td data-th="Product">
                                    <div class="row">
                                        <div class="col-sm-4 hidden-xs"><img src="${item.getImage()}" width="120" class="img-responsive"/></div>
                                    <div class="col-sm-8">
                                        <h4 class="nomargin">${item.getName()}</h4>
                                    </div>
                                </div>
                            </td>
                            <td></td>
                            <td></td>
                            <td data-th="Price">${item.getPrice()} €</td>
                            <td class="actions" data-th="">
                                <form action="/WebProjekt/cart" method="post">
                                    <input type="hidden" name="id" value="${item.getGameId()}"/>
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
                                <c:when test="${!empty cart}">
                                    <c:choose>
                                        <c:when test="${!empty currentPage}">
                                            <a href="shop?recordsPerPage=${requestScope.recordsPerPage}&currentPage=${requestScope.currentPage}" class="btn btn-warning"><i class="fa fa-angle-left"></i> Weiter Einkaufen</a>
                                        </c:when>
                                    </c:choose>
                                </c:when>
                            </c:choose>
                        </td>
                        <td colspan="2" class="hidden-xs"></td>
                        <c:choose>
                            <c:when test="${!empty cart}">
                                <td class=" text-center"><strong>Gesamtpreis ${total}</strong></td>
                                <td>
                                    <form action="/WebProjekt/cart" method="post">
                                        <input type="hidden" name="totalPrice" value="${total}"/>
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