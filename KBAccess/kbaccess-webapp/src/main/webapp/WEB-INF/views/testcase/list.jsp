<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="fr">
    <c:choose>
        <%-- All testcases --%>
        <c:when test="${contextOfRequest == 'allTestcases'}">
            <c:set var="title" scope="page">
                <fmt:message key="testcase.searchAllTestcasesTitle"/>
            </c:set>
            
            <c:set var="searchH1" scope="page">
                <fmt:message key="testcase.searchAllTestcasesH1"/>
            </c:set>
            
            <%-- breadcrumb --%>
            <c:set var="bcSearchTestcases"><fmt:message key="testcase.searchExamples"/></c:set>
            <c:set var="bcAllTestcases"><fmt:message key="testcase.searchAllTestcasesTitle"/></c:set>
            <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
            <c:set target="${breadcrumbTrail}" property="${bcSearchTestcases}" value="/example/search.html"/>/>
            <c:set target="${breadcrumbTrail}" property="${bcAllTestcases}" value=""/>
        </c:when>
            
        <%-- Testcases of a user --%>
        <c:when test="${contextOfRequest == 'userTestcases'}">
            <c:set var="title" scope="page">
                <fmt:message key="testcase.searchUserTestcasesTitle"/> ${account.displayedName}
            </c:set>
            
            <c:set var="searchH1" scope="page">
                <fmt:message key="testcase.searchUserTestcasesH1"/> ${account.displayedName}
            </c:set>
            
            <%-- breadcrumb --%>
            <c:set var="bcListOfTestcases" scope="page"><fmt:message key="testcase.listOfTestcases" /></c:set>
            <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
            <c:set target="${breadcrumbTrail}" property="${account.displayedName}" value="/account/details/${account.id}/profile.html"/>
            <c:set target="${breadcrumbTrail}" property="${bcListOfTestcases}" value=""/>
        </c:when>
        
        <%-- Result of a specific search --%>
        <c:otherwise>
            <%-- build search parameter list --%>
            <c:set var="separator" scope="page" value="," />
            <c:set var="searchParametersList" scope="page">
                <c:forEach var="parameter" items="${parameterMap}" varStatus="status">
                    
                    <c:choose>
                        <c:when test="${parameter.key == 'accessibility.level' or parameter.key == 'result'}">
                            <fmt:message key="${parameter.key}"/> <fmt:message key="${parameter.value}"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="${parameter.key}"/> ${parameter.value}
                        </c:otherwise>
                    </c:choose>
                    
                    <c:if test="${!status.last}">
                        <c:out value="${separator}" />		
                    </c:if>
                    </c:forEach>
            </c:set>
            
            <c:set var="title" scope="page">
                <fmt:message key="testcase.searchTestcasesTitle"/> ${searchParametersList}
            </c:set>
            
            <c:set var="searchH1" scope="page">
                <fmt:message key="testcase.searchTestcasesH1"/> ${searchParametersList}
            </c:set>
            
            <%-- breadcrumb --%>
            <c:set var="bcSearchTestcases"><fmt:message key="testcase.searchExamples"/></c:set>
            <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
            <c:set target="${breadcrumbTrail}" property="${bcSearchTestcases}" value="/example/search.html"/>/>
            <c:set target="${breadcrumbTrail}" property="${searchParametersList}" value=""/>
        </c:otherwise> 
    </c:choose>
        
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>

        <h1 class="page-header">${searchH1}</h1>
        <div class="row-fluid">
            <%@include file='/WEB-INF/template/testcase-list.jspf'%>
            <c:if test="${showTestcaseSearchForm}">
                <%@include file='/WEB-INF/template/select-form.jspf'%>
            </c:if>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>