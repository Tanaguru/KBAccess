<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <%@include file="/WEB-INF/template/head.jspf" %>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <div class="container-fluid">
            <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>

            <div class="page-header"><h1>${accountListH1}</h1></div>

            <div class="row-fluid">
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
                                <th class="thTableComptes">Actions</th>
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
                                                <a href="<c:url value='/admin/edituser.html?id=${account.id}'/>" title="Modifier utilisateur ${account.id}"
                                                    class="tc-modify">
                                                    <img src="<c:url value='/assets/images/icon-crystalclear-edit-button-16x16.png'/>"
                                                        alt="Modifier utilisateur ${account.id}" />
                                                </a>
                                                <%--<a href="<c:url value='./admin/deleteuser.html?id=${account.id}'/>"
                                                    title="Supprimer account ${account.id}"
                                                    class="tc-delete">
                                                    <img src="<c:url value='/assets/images/icon-crystalclear-delete-cancel-button-16x16.png'/>"
                                                            alt="Supprimer account ${account.id}" />
                                                </a>--%>
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
