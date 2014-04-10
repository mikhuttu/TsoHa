package Servletit;

import Mallit.Kilpailija;
import Mallit.Kilpailu;
import Mallit.Tulos;
import Mallit.Valiaikapiste;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
    
public class KilpailuServlet extends YleisServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {    
        response.setContentType("text/html;charset=UTF-8");

        int id = haeId(request);
        
        Kilpailu kilpailu = new Kilpailu().haeKilpailu(id);
        request.setAttribute("kilpailu", kilpailu);
        request.setAttribute("kilpailijat", new Kilpailija().haeKilpailunKilpailijat(kilpailu));
        
        Valiaikapiste piste = new Valiaikapiste().haeValiaikapisteKorkeimmallaNumerolla(kilpailu);
        
        if (piste != null) {
            request.setAttribute("kilpailutulokset", new Tulos().haeValiaikapisteenTulokset(piste));
        }
        
        request.setAttribute("valiaikapisteet", new Valiaikapiste().haeKilpailunValiaikapisteet(kilpailu));
        
        PrintWriter out = luoPrintWriter(response);
        
        try {
            paivitaIlmoitus(request);
            naytaJSP("kilpailu", request, response);
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
        return "Näyttää kilpailusivun.";
    }
}