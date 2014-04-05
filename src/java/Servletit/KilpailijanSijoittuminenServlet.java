package Servletit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KilpailijanSijoittuminenServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
    
        String kilpailijaIdParam = request.getParameter("kilpailija");
        String valiaikapisteIdParam = request.getParameter("valiaikapiste");
        int kilpailijaId;
        int valiaikapisteId;
        
        try {
            kilpailijaId = Integer.parseInt(kilpailijaIdParam);
            kilpailijaId = Integer.parseInt(valiaikapisteIdParam);
        }
        catch (NumberFormatException e) {
            kilpailijaId = 0;
            valiaikapisteId = 0;
        }
    }
}
