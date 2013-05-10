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
                <h1>Testcase <%@include file="/WEB-INF/template/block/testcase-h1.jspf" %></h1>
            </header>
        </div>
        <div class="row-fluid">
            <div class="span3">
                <img alt="Aperçu de la page ${testcase.webarchiveLocalUrl}" src="${configProperties['snapshotServiceUrl']}${testcase.webarchiveLocalUrl}" />
            </div>
            <%--            <div class="span6">
                                <h2>Caractéristiques</h2>
                                <dl class="dl-horizontal">
                                    <dt>Référentiel :</dt>
                                    <dd><a href="<c:url value='http://www.braillenet.org/accessibilite/referentiel-aw21/'/>">${testcase.referenceLabel}</a></dd>
                                    <dt>Critère :</dt>
                                    <dd><a href="<c:url value='http://www.braillenet.org/accessibilite/referentiel-aw21/liste-deploye.php#crit-${testcase.webRefCriterionLabel}'/>">${testcase.criterionLabel}</a></dd>
                                    <dt>Résultat :</dt>
                                    <dd>
                                        <a href="<c:url value='/testcase/list.html?result=${testcase.resultId}&amp;criterion=${testcase.criterionId}'/>">
                                    <c:set var="resultId" value="${testcase.resultId}"/>
                                    <%@include file="/WEB-INF/template/inline/result.jspf" %>
                                </a>
                            </dd>
                                    <c:if test="${fn:length(testcase.testResults) > 0}">
                                        <dt>Test :</dt>
                                        <dd>
                                            <c:set var="isFirst" value="${true}"/>
                                            <c:forEach var="testResult" items="${testcase.testResults}">  
                                                <a href="<c:url value='http://www.braillenet.org/accessibilite/referentiel-aw21/liste-deploye.php#test-${testcase.webRefTestLabel}'/>">${testResult.testLabel}</a><br />
                                            <dt>Résultat :</dt>
                                            <dd>
                                                <c:set var="resultId" value="${testResult.resultId}"/>
                                                <c:set var="pictoSize" value="s"/>
                                                <%@include file="/WEB-INF/template/inline/result-picto.jspf" %>
                                                <%@include file="/WEB-INF/template/inline/result.jspf" %>
                                            </dd>
                                            </c:forEach>
                                        </dd>
                                    </c:if>
                                    <dt>Description :</dt>
                                    <dd>${testcase.description}&nbsp;</dd>
                                </dl>
                            </div>--%>
            <div class="span6">
                <h2>Exemple d'accessibilité n°${testcase.id}</h2>
                <table class="data-table table table-condensed table-vertical">
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
                            &nbsp;(en date du <fmt:formatDate pattern="dd/MM/yyyy hh'h'mm" value="${testcase.webarchiveCreationDate}"/>)
                            <a href="${testcase.webarchiveOriginalUrl}" title="${testcase.webarchiveOriginalUrl}">
                                <img id="originalUrl-link-img" src="<c:url value='/assets/images/window-duplicate.png'/>" alt="${testcase.webarchiveOriginalUrl}"/>
                            </a>
                        </td>
                    </tr>
                </table>
                <h2>Informations</h2>
                <table class="data-table table table-condensed table-vertical">
                    <tr>
                        <th scope="row">Auteur :</th>
                        <td><a href="<c:url value='/account/details/${testcase.authorId}/profile.html'/>">${testcase.authorDisplayedName}</a></td>
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
            <div class="span3">
                <h3>Actions</h3>
                <c:set var="hasCRUDPermission"
                       value="${authenticatedUser.id == testcase.authorId
                                or authenticatedUser.accessLevel.accessLevelEnumType.type == 'moder'
                                or authenticatedUser.accessLevel.accessLevelEnumType.type == 'admin'}"/>
                <c:choose>
                    <c:when test="${hasCRUDPermission}">
                        <p class="alert alert-info">
                            <a href="<c:url value='/testcase/edit-details/${testcase.id}/${testcaseUrl}'/>">Modifier</a><br/>
                            <a href="<c:url value='/testcase/delete/${testcase.id}/${testcaseUrl}'/>">Supprimer</a>
                        </p>
                    </c:when>
                    <c:otherwise>
                        <p class="alert alert-info">
                            Aucune action disponible pour ce testcase.
                        </p>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="span3">
                <h3>Voir aussi</h3>
                <c:set var="isTestcaseCreator"
                       value="${authenticatedUser.id == testcase.authorId}"/>
                <div class="alert alert-info">             
                    <p>
                        <a href="<c:url value='/testcase/list.html?reference=${testcase.referenceId}'/>">
                            Tous les testcases sur ${testcase.referenceLabel}
                        </a><br />    
                        <a href="<c:url value='/testcase/list.html?criterion=${testcase.criterionId}'/>">
                            Tous les testcases sur le critère ${testcase.criterionLabel}
                        </a><br />     
                        <c:forEach var="testResult" items="${testcase.testResults}">
                            <c:choose>
                                <c:when test="${isFirst}">
                                    <c:set var="isFirst" value="false"/>
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>

                            <a href="<c:url value='/testcase/list.html?test=${testResult.testId}'/>">
                                Tous les testcases sur le test ${testResult.testLabel}
                            </a><br />
                        </c:forEach>
                        <c:if test="${!isTestcaseCreator}">
                            <a href="<c:url value='/testcase/list.html?account=${testcase.authorId}'/>">
                                Tous les testcases de ${testcase.authorDisplayedName}
                            </a><br />    
                        </c:if>
                    <p>
                </div>
            </div>        
        </div>

        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
