<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title">
	<fmt:message key="testcase.searchTitle" />
    </c:set>
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <c:set var="bcSearchExamples" scope="page"><fmt:message key="breadcrumb.searchExamples" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcSearchExamples}" value=""/>
        
        <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>
        
        <h1 class="page-header"><fmt:message key="testcase.searchH1" /></h1>
        <div class="row-fluid">
            <%@include file='/WEB-INF/template/select-form.jspf'%>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>