<%@ page pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:set var="title">
        <fmt:message key="legal.pageTitle"/>
    </c:set>
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <c:set var="bcLegalNotice" scope="page"><fmt:message key="breadcrumb.legalNotice" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcLegalNotice}" value=""/>
        
        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf' %>

        <div class="page-header"><h1><fmt:message key="legal.h1"/></h1></div>
        <div class="row-fluid">
            <h2><fmt:message key="legal.editor"/></h2>
            <address>
                <acronym title="Société par Actions Simplifiés">SAS</acronym> <em>Open-S</em> <br />
                <br />
                Cap Omega - <acronym title="Course Spéciale">CS</acronym> 39521<br />
                Rond point Benjamin Franklin<br />
                34960 Montpellier Cedex 2 FRANCE
            </address>
            <p>
                <fmt:message key="legal.phone"/>: +33 (0) 972 11 26 06<br/>
                <fmt:message key="legal.email"/>: <a href="mailto:&#111;&#112;&#101;&#110;&#045;&#115;&#064;&#111;&#112;&#101;&#110;&#045;&#115;&#046;&#099;&#111;&#109;">&#111;&#112;&#101;&#110;&#045;&#115;&#064;&#111;&#112;&#101;&#110;&#045;&#115;&#046;&#099;&#111;&#109;</a>
            </p>
        </div>
        <div class="row-fluid">
            <h2><fmt:message key="legal.director"/></h2>
            <p>Matthieu Faure</p>
        </div>
        <div class="row-fluid">
            <h2><fmt:message key="legal.hosting"/></h2>
            <p>OVH - 2 rue Kellermann 59100 Roubaix France</p>
        </div>

        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>