<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <%-- set title --%>
    <c:set var="title" value="Activation du compte" />
    <c:if test="${not empty activateAccountError}">
        <c:set var="title" value="Erreur lors de l'activation du compte"/>
    </c:if>
    <%-- include html header --%>
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>

        <div class="page-header"><h1>Activation du compte</h1></div>
        <div class="row-fluid">
            <p class="alert alert-success">
                Votre compte a bien été activé.
                Vous pouvez désormais vous identifier sur la
                <a href="login.html">page d'authentification</a>.
            </p>
        </div>                    
            
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
