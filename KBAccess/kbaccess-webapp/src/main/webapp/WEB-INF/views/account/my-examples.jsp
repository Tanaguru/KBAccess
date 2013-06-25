<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="fr">
    <c:set var="title">
        <fmt:message key="account.myExamplesTitle" />
    </c:set>
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <c:set var="bcMyExamples" scope="page"><fmt:message key="breadcrumb.myExamples" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcMyExamples}" value=""/>
        
        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>

        <h1 class="page-header"><fmt:message key="account.myExamplesH1" /></h1>
        <div class="row-fluid">
            <%@include file='/WEB-INF/template/testcase-list.jspf'%>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>