<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <c:set var="title">
        <fmt:message key="webarchive.listTitle" />
    </c:set>
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <c:set var="bcListOfWebarchives" scope="page"><fmt:message key="breadcrumb.listOfWebarchives" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcListOfWebarchives}" value=""/>
        
        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>

        <h1 class="page-header"><fmt:message key="webarchive.listH1" /></h1>
        <div class="row-fluid">
            <%@include file='/WEB-INF/template/webarchive-list.jspf'%>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>