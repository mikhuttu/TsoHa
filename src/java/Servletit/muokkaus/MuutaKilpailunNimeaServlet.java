package Servletit.muokkaus;

import Mallit.Kilpailu;
import Servletit.YleisServlet;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MuutaKilpailunNimeaServlet extends YleisServlet {
    
    /**
     * Muuttaa valitun kilpailun nimeä mikäli syötetty uusi nimi on oikeanlainen, ja
     * sen nimistä kilpailua ei ole vielä tietokannassa.
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        if (ohjaaKirjautumisSivulleJosEiKirjautunut(request, response)) {
            return;
        }
        
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = luoPrintWriter(response);
        
        try {
            int id = haeId(request);
            
            talletaSessionId(request, id);
            
            String uusi = haeStringArvo("uusi", request).trim();
            
            if (uusi == null || uusi.isEmpty()) {
                tallennaIlmoitus("Nimikenttä oli jätetty tyhjäksi. Nimeä ei muokattu.", request);
                ohjaaSivulle("kilpailunnimenmuokkaus", response);
                return;
            }
            
            if (uusi.length() > 30) {
                tallennaIlmoitus("Kilpailun nimi saa olla max. 30 merkkiä pitkä. Antamasi nimi oli pituudeltaan " + uusi.length() + " merkkinen.", request);
                ohjaaSivulle("kilpailunnimenmuokkaus", response);   
                return;
            }
            
            if (new Kilpailu().onOlemassa(uusi)) {
                tallennaIlmoitus("Nimellä '" + uusi + "' oleva kilpailu on jo olemassa. Valitse kilpailulle toinen nimi.", request);
                ohjaaSivulle("kilpailunnimenmuokkaus", response);
                return;
            }

            Kilpailu kilpailu = new Kilpailu().haeKilpailu(id);
            String vanha = kilpailu.getNimi();
            
            new Kilpailu().paivitaNimi(id, uusi);
            
            tallennaIlmoitus("Kilpailun '" + vanha + "' nimi vaihdettiin onnistuneesti!", request);  
            ohjaaSivulle("kilpailu", response);
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
        return "";
    }
}
