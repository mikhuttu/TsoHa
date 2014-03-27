<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Kilpailun muokkaussivu">

<h1>Muokkaa kilpailua: ${kilpailu}</h1>
        
<ul>
    <p>
        <button type="button" class="btn-default">Laadi lähtölista</button>
    </p>
            
    <p>
        <button type="button" class="btn-default">Muuta tietoja</button>
    </p>
            
    <p>
        <button type="button" class="btn-info">Lisää kilpailija</button>
                
            <select multiple class="form-control">
                <option>Matti</option>
                <option>Pekka</option>
                <option>Erkki</option>
                <option>Uolevi</option>
            </select>  
    </p>
            
    <p>
        <button type="button" class="btn-warning">Poista kilpailija</button>
                
            <select multiple class="form-control">
                <option>Matti</option>
                <option>Pekka</option>
                <option>Erkki</option>
                <option>Uolevi</option>
            </select>  
    </p>
            
    <p>
        <button type="button" class="btn-primary">Kirjaa tulos</button>
                
        <form role="form">
            <div class="form-group">
                     
                <p>
                    <label for="valiaikapiste">Väliaikapiste</label>
                    
                        <select multiple class="form-control" id="valiaikapiste">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                        </select>
                </p>
                
                <p>
                    <label for="kilpailija">Kilpailija</label>
                    
                        <select multiple class="form-control" id="kilpailija">
                            <option>Matti</option>
                            <option>Pekka</option>
                            <option>Erkki</option>
                            <option>Uolevi</option>
                        </select>   
                </p>
                
                <p>
                            
                    <label for="aika">Aika</label>
                        <input type="time" class="form-control" id="aika" placeholder="00:00:00">
                            
                </p>
            </div>
                    
                <button type="submit" class="btn btn-default">Lisää</button>
        </form>
    </p>
</ul>

</t:pohja>