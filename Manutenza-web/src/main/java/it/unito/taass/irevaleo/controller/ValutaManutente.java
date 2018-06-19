package it.unito.taass.irevaleo.controller;

import it.unito.taass.manutenza.ejb.GestoreManutenteLocal;
import it.unito.taass.manutenza.ejb.GestoreRichiesteLocal;
import it.unito.taass.manutenza.entities.Feedback;
import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Richiesta;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author irene
 */
public class ValutaManutente extends HttpServlet {

    @EJB
    private GestoreRichiesteLocal gestoreRichieste;

    @EJB
    private GestoreManutenteLocal gestoreManutente;
    
    
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
        
        /* campi del form */
        Long richiestaId = Long.parseLong(request.getParameter("richiestaId"));
        String manutenteEmail = request.getParameter("manutenteEmail");
        short vComplessiva = Short.parseShort(request.getParameter("val-complessiva"));
        short professionalita = Short.parseShort(request.getParameter("professionalita"));
        short comunicazione = Short.parseShort(request.getParameter("comunicazione"));
        String commento = request.getParameter("commento");
        
        //recupera la richiesta dal db
        Richiesta richiesta = gestoreRichieste.cercaRichiestaId(richiestaId);
        
        //recupera il manutente dal db (ricerca per email
        Manutente manutente = gestoreManutente.cercaManutente(manutenteEmail);
        
        //attuale valutazione del manutente
        int vManutente = manutente.getValutazioneComplessiva();
        
        //la nuova valutazione è la media con la valutazione precendente
        int val = (vManutente + vComplessiva)/2;
        //aggiornamento della valutazione complessiva relativa al Manutente (aggiornaManutente)
        manutente.setValutazioneComplessiva(val);
        gestoreManutente.aggiornaManutente(manutente);
        
        //recupero il manutente aggiornato dal db
        manutente = gestoreManutente.cercaManutente(manutenteEmail);
        
        //creazione oggetto feedback
        Feedback feedback = new Feedback();
        feedback.setRichiesta(richiesta);
        feedback.setManutente(manutente);
        feedback.setValutazioneComplessiva(vComplessiva);
        feedback.setProfessionalita(professionalita);
        feedback.setComunicazione(comunicazione);
        feedback.setCommento(commento);
        
        //salvataggio del feedback sul db
        
        //System.out.println("FORM DATA: " + richiestaId + " " + vComplessiva + " " + professionalita + " " + comunicazione + " " + commento);
        
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
