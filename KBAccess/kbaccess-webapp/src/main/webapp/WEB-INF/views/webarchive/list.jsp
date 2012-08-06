<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="fr">
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <div class="container">
            <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>

            <h1 class="page-header">${webarchiveListH1}</h1>
            <div class="row">
                <%@include file='/WEB-INF/template/webarchive-list.jspf'%>
            </div>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>