<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:pohja pageTitle="Muuta kilpailijan tietoja">

    <c:if test ="${kirjautunut == null}">
        <jsp:forward page="ohjaaKirjautumisSivulle"></jsp:forward>
    </c:if>

    <div class="container">

        <h1>Kilpailija: '${kilpailija.nimi}'</h1>
        
        <br>

        <form action="muutakilpailijannimea?id=${kilpailija.id}" method="POST">
            <h2>Muuta kilpailijan nime�</h2>
            
            <label for="uusi" class="col-md-2 control-label">Uusi nimi: </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="uusi">
            </div>

            <button type="submit" class="btn btn-default">Muuta nime�</button>
        </form>

        <br><br>

        <p>HUOM!<br>
            "Poista kilpailija" nappia painamalla kilpailija poistetaan v�litt�m�sti ja sit� on mahdotonta en�� takaisin.</p>

        <form action="poistakilpailija?id=${kilpailija.id}" method="POST">
            <button type="submit" class="btn-danger">Poista kilpailija</button>
        </form>

    </div>
</t:pohja>