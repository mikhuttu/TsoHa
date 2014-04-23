<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:pohja pageTitle="Kilpailijan sijoittuminen matkalla">

    <div class="container">
        
        <h1>Kilpailu: ${kilpailu.nimi}</h1>
        
        <h2>Kilpailija: ${kilpailija.nimi}</h2>
        <h2>V�liaikapiste: ${valiaikapiste.numero}</h2>

        <ul>   
            <li>Sijoitus: ${sijoitus}</li>
            <li>Matkaa k�rkeen: ${matkaakarkeen}</li>
            <li>Edell� seuraavasta: ${edellaseuraavasta}</li>
        </ul>  
    </div>

</t:pohja>