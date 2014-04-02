<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:pohja pageTitle="Etusivu">

    <div class="container">
    
        <h1>Etusivu</h1>
    
        <ul class="nav nav-tabs">
            
            <c:if test= "${kirjautunut!= null}">
                <br>
                <li><a href="/HiihtoTulosPalvelu/kilpailumuokkaus">Kilpailumuokkaussivu</a></li>
                <li><a href="/HiihtoTulosPalvelu/kirjauduulos">Kirjaudu ulos</a></li>
                
            </c:if>
            
            <li class="active">Kilpailut</li>
              
        </ul>
        
        <br>
    
        <ul>
            
            <c:forEach var="kilpailu" items="${kilpailut}">

                <a href="/HiihtoTulosPalvelu/kilpailu?id=${kilpailu.id}"> ${kilpailu.nimi}</a>

            </c:forEach>
        </ul>
            
    </div>
    
</t:pohja>