<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">Übersicht</jsp:attribute>
        
    <jsp:attribute name="main">
        <h1>Key4Free</h1>
        
        <c:choose>
            <c:when test="${!empty game}">
                <div>
                    ${game.name}
                    ${game.headerImage}
                    <img src="${game.headerImage}" alt="Selfhtml">
                </div>
                <table>
                    <c:forEach items="${allGames}" var="game">
                        <tr>
                            <td>
                                ${game.name}
                                <img src="${game.headerImage}" alt="${game.name}">
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
    </jsp:attribute>
</template:base>