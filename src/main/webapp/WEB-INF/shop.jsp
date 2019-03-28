<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>

    <jsp:attribute name="title">Games</jsp:attribute>
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

        <c:choose>
            <c:when test="${!empty games}">
                <div class="row">   
                    <form action="#"  style="margin: 10px;">
                        <label>Genre:
                            <select name="genre" onchange="generateLinkGenre(this.value);">                               
                                <option value="test">Auswählen..</option>
                                <option value="indie">Indie</option>
                                <option value="aktion">Action</option>
                                <option value="adventure">Adventure</option>
                                <option value="strategy">Strategy</option>
                                <option value="rpg">RPG</option>
                                <option value="simulation">Simulation</option>
                                <option value="sport">Sport</option>
                                <option value="racing">Racing</option>
                            </select>
                        </label>
                    </form>

                    <form action="#" style="margin: 10px;">
                        <label>Kategorie:
                            <select name="category" onchange="generateLinkCategory(this.value);">
                                <option value="#">Auswählen..</option>
                                <option value="singleplayer">Singleplayer</option>
                                <option value="multiplayer">Multiplayer</option>
                            </select>
                        </label>
                    </form>

                    <form action="#" style="margin: 10px;">
                        <label>Preis:
                            <select name="price" onchange="generateLinkPrice(this.value);">
                                <option value="#">Auswählen..</option>
                                <option value="0">0 €</option>
                                <option value="5">bis 5€</option>
                                <option value="10">bis 10€</option>
                                <option value="20">bis 20€</option>
                                <option value="30">bis 30€</option>
                                <option value="40">bis 40€</option>
                                <option value="50">bis 50€</option>
                            </select>
                        </label>
                    </form>
                </div>
                <div class="row col-md-6">
                    <table class="table table-striped table-bordered table-sm" style="table-layout:fixed;">
                        <tr>
                            <th style="width:309px;">Cover</th>
                            <th style="width:400px;">Titel</th>
                            <th style="width:70px;">Preis</th>
                            <th style="width:120px;">Veröffentlicht</th>
                            <th style="width:100px;"></th>
                            <th style="width:150px;">
                                <div class="btn-group">
                                    <button class="btn btn-secondary btn-sm dropdown-toggle" method="POST" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Sortieren nach...
                                    </button>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" href="#">A--Z</a>
                                        <a class="dropdown-item" href="#">Z--A</a>
                                        <a class="dropdown-item" href="#">Preis absteigend</a>
                                        <a class="dropdown-item" href="#">Preis aufsteigend</a>
                                    </div>
                                </div>

                            </th>

                        </tr>

                        <c:forEach items="${games}" var="game">
                            <tr>
                                <td> <a href="detail?gameid=${game.getGameId()}">
                                        <img src="${game.getImage()}" alt="${game.getName()}">
                                    </a>
                                </td>    
                                <td>${game.getName()}</td>
                                <td class="roundPriceId">${game.getPrice()}</td>
                                <td>${game.getReleaseYear()}</td>
                                <td>${game.getFsk()}</td>
                                <td align="center">
                                    <form action="/WebProjekt/cart" method="post">
                                        <input type="hidden" name="currentUrl" value="${requestScope
                                                                                        ['javax.servlet.forward.query_string']}">
                                        <input type="hidden" name="action" value="add">
                                        <input type="hidden" name="id" value="${game.getGameId()}">
                                        <button type="submit" class="btn btn-success btn-lg btn-block btnKaufenId">Kaufen</button>
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
        
        <c:set var="paginationUrl" value="shop?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}"></c:set>
        <c:set var=""></c:set>
        
        <div class="d-flex justify-content-center">
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
        </div>
    </jsp:attribute>
</template:base>