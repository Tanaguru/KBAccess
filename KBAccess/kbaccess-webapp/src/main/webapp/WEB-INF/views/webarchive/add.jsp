<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title">
        <fmt:message key="webarchive.addTitle" />
    </c:set>
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file="/WEB-INF/template/header.jspf" %>
        
        <c:set var="bcAddWebarchive" scope="page"><fmt:message key="breadcrumb.addWebarchive" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcAddWebarchive}" value=""/>
        
        <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>

        <div class="page-header"><h1><fmt:message key="webarchive.addH1" /></h1></div>

        <div class="row-fluid">
            <div id="ajout-webarchive-1" class="boite">
                <form:form  commandName="newWebarchiveCommand" action="add.html" method="POST">
                    <c:if test="${generalErrorMessage != null}">
                        <p class="alert alert-error">
                            <fmt:message key="${generalErrorMessage}" />
                        </p>
                    </c:if>
                    <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                    <div class="control-group">
                        <label class="control-label" for="webpage_url"><%@include file="/WEB-INF/template/inline/mandatory.jspf"%> URL&nbsp;:</label>
                        <div class="controls">
                            <form:input id="webpage_url" path="url" />
                            <form:errors path="url" cssClass="alert alert-error" element="p"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="webpage_description"><fmt:message key="description" />&nbsp;:</label>
                        <div class="controls">
                            <form:textarea id="webpage_description" path="descriptionNewWebarchive" rows="4" cols="35" />
                            <form:errors path="descriptionNewWebarchive" cssClass="alert alert-error" element="p"/>
                        </div>
                    </div>
                    <div class="form-actions">
                        <button class="btn btn-primary"><fmt:message key="webarchive.archive" /></button>
                    </div>
                </form:form>
            </div>
        </div>
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
