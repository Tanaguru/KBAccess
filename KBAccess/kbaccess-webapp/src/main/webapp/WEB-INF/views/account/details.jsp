<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title">
	<fmt:message key="account.detailsTitleProfileOf" /> ${account.displayedName}
        (<fmt:message key="account.detailsTitleUser" /> ${account.id})
    </c:set>
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@ include file='/WEB-INF/template/header.jspf' %>
        
        <c:set var="bcAccountDetails" scope="page"><fmt:message key="user" /> ${account.displayedName}</c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcAccountDetails}" value=""/>
        
        <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>
        <c:if test="${not empty successMessage}">
                <div class="row-fluid">
                    <p class="alert alert-success"><fmt:message key="${successMessage}"/></p>
                </div>
        </c:if>

        <%@include file="/WEB-INF/template/block/account-details.jspf" %>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
