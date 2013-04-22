<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <div class="container-fluid">
            <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>

            <div class="page-header"><h1>Ajout d'un testcase, étape 3/3 : Récapitulatif</h1></div>
            <div class="row well">
                <p>Recapitulatif du testcase : </p>
                <ul>
                    <li>Referentiel : ${testcase.criterion.reference.label}</li>
                    <li>Themathique : ${testcase.criterion.theme.label}</li>
                    <li>Critère : ${testcase.criterion.label}</li>
                    <li>Niveau : ${testcase.criterion.level.label}</li>
                </ul>
                <ul>
                    <li> Resultat : ${testcase.result.label}</li>
                    <c:if test="${not empty testcase.description}">
                        <li>Description : ${testcase.description}</li>
                    </c:if>
                    <li> Webarchive :  <a href="${testcase.webarchive.localUrl}">${testcase.webarchive.localUrl}</a>
                        <p>Votre webarchive a bien été créée, mais ne sera consultable <strong>que dans quelques minutes</strong>.</p>
                    </li>

                </ul>
                <p><a href="<c:url value='/testcase/details/${testcase.id}-.html'/>">Visualiser le testcase</a> ou <a href="<c:url value='/'/>">revenir à l'accueil</a>.</p>
            </div>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>