package Servletit.lisays;

import Mallit.Kilpailu;
import Servletit.YleisServlet;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LisaaKilpailuServlet extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = luoPrintWriter(response);
        
        try {
            String nimi = haeStringArvo("nimi", request).trim();
            
            if (nimi == null || nimi.isEmpty()) {
                tallennaIlmoitus("Nimikenttä oli jätetty tyhjäksi. Uutta kilpailua ei lisätty.", request);
                ohjaaSivulle("lisaakilpailulomake", response);   
                return;
            }
            
            if (nimi.length() > 30) {
                tallennaIlmoitus("Kilpailun nimi saa olla max. 30 merkkiä pitkä. Antamasi nimi oli pituudeltaan " + nimi.length() + " merkkinen.", request);
                ohjaaSivulle("lisaakilpailulomake", response);   
                return;
            }
            
            if (new Kilpailu().lisaaKilpailu(nimi)) {
                tallennaIlmoitus("Nimellä '" + nimi + "' oleva kilpailu on jo olemassa. Valitse kilpailulle toinen nimi.", request);
                ohjaaSivulle("lisaakilpailulomake", response);
                return;
            }
            
            Kilpailu kilpailu = new Kilpailu().haeKilpailu(nimi);
            talletaSessionId(request, kilpailu.getId());
            
            paivitaIlmoitus(request);
            
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
        return "Lisää kantaan uuden kilpailun.";
    }
}
