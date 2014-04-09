package Servletit;

import Mallit.Kilpailija;
import Mallit.Tulos;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KirjaaTulosServlet extends YleisServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        
        if (ohjaaKirjautumisSivulleJosEiKirjautunut(request, response)) {
            return;
        }
        
        int kilpailuId = haeId(request);
        
        int osallistujaId = haeIntArvo("kilpailija", request);
        int valiaikapisteId = haeIntArvo("valiaikapiste", request);
        String aika = haeStringArvo("aika", request);
        
        if (ohjaaKilpailuSivulleJosArvoaEiValittu(request, response, osallistujaId, kilpailuId) || 
            ohjaaKilpailuSivulleJosArvoaEiValittu(request, response, valiaikapisteId, kilpailuId) ||
            ohjaaKilpailuSivulleJosArvoaEiValittu(request, response, aika, kilpailuId)) {
            
            tallennaIlmoitus("Kilpailija-, väliaikapiste- tai aikakenttä oli jätetty tyhjäksi.", request);
            return;
        }
        
        if (! aikaOnValidi(aika)) {
            String paivitys = "Valittu aika ei ollut muotoa 'hours:minutes:seconds'";
            ohjaaKilpailuSivulle(paivitys, request, response, kilpailuId);
            return;
        }
        String aikaIlmanPisteita = poistaKaksoisPisteet(aika);
        
        Kilpailija kilpailija = new Kilpailija().haeKilpailija(osallistujaId);
        
        PrintWriter out = luoPrintWriter(response);

        try {
            Tulos tulos = new Tulos().haeTulosKorkeimmallaIdlla();
            
            new Tulos().kirjaaTulos(getId(tulos) + 1, aikaIlmanPisteita, osallistujaId, valiaikapisteId);
            
            String paivitys = "Kilpailijan " + kilpailija.getNimi() + " aika (" + aika + ") kirjattiin onnistuneesti!";
            ohjaaKilpailuSivulle(paivitys, request, response, kilpailuId);
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
        return "Kilpailijan tuloksen lisäämisen kilpailuun.";
    }
    
    private String poistaKaksoisPisteet(String aika) {
        String palautettava = "";
        int i = 0;
        while (i < aika.length()) {
            if (aika.charAt(i) != ':') {
                palautettava += aika.charAt(i);
            }
            i++;
        }
        return palautettava;
    }
    
    private boolean aikaOnValidi(String aika) {
        if (aika.length() != 8 || aika.charAt(2) != ':' || aika.charAt(5) != ':') {
            return false;
        }
        
        String tunnit = aika.charAt(0) + "" + aika.charAt(1) + "";
        String minuutit = aika.charAt(3) +  "" + aika.charAt(4) + "";
        String sekunnit = aika.charAt(6) +  "" + aika.charAt(7) + "";
        
        int t;
        int m;
        int s;
        
        try {
            t = Integer.parseInt(tunnit);
            m = Integer.parseInt(minuutit);
            s = Integer.parseInt(sekunnit);
        }
        catch (NumberFormatException e) {
            return false;
        }
        
        if (t >= 0 && t < 100) {
            if (m >= 0 && m < 60) {
                if (s >= 0 && s < 60) {
                    return true;
                }
            }
        }
        return false;
    }
}