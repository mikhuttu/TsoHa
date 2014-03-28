<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:pohja pageTitle="Etusivu">

    <div class="container">
    
        <h1>Etusivu</h1>
    
        <ul class="nav nav-tabs">
            
            <c:if test= "${kirjautunut!= null}">
                <br>
                <li><a href="/HiihtoTulosPalvelu/kilpailumuokkaus">Kilpailumuokkaussivu</a></li>
                <li><a href="/HiihtoTulosPalvelu/kirjauduulos">Kirjaudu ulos</a></li>
                
            </c:if>
            
            <li class="active">Kilpailut</li>
              
        </ul>
                    <?php foreach(kilpailut as kilpailu): ?>
                        <a href="#"><? php echo kilpailu.nimi; ?></a>
                    <?php endforeach; ?>
        <br>
    
        <ul>
            <li><a href="#">Kilpailu 1</a></li>
            <li><a href="#">Kilpailu 2</a></li>
            <li><a href="#">Kilpailu 3</a></li>
            <li><a href="#">Kilpailu 4</a></li>
            <li><a href="#">Kilpailu 5</a></li>
        </ul>     
            
    </div>
    
</t:pohja>