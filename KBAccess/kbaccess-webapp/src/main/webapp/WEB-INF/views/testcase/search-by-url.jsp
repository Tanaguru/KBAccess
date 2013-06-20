<%@page pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <c:set var="title">
	<fmt:message key="testcase.searchUrlTitle" />
    </c:set>
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <c:set var="bcSearchExamplesByUrl" scope="page"><fmt:message key="breadcrumb.searchExamplesByUrl" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcSearchExamplesByUrl}" value=""/>
        
        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf' %>

        <div class="page-header"><h1><fmt:message key="testcase.searchUrlTitle" /></h1></div>

        <div class="row-fluid">
            <fmt:message key="testcase.searchUrlNotice" />
            <dl>
                <dt><fmt:message key="testcase.searchUrlByTest" /></dt>
                <dd>
                    <p>
                    <code>http://www.kbaccess.org/<span style="font-weight: bold;">kba</span><fmt:message key="testcase.searchUrlByTestRequest" /></code><br/>
                    <fmt:message key="example" /> : <a href="http://www.kbaccess.org/kba/AW21/1.1.1/">http://www.kbaccess.org/kba/AW21/1.1.1/</a>
                    </p>
                </dd>
                <dt><fmt:message key="testcase.searchUrlByResult" /></dt>
                <dd>
                    <p>
                    <code>http://www.kbaccess.org/<span style="font-weight: bold;">kba</span><fmt:message key="testcase.searchUrlByResultRequest" /></code><br/>
                    <fmt:message key="example" /> : <a href="http://www.kbaccess.org/kba/AW21/Failed/">http://www.kbaccess.org/kba/AW21/Failed/</a>
                    </p>
                </dd>
                <dt><fmt:message key="testcase.searchUrlByTestAndResult" /></dt>
                <dd>
                    <p>
                    <code>http://www.kbaccess.org/<span style="font-weight: bold;">kba</span><fmt:message key="testcase.searchUrlByTestAndResultRequest" /></code><br/>
                    <fmt:message key="example" /> : <a href="http://www.kbaccess.org/kba/AW21/1.1.1/Passed">http://www.kbaccess.org/kba/AW21/1.1.1/Passed/</a>
                    </p>
                </dd>
            </dl>

        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>