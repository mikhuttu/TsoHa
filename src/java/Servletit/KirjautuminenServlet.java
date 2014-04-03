package Servletit;

import Mallit.Kayttaja;
import Mallit.Kirjautuminen;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class KirjautuminenServlet extends YleisServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        if (onkoKirjautunut(request) != null) {
            ohjaaSivulle("etusivu", response);
            return;
        }
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        
        try {
            out = response.getWriter();
        }
        catch (IOException e) {}
        
        try {
            String tunnus = request.getParameter("tunnus");
            String salasana = request.getParameter("salasana");
            
            
            if (tunnus == null || tunnus.length() == 0) {
                asetaVirhe("Kirjautuminen epäonnistui! Et antanut käyttäjätunnusta.", request);
                naytaJSP("kirjautuminen", request, response);
                return;
            }
            
            request.setAttribute("kayttaja", tunnus);
            
            if (salasana == null || salasana.length() == 0) {
                asetaVirhe("Kirjautuminen epäonnistui! Et antanut salasanaa.", request);
                naytaJSP("kirjautuminen", request, response);
                return;
            }
            
            Kayttaja kayttaja = new Kirjautuminen().etsiKayttajaTunnuksilla(tunnus, salasana);
            
            if (kayttaja != null) {
                talletaKirjautunut(request, kayttaja);
                ohjaaSivulle("etusivu", response);
            }
            
            else {
                asetaVirhe("Kirjautuminen epäonnistui! Antamasi tunnus tai salasana on väärä.", request);
                naytaJSP("kirjautuminen", request, response);
            }
            
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
        return "Short description";
    }
}