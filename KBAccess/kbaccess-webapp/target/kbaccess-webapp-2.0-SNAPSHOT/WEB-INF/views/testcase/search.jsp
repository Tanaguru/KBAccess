<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="fr">
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <div class="container-fluid">
            <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>

            <h1 class="page-header">Recherche par choix</h1>
            <div class="row-fluid">
                <%@include file='/WEB-INF/template/select-form.jspf'%>
            </div>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>