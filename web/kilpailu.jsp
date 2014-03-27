<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pohja pageTitle="Kilpailu">


<div class="container">
    <h1>Valittu kilpailu: ${kilpailu}</h1>
               
    <ul>
        <p>
            <button type='button' class='btn-link expandable collapsed' data-toggle='collapse' data-target='#expandable_1'>Tarkastele lähtölistaa</button>
         
            <div id='expandable_1' class='collapse'>
        
                <section>
                    <figure
                        <p>Täällä ei ole vielä mitään.</p>
                    </figure>
                </section>
    
            </div>
        
        </p>

        <p>
            <button type="button" class="btn btn-success">Tarkastele lopputuloksia</button>        
        </p>

        <p>
            <button type="button" class="btn btn-warning">Väliaikapisteen tilanne</button>

                <select class="form-control">
                    <option>1</option>       
                    <option>2</option>            
                    <option>3</option>            
                    <option>4</option>          
                    <option>5</option>
                </select>
        </p>
    
        <p>
            <button type="button" class="btn btn-default">Kilpailijan sijoittuminen</button>
                
                <select class="form-control">
                    <option>Matti</option>
                    <option>Pekka</option>
                    <option>Erkki</option>          
                    <option>Uolevi</option>     
                </select>    
        </p>
    </ul>
</div>
    
</t:pohja>