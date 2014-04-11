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
        
        <br><br>
        
        <h1>Kilpailijat</h1>
        
        <br>
        
        <form action="kilpailija" method="POST">
            <div class="form group">

                <select id="kilpailija" name="kilpailija" onchange="return setValue();">
                    <c:forEach var="kilpailija" items="${kilpailijat}">
                        <option value="${kilpailija.id}">${kilpailija.nimi}</option>
                    </c:forEach>
                </select>
                
                <input type="hidden" name="kilpailija" id="kilpailija">
                <input type="submit" class="btn-info" value="Mene kilpailijan sivulle" name="btn_kilpailija">
            </div>
        </form>
    </div>

</t:pohja>