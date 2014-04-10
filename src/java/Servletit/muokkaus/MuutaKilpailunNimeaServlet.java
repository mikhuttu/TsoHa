package Servletit.muokkaus;

import Mallit.Kilpailu;
import Servletit.YleisServlet;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MuutaKilpailunNimeaServlet extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        
        if (ohjaaKirjautumisSivulleJosEiKirjautunut(request, response)) {
            return;
        }
        
        PrintWriter out = luoPrintWriter(response);
        
        try {

            int id = haeId(request);
            talletaSessionId(request, id);
            
            String uusi = haeStringArvo("uusi", request).trim();
            
            if (uusi == null || uusi.isEmpty()) {
                tallennaIlmoitus("Nimikenttä oli jätetty tyhjäksi. Nimeä ei muokattu.", request);
                ohjaaSivulle("muutakilpailunnimi", response);
                return;
            }
            
            if (uusi.length() > 30) {
                tallennaIlmoitus("Kilpailun nimi saa olla max. 30 merkkiä pitkä. Antamasi kilpailu oli pituudeltaan " + uusi.length() + " merkkinen.", request);
                ohjaaSivulle("muutakilpailunnimi", response);   
                return;
            }
            
            if (new Kilpailu().onOlemassa(uusi)) {
                tallennaIlmoitus("Nimellä '" + uusi + "' oleva kilpailu on jo olemassa. Valitse kilpailulle toinen nimi.", request);
                ohjaaSivulle("muutakilpailunnimi", response);
                return;
            }

            new Kilpailu().paivitaNimi(id, uusi);

            Kilpailu kilpailu = new Kilpailu().haeKilpailu(id);
            
            tallennaIlmoitus("Kilpailun '" + kilpailu.getNimi() + "' nimi vaihdettiin onnistuneesti!", request);  
            ohjaaSivulle("kilpailumuokkaus", response);
        }
        
        finally {
            if (out != null) {
                out.close();
            }
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Muuttaa kannassa olevan kilpailun nimeä.";
    }
}
