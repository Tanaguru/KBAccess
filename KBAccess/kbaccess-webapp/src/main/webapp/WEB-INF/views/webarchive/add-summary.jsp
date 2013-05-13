<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="Récapitulatif de l'ajout de webarchive" />
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <div class="container-fluid">
            <%@include file='/WEB-INF/template/breadcrumb-trail.jspf' %>

            <div class="page-header"><h1>Synthèse de la webarchive</h1></div>

            <div class="row-fluid">
                <p>
                    Une archive de la page ${webarchive.url} 
                    a été créée à la date du ${webarchive.creationDate} et est accessible à l'Url suivante :
                </p>
                <p>
                    <a href="${webarchive.localUrl}">${webarchive.localUrl}</a>
                </p>
                <p>Votre webarchive a bien été créée, mais ne sera consultable <strong>que dans quelques minutes</strong>.</p>
                <p><a href="<c:url value='/'/>">Revenir à l'accueil</a></p>
            </div>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
