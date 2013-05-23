<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="Suppression de l'exemple ${testcase.id}" />
    <%@include file="/WEB-INF/template/head.jspf" %>
    <body>
        <%@include file="/WEB-INF/template/header.jspf" %>
        
        <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>

        <div class="page-header">
            <h1>Supppression de l'exemple <%@include file="/WEB-INF/template/block/testcase-h1.jspf" %></h1>
       </div>

        <c:choose>
            <%-- An error occured when deleting the testcase --%>
            <c:when test="${not empty errorMessage}">
                <div class="row-fluid">
                    <p class="alert alert-error">${errorMessage}</p>
                </div>
            </c:when>
            <%-- Confirmation that the testcase has been deleted --%>
            <c:when test="${not empty successMessage}">
                <div class="row-fluid">
                    <p class="alert alert-success">${successMessage}</p>
                </div>
            </c:when>
            <%-- Ask confirmation for deleting the testcase --%>
            <c:otherwise>
                <c:set var="hasCRUDPermission"
                        value="${authenticatedUser.id == testcase.authorId
                                 or authenticatedUser.accessLevel.accessLevelEnumType.type == 'moder'
                                 or authenticatedUser.accessLevel.accessLevelEnumType.type == 'admin'}"/>
                    <c:choose>
                        <c:when test="${hasCRUDPermission}">
                            <div class="row-fluid">
                                <p>Etes-vous sûr de vouloir supprimer cet exemple ? </p>
                                   <form:form  class="form-horizontal" commandName="deleteTestcaseCommand" action="delete.html" method="POST">
                                       <form:hidden path="id"></form:hidden>  
                                       <button class="btn btn-primary">Confirmer</button>   
                                   </form:form>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <p class="alert alert-error">
                                Vous n'êtes pas autorisé à supprimer cet exemple.
                            </p>
                        </c:otherwise>
                    </c:choose>
            </c:otherwise>
         </c:choose>
            
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
