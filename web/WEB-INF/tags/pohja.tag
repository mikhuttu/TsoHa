<%@tag description="HiihtoTulosPalvelun sivujen tägi" pageEncoding="UTF-8"%>

<%@attribute name="pageTitle"%>

<!DOCTYPE html>
<html>
    <head>
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap-theme.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        
        <meta charset="utf-8" />
        
        <title>${pageTitle}</title>
        
        <style>
            h1 { font-size: 1.4em; }
        </style>
        
        <c:if test="${virheViesti != null}">
            <div class="alert alert-danger">${virheIlmoitus}</div>
        </c:if>
 
    </head>
    
    <body>
        <jsp:doBody/>
    </body>
</html>