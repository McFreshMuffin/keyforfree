<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">Übersicht</jsp:attribute>

    <jsp:attribute name="nav_log">
        <c:choose>
            <c:when test="${empty user}">
                <a class="nav-link" href="login?type=login">Login</a>
            </c:when>
            <c:otherwise>
                <a class="nav-link" href="login?type=logout">Logout</a>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>

    <jsp:attribute name="main">
        <table cellpadding="2" cellspacing="2" border="1">
            <tr>
                <th>Option</th>
                <th>Id</th>
                <th>Name</th>
                <th>Photo</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Sub Total</th>
            </tr>
            <c:set var="total" value="0"></c:set>
            <c:forEach var="item" items="${cart}">
                <c:set var="total" value="${total + item.getPriceFinal()}"></c:set>
                    <tr>
                        <td align="center">
                            <a href="${pageContext.request.contextPath }/cart?action=remove&id=${item.getGameId()}"
                           onclick="return confirm('Are you sure?')">Remove</a>
                    </td>
                    <td>${item.getGameId() }</td>
                    <td>${item.getName()}</td>
                    <td>
                        <img src="${item.getHeaderImage()}" width="120">
                    </td>
                    <td>${item.getPriceFinal()}</td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="6" align="right">Total</td>
                <td>${total }</td>
            </tr>
        </table>
        <br>
        <form>
            <input type="hidden" name="currentUrl" value="${requestScope['javax.servlet.forward.query_string']}">
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="id" value="${game.getGameId()}">
            <button type="submit" class="btn btn-success btn-lg btn-block">Kaufen</button>
        </form>
        <a href="shop?${requestScope.currentPage}">Continue Shopping</a>
    </jsp:attribute>
</template:base>