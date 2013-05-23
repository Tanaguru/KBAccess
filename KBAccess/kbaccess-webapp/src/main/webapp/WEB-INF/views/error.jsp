<%@page pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="Erreur" />
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
            
            <div class="page-header"><h1>Erreur</h1></div>
            <div class="row-fluid">
                <h2 lang="en"></h2>
                <p class="alert alert-error">
                    La page à laquelle vous essayez d'accéder n'existe pas ou plus. 
                </p>
            </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>