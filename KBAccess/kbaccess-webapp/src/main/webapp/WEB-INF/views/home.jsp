<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="fr">
    <c:set var="title">
        <fmt:message key="home.pageTitle"/>
    </c:set>
    <%@include file='/WEB-INF/template/head.jspf' %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <div class="row-fluid hero-unit span12">
            <h1>
                <fmt:message key="home.h1"/>
            </h1>
            <p>
                <fmt:message key="home.callToAction"/>
            <p>
        </div>

         <div class="row-fluid statistics-div">
            <div class="span3">
                <h2><fmt:message key="statistics"/></h2>
                <ul id="statistics-list" class="unstyled">
                    <li>
                        <a href="<c:url value='/example/list.html'/>">
                            <span id="stat-testcase-count">${statistics.testcaseCount}</span>
                            <fmt:message key="accessibility.example"/><c:if test="${statistics.testcaseCount > 1}">s</c:if>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value='/webarchive/list.html'/>">
                            <span id="stat-webarchive-count">${statistics.webarchiveCount}</span>
                            &nbsp;Web Archive<c:if test="${statistics.webarchiveCount > 1}">s</c:if>
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value='/account/list.html'/>">
                            <span id="stat-user-count">${statistics.userCount}</span>
                            <fmt:message key="contributor"/><c:if test="${statistics.userCount > 1}">s</c:if>
                        </a>
                    </li>
                    <li>
                        <span id="stat-reference-count">${statistics.frameOfReferenceCount}</span>  <fmt:message key="accessibility.reference"/><c:if test="${statistics.frameOfReferenceCount > 1}">s</c:if>
                    </li>
                </ul>
            </div>
            <div class="span3">
                <h2><fmt:message key="statistics.topContribs"/></h2>
                <c:set var="accountStatistics" value="${statistics.bestContributors}"/>
                <%@include file="/WEB-INF/template/block/account-statistics.jspf" %>
            </div>    
            <div class="span3">
                <h2><fmt:message key="statistics.mostFurnishedTests"/></h2>
                <c:set var="referenceTestStatistics" value="${statistics.mostFurnishedReferenceTest}"/>
                <%@include file="/WEB-INF/template/block/criterion-statistics.jspf" %>
            </div>
            <div class="span3">
                <h2><fmt:message key="statistics.leastFurnishedTests"/></h2>
                <c:set var="referenceTestStatistics" value="${statistics.leastFurnishedReferenceTest}"/>
                <%@include file="/WEB-INF/template/block/criterion-statistics.jspf" %>
            </div>
        </div> 

        <div class="row-fluid">
            <h2><fmt:message key="home.lastExamples"/></h2>
            
            <c:set var="isHome" value="${true}"/>
            <%@include file='/WEB-INF/template/testcase-list.jspf' %>
        </div>
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>