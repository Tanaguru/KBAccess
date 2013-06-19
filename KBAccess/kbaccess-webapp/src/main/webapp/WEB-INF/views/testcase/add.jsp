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
        
        <c:set var="bcAddExampleStep1" scope="page"><fmt:message key="breadcrumb.addExampleStep1" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcAddExampleStep1}" value=""/>
        
        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>

        <h1 class="page-header"><fmt:message key="testcase.addH1" /></h1>
        <div class="row-fluid">
            <c:url var='addUrl' value='/example/add.html'/>
            
            <form:form commandName="newTestcaseCommand" action="${addUrl}" method="POST">
                <table>
                    <tbody>
                        <tr>
                            <td colspan="2">
                                <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                            </td>
                        </tr>
                        <tr>
                            <td class="col1">
                                <label class="control-label" for="testcase_idtest"><%@include file="/WEB-INF/template/inline/mandatory.jspf"%>Test :</label>
                            </td>
                            <td>
                                <form:select path="idTest" id="testcase_idtest" size="5">
                                    <%@include file="/WEB-INF/template/form/options/test-ids.jspf" %>
                                </form:select>
                                <form:errors path="idTest" cssClass="alert alert-error" element="p"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="col1">
                                <label class="control-label" for="testcase_idresult"><%@include file="/WEB-INF/template/inline/mandatory.jspf"%><fmt:message key="result" /> :</label>
                            </td>
                            <td>
                                <form:select path="idResult" id="testcase_idresult" size="4">
                                    <%@include file="/WEB-INF/template/form/options/result-ids.jspf" %>
                                </form:select>
                                <form:errors path="idResult" cssClass="alert alert-error" element="p"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="col1">
                                <label for="testcase_description"><fmt:message key="description" /> :</label>
                            </td>
                            <td>
                                <form:textarea path="description" id="testcase_description" rows="4" cols="35"/>
                                <form:errors path="description" cssClass="alert alert-error" element="p"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="col1">
                            <td>
                                <input type="submit" value="<fmt:message key="testcase.addButton" />"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form:form>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>