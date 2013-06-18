<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <c:set var="title">
	<fmt:message key="testcase.searchUrlResultTitle" /> ${codeRef} ${codeTest} ${codeResult}
    </c:set>
    
    <%@include file="/WEB-INF/template/head.jspf" %>
    
    <body>
        <p>
            <fmt:message key="testcase.searchUrlResultTitle" /> :<br />
            <fmt:message key="accessibility.reference" /> : ${codeRef}<br />
            Test : ${codeTest}<br />
            <fmt:message key="result" /> : ${codeResult}<br />
            <fmt:message key="accessibility.level" /> : <fmt:message key="all" /><br />
            <c:choose>
                <c:when test="${errorMessage != null}">
                <h1><fmt:message key="error" /></h1>
                ${errorMessage}
                </c:when>
                <c:when test="${testcaseList == null or empty testcaseList}">
                    <fmt:message key="testcase.searchUrlResultNoResult" />
                </c:when>
                <c:otherwise>
                    <ul id="resultat">
                        <c:forEach var="testcase" items="${testcaseList}">
                            <%@include file="/WEB-INF/template/inline/url-search-result-li.jspf" %>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
        </p>
    </body>
</html>
