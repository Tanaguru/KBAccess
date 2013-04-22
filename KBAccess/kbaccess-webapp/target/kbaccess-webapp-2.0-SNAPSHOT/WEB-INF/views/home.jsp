<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">

    <c:set var="title" value="KBAccess"/>
    <%@include file='/WEB-INF/template/head.jspf' %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        <div class="container-fluid">
            <div class="page-header">
                 <h1>KBAccess</h1>
            </div>
            <div class="row-fluid">
                <p>Bienvenue sur le site de KBAccess, la base de connaissance des bons et mauvais exemples d'accessibilité.</p>
            </div>

             <div class="row-fluid">
                 <h2>Dernières statistiques</h2>
                <div class="span4">
                    <ul id="statistics-list" class="unstyled">
                        <li><span id="stat-testcase-count">${statistics.testcaseCount}</span> Testcases</li>
                        <li><span id="stat-webarchive-count">${statistics.webarchiveCount}</span> Web Archives</li>
                        <li><span id="stat-reference-count">${statistics.frameOfReferenceCount}</span> Référentiels</li>
                        <li><span id="stat-user-count">${statistics.userCount}</span> Utilisateurs</li>
                    </ul>
                </div>
                <div class="span3">
                    <h3>Critères les plus fournis</h3>
                    <c:set var="criterionStatistics" value="${statistics.mostReferencedCriterion}"/>
                    <%@include file="/WEB-INF/template/block/criterion-statistics.jspf" %>
                </div>
                <div class="span3">
                    <h3>Critères les moins fournis</h3>
                    <c:set var="criterionStatistics" value="${statistics.leastReferencedCriterion}"/>
                    <%@include file="/WEB-INF/template/block/criterion-statistics.jspf" %>
                </div>
            </div> 

            <div class="row-fluid">
                <h2>Les 5 derniers Testcases</h2>
                <%@include file='/WEB-INF/template/testcase-list.jspf' %>
            </div>
        </div>        
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>