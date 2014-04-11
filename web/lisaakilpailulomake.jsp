<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:pohja pageTitle="Lis�� kilpailun nimi">    
    
    <c:if test ="${kirjautunut == null}">
        <jsp:forward page="ohjaaKirjautumisSivulle"></jsp:forward>
    </c:if>
    
    <div class="container">

        <h1>Lis�� kilpailu</h1>

        <form action="lisaakilpailu" method="POST">
            <label for="nimi" class="col-md-2 control-label">Kilpailun nimi: </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="nimi">
            </div>

            <button type="submit" class="btn btn-default">Lis�� kilpailu</button>
        </form>
    </div>

</t:pohja>