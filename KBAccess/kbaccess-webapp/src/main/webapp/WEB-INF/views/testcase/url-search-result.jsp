<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="Résultat de recherche portant sur ${codeRef} ${codeTest} ${codeResult}" />
    <%@include file="/WEB-INF/template/head.jspf" %>
    
    <body>
        <p>
            Résultat de recherche portant sur :<br />
            Referentiel : ${codeRef}<br />
            Test : ${codeTest}<br />
            Resultat : ${codeResult}<br />
            Niveau : Tous<br />
            <c:choose>
                <c:when test="${errorMessage != null}">
                <h1>Erreur</h1>
                ${errorMessage}
                </c:when>
                <c:when test="${testcaseList == null or empty testcaseList}">
                    Aucun résultat
                </c:when>
                <c:otherwise>
                    <ul id="resultat">
                        <c:forEach var="testcase" items="${testcaseList}">
                            <%@include file="/WEB-INF/template/inline/url-search-result-li.jspf" %>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
        </p>
    </body>
</html>
