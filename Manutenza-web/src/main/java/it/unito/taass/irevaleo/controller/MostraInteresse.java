package it.unito.taass.irevaleo.controller;

import it.unito.taass.manutenza.ejb.GestoreProposteLocal;
import it.unito.taass.manutenza.ejb.GestoreRichiesteLocal;
import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Richiesta;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author irene
 */
public class MostraInteresse extends HttpServlet {

    @EJB
    private GestoreRichiesteLocal gestoreRichieste;

    @EJB
    private GestoreProposteLocal gestoreProposte;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        ServletContext ctx = getServletContext(); //contesto della Servlet
	HttpSession s = request.getSession(); //sessione
        
        //recupero il Manutente in sessione
        Manutente manutente = (Manutente) s.getAttribute("utente");
        
        //id della richiesta
        Long richiestaId = Long.parseLong(request.getParameter("richiestaId"));
        //prezzo proposto
        Float prezzo = Float.parseFloat(request.getParameter("prezzo"));
        
        //recupero la richiesta dal DB
        Richiesta richiesta = gestoreRichieste.cercaRichiestaId(richiestaId);
        
        //creo l'oggetto proposta
        gestoreProposte.creaProposta(manutente, richiesta, prezzo);
        
        //torna alla pagina delle nuove proposte
        String url = request.getContextPath() + "/MainController?action=nuoveProposte";
        response.sendRedirect(url);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
