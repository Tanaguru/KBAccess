<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="Mot de passe perdu" />
    <%@include file='/WEB-INF/template/head.jspf'%>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf' %>

        <div class="page-header"><h1>Demande d'un nouveau mot de passe</h1></div>
        <div class="row-fluid">
            <c:choose>
                <c:when test="${passwordSent}">
                    <p class="alert alert-success">
                        Vous allez recevoir un mail pour redéfinir votre mot de passe.
                    </p>                    
                </c:when>
                <c:when test="${passwordLostError != null}">
                    <p class="alert alert-error">${passwordLostError}</p>
                </c:when>
                <c:otherwise>
                    <p>Veuillez saisir votre adresse email :</p>
                    <form:form  class="form-horizontal" commandName="passwordLostCommand" action="password-lost.html" method="POST">
                        <div class="control-group">
                            <label class="control-label" for="password_lost_email">Email :</label>
                            <div class="controls">
                                <form:input path="email" id="password_lost_email"/>
                                <form:errors path="email" cssClass="alert alert-error" element="p"/>
                            </div>
                        </div>
                        <div class="form-actions">
                            <button class="btn btn-primary">Envoyer</button>
                        </div>
                    </form:form>
                </c:otherwise>
            </c:choose>
        </div>

        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
