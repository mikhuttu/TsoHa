<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:pohja pageTitle="Kilpailijasivu">

    <div class="container">

        <h1>Kilpailija: ${kilpailija.nimi}</h1>

        <br>
        <c:if test ="${kirjautunut != null}">
            <li><a href="/HiihtoTulosPalvelu/kilpailijanmuokkaus?id=${kilpailija.id}">Muokkaa tietoja</a></li>
            <br>
        </c:if>

 
        <label for="kilpaillut kilpailuissa">Osallistunut kilpailuihin: </label>
        
        <ul>  
            <c:forEach var="kilpailu" items="${kilpailut}">
                <li><a href="/HiihtoTulosPalvelu/kilpailu?id=${kilpailu.id}">${kilpailu.nimi}</a></li>
            </c:forEach>
        </ul>
        
    </div>
</t:pohja>