<%@page pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <c:set var="title" value="Recherche par URL - KBAccess"/>
    <%@include file='/WEB-INF/template/head.jspf'%>
    <body>
        <%@include file='/WEB-INF/template/header.jspf' %>
        
        <div class="container">
            <%@include file='/WEB-INF/template/breadcrumb-trail.jspf' %>

            <div class="page-header"><h1>Recherche par URL</h1></div>

            <div class="row">
                <p>Il est possible de chercher des <span lang="en">testcases</span> sans passer par le formulaire de recherche, en saisissant dans l'URL directement ce que l'on cherche.</p>
                <p>Les formalismes possibles sont les suivants&nbsp;:</p>
                <dl>
                    <dt>Par test&nbsp;:</dt>
                    <dd>
                        <p>
                        <code>http://www.kbaccess.org/<span style="font-weight: bold;">kba</span>/ &lt;code-r&egrave;f&egrave;rentiel&gt; / &lt;code-test&gt;  </code><br/>
                        Exemple&nbsp;: <a href="http://www.kbaccess.org/kba/AW21/1.1.1/">http://www.kbaccess.org/kba/AW21/1.1.1/</a>
                        </p>
                    </dd>
                    <dt>Par r&egrave;sultat&nbsp;:</dt>
                    <dd>
                        <p>
                        <code>http://www.kbaccess.org/<span style="font-weight: bold;">kba</span>/ &lt;code-r&egrave;f&egrave;rentiel&gt; / &lt;code-résultat&gt;  </code><br/>
                        Exemple&nbsp;: <a href="http://www.kbaccess.org/kba/AW21/Failed/">http://www.kbaccess.org/kba/AW21/Failed/</a>
                        </p>
                    </dd>
                    <dt>Par test et par résultat&nbsp;:</dt>
                    <dd>
                        <p>
                        <code>http://www.kbaccess.org/<span style="font-weight: bold;">kba</span>/ &lt;code-r&egrave;f&egrave;rentiel&gt; / &lt;code-test&gt; / &lt;code-résultat&gt; </code><br/>
                        Exemple&nbsp;: <a href="http://www.kbaccess.org/kba/AW21/1.1.1/Passed">http://www.kbaccess.org/kba/AW21/1.1.1/Passed/</a>
                        </p>
                    </dd>
                </dl>

            </div>
        </div>

        <%@ include file='/WEB-INF/template/footer.jspf' %>
    </body>
</html>