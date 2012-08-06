<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">

    <%@include file='/WEB-INF/template/head.jspf'%>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <div class="container">
            <%@include file='/WEB-INF/template/breadcrumb-trail.jspf' %>

            <div class="page-header"><h1>Demande d'un nouveau mot de passe</h1></div>
            <div class="row">
                <c:choose>
                    <c:when test="${passwordSent}">
                        <p class="alert alert-error">Vous allez recevoir un mail contenant votre nouveau mot de passe.</p>                    
                    </c:when>
                    <c:when test="${passwordLostError != null}">
                        <p class="alert alert-error">${passwordLostError}</p>
                    </c:when>
                    <c:otherwise>
                        <p>Veuillez saisir votre adresse email et nous vous enverrons un nouveau mot de passe :</p>
                        <form:form  class="form-horizontal" commandName="passwordLostCommand" action="password-lost.html" method="POST">
                            <div class="control-group">
                                <label class="control-label" for="password_lost_email">Email :</label>
                                <div class="controls">
                                    <form:input path="email" id="password_lost_email"/>
                                    <form:errors path="email" cssClass="alert alert-error" element="p"/>
                                </div>
                            </div>
                            <div class="form-actions">
                                <button class="btn btn-primary">M'envoyer un nouveau mot de passe</button>
                            </div>
                        </form:form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
