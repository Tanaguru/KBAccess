<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <c:set var="title">
	<fmt:message key="admin.editUserTitle" />
    </c:set>
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
            <%@include file="/WEB-INF/template/block/account-details.jspf" %>
        </c:if>
        <c:if test="${accountCommand != null}">
            <div class="row-fluid">
                <h2><fmt:message key="admin.editUserEditInfos" /></h2>
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
                                <label class="control-label" for="account_last_name"><fmt:message key="guest.subscribeLastName" /> :</label>
                                <div class="controls">
                                    <form:input path="lastName" id="account_last_name" maxlength="30"/>
                                    <form:errors path="lastName" cssClass="alert alert-error" element="p"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="account_first_name"><fmt:message key="guest.subscribeFirstName" /> :</label>
                                <div class="controls">
                                    <form:input path="firstName" id="account_first_name" maxlength="30"/>
                                    <form:errors path="firstName" cssClass="alert alert-error" element="p"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="account_url"><fmt:message key="websiteUrl" /> :</label>
                                <div class="controls">
                                    <form:input path="url" id="account_url"/>
                                    <form:errors path="url" cssClass="alert alert-error" element="p"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="account_access_level"><fmt:message key="admin.usersRole" />:</label>
                                <div class="controls">
                                    <form:select path="accessLevelId" id="account_access_level" size="1">
                                        <%@include file="/WEB-INF/template/form/options/roles.jspf" %>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-actions">
                                <button class="btn btn-primary"><fmt:message key="admin.editUserButton" /></button>
                            </div>
                       </form:form>
                </div>
            </div>
        </c:if>
            
        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
