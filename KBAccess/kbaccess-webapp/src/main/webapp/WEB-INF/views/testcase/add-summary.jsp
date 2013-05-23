<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="Ajout d'un exemple 3/3 - Récapitulatif " />
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>
        
        <c:set var="testcaseUrl">
                <%@include file="/WEB-INF/template/inline/testcase-url.jspf" %>
        </c:set>
        
        <div class="page-header"><h1>Ajout d'un exemple, étape 3/3 : Récapitulatif</h1></div>
        <div class="row well">
            <p>Récapitulatif de l'exemple : </p>
            <ul>
                <c:forEach var="testResult" items="${testcase.testResults}">  
                    <li>Test :        
                        <a href="<c:url value='http://www.braillenet.org/accessibilite/referentiel-aw21/liste-deploye.php#test-${testcase.webRefTestLabel}'/>">${testResult.testLabel}</a>
                        <a href="<c:url value='http://www.braillenet.org/accessibilite/referentiel-aw21/'/>">(${testcase.referenceLabel})</a>
                    </li>
                    <li>Résultat :
                        <c:set var="resultId" value="${testResult.resultId}"/>
                        <c:set var="pictoSize" value="s"/>
                        <%@include file="/WEB-INF/template/inline/result-picto.jspf" %>
                        <%@include file="/WEB-INF/template/inline/result.jspf" %>
                    </li>
                    </c:forEach>
            </ul>
            <ul>
                <li>URL :
                    <a href="${testcase.webarchiveLocalUrl}" rel="nofollow">Archive web de ${testcase.webarchiveOriginalUrl}</a>
                    <a title="${testcase.webarchiveOriginalUrl}" href="${testcase.webarchiveOriginalUrl}">
                        <img id="originalUrl-link-img" src="<c:url value='/assets/images/window-duplicate.png'/>" alt="${testcase.webarchiveOriginalUrl}"/>
                    </a>
                    <p>Votre webarchive a bien été créée, mais ne sera consultable <strong>que dans quelques minutes</strong>.</p>
                </li>
                <c:if test="${not empty testcase.description}">
                    <li>Description : ${testcase.description}</li>
                </c:if>
            </ul>
            <p><a href="<c:url value='/example/details/${testcase.id}/${testcaseUrl}'/>">Visualiser l'exemple</a> ou <a href="<c:url value='/'/>">revenir à l'accueil</a>.</p>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>