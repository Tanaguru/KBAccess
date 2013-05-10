<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="Ajout de testcase 1/3 - Testcase " />
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <%@include file='/WEB-INF/template/breadcrumb-trail.jspf'%>

        <h1 class="page-header">Ajout d'un testcase, étape 1/3 : Caractéristiques du testcase</h1>
        <div class="row-fluid">
            <form:form commandName="newTestcaseCommand" action="add.html" method="POST">
                <table>
                    <tbody>
                        <tr>
                            <td colspan="2">
                                <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                            </td>
                        </tr>
                        <tr>
                            <td class="col1">
                                <label class="control-label" for="testcase_idtest"><%@include file="/WEB-INF/template/inline/mandatory.jspf"%>Test :</label>
                            </td>
                            <td>
                                <form:select path="idTest" id="testcase_idtest" size="5">
                                    <%@include file="/WEB-INF/template/form/options/test-ids.jspf" %>
                                </form:select>
                                <form:errors path="idTest" cssClass="alert alert-error" element="p"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="col1">
                                <label class="control-label" for="testcase_idresult"><%@include file="/WEB-INF/template/inline/mandatory.jspf"%>Resultat :</label>
                            </td>
                            <td>
                                <form:select path="idResult" id="testcase_idresult" size="4">
                                    <%@include file="/WEB-INF/template/form/options/result-ids.jspf" %>
                                </form:select>
                                <form:errors path="idResult" cssClass="alert alert-error" element="p"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="col1">
                                <label for="testcase_description">Commentaire :</label>
                            </td>
                            <td>
                                <form:textarea path="description" id="testcase_description" rows="4" cols="35"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="col1">
                            <td>
                                <input type="submit" value="Ajouter"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form:form>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>