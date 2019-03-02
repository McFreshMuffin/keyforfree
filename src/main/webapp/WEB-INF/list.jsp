<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>

    <jsp:attribute name="title">Games</jsp:attribute>

    <jsp:attribute name="main">
        <div class="row col-md-6">
            <c:choose>
                <c:when test="${!empty games}">

                    <table class="table table-striped table-bordered table-sm">
                        <tr>
                            <th>Bild</th>
                            <th>Name</th>
                            <th>Preis</th>
                            <th>ReleaseDatum</th>
                            <th>Alter</th>
                        </tr>

                        <c:forEach items="${games}" var="game">
                            <tr>
                                <td><img src="${game.getHeaderImage()}" alt="${game.getName()}"></td>    
                                <td>${game.getName()}</td>
                                <td>${game.getPriceFinal()} €</td>
                                <td>${game.getReleaseDate()}</td>
                                <td>${game.getRequiredAge()}</td>
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
                                             href="ListServlet?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage-2 eq i}">
                            <li class="page-item"><a class="page-link" 
                                                     href="ListServlet?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                            </li>
                        </c:when>
                        <c:when test="${currentPage-1 eq i}">
                            <li class="page-item"><a class="page-link" 
                                                     href="ListServlet?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                            </li>
                        </c:when>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item active"><a class="page-link">
                                    ${i} <span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:when test="${currentPage+1 eq i}">
                            <li class="page-item"><a class="page-link" 
                                                     href="ListServlet?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                            </li>
                        </c:when>
                        <c:when test="${currentPage+2 eq i}">
                            <li class="page-item"><a class="page-link" 
                                                     href="ListServlet?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                            </li>
                        </c:when>

                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item"><a class="page-link" 
                                             href="ListServlet?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                    </li>
                </c:if>              
            </ul>
        </nav>
    </jsp:attribute>
</template:base>