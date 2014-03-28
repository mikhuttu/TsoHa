package Servletit;

import Mallit.Kayttaja;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class YleisServlet extends HttpServlet {
    
    protected void asetaVirhe(String ilmoitus, HttpServletRequest request) {
        request.setAttribute("virheIlmoitus", ilmoitus);
    }
    
    protected void ohjaaSivulle(String sivu, HttpServletResponse response) {
        try {
            response.sendRedirect(sivu);
        } 
        
        catch (IOException ex) {
            Logger.getLogger(YleisServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void naytaJSP(String sivu, HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = request.getRequestDispatcher(sivu + ".jsp");
        
        try {
            dispatcher.forward(request, response);
        }
        
        catch (ServletException e) {
            System.out.println("ServletException");
        } 
        
        catch (IOException e) {
            System.out.println("IOException");
        }
    }
    
    
    
    protected void talletaKirjautunut(HttpServletRequest request, Kayttaja kayttaja) {
        request.getSession().setAttribute("kirjautunut", kayttaja);
    }
    
    protected Kayttaja onkoKirjautunut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Kayttaja kayttaja = (Kayttaja) session.getAttribute("kirjautunut");
        
        return kayttaja;
    }
    
    protected void kirjauduUlos(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("kirjautunut");
    }
}