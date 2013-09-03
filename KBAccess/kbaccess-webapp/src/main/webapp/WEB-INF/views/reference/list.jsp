<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title">
	<fmt:message key="statistics.topContributorsTitle" />
    </c:set>
    <%@include file="/WEB-INF/template/head.jspf" %>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <c:set var="bcTopContributors" scope="page"><fmt:message key="breadcrumb.topContributors" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcTopContributors}" value=""/>
        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>

        <div class="page-header"><h1><fmt:message key="statistics.topContributorsH1" /></h1></div>
        
        <div class="row-fluid">
            <c:choose>
                <c:when test="${empty contributors}">
                    <p class="alert alert-info"><fmt:message key="statistics.noContributors" /></p>
                </c:when>
                <c:otherwise>
                    <table summary="<fmt:message key="statistics.topContributorsTableSummary" />" id="tableComptes" class="table table-strip">
                        <caption class="data-table-caption"><fmt:message key="statistics.topContributorsTableCaption" /></caption>
                        <tr>
                            <th class="thTableComptes"><fmt:message key="contributor" /></th>
                            <th class="thTableComptes"><fmt:message key="websiteUrl" /></th>
                            <th class="thTableComptes"><fmt:message key="statistics.testcasesFromCreator" /></th>
                        </tr>
                        <c:forEach var="account" items="${contributors}">   
                            <tr">                               
                                <td class="tdTableComptes">
                                    <a href="<c:url value='/account/details/${account.id}/profile.html'/>"
                                       title="<fmt:message key="account.profileOfUser" /> ${account.displayedName}">
                                        ${account.displayedName}
                                    </a>
                                </td>
                                <td class="tdTableComptes">
                                    <a href="${account.myUrl}" rel="nofollow">${account.myUrl}</a>
                                </td>
                                <td class="tdTableComptes">
                                    <c:set var="example" scope="page">
                                        <c:choose>
                                            <c:when test="${account.nbCreatedTestcases > 1}">
                                                <fmt:message key="accessibility.examples"/>
                                            </c:when>
                                            <c:otherwise>
                                                <fmt:message key="accessibility.example"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:set>
                                    <a href="<c:url value='/example/list.html?account=${account.id}'/>"
                                       title="${account.nbCreatedTestcases} ${example} <fmt:message key="from"/> ${account.displayedName}">
                                        ${account.nbCreatedTestcases}
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                    
                </c:otherwise>
            </c:choose>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
