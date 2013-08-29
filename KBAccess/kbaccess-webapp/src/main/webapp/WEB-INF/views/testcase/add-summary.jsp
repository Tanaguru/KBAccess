<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title">
	<fmt:message key="testcase.addSummaryTitle" />
    </c:set>
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <c:set var="bcAddExampleStep4" scope="page"><fmt:message key="breadcrumb.addExampleStep4" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcAddExampleStep4}" value=""/>
        
        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>
        
        <c:set var="testcaseUrl">
                <%@include file="/WEB-INF/template/inline/testcase-url.jspf" %>
        </c:set>
        
        <div class="page-header">
            <h1>
                <fmt:message key="testcase.addSummaryH1" />
            </h1>
        </div>
        <div class="row-fluid">
            <p class="alert alert-success">
                <c:choose>
                    <c:when test="${webarchiveCreation == true}">
                        <fmt:message key="webarchive.addSummaryNoteCreation" />
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="testcase.addSummaryExampleAvailable" />
                        <a href="<c:url value='/example/details/${testcase.testcaseId}/${testcaseUrl}'/>">
                            <fmt:message key="testcase.addSummaryVisualize" />
                        </a>
                    </c:otherwise>
                </c:choose>
            </p>
            <p>
                <a href="<c:url value="/example/add.html"/>">
                    <fmt:message key="testcase.addAnotherExample"/>
                </a>
                <fmt:message key="or"/>
                <a href="<c:url value="/example/search.html"/>">
                    <fmt:message key="testcase.searchExamples"/>
                </a>
            </p>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>