<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title">
        <fmt:message key="testcase.addTitle" />
    </c:set>
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <c:set var="bcAddExampleStep2" scope="page"><fmt:message key="breadcrumb.addExampleStep2" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcAddExampleStep2}" value=""/>
        
        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>

        <h1 class="page-header"><fmt:message key="testcase.addH1" /></h1>
        <div class="row-fluid">
            <c:url var='addUrl' value='/example/add-details.html'/>
            
            <form:form id="new-testcase-form" commandName="newTestcaseCommand" action="${addUrl}" method="POST">
                <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                
                <form:hidden path="idReference" />
                
                <label class="tc-add-label"><%@include file="/WEB-INF/template/inline/mandatory.jspf"%><fmt:message key="testcase.elementToBeTested"/> : </label>
                <c:forEach var="referenceDepth" items="${referenceDepthList}" varStatus="status">
                    <form:radiobutton id="test${status.count}" path="idReferenceDepth" value="${referenceDepth.id}"/><fmt:message key="${referenceDepth.code}"/>
                </c:forEach>
                
                <%--<label class="tc-add-label" for="testcase_idtest"><%@include file="/WEB-INF/template/inline/mandatory.jspf"%>Test :</label>
                <form:select class="span5" path="idReferenceTest" id="testcase_idtest" size="5">
                    <%@include file="/WEB-INF/template/form/options/test-ids.jspf" %>
                </form:select>
                <form:errors path="idReferenceTest" cssClass="alert alert-error" element="p"/>--%>
                
                <c:forEach var="entry" items="${referenceTestMap}" varStatus="status">
                    <div id="test-div-${status.count}">
                        <label class="tc-add-label" for="testcase_idtest">
                            <%@include file="/WEB-INF/template/inline/mandatory.jspf"%>
                            <fmt:message key="${entry.key.code}"/> :
                        </label>
                        <form:select class="span5" path="idReferenceTest" id="testcase_idtest" size="5">
                            <c:set var="referenceTestList" value="${entry.value}"/>
                            <%@include file="/WEB-INF/template/form/options/test-ids.jspf" %>
                        </form:select>
                        <form:errors path="idReferenceTest" cssClass="alert alert-error" element="p"/>
                    </div>
                </c:forEach>
                
                <label class="tc-add-label" for="testcase_idresult"><%@include file="/WEB-INF/template/inline/mandatory.jspf"%><fmt:message key="result" /> :</label>
                <form:select class="span5" path="idResult" id="testcase_idresult" size="4">
                    <%@include file="/WEB-INF/template/form/options/result-ids.jspf" %>
                </form:select>
                <form:errors path="idResult" cssClass="alert alert-error" element="p"/>
                
                <label class="tc-add-label" for="testcase_description"><fmt:message key="description" /> :</label>
                <form:textarea class="span5" path="description" id="testcase_description" rows="4" cols="35"/>
                <form:errors path="description" cssClass="alert alert-error" element="p"/>
                
                <br />
                <button class="btn btn-info"><fmt:message key="testcase.addButton" /></button>
            </form:form>
            
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
        
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script>
            $(function(){
                $("#test-div-2").hide();
                
                $("#test1").change(function() {
                        $("#test-div-2").hide();
                        $("#test-div-1").show();
                    });
                $("#test2").change(function() {
                        $("#test-div-1").hide();                
                        $("#test-div-2").show();
                    });
            })
        </script>
    </body>
</html>

