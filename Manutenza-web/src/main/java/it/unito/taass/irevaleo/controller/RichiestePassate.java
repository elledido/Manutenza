package it.unito.taass.irevaleo.controller;

import it.unito.taass.irevaleo.Utilita;
import it.unito.taass.manutenza.ejb.GestoreProposteLocal;
import it.unito.taass.manutenza.ejb.GestoreRichiesteLocal;
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
 *
 * @author irene
 */
public class RichiestePassate extends HttpServlet {

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
        
        HttpSession s = request.getSession();
        ServletContext ctx = getServletContext();
        
        Manutente manutente = (Manutente) s.getAttribute("utente");
        Utente utente = Utilita.manutente2utente(manutente);
        
        /* ELENCO DELLE RICHIESTE COMPLETATE*/
        List<Richiesta> listaRichiesteCompletate = this.gestoreRichieste.cercaRichieste(utente, Utilita.COMPLETATA);
        List<Richiesta> listaRichiesteValutate = this.gestoreRichieste.cercaRichieste(utente, Utilita.VALUTATA);
        
        ArrayList<Proposta> listaLavoriCompletati = new ArrayList();
        
        //cerco le proposte relative alle richieste accettate
        for(Richiesta r: listaRichiesteCompletate){
            System.out.println("RICHIESTA: " + r.getId());
            listaLavoriCompletati.add(gestoreProposte.cercaPropostaAccettata(r.getId()));
        }
        
        /* MANCANO I FEEDBACK ASSOCIATI ALLE RICHIESTE VALUTATE!!! */
        
        request.setAttribute("lavoriCompletati", listaLavoriCompletati);
        request.setAttribute("richiesteValutate", listaRichiesteValutate);
        
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
