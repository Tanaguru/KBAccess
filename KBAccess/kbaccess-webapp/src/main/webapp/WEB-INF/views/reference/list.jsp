<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title">
	<fmt:message key="reference.listTitle" />
    </c:set>
    <%@include file="/WEB-INF/template/head.jspf" %>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <c:set var="bcReferenceList" scope="page"><fmt:message key="breadcrumb.referenceList" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcReferenceList}" value=""/>
        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>

        <div class="page-header"><h1><fmt:message key="reference.listH1" /></h1></div>
        
        <div class="row-fluid">
            <c:choose>
                <c:when test="${empty referenceCoverageList}">
                    <p class="alert alert-info"><fmt:message key="reference.noReference" /></p>
                </c:when>
                <c:otherwise>
                    <table id="reference-list-table" summary="<fmt:message key="reference.listTableSummary" />" class="table table-strip">
                        <caption class="data-table-caption"><fmt:message key="reference.listTableCaption" /></caption>
                        <tr>
                            <th class="colReference"><fmt:message key="accessibility.reference" /></th>
                            <th class="colCountry"><fmt:message key="country" /></th>
                            <th class="colCoverage"><fmt:message key="accessibility.coverage" />*</th>
                            <th class="colExampleCount"><fmt:message key="exampleCount" /></th>
                            <th class="colExamplePassedCount"><fmt:message key="examplePassedCount" /></th>
                            <th class="colExampleFailedCount"><fmt:message key="exampleFailedCount" /></th>
                        </tr>
                        <c:forEach var="referenceCoverage" items="${referenceCoverageList}" varStatus="status">   
                            <tr>                               
                                <td>
                                    <a href="<fmt:message key="${referenceCoverage.code}-url"/>">
                                           <fmt:message key="${referenceCoverage.code}-label"/>
                                    </a>
                                </td>
                                <td>
                                    ${referenceCoverage.country}
                                </td>
                                <td class="reference-coverage-value">
                                    ${referenceCoverage.coverage}%
                                </td>
                                <td class="example-count-td">
                                    <c:choose>
                                       <c:when test="${referenceCoverage.testcaseCount != 0}">
                                           <c:set var="exampleCountTitle" scope="page">
                                               <fmt:message key='exampleListOn'>
                                                   <fmt:param value="${referenceCoverage.testcaseCount}"/>
                                               </fmt:message> ${referenceCoverage.code}
                                           </c:set>
                                           <c:set var="exampleCountUrl" 
                                                  scope="page" 
                                                  value="/example/list.html?codeReference=${referenceCoverage.code}"/>
                                       </c:when>
                                       <c:otherwise>
                                           <c:set var="exampleCountTitle" scope="page">
                                               <fmt:message key='addExampleOn' /> ${referenceCoverage.code}
                                           </c:set>
                                           <c:set var="exampleCountUrl" 
                                                  scope="page" 
                                                  value="/example/add.html?reference=${referenceCoverage.id}"/>
                                       </c:otherwise>
                                    </c:choose>
                                    
                                    <a href="<c:url value='${exampleCountUrl}'/>"
                                       title="${exampleCountTitle}">
                                       ${referenceCoverage.testcaseCount}
                                    </a>
                                </td>
                                <td class="example-passed-count-td">
                                    <c:choose>
                                       <c:when test="${referenceCoverage.testcasePassedCount != 0}">
                                           <c:set var="examplePassedCountTitle" scope="page">
                                               <fmt:message key='passedExampleListOn'>
                                                   <fmt:param value="${referenceCoverage.testcasePassedCount}"/>
                                               </fmt:message> ${referenceCoverage.code}
                                           </c:set>
                                           <c:set var="examplePassedCountUrl" 
                                                  scope="page" 
                                                  value="/example/list.html?codeReference=${referenceCoverage.code}&amp;idResult=1"/>
                                       </c:when>
                                       <c:otherwise>
                                           <c:set var="examplePassedCountTitle" scope="page">
                                               <fmt:message key='addPassedExampleOn' /> ${referenceCoverage.code}
                                           </c:set>
                                           <c:set var="examplePassedCountUrl" 
                                                  scope="page" 
                                                  value="/example/add.html?reference=${referenceCoverage.id}"/>
                                       </c:otherwise>
                                    </c:choose>
                                    
                                    <a href="<c:url value='${examplePassedCountUrl}'/>"
                                       title="${examplePassedCountTitle}">
                                       ${referenceCoverage.testcasePassedCount}
                                    </a>
                                </td>
                                <td class="example-failed-count-td">
                                    <c:choose>
                                       <c:when test="${referenceCoverage.testcaseFailedCount != 0}">
                                           <c:set var="exampleFailedCountTitle" scope="page">
                                               <fmt:message key='failedExampleListOn'>
                                                   <fmt:param value="${referenceCoverage.testcaseFailedCount}"/>
                                               </fmt:message> ${referenceCoverage.code}
                                           </c:set>
                                           <c:set var="exampleFailedCountUrl" 
                                                  scope="page" 
                                                  value="/example/list.html?codeReference=${referenceCoverage.code}&amp;idResult=2"/>
                                       </c:when>
                                       <c:otherwise>
                                           <c:set var="exampleFailedCountTitle" scope="page">
                                               <fmt:message key='addFailedExampleOn' /> ${referenceCoverage.code}
                                           </c:set>
                                           <c:set var="exampleFailedCountUrl" 
                                                  scope="page" 
                                                  value="/example/add.html?reference=${referenceCoverage.id}"/>
                                       </c:otherwise>
                                    </c:choose>
                                    
                                    <a href="<c:url value='${exampleFailedCountUrl}'/>"
                                       title="${exampleFailedCountTitle}">
                                       ${referenceCoverage.testcaseFailedCount}
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>     
                    <p id="coverage-details">
                        (*) <fmt:message key="accessibility.coverageDetails"/>
                    </p>               
                </c:otherwise>
            </c:choose>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>