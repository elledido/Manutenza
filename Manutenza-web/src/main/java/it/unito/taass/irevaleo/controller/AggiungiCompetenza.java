package it.unito.taass.irevaleo.controller;

import it.unito.taass.manutenza.ejb.GestoreManutenteLocal;
import it.unito.taass.manutenza.entities.Competenza;
import it.unito.taass.manutenza.entities.Manutente;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Aggiunge una nuova competenza alla lista delle competenze del Manutente in 
 * sessione
 * 
 * @author irene
 */
public class AggiungiCompetenza extends HttpServlet {

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
        
        ServletContext ctx = getServletContext(); //contesto della Servlet
        HttpSession s = request.getSession(); // sessione corrente
        
        //recupero i dati del manutente dalla sessione
        Manutente manutente = (Manutente) s.getAttribute("utente");
        
        /* dati del form */
        String categoria = request.getParameter("categoria");
        String tipo = request.getParameter("tipo");
        String partitaIVA = request.getParameter("partitaIVA");
        String zona = request.getParameter("zona");
        
        //creo la competenza
        Competenza competenza = new Competenza();
        competenza.setCategoria(categoria); //categoria
        competenza.setZonaDiCompetenza(zona); //zona di competenza
        competenza.setTipo(tipo); //professionista o amatoriale
        //se si tratta di un professionista
        if(tipo.equals("P")) {
            competenza.setPartitaIva(partitaIVA); //partita IVA
        }
        
        //aggiungi la competenza alla lista delle competenze del manutente
        manutente.addCompetenza(competenza);
        //aggiorna i dati del manutente nel DB
        gestoreManutente.aggiornaManutente(manutente);
        
        //torna alla pagina delle competenze
        String url = request.getContextPath() + "/MainController?action=competenze";
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
