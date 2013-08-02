<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title">
	<fmt:message key="guest.activateTitle" />
    </c:set>
    
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

         <c:set var="bcAccountActivation" scope="page"><fmt:message key="breadcrumb.accountActivation" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcAccountActivation}" value=""/>
        <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>

        <div class="page-header"><h1><fmt:message key="guest.activateH1" /></h1></div>
        <div class="row-fluid">
            <c:choose>
                <c:when test="${errorMessage == true}">
                    <p class="alert alert-error">
                        <fmt:message key="guest.tokenExpired" />
                    </p>
                    <p class="alert alert-info">
                        <fmt:message key="guest.newTokenSent" />
                    </p>
                </c:when>
                <c:otherwise>
                    <p class="alert alert-success">
                        <fmt:message key="guest.activateSuccess" />
                    </p>
                </c:otherwise>
            </c:choose> 
        </div>                    
            
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
