<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:pohja pageTitle="Kilpailu">


<div class="container">
    <h1>Valittu kilpailu: ${kilpailu.nimi}</h1>
               
    <br>
    
    <c:if test ="${kirjautunut != null}">
        <li><a href="/HiihtoTulosPalvelu/kilpailumuokkaus=?id=${kilpailu.id}">Muokkaa kilpailua</a></li>
        <br>
    </c:if>
        
        <div class="panel-group" id="accordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                            Tarkastele lähtölistaa
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <ul>
                            <c:forEach var="kilpailija" items="${kilpailijat}">
                                <div class="${kilpailija.id}">${kilpailija.nimi}</div>
                            </c:forEach>
                            
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    
        <br>
    
        <div class="panel-group" id="accordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                            Tarkastele lopputuloksia
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <ul>
                            <c:forEach var="kilpailutulos" items="${kilpailutulokset}">
                                <li><div class="${kilpailutulos.id}">${kilpailutulos.kilpailija}, ${kilpailutulos.aika}</div></li>
                            </c:forEach>
                            
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    
        <br>
        
        <div class="panel-group" id="accordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                            Väliaikapisteen tilanne
                        </a>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <ul>
                            <c:forEach var="valiaikapiste" items="${valiaikapisteet}">                   
                                <li><a href="/HiihtoTulosPalvelu/valiaikapiste=?id=${valiaikapiste.id}">Väliaikapiste ${valiaikapiste.id}</a></li>
                            </c:forEach>
                            
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        
        <br>
        
            <button type="button" class="btn btn-default">Kilpailijan sijoittuminen</button>
                <label>Kilpailijat</label>
                
                <select name="kilpailija">
                    <c:forEach var="kilpailija" items="${kilpailijat}">
                        <option value="${kilpailija.id}">${kilpailija.nimi}</option>
                    </c:forEach>   
                </select>
                
                <label>Väliaikapisteet</label>
            
                <select name="kilpailija">
                    <c:forEach var="kilpailija" items="${kilpailijat}">
                        <option value="${kilpailija.id}">${kilpailija.nimi}</option>
                    </c:forEach>   
                </select>
        </p>
</div>
    
</t:pohja>