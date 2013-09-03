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
                <c:when test="${empty referenceList}">
                    <p class="alert alert-info"><fmt:message key="reference.noReference" /></p>
                </c:when>
                <c:otherwise>
                    <table summary="<fmt:message key="reference.listTableSummary" />" class="table table-strip">
                        <caption class="data-table-caption"><fmt:message key="reference.listTableCaption" /></caption>
                        <tr>
                            <th><fmt:message key="accessibility.reference" /></th>
                            <th><fmt:message key="country" /></th>
                            <th><fmt:message key="accessibility.coverage" /></th>
                            <th><fmt:message key="exampleCount" /></th>
                        </tr>
                        <c:forEach var="reference" items="${referenceList}" varStatus="status">   
                            <tr>                               
                                <td>
                                    <a href="<fmt:message key="${reference.code}-url"/>">
                                           <fmt:message key="${reference.code}-label"/>
                                    </a>
                                </td>
                                <td>
                                    ${reference.country}
                                </td>
                                <td class="reference-coverage-value">
                                    ${status.index * 20}%
                                </td>
                                <td>
                                    <a href="<c:url value='/example/result.html?reference=${reference.id}'/>"
                                       title="<fmt:message key='exampleListOn'/> ${reference.code}">
                                        ${status.index * 100}
                                    </a>
                            </tr>
                        </c:forEach>
                    </table>                    
                </c:otherwise>
            </c:choose>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>