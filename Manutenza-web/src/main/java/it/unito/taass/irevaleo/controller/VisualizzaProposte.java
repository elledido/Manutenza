package it.unito.taass.irevaleo.controller;

import it.unito.taass.manutenza.ejb.GestoreProposteLocal;
import it.unito.taass.manutenza.ejb.GestoreRichiesteLocal;
import it.unito.taass.manutenza.entities.Proposta;
import it.unito.taass.manutenza.entities.Richiesta;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Scarica dal DB i dati relativi alle proposte associate ad una certa richiesta
 * dell'utente in sessione
 *
 * @author irene
 */
public class VisualizzaProposte extends HttpServlet {

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
        Long richiestaId = Long.parseLong(request.getParameter("richiesta")); //id della richiesta
        
        //dati della richiesta (ricerca richiesta per id)
        Richiesta richiesta = gestoreRichieste.cercaRichiestaId(richiestaId);
        
        //proposte relative alla richiesta
        List<Proposta> proposte = gestoreProposte.cercaProposteRichieste(richiestaId);
        
        //titolo della richiesta
        request.setAttribute("titolo", richiesta.getTitolo());
        //elenco delle proposte
        request.setAttribute("proposte", proposte);
        
        ctx.getRequestDispatcher("/jsp/proposte.jsp").forward(request, response); //vai alla pagina delle proposte
        
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
