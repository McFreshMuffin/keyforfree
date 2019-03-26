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
        <div class="row col-md-6">
            <c:choose>
                <c:when test="${!empty games}">

                    <form action="#">
                        <label>Genre:
                            <select name="genre" onchange="generateLink(this.value);">
                                <input type="hidden" name="genrefilter">
                                <option value="test">Auswählen..</option>
                                
                                <option value="?recordsPerPage=10&currentPage=1&genre=indie">Indie</option>
                                <option value="?recordsPerPage=10&currentPage=1&genre=aktion">Action</option>
                                <option value="?recordsPerPage=10&currentPage=1&genre=adventure">Adventure</option>
                                <option value="?recordsPerPage=10&currentPage=1&genre=strategy">Strategy</option>
                                <option value="?recordsPerPage=10&currentPage=1&genre=rpg">RPG</option>
                                <option value="?recordsPerPage=10&currentPage=1&genre=simulation">Simulation</option>
                                <option value="?recordsPerPage=10&currentPage=1&genre=sport">Sport</option>
                                <option value="?recordsPerPage=10&currentPage=1&genre=racing">Racing</option>
                            </select>
                        </label>
                    </form>
                    
                    <form action="#">
                        <label>Kategorie:
                            <select name="genre" onchange="location = this.value;">
                                <option value="">Auswählen..</option>
                                <option value="#">Singleplayer</option>
                                <option value="#">Multiplayer</option>
                            </select>
                        </label>
                    </form>

                    <table class="table table-striped table-bordered table-sm" style="table-layout:fixed;">
                        <tr>
                            <th style="width:309px;">Bild</th>
                            <th style="width:400px;">Name</th>
                            <th style="width:70px;">Preis</th>
                            <th style="width:120px;">Veröffentlicht</th>
                            <th style="width:100px;"></th>
                            <th style="width:150px;">
                                <div class="btn-group">
                                    <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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
                                <td>${game.getPrice()} €</td>
                                <td>${game.getReleaseYear()}</td>                                
                                <td align="center">
                                    <form action="/WebProjekt/cart" method="post">
                                        <input type="hidden" name="currentUrl" value="${requestScope
                                         ['javax.servlet.forward.query_string']}">
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