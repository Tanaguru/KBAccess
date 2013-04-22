 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
    <%-- set title --%>
    <c:set var="title" value="Inscription - KBAccess" />
    <spring:hasBindErrors name="newAccountCommand">
        <c:set var="title" value="Inscription avec erreurs - KBAccess"/>
    </spring:hasBindErrors>
    <%-- include html header --%>
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <div class="container-fluid">
            <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>

            <div class="page-header"><h1>Inscription</h1></div>
            <div class="row-fluid">
                <c:choose>
                    <c:when test="${newAccountCreated}">
                        <p class="alert alert-success">
                            Votre compte a bien été créé.
                            Vous allez recevoir un mail de confirmation pour activer votre compte.
                        </p>
                    </c:when>
                    <c:when test="${not empty subscribeError}">
                        <p class="alert alert-error">${subscribeError}</p>
                    </c:when>
                    <c:otherwise>
                        <form:form commandName="newAccountCommand" 
                                action="subscribe.html"
                                method="POST"
                                class="form-horizontal">
                            <spring:hasBindErrors name="newAccountCommand">
                                <form:errors path="generalErrorMessage" cssClass="error" element="p"/>
                            </spring:hasBindErrors>
                            <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                            <div class="control-group">
                                <label class="control-label" for="subscription_email"><%@include file="/WEB-INF/template/inline/mandatory.jspf" %> Email :</label>
                                <div class="controls">
                                    <form:input path="email" id="subscription_email"/>
                                    <form:errors path="email" cssClass="alert alert-error" element="p"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="subscription_password"><%@include file="/WEB-INF/template/inline/mandatory.jspf"%> Mot de passe :</label>
                                <div class="controls">
                                    <form:password path="password" id="subscription_password"/>
                                    <form:errors path="password" cssClass="error"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="subscription_password_confirmation">
                                    <span class="mandatory" title="Champs obligatoire">*</span>
                                    Mot de passe (confirmation) :
                                </label>
                                <div class="controls">
                                    <form:password path="passwordConfirmation" id="subscription_password_confirmation"/>
                                    <form:errors path="passwordConfirmation" cssClass="error"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="subscription_lastname">Nom :</label>
                                <div class="controls">
                                    <form:input path="lastName" id="subscription_lastname"/>
                                    <form:errors path="lastName" cssClass="error"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="subscription_firstname">Prénom :</label>
                                <div class="controls">
                                    <form:input path="firstName" id="subscription_firstname"/>
                                    <form:errors path="firstName" cssClass="error"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="subscription_url">URL site web :</label>
                                <div class="controls">
                                    <form:input path="url" id="subscription_url" />
                                    <form:errors path="url" cssClass="error"/>
                                </div>
                            </div>
                            <div class="form-actions">
                                <button class="btn btn-primary">S'inscrire</button>
                            </div>
                        </form:form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
            
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
