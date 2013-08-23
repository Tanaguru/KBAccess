<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title">
        <fmt:message key="testcase.selectReferenceTitle" />
    </c:set>
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <c:set var="bcAddExampleStep1" scope="page"><fmt:message key="breadcrumb.addExampleStep1" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcAddExampleStep1}" value=""/>
        
        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>

        <h1 class="page-header">
            <fmt:message key="testcase.addExampleH1" /><br />
            <span id="add-example-h1-step"><fmt:message key="testcase.selectReferenceH1" /></span>
        </h1>
        <div class="row-fluid">
            <c:url var='selectReferenceUrl' value='/example/add.html'/>
            
            <form:form id="new-tc-form" commandName="selectReferenceCommand" action="${selectReferenceUrl}" method="POST">
                <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                
                <fieldset>
                    <legend class="new-tc-label">
                        <%@include file="/WEB-INF/template/inline/mandatory.jspf"%>&nbsp;<fmt:message key="accessibility.reference"/> :
                    </legend>

                    <div class="new-tc-block">
                            <c:forEach var="reference" items="${referenceList}">
                                <form:radiobutton id="reference${reference.id}" path="idReference" value="${reference.id}"/>
                                <label class="inline-label" for="reference${reference.id}">
                                    <abbr title="<fmt:message key="${reference.code}-abbr"/>">
                                        <fmt:message key="${reference.code}"/>
                                    </abbr>
                                </label>
                                <br />
                            </c:forEach>
                        <form:errors path="idReference" cssClass="alert alert-error" element="p"/>
                    </div>
                    <div class="form-actions">
                        <button class="btn btn-info"><fmt:message key="testcase.nextStep" /></button>
                    </div>
                </fieldset>
            </form:form>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>