package Servletit.muokkaus;

import Mallit.Kilpailija;
import Servletit.YleisServlet;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MuutaKilpailijanNimeaServlet extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = luoPrintWriter(response);
        
        try {
            int id = haeId(request);
            
            talletaSessionId(request, id);
            
            String uusi = haeStringArvo("uusi", request).trim();
            
            if (uusi == null || uusi.isEmpty()) {
                tallennaIlmoitus("Nimikenttä oli jätetty tyhjäksi. Uutta kilpailijaa ei lisätty.", request);
                ohjaaSivulle("kilpailijanmuokkaus", response);   
                return;
            }
            
            if (uusi.length() > 50) {
                tallennaIlmoitus("Kilpailijan nimi saa olla max. 50 merkkiä pitkä. Antamasi nimi oli pituudeltaan " + uusi.length() + " merkkinen.", request);
                ohjaaSivulle("kilpailijanmuokkaus", response);   
                return;
            }
            
            Kilpailija kilpailija = new Kilpailija().haeKilpailija(id);
            String vanha = kilpailija.getNimi();
            
            new Kilpailija().paivitaNimi(id, uusi);
            
            tallennaIlmoitus("Kilpailijan '" + vanha + "' nimi vaihdettiin onnistuneesti!", request);  
            ohjaaSivulle("kilpailija", response);
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
        return "Muuttaa kannassa olevan kilpailijan nimeä.";
    }
}
