package it.unito.taass.irevaleo.controller;

import it.unito.taass.irevaleo.Utilita;
import it.unito.taass.manutenza.ejb.GestoreProposteLocal;
import it.unito.taass.manutenza.ejb.GestoreRichiesteLocal;
import it.unito.taass.manutenza.entities.Proposta;
import it.unito.taass.manutenza.entities.Richiesta;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Aggiorna lo stato di un proposta ad "accettato" e lo stato della relativa 
 * richiesta da "in attesa" ad "accettata"
 *
 * @author irene
 */
public class AccettaProposta extends HttpServlet {

    @EJB
    private GestoreProposteLocal gestoreProposte;
    
    @EJB
    private GestoreRichiesteLocal gestoreRichieste;
    
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
        
        //id della proposta da aggiornare
        Long propostaId = Long.parseLong(request.getParameter("propostaId"));
        //id della richiesta da aggiornare
        Long richiestaId = Long.parseLong(request.getParameter("richiestaId"));
        
        //scarico l'oggetto richiesta dal DB
        Richiesta richiesta = gestoreRichieste.cercaRichiestaId(richiestaId);
        //aggiorno lo stato della richiesta
        richiesta.setStato(Utilita.ACCETTATA);
        gestoreRichieste.aggiornaRichiesta(richiesta);
        
        System.out.println(richiestaId);
        
        //scarico l'oggetto proposta dal DB
        Proposta proposta = gestoreProposte.cercaPerId(propostaId);
        
        System.out.println(proposta.getRichiesta().getStato());
        
        //aggiorno lo stato della proposta
        proposta.setAccettato(true);
        gestoreProposte.aggiornaProposta(proposta);
        
        response.setContentType("text");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("Proposta aggiornata");
        
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
