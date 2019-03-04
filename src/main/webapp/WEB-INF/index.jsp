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
        <form action="ListServlet">
            <input type="hidden" name="currentPage" value="1">
            <div class="form-group col-md-4">
                <label for="records">Select records per page:</label>
                <select class="form-control" id="records" name="recordsPerPage"> 
                    <option value="5">5</option> 
                    <option value="10" selected>10</option>
                    <option value="15">15</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>

        <c:choose>
            <c:when test="${!empty user}">
                <ul>
                    <li>
                        ${user.email}
                    </li>
                    <li>
                        ${user.vorname};
                    </li>
                    <li>
                        ${user.nachname};
                    </li>
                </ul>
            </c:when>
            <c:otherwise>
                <p>Noch nicht eingeloggt</p>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
</template:base>