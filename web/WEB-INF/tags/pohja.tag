<%@tag description="HiihtoTulosPalvelun sivujen tägi" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<%@attribute name="pageTitle"%>

<!DOCTYPE html>
<html>
    <head>
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap-theme.css" rel="stylesheet">

        <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <meta charset="utf-8" />
        
        <title>${pageTitle}</title>
        
        <style>
            h1 { font-size: 1.6em; }
            h2 { font-size: 1.5em; }
        </style>

        <c:if test="${virheViesti != null}">
            <div class="alert alert-info">${ilmoitus}</div>
        </c:if>
        
    </head>
    
    <body>
        <ul class="nav nav-tabs">
            <li><a href="/HiihtoTulosPalvelu/etusivu">Etusivu</a></li>
            
            <d:if test ="${kirjautunut == null}">
                <li><a href="/HiihtoTulosPalvelu/kirjautuminen">Kirjaudu sisään</a></li>
            </d:if>
            
            <d:if test= "${kirjautunut!= null}">
                <li><a href="/HiihtoTulosPalvelu/lisaakilpailullenimi">Lisää kilpailu</a></li>
                <li><a href="/HiihtoTulosPalvelu/lisaakilpailijajarjestelmaan">Lisää kilpailija</a></li>
                <li><a href="/HiihtoTulosPalvelu/tuhoakilpailija">Poista kilpailija</a></li>
                <li><a href="/HiihtoTulosPalvelu/kirjauduulos">Kirjaudu ulos</a></li>
            </d:if>
        </ul>
        
        <jsp:doBody/>
    </body>
</html>