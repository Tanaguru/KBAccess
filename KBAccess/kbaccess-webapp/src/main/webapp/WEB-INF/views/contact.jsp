<%@ page pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="Contact" />
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

            <%@include file='/WEB-INF/template/breadcrumb-trail.jspf' %>
            
            <div class="page-header"><h1>Contact</h1></div>
            <div class="row-fluid">
                <h2 lang="en">Mailing-list</h2>
                <p>
                    Pour suivre l'ajout de nouveaux testcases / webarchives et discuter avec les contributeurs,
                    vous pouvez vous abonner à la mailing-list <code>&#117;&#115;&#101;&#114;&#115;&#045;&#102;&#114;&#064;&#107;&#098;&#097;&#099;&#099;&#101;&#115;&#115;&#046;&#111;&#114;&#103;</code>. <br/>
                    Pour cela envoyez un mail à <a href="mailto:&#117;&#115;&#101;&#114;&#115;&#045;&#102;&#114;&#045;&#115;&#117;&#098;&#115;&#099;&#114;&#105;&#098;&#101;&#064;&#107;&#098;&#097;&#099;&#099;&#101;&#115;&#115;&#046;&#111;&#114;&#103;">&#117;&#115;&#101;&#114;&#115;&#045;&#102;&#114;&#045;&#115;&#117;&#098;&#115;&#099;&#114;&#105;&#098;&#101;&#064;&#107;&#098;&#097;&#099;&#099;&#101;&#115;&#115;&#046;&#111;&#114;&#103;</a>
                </p>
            </div>
            <div class="row-fluid">
                <h2><span lang="en">Team</span> KBAccess</h2>
                <p><a href="mailto:&#116;&#101;&#097;&#109;&#064;&#107;&#098;&#097;&#099;&#099;&#101;&#115;&#115;&#046;&#111;&#114;&#103;">&#116;&#101;&#097;&#109;&#064;&#107;&#098;&#097;&#099;&#099;&#101;&#115;&#115;&#046;&#111;&#114;&#103;</a></p>
            </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>