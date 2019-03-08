<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">Übersicht</jsp:attribute>

    <jsp:attribute name="main">
        <form action="index.html">
            <input type="hidden" name="currentPage" value="1">
            <div class="form-group col-md-4">
                <label for="records">Select records per page:</label>
                <select class="form-control" id="records" name="recordsPerPage"> 
                    <option value="5">5</option> 
                    <option value="10" selected>10</option>
                    <option value="15">15</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Send</button>
        </form>
    </jsp:attribute>
</template:base>