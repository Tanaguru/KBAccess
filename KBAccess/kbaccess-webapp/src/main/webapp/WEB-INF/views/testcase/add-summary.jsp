<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <c:set var="title">
	<fmt:message key="testcase.addSummaryTitle" />
    </c:set>
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <c:set var="bcAddExampleStep3" scope="page"><fmt:message key="breadcrumb.addExampleStep3" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcAddExampleStep3}" value=""/>
        
        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>
        
        <c:set var="testcaseUrl">
                <%@include file="/WEB-INF/template/inline/testcase-url.jspf" %>
        </c:set>
        
        <div class="page-header"><h1><fmt:message key="testcase.addSummaryH1" /></h1></div>
        <div class="row well">
            <p><fmt:message key="testcase.addSummarySummary" /></p>
            <ul>
                <c:forEach var="testResult" items="${testcase.testResults}">  
                    <li>Test :        
                        <a href="<c:url value='http://www.braillenet.org/accessibilite/referentiel-aw21/liste-deploye.php#test-${testcase.webRefTestLabel}'/>">${testResult.testLabel}</a>
                        <a href="<c:url value='http://www.braillenet.org/accessibilite/referentiel-aw21/'/>">(${testcase.referenceLabel})</a>
                    </li>
                    <li><fmt:message key="result" /> :
                        <c:set var="resultId" value="${testResult.resultId}"/>
                        <c:set var="pictoSize" value="s"/>
                        <%@include file="/WEB-INF/template/inline/result-picto.jspf" %>
                        <%@include file="/WEB-INF/template/inline/result.jspf" %>
                    </li>
                    </c:forEach>
            </ul>
            <ul>
                <li>URL :
                    <a href="${testcase.webarchiveLocalUrl}" rel="nofollow"><fmt:message key="testcase.addSummaryArchiveOf" /> ${testcase.webarchiveOriginalUrl}</a>
                    <a title="${testcase.webarchiveOriginalUrl}" href="${testcase.webarchiveOriginalUrl}">
                        <img id="originalUrl-link-img" src="<c:url value='/assets/images/window-duplicate.png'/>" alt="${testcase.webarchiveOriginalUrl}"/>
                    </a>
                    <p><fmt:message key="webarchive.addSummaryNote" />.</p>
                </li>
                <c:if test="${not empty testcase.description}">
                    <li><fmt:message key="description" /> ${testcase.description}</li>
                </c:if>
            </ul>
            <p>
                <a href="<c:url value='/example/details/${testcase.id}/${testcaseUrl}'/>"><fmt:message key="testcase.addSummaryVisualize" /></a>
                <fmt:message key="testcase.addSummaryOrGoBack" />
            </p>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>