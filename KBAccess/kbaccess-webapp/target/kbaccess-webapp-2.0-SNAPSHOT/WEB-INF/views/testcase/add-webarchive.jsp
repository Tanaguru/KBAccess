<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="Ajout de testcase 2/3 - Webarchive " />
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>

        <h1 class="page-header">Ajout d'un testcase, étape 2/3 : Attacher une webarchive</h1>
        <div class="row-fluid">
            <form:form commandName="newTestcaseCommand" action="add-finalize.html" method="POST">
                <form:hidden path="description"/>
                <form:hidden path="idTest"/>
                <form:hidden path="idResult"/>
                <form:errors path="createWebarchive" cssClass="alert alert-error" element="p"/>
                <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                <div class="control-group">
                    <form:radiobutton path="createWebarchive" value="false" id="testcase_existing_webarchive"/>
                    <label class="control-label" for="testcase_existing_webarchive">Utiliser une webarchive existante</label>
                    <div class="controls">
                        <div class="control-group">
                            <label class="control-label" for="testcase_idwebarchive"><%@include file="/WEB-INF/template/inline/mandatory.jspf"%>Webarchive :</label>
                            <div class="controls">
                                <form:select path="idWebarchive" id="testcase_idwebarchive">
                                    <c:forEach var="webarchive" items="${webarchiveList}">
                                        <option value="${webarchive.id}">${webarive.url} : ${webarchive.creationDate}</option>
                                    </c:forEach>
                                </form:select>
                                <form:errors path="idWebarchive" cssClass="alert alert-error" element="p"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <form:radiobutton path="createWebarchive" value="true" id="testcase_create_webarchive"/>
                    <label class="control-label" for="testcase_create_webarchive">... ou créer une nouvelle webarchive</label>
                    <div class="controls">
                        <div class="control-group">
                            <label class="control-label" for="webarchive_url"><span class="mandatory" title="Champ obligatoire">*</span>URL :</label>
                            <div class="controls">
                                <form:input path="urlNewWebarchive" id="webarchive_url"/>
                                <form:errors path="urlNewWebarchive" cssClass="alert alert-error" element="p"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label for="webarchive_description">Commentaire :</label>
                            <div class="controls">
                                <form:textarea path="descriptionNewWebarchive" id="webarchive_description" rows="4" cols="35"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <button class="btn btn-info">Ajouter</button>
                </div>
            </form:form>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>