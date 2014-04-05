<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:pohja pageTitle="Kilpailun muokkaussivu">

    <h1>Muokkaa kilpailua: ${kilpailu.nimi}</h1>

    <br>
    
    <a href="/HiihtoTulosPalvelu/lahtolista?id="${kilpailu.id}>Laadi l�ht�lista</a>
    
    <br><br>
            
    <form action="lisaakilpailija?id=${kilpailu.id}" method="POST">
        <div class="form group">
        
            <label for="lisaa kilpailija">Lis�� kilpailija</label>
        
            <select name="lisaa kilpailija">
                <c:forEach var="kilpailija" items="${muutKilpailijat}">
                    <option name="kilpailija" value="${kilpailija.id}">${kilpailija.nimi}</option>
                </c:forEach>
            </select>

            <button type="submit" class="btn-success">Lis��</button>
        </div>
    </form>

    <br><br>
    
    <form action="lisaavaliaikapiste?id=${kilpailu.id}" method="POST">
        <button type="submit" class="btn-info">Lis�� uusi v�liaikapiste</button>
    </form>

    <br>
    
    <form action="kirjaatulos?id=${kilpailu.id}" method="POST">
        <div class="form-group">

            <label for="valiaikapiste">V�liaikapiste</label>
            
            <select name="valiaikapiste">
                <c:forEach var="valiaikapiste" items="${valiaikapisteet}">
                    <option name="valiaikapiste" value="${valiaikapiste.id}">${valiaikapiste.numero}</option>
                </c:forEach>
            </select>
            
            <br>
            
            <label for="osallistuja">Osallistuja</label>
            
            <select name="osallistuja">
                <c:forEach var="kilpailija" items="${osallistujat}">
                    <option name="kilpailija" value="${kilpailija.id}">${kilpailija.nimi}</option>
                </c:forEach>
            </select>
            
            <br>
            
            <label for="aika">Aika</label>
            <input type="text" name="aika" placeholder="00:00:00">
        
            <button type="submit" class="btn-primary">Kirjaa tulos</button>    
        </div>
    </form>

    <br><br>
    
    <form action="poistakilpailija?id=${kilpailu.id}" method="POST">
        <label for="poista kilpaija">Poista kilpailija</label>
        
        <select name="poista kilpailija">
             <c:forEach var="kilpailija" items="${osallistujat}">
                <option name="kilpailija" value="${kilpailija.id}">${kilpailija.nimi}</option>
             </c:forEach>   
         </select>
        
         <button type="submit" class="btn-warning">Poista kilpailija</button>
    </form>

    <br>
    
    <form action="poistavaliaikapiste?id=${kilpailu.id}" method="POST">
        <label for="poista valiaikapiste">Poista v�liaikapiste</label>
        
        <select name="poista valiaikapiste">
             <c:forEach var="valiaikapiste" items="${valiaikapisteet}">
                <option name="valiaikapiste" value="${valiaikapiste.id}">${valiaikapiste.numero}</option>
             </c:forEach>   
         </select>
        
         <button type="submit" class="btn-warning">Poista v�liaikapiste</button>
    </form>

    <br><br>
    

    
    <p>HUOM!<br>
        "Poista kilpailu" nappia painamalla kilpailu poistetaan v�litt�m�sti ja sit� on mahdotonta en�� saada k�ytt��n.</p>
    
    <form action="poistakilpailu?id=${kilpailu.id}" method="POST">
        <button type="submit" class="btn-danger">Poista kilpailu</button>
    </form>
    
</t:pohja>