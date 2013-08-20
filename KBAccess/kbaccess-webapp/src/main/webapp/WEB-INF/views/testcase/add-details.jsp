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
            
            <form:form id="new-tc-form" commandName="newTestcaseCommand" action="${addUrl}" method="POST">
                <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                
                <form:hidden path="idReference" />
                
                <div>
                    <label class="new-tc-label">
                        <%@include file="/WEB-INF/template/inline/mandatory.jspf"%>
                        <fmt:message key="testcase.elementToBeTested"/> : 
                    </label>
                    
                    <div class="new-tc-block">
                        <c:forEach var="entry" items="${referenceTestMap}" varStatus="status">
                            <div class="new-tc-depth-block">
                                <label>
                                    <form:radiobutton id="test${status.count}" path="idReferenceDepth" value="${entry.key.id}"/>
                                    <fmt:message key="${entry.key.code}"/>
                                </label> : 
                            </div>
                            
                            <form:select class="span10" id="test-div-${status.count}" path="idReferenceTest" size="5">
                                <c:set var="referenceTestList" value="${entry.value}"/>
                                <%@include file="/WEB-INF/template/form/options/test-ids.jspf" %>
                            </form:select>
                            <form:errors id="test-div-${status.count}-error" path="idReferenceTest" cssClass="alert alert-error span10 new-tc-test-error" element="p"/>
                        </c:forEach>
                    </div>
                    
                    <label id="new-tc-result-label" class="new-tc-label" for="testcase_idresult"><%@include file="/WEB-INF/template/inline/mandatory.jspf"%><fmt:message key="result" /> :</label>
                    <div class="new-tc-block">
                        <%@include file="/WEB-INF/template/form/options/result-ids.jspf" %>
                        <form:errors path="idResult" cssClass="alert alert-error" element="p"/>
                    </div>
                    
                    <label class="new-tc-label" for="testcase_description"><fmt:message key="description" /> :</label>
                    <div class="new-tc-block">
                        <form:textarea class="span11" path="description" id="testcase_description" rows="4" cols="35"/>
                        <form:errors path="description" cssClass="alert alert-error" element="p"/>
                    </div>
                        
                <div class="form-actions">
                    <button class="btn btn-info"><fmt:message key="testcase.nextStep" /></button>
                </div>
            </form:form>
            
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
        
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
         <script>
            $(function(){
                if ($("#test-div-2").length > 0) {
                    $("#test-div-1").prop('disabled', true);
                    $("#test-div-1").css('color', '#999999');
                    $("#test-div-1").attr('aria-disabled', 'true');
                    $("#test-div-1-error").hide();
                    
                    $("#test1").change(function() {
                        $("#test-div-2").prop('disabled', true);
                        $("#test-div-2").css('color', '#999999');
                        $("#test-div-2").attr('aria-disabled', 'true');
                        $("#test-div-2-error").hide();
                        $("#test-div-1").prop('disabled', false);
                        $("#test-div-1").css('color', '#555555');
                        $("#test-div-1").attr('aria-disabled', 'false');
                        $("#test-div-1-error").show();
                    });
                    
                    $("#test2").change(function() {
                        $("#test-div-1").prop('disabled', true);
                        $("#test-div-1").css('color', '#999999');
                        $("#test-div-1").attr('aria-disabled', 'true');
                        $("#test-div-1-error").hide();
                        $("#test-div-2").prop('disabled', false);
                        $("#test-div-2").css('color', '#555555');
                        $("#test-div-2").attr('aria-disabled', 'false');
                        $("#test-div-2-error").show();
                    });
                }
            })
        </script>
    </body>
</html>