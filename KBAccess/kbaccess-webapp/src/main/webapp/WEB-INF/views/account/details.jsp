<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="Profile de l'utilisateur ${account.id}" />
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@ include file='/WEB-INF/template/header.jspf' %>
        
        <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>
        <c:if test="${not empty successMessage}">
                <div class="row-fluid">
                    <p class="alert alert-success">${successMessage}</p>
                </div>
        </c:if>

        <%@include file="/WEB-INF/template/block/account-details.jspf" %>

        <c:if test="${accountCommand != null}">
            <div class="row-fluid">
                <h2>Modification des informations personnelles</h2>
                <spring:hasBindErrors name="accountCommand">
                    <form:errors path="generalErrorMessage" cssClass="alert alert-error" element="p"/>         
                </spring:hasBindErrors>
                    <div class="boite">
                        <form:form action="my-account.html"
                                commandName="accountCommand"
                                method="POST"
                                class="form-horizontal">
                            <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                            <div class="control-group">
                                <label class="control-label" for="account_email">
                                    <%--<%@include file="/WEB-INF/template/inline/mandatory.jspf" %>--%>
                                    Email :
                                </label>
                                <div class="controls">
                                    <form:input path="email" id="account_email" disabled="${'true'}"/>
                                    <form:hidden path="email"/>
                                    <%--<form:errors path="email" cssClass="alert alert-error" element="p"/>--%>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="account_password"><%@include file="/WEB-INF/template/inline/mandatory.jspf" %> Mot de passe :</label>
                                <div class="controls">
                                    <form:password path="password" id="account_password"/>
                                    <form:errors path="password" cssClass="alert alert-error" element="p"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="account_password_confirmation"><%@include file="/WEB-INF/template/inline/mandatory.jspf" %> Mot de passe (confirmation) :</label>
                                <div class="controls">
                                    <form:password path="passwordConfirmation" id="account_password_confirmation"/>	
                                    <form:errors path="passwordConfirmation" cssClass="alert alert-error" element="p"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="account_last_name">Nom :</label>
                                <div class="controls">
                                    <form:input path="lastName" id="account_last_name" maxlength="30"/>
                                    <form:errors path="lastName" cssClass="alert alert-error" element="p"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="account_first_name">Prénom :</label>
                                <div class="controls">
                                    <form:input path="firstName" id="account_first_name" maxlength="30"/>
                                    <form:errors path="firstName" cssClass="alert alert-error" element="p"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="account_url">URL site web :</label>
                                <div class="controls">
                                    <form:input path="url" id="account_url"/>
                                    <form:errors path="url" cssClass="alert alert-error" element="p"/>
                                </div>
                            </div>
                            <div class="form-actions">
                                <button class="btn btn-primary">Modifier</button>
                            </div>
                       </form:form>
                </div>
            </c:if>
        </div>
            
        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
