<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
    <%@include file="/WEB-INF/template/head.jspf" %>
    <body>
        <%@include file="/WEB-INF/template/header.jspf" %>
        
        <div class="container">
            <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>
            <div class="page-header">
                <h1>Testcase n°${testcase.id} : ${testcase.title}</h1>
            </div>
            <div class="row">
                <div class="span2">
                    <img alt="Aperçus de la page archivée" src=""/>
                </div>
                <div class="offset2 span10">
                    <table class="data-table table table-condensed table-vertical">
                        <tr>
                            <th scope="row">URL d'origine :</th>
                            <td><a href="${testcase.webarchiveOriginalUrl}" rel="nofollow">${testcase.webarchiveOriginalUrl}</a></td>
                        </tr>
                        <tr>
                            <th scope="row">URL archivée :</th>
                            <td><a href="${testcase.webarchiveLocalUrl}" rel="nofollow">${testcase.webarchiveLocalUrl}</a></td>
                        </tr>
                        <tr>
                            <th scope="row">Date :</th>
                            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${testcase.webarchiveCreationDate}"/></td>
                        </tr>
                        <tr>
                            <th scope="row">Auteur :</th>
                            <td><a href="<c:url value='/account/details?id=${testcase.authorId}'/>">${testcase.authorDisplayedName}</a></td>
                        </tr>
                    </table>
                </div>
                <div class="row">
                    <div class="span8">
                        <h2>Caractéristiques</h2>
                        <dl class="dl-horizontal">
                            <dt>Référentiel :</dt>
                            <dd><a href="<c:url value='/testcase/list.html?reference=${testcase.referenceId}'/>">${testcase.referenceLabel}</a></dd>
                            <dt>Critère :</dt>
                            <dd><a href="<c:url value='/testcase/list.html?criterion=${testcase.criterionId}'/>">${testcase.criterionLabel}</a></dd>
                            <dt>Résultat :</dt>
                            <dd>
                                <a href="<c:url value='/testcase/list.html?result=${testcase.resultId}&amp;criterion=${testcase.criterionId}'/>">
                                    <c:set var="result" value="${testcase.resultLabel}"/>
                                    <%@include file="/WEB-INF/template/inline/result.jspf" %>
                                </a>
                            </dd>
                            <c:if test="${fn:length(testcase.testResults) > 0}">
                                <dt>Test :</dt>
                                <dd>
                                    <c:set var="isFirst" value="${true}"/>
                                    <c:forEach var="testResult" items="${testcase.testResults}"><c:choose><c:when test="${isFirst}"><c:set var="isFirst" value="false"/></c:when><c:otherwise>,</c:otherwise></c:choose>
                                        <a href="<c:url value='/testcase/list.html?test=${testResult.testId}'/>">${testResult.testLabel}</a> :
                                        <c:set var="result" value="${testResult.resultLabel}"/>
                                        <%@include file="/WEB-INF/template/inline/result.jspf" %></c:forEach>
                                </dd>
                            </c:if>
                            <dt>Description :</dt>
                            <dd>${testcase.description}&nbsp;</dd>
                        </dl>
                    </div>
                    <div class="span4">
                        <h2>Actions</h2>
                        <c:set var="hasCRUDPermition"
                            value="${authenticatedUser.id == testcase.authorId or
                                        authenticatedUser.accessLevel.accessLevelEnumType.type == 'admin'}"/>
                        <c:choose>
                            <c:when test="${hasCRUDPermition}">
                                <div>
                                    <a href="<c:url value='/testcase/edit-details?id=${testcase.id}'/>">Modifier</a><br/>
                                    <a href="<c:url value='/testcase/delete?id=${testcase.id}'/>">Supprimer</a>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <p class="alert alert-info">
                                    Aucune action disponible pour ce testcase.
                                </p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>

        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
