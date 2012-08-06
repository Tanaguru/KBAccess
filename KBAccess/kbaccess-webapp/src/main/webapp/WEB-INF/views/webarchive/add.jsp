<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">

    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file="/WEB-INF/template/header.jspf" %>
        
        <div class="container">
            <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>

            <div class="page-header"><h1>Ajout de webarchive</h1></div>

            <div class="row">
                <h2>Ajouter une webarchive</h2>
                <div id="ajout-webarchive-1" class="boite">
                    <form:form  commandName="newWebarchiveCommand" action="add.html" method="POST">
                        <spring:hasBindErrors name="newWebarchiveCommand">
                            <form:errors path="generalErrorMessage" cssClass="alert alert-error" element="p"/>
                        </spring:hasBindErrors>
                        <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                        <div class="control-group">
                            <label class="control-label" for="webpage_url"><%@include file="/WEB-INF/template/inline/mandatory.jspf"%> URL&nbsp;:</label>
                            <div class="controls">
                                <form:input id="webpage_url" path="url" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" for="webpage_description">Description&nbsp;:</label>
                            <div class="controls">
                                <form:textarea id="webpage_description" path="description" rows="4" cols="35" />
                            </div>
                        </div>
                        <div class="form-actions">
                            <button class="btn btn-primary">Archiver</button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>

        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
