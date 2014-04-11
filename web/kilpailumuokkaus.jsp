<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:pohja pageTitle="Kilpailun muokkaussivu">

    <c:if test ="${kirjautunut == null}">
        <jsp:forward page="ohjaaKirjautumisSivulle"></jsp:forward>
    </c:if>
    
    <div class="container">
        <h1>Muokkaa kilpailua: ${kilpailu.nimi}</h1>

        <br>
        
        <a href="/HiihtoTulosPalvelu/kilpailunnimenmuokkaus?id=${kilpailu.id}">Muuta nimeä</a>

        <br><br>

        <form action="lisaakilpailijakilpailuun?id=${kilpailu.id}" method="POST">
            <div class="form group">

                <label for="lisaa kilpailija">Lisää kilpailija</label>

                <select id="kilpailija" name="kilpailija" onchange="return setValue();">
                    <c:forEach var="kilpailija" items="${muutKilpailijat}">
                        <option value="${kilpailija.id}">${kilpailija.nimi}</option>
                    </c:forEach>
                </select>

                <input type="hidden" name="lisaa" id="lisaa">
                <input type="submit" class="btn-info" value="Lisää kilpailija" name="btn_lisaa">

            </div>
        </form>

        <br><br>

        <form action="lisaavaliaikapiste?id=${kilpailu.id}" method="POST">
            <button type="submit" class="btn-info">Lisää uusi väliaikapiste</button>
        </form>

        <br>

        <form action="kirjaatulos?id=${kilpailu.id}" method="POST">
            <div class="form-group">

                <label for="valiaikapiste">Väliaikapiste</label>

                <select id="valiaikapiste" name="valiaikapiste" onchange="return setValue();">
                    <c:forEach var="valiaikapiste" items="${valiaikapisteet}">
                        <option name="valiaikapiste" value="${valiaikapiste.id}">${valiaikapiste.numero}</option>
                    </c:forEach>   
                </select>

                <br>

                <label for="kilpailija">Kilpailija</label>

                <select id="kilpailija" name="kilpailija" onchange="return setValue();">
                    <c:forEach var="kilpailija" items="${osallistujat}">
                        <option name="kilpailija" value="${kilpailija.id}">${kilpailija.nimi}</option>
                    </c:forEach>   
                </select>

                <br>

                <label for="aika">Aika</label>
                <input type="text" name="aika" placeholder="00:00:00">

                <input type="submit" class="btn-primary" value="Kirjaa tulos" name="btn_kirjaa">
            </div>
        </form>

        <br><br>

        <form action="poistakilpailijakilpailusta?id=${kilpailu.id}" method="POST">
            <label for="poista kilpaija">Poista kilpailija</label>

            <select id="kilpailija" name="kilpailija" onchange="return setValue();">
                <c:forEach var="kilpailija" items="${osallistujat}">
                    <option name="kilpailija" value="${kilpailija.id}">${kilpailija.nimi}</option>
                </c:forEach>   
            </select>

            <input type="hidden" name="poista" id="poista">
            <input type="submit" class="btn-warning" value="Poista kilpailija" name="btn_poista">

        </form>

        <br>

        <form action="poistavaliaikapiste?id=${kilpailu.id}" method="POST">
            <label for="poista valiaikapiste">Poista väliaikapiste</label>

            <select id="valiaikapiste" name="valiaikapiste" onchange="return setValue();">
                <c:forEach var="valiaikapiste" items="${valiaikapisteet}">
                    <option name="valiaikapiste" value="${valiaikapiste.id}">${valiaikapiste.numero}</option>
                </c:forEach>   
            </select>

            <input type="hidden" name="poista" id="poista">
            <input type="submit" class="btn-warning" value="Poista valiaikapiste" name="btn_poista">

        </form>

        <br><br>

        <p>HUOM!<br>
            "Poista kilpailu" nappia painamalla kilpailu poistetaan välittömästi ja sitä on mahdotonta enää takaisin.</p>

        <form action="poistakilpailu?id=${kilpailu.id}" method="POST">
            <button type="submit" class="btn-danger">Poista kilpailu</button>
        </form>
    </div>

</t:pohja>