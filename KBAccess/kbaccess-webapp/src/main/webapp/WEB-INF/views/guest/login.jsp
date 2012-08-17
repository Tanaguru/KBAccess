<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
    <%-- set title --%>
    <c:set var="title" value="Connexion - KBAccess" />
    <c:if test="${not empty error}">
        <c:set var="title" value="Erreur de connexion - KBAccess"/>
    </c:if>
    <%-- include html header --%>
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <div class="container">
            <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>

            <div class="page-header"><h1>Connexion</h1></div>
            <div class="row">
                <c:choose>
                    <c:when test='${param.error == "errorOnLogin"}'>
                        <p class="alert alert-error">
                            <strong>Erreur de connexion :</strong> mauvais email ou mot de passe.
                        </p>
                    </c:when>
                    <c:when test='${param.error == "sessionTimeout"}'>
                        <p class="alert alert-error">
                            <strong>Erreur de connexion :</strong> votre session a expirée.
                        </p>
                    </c:when>
                </c:choose>
                <form class="form-horizontal" action="<c:url value='/j_spring_security_check'/>" method="POST">
                    <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                    <div class="control-group">
                        <label class="control-label" for="login_email"><%@include file="/WEB-INF/template/inline/mandatory.jspf" %>Email :</label>
                        <div class="controls">
                            <input type="text" name="j_username" id="login_email"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="login_password"><%@include file="/WEB-INF/template/inline/mandatory.jspf" %>Mot de passe :</label>
                        <div class="controls">
                            <input type="password" name="j_password" id="login_password"/>
                        </div>
                    </div>
                    <div class="form-actions">
                        <button class="btn btn-primary">Connexion</button>
                    </div>
                </form>
                <p class="alert alert-info"><a href="<c:url value='/guest/password-lost.html'/>">Mot de passe oublié ?</a></p>
            </div>
        </div>
                        
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
