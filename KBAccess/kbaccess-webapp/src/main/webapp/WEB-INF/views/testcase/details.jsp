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

        <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>
        <%-- If we just edited the testcase --%>
        <c:if test="${not empty successMessage}">
            <div class="row-fluid">
                <p class="alert alert-success">${successMessage}</p>
            </div>
        </c:if>

        <div class="row-fluid">
            <header class="page-header span12">
                <h1>Exemple <%@include file="/WEB-INF/template/block/testcase-h1.jspf" %></h1>
            </header>
        </div>
        <div class="row-fluid">
            <div class="span3">
                <img alt="Aperçu de la page ${testcase.webarchiveLocalUrl}" src="${configProperties['snapshotServiceUrl']}${testcase.webarchiveLocalUrl}" />
            </div>     
            <div class="span6">
                <h2>Caractéristiques</h2>
                <table class="data-table table table-condensed table-vertical" summary="Caractéristiques de l'exemple ${testcase.id}">
                    <caption class="data-table-caption">Caractéristiques de l'exemple ${testcase.id}</caption>
                    <c:set var="isFirst" value="${true}"/>
                    <c:forEach var="testResult" items="${testcase.testResults}">  
                        <tr>
                            <th scope="row">Test :</th>
                            <td>
                                <a href="<c:url value='http://www.braillenet.org/accessibilite/referentiel-aw21/liste-deploye.php#test-${testcase.webRefTestLabel}'/>">${testResult.testLabel}</a>
                                <a href="<c:url value='http://www.braillenet.org/accessibilite/referentiel-aw21/'/>">(${testcase.referenceLabel})</a>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">Résultat :</th>
                            <td>
                                <c:set var="resultId" value="${testResult.resultId}"/>
                                <c:set var="pictoSize" value="s"/>
                                <%@include file="/WEB-INF/template/inline/result-picto.jspf" %>
                                <%@include file="/WEB-INF/template/inline/result.jspf" %>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th scope="row">URL :</th>
                        <td>
                            <a href="${testcase.webarchiveLocalUrl}" rel="nofollow">Archive web de ${testcase.webarchiveOriginalUrl}</a>
                            &nbsp;en date du <fmt:formatDate pattern="dd/MM/yyyy hh'h'mm" value="${testcase.webarchiveCreationDate}"/>
                           <%-- <a href="${testcase.webarchiveOriginalUrl}" title="${testcase.webarchiveOriginalUrl}">
                                <img id="originalUrl-link-img" src="<c:url value='/assets/images/window-duplicate.png'/>" alt="${testcase.webarchiveOriginalUrl}"/>
                            </a>--%>
                        </td>
                    </tr>
                </table>
                <h2>Informations</h2>
                <table class="data-table table table-condensed table-vertical" summary="Informations sur l'exemple ${testcase.id}">
                    <caption class="data-table-caption">Informations sur l'exemple ${testcase.id}</caption>
                    <tr>
                        <th scope="row">Contributeur :</th>
                        <td>
                            <a href="<c:url value='/account/details/${testcase.authorId}/profile.html'/>">${testcase.authorDisplayedName}</a>
                            &nbsp; 
                            <%--<c:if test="${!isTestcaseCreator}">--%>
                                (<a href="<c:url value='/example/list.html?account=${testcase.authorId}'/>">
                                    Tous les exemples de ${testcase.authorDisplayedName}
                                </a>)  
                            <%--</c:if>--%>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Date :</th>
                        <td><fmt:formatDate pattern="dd/MM/yyyy hh'h'mm" value="${testcase.creationDate}"/></td>
                    </tr>
                    <tr>
                        <th scope="row">Description :</th>
                        <td>${testcase.description}&nbsp;</td>
                    </tr>
                </table>
            </div>
            <c:set var="hasCRUDPermission"
                   value="${authenticatedUser.id == testcase.authorId
                            or authenticatedUser.accessLevel.accessLevelEnumType.type == 'moder'
                            or authenticatedUser.accessLevel.accessLevelEnumType.type == 'admin'}"/>
                <c:if test="${hasCRUDPermission}">
                <div class="span3">
                    <h3>Actions</h3>
                        <div class="alert alert-info">
                            <ul class="tc-details-ul">
                                <li>
                                    <img src="<c:url value='/assets/images/icon-crystalclear-edit-button-16x16.png'/>"
                                                        alt="Modifier exemple ${testcase.id}" />
                                    <a href="<c:url value='/example/edit-details/${testcase.id}/${testcaseUrl}'/>" 
                                       title="Modifier exemple ${testcase.id}"
                                       class="tc-action-link">
                                       Modifier
                                    </a>
                                <li>
                                    <img src="<c:url value='/assets/images/icon-crystalclear-delete-cancel-button-16x16.png'/>"
                                            alt="Supprimer exemple ${testcase.id}" />
                                    <a href="<c:url value='/example/delete/${testcase.id}/${testcaseUrl}' />"
                                        title="Supprimer exemple ${testcase.id}"
                                        class="tc-action-link">
                                        Supprimer
                                    </a>
                                </li>
                            </ul>
                        </div>
                </div>
                </c:if>
            <div class="span3">
                <h3>Voir aussi</h3>
                <c:set var="isTestcaseCreator"
                       value="${authenticatedUser.id == testcase.authorId}"/>
                <div class="alert alert-info">
                    <ul class="tc-details-ul">             
                        <li>
                            <a href="<c:url value='/example/list.html?reference=${testcase.referenceId}'/>">
                                Tous les exemples sur : ${testcase.referenceLabel}
                            </a>
                        </li>
                        <li>
                            <a href="<c:url value='/example/list.html?criterion=${testcase.criterionId}'/>">
                                Tous les exemples sur : Critère ${testcase.criterionLabel}
                            </a>     
                        </li>
                        <c:forEach var="testResult" items="${testcase.testResults}">
                            <c:choose>
                                <c:when test="${isFirst}">
                                    <c:set var="isFirst" value="false"/>
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                            <li>
                                <a href="<c:url value='/example/list.html?test=${testResult.testId}'/>">
                                    Tous les exemples sur : Test ${testResult.testLabel}
                                </a>
                            </li>
                        </c:forEach>

                    </ul>
                </div>
            </div>        
        </div>

        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
