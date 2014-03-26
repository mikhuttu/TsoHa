<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Etusivu">
</t:pohja>

<div class="container">
    
    <h1>Etusivu</h1>
    
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">Kilpailut</a></li>
        <li><a href="#">Ylläpito</a></li>
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