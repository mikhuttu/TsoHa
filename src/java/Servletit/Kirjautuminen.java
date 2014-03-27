package Servletit;

import Mallit.Kayttaja;
import Mallit.KirjautuminenMalli;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Kirjautuminen extends YleisServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        if (onkoKirjautunut(request)) {
            ohjaaSivulle(response, "etusivu");
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
                naytaSivu("kirjautuminen", request, response);
                return;
            }
            
            request.setAttribute("kayttaja", tunnus);
            
            if (salasana == null || salasana.length() == 0) {
                asetaVirhe("Kirjautuminen epäonnistui! Et antanut salasanaa.", request);
                naytaSivu("kirjautuminen", request, response);
                return;
            }
            
            Kayttaja kayttaja = KirjautuminenMalli.etsiKayttajaTunnuksilla(tunnus, salasana);
            
            if (kayttaja != null) {
                talletaKirjautunut(request, kayttaja);
                ohjaaSivulle(response, "etusivu");
            }
            
            else {
                asetaVirhe("Kirjautuminen epäonnistui! Antamasi tunnus tai salasana on väärä.", request);
                naytaSivu("kirjautuminen", request, response);
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
