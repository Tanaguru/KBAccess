<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="testcaseUrl">
        <%@include file="/WEB-INF/template/inline/testcase-url.jspf" %>
    </c:set>
    <c:set var="title">
        <%@include file="/WEB-INF/template/inline/testcase-details-title.jspf" %>
    </c:set>
    <%@include file="/WEB-INF/template/head.jspf" %>
    <body>
        <%@include file="/WEB-INF/template/header.jspf" %>
        
        <c:set var="bcAddExampleDetails" scope="page"><fmt:message key="accessibility.example" /> ${testcase.testcaseId}</c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcAddExampleDetails}" value=""/>
        
        <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>
        <%-- If we just edited the testcase --%>
        <c:if test="${not empty successMessage}">
            <div class="row-fluid">
                <p class="alert alert-success"><fmt:message key="${successMessage}" /></p>
            </div>
        </c:if>

        <div class="row-fluid">
            <header class="page-header span12">
                <h1><fmt:message key="accessibility.example" /> <%@include file="/WEB-INF/template/block/testcase-h1.jspf" %></h1>
            </header>
        </div>
        <div class="row-fluid">
            <div class="span3">
                <c:set var="snapshotUrl">
                    <c:choose>
                        <c:when test="${configProperties['snapshotServiceUrl'] == null}">
                            <c:url value='/assets/images/snapshot-not-available.png'/>
                        </c:when>
                        <c:otherwise>
                            ${configProperties['snapshotServiceUrl']}${testcase.webarchiveLocalUrl}
                        </c:otherwise>
                    </c:choose>
                </c:set>
                <img alt="Aperçu de la page ${testcase.webarchiveLocalUrl}" src="${snapshotUrl}" />
            </div>     
            <div class="span5">
                <h2><fmt:message key="testcase.detailsSpecs" /></h2>
                <table id="tc-details-caracteristics-table" class="data-table table table-condensed table-vertical" summary="<fmt:message key="testcase.detailsSpecsOfExample" /> ${testcase.testcaseId}">
                    <caption class="data-table-caption"><fmt:message key="testcase.detailsSpecsOfExample" /> ${testcase.testcaseId}</caption>
                        <tr>
                            <th scope="row">Test :</th>
                            <td>
                                <c:set var="testWebRef" scope="page">
                                    <fmt:message key="${testcase.testWebRefCode}" />
                                </c:set>
                                <c:set var="referenceWebRef" scope="page">
                                    <fmt:message key="${testcase.referenceWebRefCode}"/>
                                </c:set>
                                
                                <a title="<fmt:message key="testcase.referenceOf" /> <fmt:message key="${testcase.testDepthCode}"/> ${testcase.testLabel}" href="<c:url value='${testWebRef}'/>">${testcase.testLabel}</a>
                                <a href="<c:url value='${referenceWebRef}'/>">(<fmt:message key="${testcase.referenceCode}-label"/>)</a>
                                
                                <div id="tc-test-description">
                                    <fmt:message key="${testcase.testCode}"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><fmt:message key="result" /> :</th>
                            <td>
                                <c:set var="resultId" value="${testcase.resultId}"/>
                                <c:set var="pictoSize" value="s"/>
                                <%@include file="/WEB-INF/template/inline/result-picto.jspf" %>
                                <%@include file="/WEB-INF/template/inline/result.jspf" %>
                            </td>
                        </tr>
                    <tr>
                        <th scope="row">URL :</th>
                        <td>
                            <a href="${testcase.webarchiveLocalUrl}" rel="nofollow">
                                <fmt:message key="testcase.detailsArchiveOf" /> ${testcase.webarchiveOriginalUrl}
                            </a>
                                <fmt:message key="testcase.detailsDateOf" /> <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${testcase.webarchiveCreationDate}"/>
                        </td>
                    </tr>
                </table>
                <h2><fmt:message key="testcase.detailsInfos" /></h2>
                <table id="tc-details-info-table" class="data-table table table-condensed table-vertical" summary="<fmt:message key="testcase.detailsInfosOnExample" /> ${testcase.testcaseId}">
                    <caption class="data-table-caption"><fmt:message key="testcase.detailsInfosOnExample" />${testcase.testcaseId}</caption>
                    <tr>
                        <th scope="row"><fmt:message key="contributor" /> :</th>
                        <td>
                            <a href="<c:url value='/account/details/${testcase.accountId}/profile.html'/>">${testcase.accountDisplayedName}</a>
                            
                            <span id="all-examples-of-user-link">
                                (<a href="<c:url value='/example/result.html?account=${testcase.accountId}'/>">
                                    <fmt:message key="testcase.detailsAllExamplesOf" /> ${testcase.accountDisplayedName}
                                </a>)
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="date" /> :</th>
                        <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${testcase.creationDate}"/></td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="description" /> :</th>
                        <c:choose>
                            <c:when test="${empty testcase.description}">
                                <td>${testcase.description}</td>
                            </c:when>
                            <c:otherwise>
                                <td id="tc-description-td">${testcase.description}</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </table>
            </div>
            <c:set var="hasCRUDPermission"
                   value="${authenticatedUser.id == testcase.accountId
                            or authenticatedUser.accessLevel.accessLevelEnumType.type == 'moder'
                            or authenticatedUser.accessLevel.accessLevelEnumType.type == 'admin'}"/>
                <c:if test="${hasCRUDPermission}">
                <div class="span4">
                    <h3><fmt:message key="testcase.detailsActions" /></h3>
                        <div class="alert alert-info">
                            <ul class="tc-details-ul">
                                <li>
                                    <img src="<c:url value='/assets/images/icon-crystalclear-edit-button-16x16.png'/>"
                                                        alt="Modifier exemple ${testcase.testcaseId}" />
                                    <a href="<c:url value='/example/edit-details/${testcase.testcaseId}/${testcaseUrl}'/>" 
                                       title="Modifier exemple ${testcase.testcaseId}"
                                       class="tc-action-link">
                                       <fmt:message key="testcase.detailsEdit" />
                                    </a>
                                <li>
                                    <img src="<c:url value='/assets/images/icon-crystalclear-delete-cancel-button-16x16.png'/>"
                                            alt="Supprimer exemple ${testcase.testcaseId}" />
                                    <a href="<c:url value='/example/delete/${testcase.testcaseId}/${testcaseUrl}' />"
                                        title="Supprimer exemple ${testcase.testcaseId}"
                                        class="tc-action-link">
                                        <fmt:message key="testcase.detailsDelete" />
                                    </a>
                                </li>
                            </ul>
                        </div>
                </div>
                </c:if>
            <div class="span4">
                <h3><fmt:message key="testcase.detailsSeeAlso" /></h3>
                <%--<c:set var="isTestcaseCreator"
                       value="${authenticatedUser.id == testcase.accountId}"/>--%>
                <div class="alert alert-info">
                    <ul class="tc-details-ul">             
                        <li>
                            <a href="<c:url value='/example/result.html?reference=${testcase.referenceId}'/>">
                                <fmt:message key="testcase.detailsAllExamplesOn" /> <fmt:message key="${testcase.referenceCode}-label" />
                            </a>
                        </li>
                        <c:forEach var="testParent" items="${testcase.testParents}">
                        <li>
                            <a href="<c:url value='/example/result.html?test=${testParent.id}'/>">
                                <fmt:message key="testcase.detailsAllExamplesOn" /> <fmt:message key="${testParent.referenceDepth.code}" /> ${testParent.label}
                            </a>     
                        </li>
                        </c:forEach>
                        <li>
                            <a href="<c:url value='/example/result.html?test=${testcase.testId}'/>">
                                <fmt:message key="testcase.detailsAllExamplesOn" /> <fmt:message key="${testcase.testDepthCode}" /> ${testcase.testLabel}
                            </a>
                        </li>
                    </ul>
                </div>
            </div>        
        </div>

        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
