<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:pohja pageTitle="V�liaikapistesivu">

    <div class="container">

        <h1>Kilpailu: <a href="/HiihtoTulosPalvelu/kilpailu?id=${kilpailu.id}"> ${kilpailu.nimi}</a></h1>
        <h2>V�liaikapiste: ${valiaikapiste.numero}</h2>

        <br>
 
        <label for="saapuneet">V�liaikapisteelle saapuneet: </label>
        
        <ul>  
            <c:forEach var="tulos" items="${tulokset}">
                <li><div class="${tulos.id}">Kilpailija: ${tulos.kilpailijaNimi}, Aika: ${tulos.aika}</div></li>
            </c:forEach>
        </ul>
        
        <label for="eivat saapuneet">V�liaikapisteelle eiv�t ole viel� saapuneet: </label>
        
        <ul>  
            <c:forEach var="kilpailija" items="${eisaapuneet}">
                <li><div class="${kilpailija.id}">Kilpailija: ${kilpailija.nimi}</div></li>
            </c:forEach>
        </ul>
    
    </div>
</t:pohja>