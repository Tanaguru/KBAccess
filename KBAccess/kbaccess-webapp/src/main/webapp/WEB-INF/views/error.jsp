<%@page pageEncoding="UTF-8" contentType="text/html" %>
<!DOCTYPE html>
<html lang="fr">
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <div class="container">
            <%@include file='/WEB-INF/template/breadcrumb-trail.jspf' %>
            
            <div class="page-header"><h1>Erreur</h1></div>
            <div class="row">
                <h2 lang="en"></h2>
                <p class="alert alert-error">
                    <c:out value="${requestScope['javax.servlet.error.message']}"/><br />
                    La page à laquelle vous essayez d'accéder n'existe pas ou plus. 
                </p>
            </div>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>