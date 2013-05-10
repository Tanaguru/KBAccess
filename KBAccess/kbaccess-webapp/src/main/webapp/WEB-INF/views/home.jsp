<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="KBAccess - La base de connaissance des bons et mauvais exemples d'accessibilité"/>
    <%@include file='/WEB-INF/template/head.jspf' %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <div class="row-fluid hero-unit span12">
            <h1 id="kbintro-p">
                KBAccess, la base de connaissance des <strong>bons</strong> et <strong>mauvais</strong> exemples <strong>d'accessibilité</strong>.
            </h1>
        </div>

         <div class="row-fluid">
            <div class="span4">
                <h2>Statistiques</h2>
                <ul id="statistics-list" class="unstyled">
                    <li><span id="stat-testcase-count">${statistics.testcaseCount}</span> Testcases</li>
                    <li><span id="stat-webarchive-count">${statistics.webarchiveCount}</span> Web Archives</li>
                    <li><span id="stat-reference-count">${statistics.frameOfReferenceCount}</span> Référentiels</li>
                    <li><span id="stat-user-count">${statistics.userCount}</span> Utilisateurs</li>
                </ul>
            </div>
            <div class="span4">
                <h2>Critères les plus fournis</h2>
                <c:set var="criterionStatistics" value="${statistics.mostReferencedCriterion}"/>
                <%@include file="/WEB-INF/template/block/criterion-statistics.jspf" %>
            </div>
            <div class="span4">
                <h2>Critères les moins fournis</h2>
                <c:set var="criterionStatistics" value="${statistics.leastReferencedCriterion}"/>
                <%@include file="/WEB-INF/template/block/criterion-statistics.jspf" %>
            </div>
        </div> 

        <div class="row-fluid">
            <h2>Les derniers Testcases</h2>
            
            <c:set var="isHome" value="${true}"/>
            <%@include file='/WEB-INF/template/testcase-list.jspf' %>
        </div>
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>