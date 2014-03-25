<%@tag description="HiihtoTulosPalvelun sivujen tägi" pageEncoding="UTF-8"%>

<%@attribute name="pageTitle"%>

<!DOCTYPE html>
<html>
    <head>
        <title${pageTitle}</title>
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap-theme.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">  
    </head>
    
    <body>
        <jsp:doBody/>
    </body>
</html>    
    