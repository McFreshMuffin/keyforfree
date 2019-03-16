<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>

    <jsp:attribute name="title">Games</jsp:attribute>

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
                <c:when test="${!empty games}">

                    <table class="table table-striped table-bordered table-sm">
                        <tr>
                            <th>Bild</th>
                            <th>Name</th>
                            <th>Preis</th>
                            <th>Veröffentlicht</th>
                            <th>Alter</th>
                            <th></th>
                        </tr>

                        <c:forEach items="${games}" var="game">
                            <tr>
                                <td><img src="${game.getImage()}" alt="${game.getName()}"></td>    
                                <td>${game.getName()}</td>
                                <td>${game.getPrice()} €</td>
                                <td>${game.getReleaseYear()}</td>
                                <td>${game.getRequiredAge()}</td>
                                <td align="center">
                                    <form action="/WebProjekt/cart" method="post">
                                        <input type="hidden" name="currentUrl" value="${requestScope['javax.servlet.forward.query_string']}">
                                        <input type="hidden" name="action" value="add">
                                        <input type="hidden" name="id" value="${game.getGameId()}">
                                        <button type="submit" class="btn btn-success btn-lg btn-block">Kaufen</button>
                                    </form>
                                </td>
                            </tr>
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

        <nav aria-label="Navigation for Games">
            <ul class="pagination">
                <c:if test="${currentPage != 1}">
                    <li class="page-item"><a class="page-link" 
                                             href="shop?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage-2 eq i}">
                            <li class="page-item"><a class="page-link" 
                                                     href="shop?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                            </li>
                        </c:when>
                        <c:when test="${currentPage-1 eq i}">
                            <li class="page-item"><a class="page-link" 
                                                     href="shop?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                            </li>
                        </c:when>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:when test="${currentPage+1 eq i}">
                            <li class="page-item"><a class="page-link" 
                                                     href="shop?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                            </li>
                        </c:when>
                        <c:when test="${currentPage+2 eq i}">
                            <li class="page-item"><a class="page-link" 
                                                     href="shop?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                            </li>
                        </c:when>

                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item"><a class="page-link" 
                                             href="shop?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                    </li>
                </c:if>              
            </ul>
        </nav>
    </jsp:attribute>
</template:base>