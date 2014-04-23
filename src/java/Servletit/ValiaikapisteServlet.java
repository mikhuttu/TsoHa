package Servletit;

import Mallit.Kilpailija;
import Mallit.Kilpailu;
import Mallit.Tulos;
import Mallit.Valiaikapiste;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
    
public class ValiaikapisteServlet extends YleisServlet {

    /**
     * Hakee kilpailijat jotka ovat saapuneet ko. väliaikapisteelle sekä ne jotka ovat
     * vielä matkalla ja näyttää valiaikapiste.jsp:n.
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {    
        response.setContentType("text/html;charset=UTF-8");
        
        int valiaikapisteId = haeId(request);
        Kilpailu kilpailu = new Kilpailu().haeKilpailuJossaValiaikapiste(valiaikapisteId);
        
        request.setAttribute("valiaikapiste", new Valiaikapiste().haeValiaikapiste(valiaikapisteId));
        request.setAttribute("kilpailu", kilpailu);
        request.setAttribute("tulokset", new Tulos().haeValiaikapisteenTulokset(valiaikapisteId));
        
        request.setAttribute("eisaapuneet", new Kilpailija().haeEivatSaapuneetValiaikapisteelle(valiaikapisteId));
        
        PrintWriter out = luoPrintWriter(response);
        
        try {
            paivitaIlmoitus(request);
            naytaJSP("valiaikapiste", request, response);
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