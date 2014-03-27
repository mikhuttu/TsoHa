<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Etusivu">


    <div class="container">
    
        <h1>Etusivu</h1>
    
        <ul class="nav nav-tabs">
            <li class="active">Kilpailut</li>
            
            <c:if "${kirjautunut = true}">
                <li>>Ylläpito</li>
            </c:if>
            
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