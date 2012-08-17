<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <%@include file="/WEB-INF/template/head.jspf" %>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <div class="container">
            <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>

            <div class="page-header"><h1>${accountListH1}</h1></div>

            <div class="row">
                <c:choose>
                    <c:when test="${empty accountList}">
                        <p class="alert alert-info">Aucun résultat.</p>
                    </c:when>
                    <c:otherwise>
                        <table id="tableComptes" class="table table-strip">
                            <tr>
                                <th class="thTableComptes">Email</th>
                                <th class="thTableComptes">Nom</th>
                                <th class="thTableComptes">Pr&eacute;nom</th>
                                <th class="thTableComptes">Rôle</th>
                                <th class="thTableComptes">URL</th>
                                <th class="thTableComptes">Etat</th>
                                <th class="thTableComptes">Supprimer</th>
                            </tr>
                            <c:forEach var="account" items="${accountList}">
                                <tr>
                                    <td class="tdTableComptes">${account.email}</td>
                                    <td class="tdTableComptes">${account.lastName}</td>
                                    <td class="tdTableComptes">${account.firstName}</td>
                                    <td class="tdTableComptes">${account.accessLevel.label}</td>
                                    <td class="tdTableComptes"><a href="${account.url}" rel="nofollow">${account.url}</a></td>
                                    <c:choose>
                                        <c:when test="${account.activated}">
                                            <td class="tdTableComptesActive">activ&eacute;</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td class="tdTableComptesDesactive">en attente</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td class="tdTableComptes">
                                        <c:choose>
                                            <c:when test="${authenticatedUser.accessLevel.rank < account.accessLevel.rank}">
                                                <a href="<c:url value='/account/delete.html?id=${account.id}'/>">
                                                    Supprimer
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                Droits insufisants
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>                    
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
