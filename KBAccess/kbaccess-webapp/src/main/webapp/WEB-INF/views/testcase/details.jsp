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
                <img alt="Aperçu de la page ${testcase.webarchiveLocalUrl}" src="${configProperties['snapshotServiceUrl']}${testcase.webarchiveLocalUrl}" />
            </div>     
            <div class="span6">
                <h2><fmt:message key="testcase.detailsSpecs" /></h2>
                <table class="data-table table table-condensed table-vertical" summary="<fmt:message key="testcase.detailsSpecsOfExample" /> ${testcase.testcaseId}">
                    <caption class="data-table-caption"><fmt:message key="testcase.detailsSpecsOfExample" /> ${testcase.testcaseId}</caption>
                        <tr>
                            <th scope="row">Test :</th>
                            <td>
                                <c:set var="testWebRefCode" scope="page">
                                    <fmt:message key="${testcase.testWebRefCode}" />
                                </c:set>
                                <a href="<c:url value='${testWebRefCode}'/>">${testcase.testLabel}</a>
                                <a href="<c:url value='http://www.braillenet.org/accessibilite/referentiel-aw21/'/>">(${testcase.referenceLabel})</a>
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
                <table class="data-table table table-condensed table-vertical" summary="<fmt:message key="testcase.detailsInfosOnExample" /> ${testcase.testcaseId}">
                    <caption class="data-table-caption"><fmt:message key="testcase.detailsInfosOnExample" />${testcase.testcaseId}</caption>
                    <tr>
                        <th scope="row"><fmt:message key="contributor" /> :</th>
                        <td>
                            <a href="<c:url value='/account/details/${testcase.accountId}/profile.html'/>">${testcase.accountDisplayedName}</a>
                            &nbsp; 
                            (<a href="<c:url value='/example/list.html?account=${testcase.accountId}'/>">
                                <fmt:message key="testcase.detailsAllExamplesOf" /> ${testcase.accountDisplayedName}
                            </a>)  
                        </td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="date" /> :</th>
                        <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${testcase.creationDate}"/></td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="description" /> :</th>
                        <td>${testcase.description}&nbsp;</td>
                    </tr>
                </table>
            </div>
            <c:set var="hasCRUDPermission"
                   value="${authenticatedUser.id == testcase.accountId
                            or authenticatedUser.accessLevel.accessLevelEnumType.type == 'moder'
                            or authenticatedUser.accessLevel.accessLevelEnumType.type == 'admin'}"/>
                <c:if test="${hasCRUDPermission}">
                <div class="span3">
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
            <div class="span3">
                <h3><fmt:message key="testcase.detailsSeeAlso" /></h3>
                <%--<c:set var="isTestcaseCreator"
                       value="${authenticatedUser.id == testcase.accountId}"/>--%>
                <div class="alert alert-info">
                    <ul class="tc-details-ul">             
                        <li>
                            <a href="<c:url value='/example/list.html?reference=${testcase.referenceId}'/>">
                                <fmt:message key="testcase.detailsAllExamplesOn" /> ${testcase.referenceLabel}
                            </a>
                        </li>
                        <li>
                            <a href="<c:url value='/example/list.html?criterion=${testcase.testParentId}'/>">
                                <fmt:message key="testcase.detailsAllExamplesOn" /> <fmt:message key="accessibility.criterion" /> ${testcase.testParentLabel}
                            </a>     
                        </li>
                        <li>
                            <a href="<c:url value='/example/list.html?test=${testcase.testId}'/>">
                                <fmt:message key="testcase.detailsAllExamplesOn" /> <fmt:message key="accessibility.test" /> ${testcase.testLabel}
                            </a>
                        </li>
                    </ul>
                </div>
            </div>        
        </div>

        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
