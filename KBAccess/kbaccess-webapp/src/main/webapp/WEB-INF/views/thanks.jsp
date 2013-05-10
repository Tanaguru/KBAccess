<%@ page pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="Remerciements" />
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

            <%@include file='/WEB-INF/template/breadcrumb-trail.jspf' %>

            <div class="page-header"><h1>Crédits et remerciements</h1></div>
            <div class="row-fluid">
                <p>Les gens qu'on a souhaité remercier publiquement:</p>
                <ul>
                    <li>Laura Laluque, David Audran et Vincent Jolicoeur pour les premières versions du code (2009)</li>
                    <li><a href="http://www.qelios.net/">Jean-Pierre Villain</a> et <a href="http://www.temesis.com/">Laurent Denis</a> pour leurs précieux retours</li>
                    <li>Toute l'équipe <a href="http://www.AccessiWeb.org/">AccessiWeb</a> pour leur confiance</li>
                    <li>Et surtout à tous les contributeurs de KBAccess !</li>
                </ul>
            </div>

        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>