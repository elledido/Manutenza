package it.unito.taass.irevaleo.controller;

import it.unito.taass.irevaleo.Utilita;
import it.unito.taass.manutenza.ejb.GestoreFeedbackLocal;
import it.unito.taass.manutenza.ejb.GestoreProposteLocal;
import it.unito.taass.manutenza.ejb.GestoreRichiesteLocal;
import it.unito.taass.manutenza.entities.Feedback;
import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Proposta;
import it.unito.taass.manutenza.entities.Richiesta;
import it.unito.taass.manutenza.entities.Utente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Scarica dal DB i dati relativi alle richieste passate fatte dall'utente in 
 * sessione, ovvero le richieste relative a lavori completati che hanno ricevuto
 * o meno una valutazione
 *
 * @author irene
 */
public class RichiestePassate extends HttpServlet {

    @EJB
    private GestoreFeedbackLocal gestoreFeedback;

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
        
        HttpSession s = request.getSession(); //sessione
        ServletContext ctx = getServletContext(); //contesto della servlet
        
        //utente in sessione
        Manutente manutente = (Manutente) s.getAttribute("utente");
        Utente utente = Utilita.manutente2utente(manutente);
        
        //richieste completate ma non ancora valutate
        List<Richiesta> richiesteCompletate = this.gestoreRichieste.cercaRichieste(utente, Utilita.COMPLETATA);
        //richieste completate e valutate
        List<Richiesta> richiesteValutate = this.gestoreRichieste.cercaRichieste(utente, Utilita.VALUTATA);
        
        //proposte relative alle richieste completate
        ArrayList<Proposta> lavoriCompletati = new ArrayList();
        
        for(Richiesta r: richiesteCompletate){
            lavoriCompletati.add(gestoreProposte.cercaPropostaAccettata(r.getId()));
        }
        
        //proposte relative alle richieste valutate
        ArrayList<Proposta> lavoriValutati = new ArrayList();
        //feedback relativi alle richieste valutate
        ArrayList<Feedback> feedbackProposte = new ArrayList();
        
        for(Richiesta r: richiesteValutate) {
            lavoriValutati.add(gestoreProposte.cercaPropostaAccettata(r.getId()));
            feedbackProposte.add(gestoreFeedback.cercaPerIdRichiesta(r.getId()));
        }
        
        request.setAttribute("lavoriCompletati", lavoriCompletati);
        request.setAttribute("lavoriValutati", lavoriValutati);
        request.setAttribute("feedbackProposte", feedbackProposte);
        
        ctx.getRequestDispatcher("/jsp/richiestePassate.jsp").forward(request, response);
        
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
