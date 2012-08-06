<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <div class="container">
            <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>

            <h1 class="page-header">${testcaseListH1}</h1>
            <div class="row">
                <%@include file='/WEB-INF/template/testcase-list.jspf'%>
                <c:if test="${showTestcaseSearchForm}">
                    <%@include file='/WEB-INF/template/select-form.jspf'%>
                </c:if>
            </div>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>