package Servletit.lisays;

import Mallit.Kilpailija;
import Servletit.YleisServlet;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LisaaKilpailijaServlet extends YleisServlet {
 
    /**
     * Lisää tietokantaan uuden kilpailijan, mikäli kilpailijalle määrätty nimi-arvo on oikeanlainen.
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        if (ohjaaKirjautumisSivulleJosEiKirjautunut(request, response)) {
            return;
        }
        
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = luoPrintWriter(response);
        
        try {
            String nimi = haeStringArvo("nimi", request).trim();
            
            if (nimi == null || nimi.isEmpty()) {
                tallennaIlmoitus("Nimikenttä oli jätetty tyhjäksi. Uutta kilpailijaa ei lisätty.", request);
                ohjaaSivulle("lisaakilpailijalomake", response);   
                return;
            }
            
            if (nimi.length() > 50) {
                tallennaIlmoitus("Kilpailijan nimi saa olla max. 50 merkkiä pitkä. Antamasi nimi oli pituudeltaan " + nimi.length() + " merkkinen.", request);
                ohjaaSivulle("lisaakilpailijalomake", response);   
                return;
            }
            
            new Kilpailija().lisaaKilpailija(nimi);
            
            tallennaIlmoitus("Kilpailija '" + nimi + "' lisätty kantaan onnistuneesti!", request);
            ohjaaSivulle("etusivu", response);
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
