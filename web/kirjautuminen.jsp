<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Kirjautuminen">

<div class="container">
    
    <h1>Kirjautumissivu</h1>
    
    <form class="form-horizontal" role="form" action="kirjautuminen" method="POST">
     
        <div class="form-group">
            <label for="tunnus" class="col-md-2 control-label">Käyttäjänimi: </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="tunnus" value="${kayttaja}">
            </div>
        </div>
     
        <div class="form-group">     
            <label for="salasana" class="col-md-2 control-label">Salasana: </label>
            <div class="col-md-10">    
                <input type="password" class="form-control" name="salasana">
            </div> 
        </div>
    
        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <div class="checkbox">
                    <label>
                        <input type="checkbox"> Muista kirjautuminen>
                    </label>
                </div> 
            </div>
        </div>
 
        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <button type="submit" class="btn btn-default">Kirjaudu sisään</button>
            </div>
        </div>
                
   </form>
</div>

</t:pohja>