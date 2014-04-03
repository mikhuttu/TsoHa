<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:pohja pageTitle="Etusivu">

    <div class="container">
    
        <h1>Etusivu</h1>
    
        <ul class="nav nav-tabs">
            
            <li class="active"><a>Kilpailut</a></li>
            
            
            <c:if test ="${kirjautunut == null}">
                <li><a href="/HiihtoTulosPalvelu/kirjautuminen">Kirjaudu sisään</a></li>
            </c:if>
                
            <c:if test= "${kirjautunut!= null}">
                <li><a href="/HiihtoTulosPalvelu/kirjauduulos">Kirjaudu ulos</a></li>
            </c:if>
  
        </ul>
        
        <br>
        
        <ul>   
            <c:forEach var="kilpailu" items="${kilpailut}">
                <li><a href="/HiihtoTulosPalvelu/kilpailu?id=${kilpailu.id}"> ${kilpailu.nimi}</a></li>
            </c:forEach>   
        </ul>    
    </div>
    
</t:pohja>