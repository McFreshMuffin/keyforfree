<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>

    <jsp:attribute name="title">${game.getName()}</jsp:attribute>

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

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/style/detail.css"/>">
    </jsp:attribute>


    <jsp:attribute name="main">
        <c:set var="test1" value="WAHR"></c:set>

            <div class="detailFirst">
                <h1 class="title">${game.getName()}</h1>
            <div class="text">
                <table class="myTable">
                    <tr>
                        <td class="myImageField" colspan="2">
                            <div class="myImageDiv">
                                <img src="${game.getImage()}" class="img-fluid img-thumbnail">
                            </div>
                        </td>
                        <td colspan="2">
                            <div class="scrollview">
                                ${game.getAboutText()}
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td class="pricingRow">Menge:</td>
                        <td class="pricingRow">Preis:</td>
                        <td class="pricingRow">Gesamt:</td>
                        <td class="pricingRow" rowspan="2">
                            <form action="/WebProjekt/cart" method="post">
                                <input type="hidden" name="currentUrl" value="${requestScope['javax.servlet.forward.query_string']}">
                                <input type="hidden" name="action" value="add">
                                <input type="hidden" name="id" value="${game.getGameId()}">
                                <button type="submit" class="btn btn-primary btn-sm btnKaufenId"><i class="fas fa-cart-plus"> In den Warenkorb legen</i></button>
                            </form>
                        </td>
                    </tr>
                    <tr>
                        <td class="pricingRow">
                            <select class="custom-select custom-select-sm" id="selection" onchange="changePrice(this.value, ${game.getPrice()})">
                                <option label="1" value="1" selected>1</option>
                                <c:forEach begin="2" end="10" var="option">
                                    <option label="${option}" value="${option}">${option}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="pricingRow roundPriceId">${game.getPrice()}</td>
                        <td class="pricingRow" id="PriceTotal"><div class="roundPriceId">${game.getPrice()}</div></td>
                    </tr>
                </table>
            </div>
        </div>

        <div class="card">
            <div class="accordion" id="accordionDetail">   
                <div class="card text-center">
                    <div class="card-header" id="cardHeaderOne">
                        <ul class="nav nav-tabs card-header-tabs">
                            <li class="nav-item">
                                <a class="nav-link active" data-toggle="tab" href="#collapseBeschreibung" role="button" aria-expanded="false" aria-controls="headingBeschreibung">Beschreibung</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#collapseInformationen" role="button" aria-expanded="false" aria-controls="headingInformation">Informationen</a>
                            </li>
                            <c:choose>
                                <c:when test="${game.getRequirements().getPlatformWindows() eq 1}">
                                    <li class="nav-item">
                                        <a class="nav-link" data-toggle="tab" href="#collapseWindowsReqs" role="button" aria-expanded="false" aria-controls="headingWindowsReqs">Systemvorraussetzungen-Windows</a>
                                    </li>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${game.getRequirements().getPlatformLinux() eq 1}">
                                    <li class="nav-item">
                                        <a class="nav-link" data-toggle="tab" href="#collapseLinuxReqs" role="button" aria-expanded="false" aria-controls="headingLinuxReqs">Systemvorraussetzungen-Linux</a>
                                    </li>
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${game.getRequirements().getPlatformMac() eq 1}">
                                    <li class="nav-item">
                                        <a class="nav-link" data-toggle="tab" href="#collapseMacReqs" role="button" aria-expanded="false" aria-controls="headingMacReqs">Systemvorraussetzungen-Mac</a>
                                    </li>
                                </c:when>
                            </c:choose>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="card-body tab-content" id="cardBodyOne">
                <div id="collapseBeschreibung" class="tab-pane fade-in active" aria-labelledby="headingBeschreibung" data-patrent="#accordion">
                    <div class="card-body">
                        ${game.getDescription()}
                    </div>
                </div>
                <div id="collapseInformationen" class="tab-pane fade" aria-labelledby="headingInformationen" data-parent="#accordion">
                    <div class="card-body">
                        Erscheinungsjahr: ${game.getReleaseYear()}
                        <br>
                        Sprachen: ${game.getSprache()}
                        <br>
                        Genre: ${genres}
                        <br>
                        Kategorien: ${categories}
                    </div>
                </div>
                <div id="collapseWindowsReqs" class="tab-pane fade" aria-labelledby="headingWindowsReqs" data-patrent="#accordion">
                    <div class="card-body">
                        <c:choose>
                            <c:when test="${game.getRequirements().getHaveMinPcReqs() eq 1}">
                                Minimum:
                                <br>
                                ${game.getRequirements().getPCMinReqsText()}
                                <br>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${game.getRequirements().getHaveRecPcReqs() eq 1}">
                                Empfohlen:
                                <br>
                                ${game.getRequirements().getPCRecReqsText()}
                            </c:when>
                        </c:choose>
                    </div>
                </div>
                <div id="collapseLinuxReqs" class="tab-pane fade" aria-labelledby="headingLinuxReqs" data-patrent="#accordion">
                    <div class="card-body">
                        <c:choose>
                            <c:when test="${game.getRequirements().getHaveMinLinuxReqs() eq 1}">
                                Minimum:
                                <br>
                                ${game.getRequirements().getLinuxMinReqsText()}
                                <br>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${game.getRequirements().getHaveRecLinuxReqs() eq 1}">
                                Empfohlen:
                                <br>
                                ${game.getRequirements().getLinuxRecReqsText()}
                            </c:when>
                        </c:choose>
                    </div>
                </div>
                <div id="collapseMacReqs" class="tab-pane fade" aria-labelledby="headingMacReqs" data-patrent="#accordion">
                    <div class="card-body">
                        <c:choose>
                            <c:when test="${game.getRequirements().getHaveMinMacReqs() eq 1}">
                                Minimum:
                                <br>
                                ${game.getRequirements().getMacMinReqsText()}
                                <br>
                            </c:when>
                        </c:choose>
                        <c:choose>
                            <c:when test="${game.getRequirements().getHaveRecMacReqs() eq 1}">
                                Empfohlen:
                                <br>
                                ${game.getRequirements().getMacRecReqsText()}
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </jsp:attribute>
</template:base>
