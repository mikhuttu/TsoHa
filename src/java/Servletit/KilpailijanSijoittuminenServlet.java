package Servletit;

import Mallit.Kilpailija;
import Mallit.Kilpailu;
import Mallit.Tulos;
import Mallit.Valiaikapiste;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KilpailijanSijoittuminenServlet extends YleisServlet {
     
     /**
     * Haetaan valitun väliaikapisteen.
     * Näkymän puolella selvitetään näissä valitun kilpailijan sijoitus sekä kuinka kaukana hän on
     * kärjestä sekä kuinka paljon edellä seuraavasta.
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {    
        response.setContentType("text/html;charset=UTF-8");
        
        int kilpailuId = haeId(request);
        int kilpailijaId = haeIntArvo("kilpailija", request);
        int valiaikapisteId = haeIntArvo("valiaikapiste", request);
        
        if (kilpailijaId == 0 || valiaikapisteId == 0) {
            ohjaaKilpailuSivulle("Kilpailijaa tai väliaikapistettä ei ollut valittu.", request, response, kilpailuId);
            return;
        }
        
        request.setAttribute("kilpailu", new Kilpailu().haeKilpailu(kilpailuId));
        request.setAttribute("kilpailija", new Kilpailija().haeKilpailija(kilpailijaId));
        request.setAttribute("valiaikapiste", new Valiaikapiste().haeValiaikapiste(valiaikapisteId));
        
        List<Tulos> tulokset = new Tulos().haeValiaikapisteenTulokset(valiaikapisteId);
        int sijoitus = haeSijoitus(tulokset, kilpailijaId);

        if (sijoitus == -1) {
            request.setAttribute("sijoitus", "Ei vielä saaapunut ko. väliaikapisteelle.");
        }
        
        else {
            String karkiaika = tulokset.get(0).getAika();
            String aika = tulokset.get(sijoitus).getAika();
            String seuraava = null;
            
            if (sijoitus + 1 < tulokset.size()) {
                seuraava = tulokset.get(sijoitus + 1).getAika();
            }
            
            request.setAttribute("sijoitus", sijoitus + 1);
            request.setAttribute("matkaakarkeen", matkaaKarkeen(request, karkiaika, aika));
            request.setAttribute("edellaseuraavasta", edellaSeuraavasta(aika, seuraava));
        }

        PrintWriter out = luoPrintWriter(response);
        
        try {
            paivitaIlmoitus(request);
            naytaJSP("kilpailijansijoittuminen", request, response);
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

    private int haeSijoitus(List<Tulos> tulokset, int kilpailijaId) {
        int i = 0;
        
        while (i < tulokset.size()) {
            
            if (tulokset.get(i).getKilpailija() == kilpailijaId) {
                return i;
            }
            i++;
        }
        
        return -1;
    }

    private String matkaaKarkeen(HttpServletRequest request, String karkiaika, String aika) {
        if (aika.equals(karkiaika)) {
            request.setAttribute("sijoitus", 1);
            return "-";
        }
        return laskeErotus(karkiaika, aika);
    }
    
    private String edellaSeuraavasta(String aika, String seuraava) {
        if (seuraava == null) {
            return "-";
        }
        return laskeErotus(aika, seuraava);
    }

    private String laskeErotus(String edella, String jaljessa) {
        int erotus = sekuntteina(jaljessa) - sekuntteina(edella);
        return aikana(erotus);
    }
    
    private int sekuntteina(String aika) {
        int tunnit = Integer.parseInt(aika.substring(0, 2));
        int minuutit = Integer.parseInt(aika.substring(3, 5));
        int sekunnit = Integer.parseInt(aika.substring(6, 8));
    
        return (tunnit * 60 + minuutit) * 60 + sekunnit;
    }
    
    private String aikana(int sekunnit) {
        int tunnit = sekunnit / 3600;
        
        int minuutitJaSekunnit = sekunnit % 3600;
        
        int minuutit = minuutitJaSekunnit / 60;
        sekunnit = minuutitJaSekunnit % 60;
        
        return Integer.toString(tunnit) + "h " + Integer.toString(minuutit) + "min " + Integer.toString(sekunnit) + "s";
    }
}
