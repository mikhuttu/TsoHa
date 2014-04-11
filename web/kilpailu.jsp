<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:pohja pageTitle="Kilpailu">

    <div class="container">
        <h1>Valittu kilpailu: ${kilpailu.nimi}</h1>

        <br>

        <c:if test ="${kirjautunut != null}">
            <li><a href="/HiihtoTulosPalvelu/kilpailumuokkaus?id=${kilpailu.id}">Muokkaa kilpailua</a></li>
            <br>
        </c:if>

        <div class="panel-group" id="accordio">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordio" href="#collapsOne">Tarkastele lähtölistaa</a>
                    </h4>
                </div>
                <div id="collapeOne" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <ul>
                            <c:forEach var="kilpailija" items="${kilpailijat}">
                                <li><a href="/HiihtoTulosPalvelu/kilpailija?id=${kilpailija.id}">${kilpailija.nimi}</a></li>
                            </c:forEach>

                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <br>

        <div class="panel-group" id="accordio">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordio" href="#collapsTwo">Tarkastele lopputuloksia</a>
                    </h4>
                </div>
                <div id="collapeTwo" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <ul>
                            <c:forEach var="tulos" items="${kilpailutulokset}">
                                <li><div class="${tulos.id}">Kilpailija: ${tulos.kilpailijaNimi}, Aika: ${tulos.aika}</div></li>
                                </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <br>

        <div class="panel-group" id="accordio">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordio" href="#collapsThree">Väliaikapisteen tilanne</a>
                    </h4>
                </div>
                <div id="collapeThree" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <ul>
                            <c:forEach var="valiaikapiste" items="${valiaikapisteet}">
                                <li><a href="/HiihtoTulosPalvelu/valiaikapiste=?id=${valiaikapiste.id}">Väliaikapiste ${valiaikapiste.numero}</a></li>
                                </c:forEach>   
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <br>

        <form action="kilpailijansijoittuminen" method="POST">
            <label>Kilpailijat</label>

            <select name="kilpailija">
                <c:forEach var="kilpailija" items="${kilpailijat}">
                    <option value="${kilpailija.id}" name="kilpailija" >${kilpailija.nimi}</option>
                </c:forEach>   
            </select>

            <label>Väliaikapisteet</label>

            <select name="valiaikapiste">
                <c:forEach var="valiaikapiste" items="${valiaikapisteet}">
                    <option value="${valiaikapiste.id}" name="valiaikapiste">${valiaikapiste.numero}</option>
                </c:forEach>   
            </select>

            <button type="submit" class="btn btn-default">Kilpailijan sijoittuminen</button>
        </form>
    </div>

</t:pohja>