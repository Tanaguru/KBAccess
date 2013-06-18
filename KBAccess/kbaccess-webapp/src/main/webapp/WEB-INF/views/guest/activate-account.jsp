<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <c:set var="title">
	<fmt:message key="guest.activateTitle" />
    </c:set>
    
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>

        <div class="page-header"><h1><fmt:message key="guest.activateH1" /></h1></div>
        <div class="row-fluid">
            <p class="alert alert-success">
                <fmt:message key="guest.activateSuccess" />
            </p>
        </div>                    
            
        <%@include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>
