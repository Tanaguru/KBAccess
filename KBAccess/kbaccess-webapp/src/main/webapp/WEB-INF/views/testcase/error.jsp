<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="Example error"/>
    <%@include file="/WEB-INF/template/head.jspf" %>
    <body>
        <%@include file="/WEB-INF/template/header.jspf" %>
        
        <c:set var="bcError" scope="page"><fmt:message key="error" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcError}" value=""/>
        
        <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>
        
        <div class="page-header">
            <h1><fmt:message key="error" /></h1>
        </div>
       
        <div class="row-fluid">
            <p class="alert alert-error">
                <c:choose>
                    <c:when test="${not empty errorMessage}">
                        <fmt:message key="${errorMessage}" />
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="error.message" />
                    </c:otherwise>
                </c:choose>
            </p>
        </div>
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
