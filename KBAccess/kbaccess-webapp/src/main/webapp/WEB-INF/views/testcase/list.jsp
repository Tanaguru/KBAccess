<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="${testcaseListTitle}" />
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>

        <h1 class="page-header">${testcaseListH1}</h1>
        <div class="row-fluid">
            <%@include file='/WEB-INF/template/testcase-list.jspf'%>
            <c:if test="${showTestcaseSearchForm}">
                <%@include file='/WEB-INF/template/select-form.jspf'%>
            </c:if>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>