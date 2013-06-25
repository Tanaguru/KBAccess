 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title">
	<fmt:message key="guest.subscribeTitle" />
    </c:set>
    
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <c:set var="bcSignUp" scope="page"><fmt:message key="breadcrumb.signUp" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcSignUp}" value=""/>
        <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>

        <div class="page-header"><h1><fmt:message key="guest.subscribeH1" /></h1></div>
        <div class="row-fluid">
            <c:choose>
                <c:when test="${newAccountCreated}">
                    <p class="alert alert-success">
                        <fmt:message key="guest.subscribeSuccess" />
                    </p>
                </c:when>
                <c:when test="${not empty subscribeError}">
                    <p class="alert alert-error"><fmt:message key="${subscribeError}"/></p>
                </c:when>
                <c:otherwise>
                    <c:url var='subscribeUrl' value='/guest/subscribe.html'/>
                    
                    <form:form commandName="newAccountCommand" 
                            action="${subscribeUrl}"
                            id="subscribe-form"
                            method="POST"
                            class="form-horizontal">
                        <spring:hasBindErrors name="newAccountCommand">
                            <form:errors path="generalErrorMessage" cssClass="alert alert-error" element="p"/>
                        </spring:hasBindErrors>
                        <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                        <div class="control-group">
                            <label class="control-label" for="subscription_email"><%@include file="/WEB-INF/template/inline/mandatory.jspf" %> Email :</label>
                            <div class="controls">
                                <form:input path="email" id="subscription_email" cssErrorClass="validation-error"/>
                                <form:errors path="email" cssClass="alert alert-error" element="span"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="subscription_password"><%@include file="/WEB-INF/template/inline/mandatory.jspf"%> <fmt:message key="password" /> :</label>
                            <div class="controls">
                                <form:password path="password" id="subscription_password" cssErrorClass="validation-error"/>
                                <form:errors path="password" cssClass="alert alert-error" element="span"/>
                                <p class="label-indication"><fmt:message key="passwordIndication" /></p>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="subscription_password_confirmation">
                                <span class="mandatory" title="Champs obligatoire">*</span>
                                <fmt:message key="passwordConfirmation" /> :
                            </label>
                            <div class="controls">
                                <form:password path="passwordConfirmation" id="subscription_password_confirmation" cssErrorClass="validation-error"/>
                                <form:errors path="passwordConfirmation" cssClass="alert alert-error" element="span"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="subscription_lastname"><fmt:message key="guest.subscribeLastName" /> :</label>
                            <div class="controls">
                                <form:input path="lastName" id="subscription_lastname" maxlength="30" cssErrorClass="validation-error"/>
                                <form:errors path="lastName" cssClass="alert alert-error" element="span"/>         
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="subscription_firstname"><fmt:message key="guest.subscribeFirstName" /> :</label>
                            <div class="controls">
                                <form:input path="firstName" id="subscription_firstname" maxlength="30" cssErrorClass="validation-error"/>
                                <form:errors path="firstName" cssClass="alert alert-error" element="span"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="subscription_url"><fmt:message key="websiteUrl" /> :</label>
                            <div class="controls">
                                <form:input path="url" id="subscription_url" cssErrorClass="validation-error"/>
                                <form:errors path="url" cssClass="alert alert-error" element="span"/>
                            </div>
                        </div>
                        <div class="form-actions">
                            <button class="btn btn-primary"><fmt:message key="guest.subscribeButton" /></button>
                        </div>
                    </form:form>
                </c:otherwise>
            </c:choose>
        </div>
            
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
