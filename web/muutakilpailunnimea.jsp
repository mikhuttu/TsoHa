<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:pohja pageTitle="Muuta kilpailun nimeä">

    <c:if test ="${kirjautunut == null}">
        <jsp:forward page="ohjaaKirjautumisSivulle"></jsp:forward>
    </c:if>

    <div class="container">


        <h1>Muuta kilpailun '${kilpailu.nimi}' nimeä</h1>

        <form action="muutakilpailunnimea?id=${kilpailu.id}" method="POST">
            <label for="uusi" class="col-md-2 control-label">Uusi nimi: </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="uusi">
            </div>
            
            <button type="submit" class="btn btn-default">Muuta nimeä</button>
        </form>
    </div>

</t:pohja>