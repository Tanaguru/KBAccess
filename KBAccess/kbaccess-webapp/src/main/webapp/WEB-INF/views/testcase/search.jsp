<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title">
	<fmt:message key="testcase.searchTitle" />
    </c:set>
    <%@include file="/WEB-INF/template/head.jspf" %>

    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>

        <c:set var="bcSearchExamples" scope="page"><fmt:message key="breadcrumb.searchExamples" /></c:set>
        <c:set target="${breadcrumbTrail}" property="KBAccess" value="/"/> 
        <c:set target="${breadcrumbTrail}" property="${bcSearchExamples}" value=""/>
        
        <%@include file="/WEB-INF/template/breadcrumb-trail.jspf" %>
        
        <h1 class="page-header"><fmt:message key="testcase.searchH1" /></h1>
        <div class="row-fluid">
            <%@include file='/WEB-INF/template/select-form.jspf'%>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
        
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script>
            $(function(){
                // At init no <select> that are specific to a referential are shown 
                // thus, no info, test or level block
                $('#ref-block').hide();
                $('#ref-block').attr('aria-hidden', 'true');
                $('div.toggle-block').hide();
                $('div.toggle-block').attr('aria-hidden', 'true');
                $('div.toggle-block select').prop('disabled', true);
                $('div.toggle-block select').attr('aria-disabled', 'true');
                
                // If a referetial is selected
                $('#selectReferentiel').on('change', function() {
                    // Hide all <div> and disable all <select> specific to a referential
                    $('#ref-block').hide();
                    $('#ref-block').attr('aria-hidden', 'true');
                    $('div.toggle-block').hide();
                    $('div.toggle-block').attr('aria-hidden', 'true');
                    $('div.toggle-block select').prop('disabled', true);
                    $('div.toggle-block select').attr('aria-disabled', 'true');
                    
                    // Show all <select> of the selected referential
                    var $referenceCode = $(this).val();
                    $('#ref-fiedlset-legend').html($('#selectReferentiel option[value=' + $referenceCode + ']').text());
                    
                    if ($referenceCode.length > 1) {
                        $('#ref-block').show();
                        $('#ref-block').attr('aria-hidden', 'false');

                        $('#' + $referenceCode + '-info-block').show();
                        $('#' + $referenceCode + '-info-block').attr('aria-hidden', 'false');
                        $('#' + $referenceCode + '-info-block select').prop('disabled', false);
                        $('#' + $referenceCode + '-info-block select').attr('aria-disabled', 'false');
                        $('#' + $referenceCode + '-test-block').show();
                        $('#' + $referenceCode + '-test-block').attr('aria-hidden', 'false');
                        $('#' + $referenceCode + '-test-block select').prop('disabled', false);
                        $('#' + $referenceCode + '-test-block select').attr('aria-disabled', 'false');
                        $('#' + $referenceCode + '-level-block').show();
                        $('#' + $referenceCode + '-level-block').attr('aria-hidden', 'false');
                        $('#' + $referenceCode + '-level-block select').prop('disabled', false);
                        $('#' + $referenceCode + '-level-block select').attr('aria-disabled', 'false');
                    }
                });
                
            })
        </script>
    </body>
</html>