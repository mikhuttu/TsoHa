<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:pohja pageTitle="Kilpailijan sijoittuminen matkalla">

    <div class="container">
        
        <h1>Kilpailu: <a href="/HiihtoTulosPalvelu/kilpailu?id=${kilpailu.id}">${kilpailu.nimi}</a></h1>
        
        <h2>Kilpailija: <a href="/HiihtoTulosPalvelu/kilpailija?id=${kilpailija.id}">${kilpailija.nimi}</a></h2>
        <h2>Väliaikapiste: <a href="/HiihtoTulosPalvelu/valiaikapiste?id=${valiaikapiste.id}">${valiaikapiste.numero}</a></h2>

        <ul>   
            <li>Sijoitus: ${sijoitus}</li>
            <li>Matkaa kärkeen: ${matkaakarkeen}</li>
            <li>Edellä seuraavasta: ${edellaseuraavasta}</li>
        </ul>  
    </div>

</t:pohja>