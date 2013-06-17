<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="KBAccess - La base de connaissance des bons et mauvais exemples d'accessibilité"/>
    <%@include file='/WEB-INF/template/head.jspf' %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <div class="row-fluid hero-unit span12">
            <h1>
                KBAccess, la base de connaissance de <span class="bold">bons</span> et <span class="bold">mauvais</span>
                exemples <span class="bold">d'accessibilité</span>.
            </h1>
            <p>
                Pour un web plus accessible. <a href="contribute.html">Contribuez !</a>
            <p>
        </div>

         <div class="row-fluid statistics-div">
            <div class="span3">
                <h2>Statistiques</h2>
                <ul id="statistics-list" class="unstyled">
                    <li>
                        <span id="stat-testcase-count">${statistics.testcaseCount}</span> Exemple<c:if test="${statistics.testcaseCount > 1}">s</c:if>
                    </li>
                    <li>
                        <span id="stat-webarchive-count">${statistics.webarchiveCount}</span> Web Archive<c:if test="${statistics.webarchiveCount > 1}">s</c:if>
                    </li>
                    <li>
                        <span id="stat-reference-count">${statistics.frameOfReferenceCount}</span> Référentiel<c:if test="${statistics.frameOfReferenceCount > 1}">s</c:if>
                    </li>
                    <li>
                        <span id="stat-user-count">${statistics.userCount}</span> Contributeur<c:if test="${statistics.userCount > 1}">s</c:if>
                    </li>
                </ul>
            </div>
            <div class="span3">
                <h2>Contributeurs les plus actifs</h2>
                <c:set var="accountStatistics" value="${statistics.bestContributors}"/>
                <%@include file="/WEB-INF/template/block/account-statistics.jspf" %>
            </div>    
            <div class="span3">
                <h2>Critères les plus fournis</h2>
                <c:set var="criterionStatistics" value="${statistics.mostReferencedCriterion}"/>
                <%@include file="/WEB-INF/template/block/criterion-statistics.jspf" %>
            </div>
            <div class="span3">
                <h2>Critères les moins fournis</h2>
                <c:set var="criterionStatistics" value="${statistics.leastReferencedCriterion}"/>
                <%@include file="/WEB-INF/template/block/criterion-statistics.jspf" %>
            </div>
        </div> 

        <div class="row-fluid">
            <h2>Les derniers exemples</h2>
            
            <c:set var="isHome" value="${true}"/>
            <%@include file='/WEB-INF/template/testcase-list.jspf' %>
        </div>
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>