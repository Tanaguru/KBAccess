<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title">
	<fmt:message key="guest.lostPasswordTitle" />
    </c:set>
    <%@include file='/WEB-INF/template/head.jspf'%>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
         <c:set var="bcForgottenPassword" scope="page"><fmt:message key="breadcrumb.forgottenPassword" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcForgottenPassword}" value=""/>
        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf' %>

        <div class="page-header"><h1><fmt:message key="guest.lostPasswordH1" /></h1></div>
        <div class="row-fluid">
            <c:choose>
                <c:when test="${passwordSent}">
                    <p class="alert alert-success">
                        <fmt:message key="guest.lostPasswordSuccess" />
                    </p>                    
                </c:when>
                <c:when test="${passwordLostError != null}">
                    <p class="alert alert-error"><fmt:message key="${passwordLostError}"/></p>
                </c:when>
                <c:otherwise>
                    <p><fmt:message key="guest.lostPasswordInputEmail" /> :</p>
                    <form:form  class="form-horizontal" commandName="passwordLostCommand" action="password-lost.html" method="POST">
                        <div class="control-group">
                            <label class="control-label" for="password_lost_email">Email :</label>
                            <div class="controls">
                                <form:input path="email" id="password_lost_email"/>
                                <form:errors path="email" cssClass="alert alert-error" element="p"/>
                            </div>
                        </div>
                        <div class="form-actions">
                            <button class="btn btn-primary"><fmt:message key="guest.lostPasswordButton" /></button>
                        </div>
                    </form:form>
                </c:otherwise>
            </c:choose>
        </div>

        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
