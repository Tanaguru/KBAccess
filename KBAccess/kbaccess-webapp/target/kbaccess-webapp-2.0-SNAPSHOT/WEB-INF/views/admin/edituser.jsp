<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="Modification de l'utilisateur ${account.id}" />
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@ include file='/WEB-INF/template/header.jspf' %>
        
        <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>
        <c:if test="${not empty successMessage}">
                <div class="row-fluid">
                    <p class="alert alert-success">${successMessage}</p>
                </div>
        </c:if>
        <c:if test="${not empty errorMessage}">
                <div class="row-fluid">
                    <p class="alert alert-error">${errorMessage}</p>
                </div>
        </c:if>
        <c:if test="${not empty account}">
            <div class="page-header"><h1>Utilisateur ${account.displayedName}</h1></div>
            <div class="row-fluid">
                <!--<div class="span2"><img src="" alt="Avatar"/></div>-->
                <div class="span12">
                    <ul class="unstyled">
                        <li>${account.nbCreatedTestcases} testcases créés</li>
                        <li>${account.nbCreatedWebarchives} webarchives créées</li>
                        <li>inscrit le <fmt:formatDate pattern="dd/MM/yyyy" value="${account.subscriptionDate}"/></li>
                        <li><a href="${account.myUrl}">${account.myUrl}</a></li>
                        <%--<li>dernière activité le <fmt:formatDate pattern="dd/MM/yyyy" value="${account.lastOperationDate}"/></li>--%>
                    </ul>
                </div>
            </div>
        </c:if>
        <c:if test="${accountCommand != null}">
            <div class="row-fluid">
                <h2>Modification des informations personnelles</h2>
                <spring:hasBindErrors name="accountCommand">
                    <form:errors path="generalErrorMessage" cssClass="alert alert-error" element="p"/>         
                </spring:hasBindErrors>
                    <div class="boite">
                        <form:form action="edituser.html"
                                commandName="accountCommand"
                                method="POST"
                                class="form-horizontal">
                            <div class="control-group">       
                                <div class="controls">
                                    <form:hidden path="accountId"/>                        
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="account_email">Email :</label>
                                <div class="controls">
                                    <form:input path="email" id="account_email"/>
                                    <form:errors path="email" cssClass="alert alert-error" element="p"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="account_password">Mot de passe :</label>
                                <div class="controls">
                                    <form:password path="password" id="account_password"/>
                                    <form:errors path="password" cssClass="alert alert-error" element="p"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="account_password_confirmation">Mot de passe (confirmation) :</label>
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
                            <div class="control-group">
                                <label class="control-label" for="account_access_level">Rôle :</label>
                                <div class="controls">
                                    <form:select path="accessLevelId" id="account_access_level" size="1">
                                        <%@include file="/WEB-INF/template/form/options/roles.jspf" %>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-actions">
                                <button class="btn btn-primary">Modifier</button>
                            </div>
                       </form:form>
                </div>
            </div>
        </c:if>
            
        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
