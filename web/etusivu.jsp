<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:pohja pageTitle="Etusivu">

    <div class="container">

        <h1>Kilpailut</h1>

        <ul>   
            <c:forEach var="kilpailu" items="${kilpailut}">
                <li><a href="/HiihtoTulosPalvelu/kilpailu?id=${kilpailu.id}"> ${kilpailu.nimi}</a></li>
            </c:forEach> 
        </ul>    
    </div>

</t:pohja>