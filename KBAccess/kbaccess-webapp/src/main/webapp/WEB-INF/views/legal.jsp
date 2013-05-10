<%@ page pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="Mentions légales" />
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

            <%@include file='/WEB-INF/template/breadcrumb-trail.jspf' %>
            
            <div class="page-header"><h1>Mentions légales</h1></div>
            <div class="row-fluid">
                <h2>Éditeur</h2>
                <address>
                    <acronym title="Société par Actions Simplifiés">SAS</acronym> <em>Open-S</em> <br />
                    <br />
                    Cap Omega - <acronym title="Course Spéciale">CS</acronym> 39521<br />
                    Rond point Benjamin Franklin<br />
                    34960 Montpellier Cedex 2 FRANCE
                </address>
                <p>Télephone: +33 (0) 972 11 26 06<br/>
                    Courrier életronique: <a href="mailto:&#111;&#112;&#101;&#110;&#045;&#115;&#064;&#111;&#112;&#101;&#110;&#045;&#115;&#046;&#099;&#111;&#109;">&#111;&#112;&#101;&#110;&#045;&#115;&#064;&#111;&#112;&#101;&#110;&#045;&#115;&#046;&#099;&#111;&#109;</a>
                </p>
            </div>
            <div class="row-fluid">
                <h2>Directeur de publication</h2>
                <p>Matthieu Faure</p>
            </div>
            <div class="row-fluid">
                <h2>Hébergement</h2>
                <p>OVH - 2 rue Kellermann 59100 Roubaix France</p>
            </div>

        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>