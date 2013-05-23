<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="Recherche d'exemple" />
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>

        <h1 class="page-header">Recherche par choix</h1>
        <div class="row-fluid">
            <%@include file='/WEB-INF/template/select-form.jspf'%>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>