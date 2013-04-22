<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <%-- set title --%>
    <c:set var="title" value="Activation du compte - KBAccess" />
    <c:if test="${not empty activateAccountError}">
        <c:set var="title" value="Erreur lors de l'activation du compte - KBAccess"/>
    </c:if>
    <%-- include html header --%>
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <div class="container-fluid">
            <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>

            <div class="page-header"><h1>Activation du compte</h1></div>
            <div class="row-fluid">
                <c:choose>
                    <c:when test='${accountActivated}'>
                        <p class="alert alert-success">
                            Votre compte a bien été activé.
                            Vous pouvez désormais vous identifier sur la
                            <a href="login.html">page d'authentification</a>.
                        </p>
                    </c:when>
                    <c:otherwise>
                        <div id="accountActivation" class="well">
                            <c:if test="${activateAccountError}">
                                <p class="alert alert-error">${activateAccountError}</p>
                            </c:if>
                            <form action="activate-account.html" method="POST" class="form-horizontal">
                                <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                                <div class="control-group">
                                    <label class="control-label" for="activate_email"><%@include file="/WEB-INF/template/inline/mandatory.jspf" %>Email :</label>
                                    <div class="controls">
                                        <input class="input-large" type="text" name="email" id="activate_email"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="activate_token"><%@include file="/WEB-INF/template/inline/mandatory.jspf" %>Jeton :</label>
                                    <div class="controls">
                                        <input type="text" name="token" id="activate_token"/>
                                    </div>
                                </div>
                                <div class="form-actions">
                                    <button class="btn btn-primary">Activation</button>
                                </div>
                            </form>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>                    
        </div>
            
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
