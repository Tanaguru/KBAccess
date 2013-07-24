<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title">
        <fmt:message key="testcase.editDetailsTitle" /> ${testcase.testcaseId}
    </c:set>
    <%@include file="/WEB-INF/template/head.jspf" %>
    <body>
        <%@include file="/WEB-INF/template/header.jspf" %>
        
        <c:set var="bcEditDetailsOfExample" scope="page"><fmt:message key="breadcrumb.editDetailsOfExample" /> ${editTestcaseCommand.id}</c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcEditDetailsOfExample}" value=""/>
        
        <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>

        <div class="page-header">
            <h1><fmt:message key="testcase.editDetailsH1" /><%@include file="/WEB-INF/template/block/testcase-h1.jspf" %></h1>
        </div>

        <c:choose>
            <c:when test="${not empty errorMessage}">
                <div class="row-fluid">
                    <p class="alert alert-error">${errorMessage}</p>
                </div>
            </c:when>
            <c:otherwise>
                <div class="row-fluid">
                    <div id="modification-testcase-1" class="boite">
                        <form:form commandName="editTestcaseCommand" action="edit-details.html" method="POST">
                            <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                            <form:hidden path="id" />
                            <div class="control-group">
                                <label class="control-label" for="testcase_test"><%@include file="/WEB-INF/template/inline/mandatory.jspf"%> Test&nbsp;:</label>
                                <div class="controls">
                                    <form:select path="idReferenceTest" size="5" id="testcase_test">
                                        <%@include file="/WEB-INF/template/form/options/test-ids.jspf" %>
                                    </form:select>           
                                    <form:errors path="idReferenceTest" cssClass="alert alert-error" element="p"/>
                                </div>
                            </div>.
                            <div class="control-group">
                                <label class="control-label" for="testcase_result"><%@include file="/WEB-INF/template/inline/mandatory.jspf"%> <fmt:message key="result" />&nbsp;:</label>
                                <div class="controls">
                                    <form:select path="idResult"  id="testcase_result" size="5">
                                        <%@include file="/WEB-INF/template/form/options/result-ids.jspf" %>
                                    </form:select>
                                    <form:errors path="idResult" cssClass="alert alert-error" element="p"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label for="testcase_description"><fmt:message key="description" /> :</label>
                                <div class="controls">
                                    <form:textarea path="description" id="testcase_description" rows="4" cols="35" />      
                                    <form:errors path="description" cssClass="alert alert-error" element="p"/>
                                </div>
                            </div>
                            <div class="form-actions">
                                <button class="btn btn-primary"><fmt:message key="testcase.detailsEdit" /></button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
