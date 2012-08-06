<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">

    <c:set var="title" value="KBAccess"/>
    <%@include file='/WEB-INF/template/head.jspf' %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

       <div class="container">
           <div class="page-header">
                <h1>KBAccess</h1>
           </div>
            <div class="row">
                <p>Bienvenue sur le site de KBAccess, la base de connaissance des bons et mauvais exemples d'accessibilité.</p>
            </div>

            <div class="row">
                <h2>Les 5 derniers Testcases</h2>
                <%@include file='/WEB-INF/template/testcase-list.jspf' %>
            </div>

            <div class="row">
                <h2>Recherche</h2>
                <%@include file='/WEB-INF/template/select-form.jspf' %>
            </div>
        </div>                
                
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>