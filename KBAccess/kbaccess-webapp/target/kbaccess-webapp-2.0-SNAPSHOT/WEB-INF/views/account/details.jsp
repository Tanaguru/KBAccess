<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@ include file='/WEB-INF/template/header.jspf' %>
        
        <div class="container-fluid">
            <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>

            <div class="page-header"><h1>Utilisateur ${account.displayedName}</h1></div>
            <div class="row-fluid">
                <div class="span2"><img src="" alt="Avatar"/></div>
                <div class="offset2 span10">
                    <ul class="unstyled">
                        <li>${account.nbCreatedTestcases} testcases créés</li>
                        <li>${account.nbCreatedWebarchives} webarchives créées</li>
                        <li>inscrit le <fmt:formatDate pattern="dd/MM/yyyy" value="${account.subscriptionDate}"/></li>
                        <li>dernière activité le <fmt:formatDate pattern="dd/MM/yyyy" value="${account.lastOperationDate}"/></li>
                    </ul>
                </div>
            </div>
            <c:if test="${accountDetailsCommand != null}">
                <div class="row-fluid">
                    <h2>Modification des informations personnelles</h2>
                    <spring:hasBindErrors name="accountDetailsCommand">
                        <p class="erreur">Le formulaire contient des erreurs</p>  
                    </spring:hasBindErrors>
                    <div class="boite">
                        <c:if test="hasBeenModified">
                            <p class="alert alert-success">Votre compte a bien été modifié.</p>
                        </c:if>
                        <form:form action="details.html"
                                commandName="accountDetailsCommand"
                                method="POST"
                                class="form-horizontal">
                            <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                            <div class="control-group">
                                <label class="control-label" for="account_email"><%@include file="/WEB-INF/template/inline/mandatory.jspf" %> Email :</label>
                                <div class="controls">
                                    <form:input path="email" id="account_email"/>
                                    <form:errors path="email" cssClass="error"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="account_password"><%@include file="/WEB-INF/template/inline/mandatory.jspf" %> Mot de passe :</label>
                                <div class="controls">
                                    <form:password path="password" id="account_password"/>
                                    <form:errors path="password" cssClass="error"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="account_password_confirmation"><%@include file="/WEB-INF/template/inline/mandatory.jspf" %> Mot de passe (confirmation) :</label>
                                <div class="controls">
                                    <form:password path="passwordConfirmation" id="account_password_confirmation"/>	
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="account_last_name">Nom :</label>
                                <div class="controls">
                                    <form:input path="lastName" id="account_last_name"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="account_first_name">Prénom :</label>
                                <div class="controls">
                                    <form:input path="firstName" id="account_first_name"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="account_url">URL site web :</label>
                                <div class="controls">
                                    <form:input path="url" id="account_url"/>
                                    <form:errors path="url" cssClass="error"/>
                                </div>
                            </div>
                            <div class="form-actions">
                                <button class="btn btn-primary">Modifier</button>
                            </div>
                        </form:form>
                    </div>
                </c:if>
            </div>
        </div>
            
        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
