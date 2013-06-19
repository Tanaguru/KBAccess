<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <c:set var="title">
	<fmt:message key="admin.usersTitle" />
    </c:set>
    <%@include file="/WEB-INF/template/head.jspf" %>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <c:set var="bcUserList" scope="page"><fmt:message key="breadcrumb.userList" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcUserList}" value=""/>
        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>

        <div class="page-header"><h1><fmt:message key="admin.usersH1" /></h1></div>
        
        <div class="row-fluid">
            <c:choose>
                <c:when test="${empty accountList}">
                    <p class="alert alert-info"><fmt:message key="admin.usersNoResult" /></p>
                </c:when>
                <c:otherwise>
                    <table summary="<fmt:message key="admin.usersTitle" />" id="tableComptes" class="table table-strip">
                        <tr>
                            <th class="thTableComptes">Email</th>
                            <th class="thTableComptes"><fmt:message key="guest.subscribeLastName" /></th>
                            <th class="thTableComptes"><fmt:message key="guest.subscribeFirstName" /></th>
                            <th class="thTableComptes"><fmt:message key="admin.usersRole" /></th>
                            <th class="thTableComptes"><fmt:message key="websiteUrl" /></th>
                            <th class="thTableComptes"><fmt:message key="admin.usersState" /></th>
                            <th class="thTableComptes"><fmt:message key="admin.usersActions" /></th>
                        </tr>
                        <c:forEach var="account" items="${accountList}">
                            <c:set var='accessLevelId' value='${account.accessLevel.id}' />
                            
                            <tr>
                                <td class="tdTableComptes">${account.email}</td>
                                <td class="tdTableComptes">${account.lastName}</td>
                                <td class="tdTableComptes">${account.firstName}</td>
                                <td class="tdTableComptes"><%@include file='/WEB-INF/template/inline/roles.jspf' %></td>
                                <td class="tdTableComptes"><a href="${account.url}" rel="nofollow">${account.url}</a></td>
                                <c:choose>
                                    <c:when test="${account.activated}">
                                        <td class="tdTableComptesActive"><fmt:message key="admin.usersActivated" /></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="tdTableComptesDesactive"><fmt:message key="admin.usersWaiting" /></td>
                                    </c:otherwise>
                                </c:choose>
                                <td class="tdTableComptes">
                                    <c:choose>
                                        <c:when test="${authenticatedUser.accessLevel.rank < account.accessLevel.rank}">
                                            <a href="<c:url value='/admin/edituser/${account.id}/edit.html'/>" title="<fmt:message key="admin.usersEditTheUser" /> ${account.id}"
                                                class="tc-modify">
                                                <img src="<c:url value='/assets/images/icon-crystalclear-edit-button-16x16.png'/>"
                                                    alt="<fmt:message key="admin.usersEditTheUser" /> ${account.id}" />
                                            </a>
                                            <%-- 
                                            <a href="<c:url value='/admin/deleteuser/${account.id}/delete.html'/>"
                                                title="Supprimer account ${account.id}"
                                                class="tc-delete">
                                                <img src="<c:url value='/assets/images/icon-crystalclear-delete-cancel-button-16x16.png'/>"
                                                        alt="Supprimer account ${account.id}" />
                                            </a>
                                            --%>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="admin.usersNotAuthorized" />
                                        </c:otherwise>
                                    </c:choose>
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
