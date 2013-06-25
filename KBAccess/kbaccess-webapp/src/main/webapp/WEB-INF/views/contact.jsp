<%@ page pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="fr">
    <c:set var="title">
        <fmt:message key="contact.pageTitle"/>    
    </c:set>
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
            
        <c:set var="bcContact" scope="page"><fmt:message key="breadcrumb.contact" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcContact}" value=""/>
        
        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf' %>

        <div class="page-header"><h1><fmt:message key="contact.h1"/>   </h1></div>
        <div class="row-fluid">
            <p>
                <fmt:message key="contact.message" />
                <a href="mailto:&#116;&#101;&#097;&#109;&#064;&#107;&#098;&#097;&#099;&#099;&#101;&#115;&#115;&#046;&#111;&#114;&#103;">&#116;&#101;&#097;&#109;&#064;&#107;&#098;&#097;&#099;&#099;&#101;&#115;&#115;&#046;&#111;&#114;&#103;</a>
            </p>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>