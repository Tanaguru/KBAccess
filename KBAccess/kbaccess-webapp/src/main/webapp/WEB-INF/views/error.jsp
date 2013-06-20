<%@page pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <c:set var="title">
         <fmt:message key="error.pageTitle"/>
    </c:set>
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
            
            <div class="page-header"><h1><fmt:message key="error.h1"/></h1></div>
            <div class="row-fluid">
                <h2 lang="en"></h2>
                <p class="alert alert-error">
                     <fmt:message key="error.message"/> 
                </p>
            </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>