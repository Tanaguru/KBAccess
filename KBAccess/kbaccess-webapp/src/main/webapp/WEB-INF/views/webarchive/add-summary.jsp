<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="fr">
    <c:set var="title">
        <fmt:message key="webarchive.addSummaryTitle" />
    </c:set>
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <div class="container-fluid">
            <c:set var="bcAddWebarchive" scope="page"><fmt:message key="breadcrumb.addWebarchive" /></c:set>
            <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
            <c:set target="${breadcrumbTrail}" property="${bcAddWebarchive}" value=""/>
        
            <%@include file='/WEB-INF/template/breadcrumb-trail.jspf' %>

            <div class="page-header"><h1><fmt:message key="webarchive.addSummaryH1" /></h1></div>

            <div class="row-fluid">
                <p>
                    <fmt:message key="webarchive.addSummaryInfos1" />
                    ${webarchive.url}
                    <fmt:message key="webarchive.addSummaryInfos2" />
                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${webarchive.creationDate}"/>
                    <fmt:message key="webarchive.addSummaryInfos3" />
                </p>
                <p>
                    <a href="${webarchive.localUrl}">${webarchive.localUrl}</a>
                </p>
                <p><fmt:message key="webarchive.addSummaryNoteCreation" /></p>
                <p><a href="<c:url value='/'/>"><fmt:message key="backToHome" /></a></p>
            </div>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
