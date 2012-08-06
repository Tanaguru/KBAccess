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

            <c:choose>
                <c:when test="${not empty errorMessage}">
                    <div class="page-header">
                        <h1>Erreur lors de la modification du testcase</h1>
                    </div>
                    <div class="row">
                        <div class="boite">
                            <p class="error">${errorMessage}</p>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="page-header"><h1>Modification du testcase de ${account.name}</h1></div>
                    <div class="row">
                        <div id="modification-testcase-1" class="boite">
                            <c:if test="${not empty successMessage}">
                                <p class="positive-return">${successMessage}</p>
                            </c:if>
                            <form:form commandName="editTestcaseCommand" action="edit-details.html" method="POST">
                                <%@include file="/WEB-INF/template/block/mandatory-fields.jspf" %>
                                <div class="control-group">
                                    <label class="control-label" for="testcase_test"><%@include file="/WEB-INF/template/inline/mandatory.jspf"%> Test&nbsp;:</label>
                                    <div class="controls">
                                        <form:select path="idTest" size="5" id="testcase_test">
                                            <%@include file="/WEB-INF/template/form/options/test-ids.jspf" %>
                                        </form:select>                                    
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="testcase_result"><%@include file="/WEB-INF/template/inline/mandatory.jspf"%> Résultat&nbsp;:</label>
                                    <div class="controls">
                                        <form:select path="idResult"  id="testcase_result" size="5">
                                            <%@include file="/WEB-INF/template/form/options/result-ids.jspf" %>
                                        </form:select>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label for="testcase_description">Commentaire :</label>
                                    <div class="controls">
                                        <form:textarea id="testcase_description" path="description" rows="4" cols="35"/>
                                    </div>
                                </div>
                                <div class="form-actions">
                                    <button class="btn btn-primary">Modifier</button>
                                </div>
                            </form:form>
                        </div>
                    </div>

                </c:otherwise>
            </c:choose>
        </div>
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
