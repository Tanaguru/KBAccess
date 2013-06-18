<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <c:set var="title">
	<fmt:message key="guest.loginTitle" />
    </c:set>
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>

        <div class="page-header"><h1><fmt:message key="guest.loginH1" /></h1></div>
        <div class="row-fluid">
            <c:choose>
                <c:when test='${param.error == "errorOnLogin"}'>
                    <p class="alert alert-error">
                        <fmt:message key="guest.loginBadInfos" />
                    </p>
                </c:when>
                <c:when test='${param.error == "sessionTimeout"}'>
                    <p class="alert alert-error">
                        <fmt:message key="guest.loginExpiredSession" />
                    </p>
                </c:when>
            </c:choose>
            <form class="form-horizontal" action="<c:url value='/j_spring_security_check'/>" method="POST">
                <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                <div class="control-group">
                    <label class="control-label" for="login_email"><%@include file="/WEB-INF/template/inline/mandatory.jspf" %>Email :</label>
                    <div class="controls">
                        <input type="text" name="j_username" id="login_email"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="login_password"><%@include file="/WEB-INF/template/inline/mandatory.jspf" %><fmt:message key="password" /> :</label>
                    <div class="controls">
                        <input type="password" name="j_password" id="login_password"/>
                    </div>
                </div>
                <div class="form-actions">
                    <button class="btn btn-primary"><fmt:message key="guest.loginButton" /></button>
                </div>
            </form>
            <p class="alert alert-info"><a href="<c:url value='/guest/password-lost.html'/>"><fmt:message key="guest.loginLostPasswordLink" /></a></p>
        </div>
                        
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
