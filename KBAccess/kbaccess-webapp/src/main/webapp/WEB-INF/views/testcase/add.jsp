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

        <h1 class="page-header"><fmt:message key="testcase.selectReferenceH1" /></h1>
        <div class="row-fluid">
            <c:url var='selectReferenceUrl' value='/example/add.html'/>
            
            <form:form id="new-tc-form" commandName="selectReferenceCommand" action="${selectReferenceUrl}" method="POST">
                <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                
                <label class="new-tc-label" for="id_reference">
                    <%@include file="/WEB-INF/template/inline/mandatory.jspf"%>&nbsp;<fmt:message key="accessibility.reference"/> :
                </label>
                
                <div class="new-tc-block">
                    <form:select path="idReference" id="id_reference" size="4">
                        <c:forEach var="reference" items="${referenceList}">
                            <option value="${reference.id}"><fmt:message key="${reference.code}"/></option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="idReference" cssClass="alert alert-error" element="p"/>
                </div>
                <div class="form-actions">
                    <button class="btn btn-info"><fmt:message key="testcase.confirmButton" /></button>
                </div>
            </form:form>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>