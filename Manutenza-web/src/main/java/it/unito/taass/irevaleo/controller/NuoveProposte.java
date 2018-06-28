package it.unito.taass.irevaleo.controller;

import it.unito.taass.irevaleo.Utilita;
import it.unito.taass.manutenza.ejb.GestoreProposteLocal;
import it.unito.taass.manutenza.ejb.GestoreRichiesteLocal;
import it.unito.taass.manutenza.entities.Competenza;
import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Proposta;
import it.unito.taass.manutenza.entities.Richiesta;
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
public class NuoveProposte extends HttpServlet {

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
        
        ServletContext ctx = getServletContext(); //contesto della Servlet
	HttpSession s = request.getSession(); //session
        
        Manutente manutente = (Manutente) s.getAttribute("utente"); //recupero il manutente in sessione
        String email = manutente.getEmail();
        List<Competenza> competenze = manutente.getListaCompetenze(); //competenze del manutente
        
        //nuove proposte di lavoro = richieste in cui utente!=manutenteInSessione, categoria=competenza.categoria, zona=zona.categoria, stato=inAttesa
        List<Richiesta> richiesteCategorie = new ArrayList();
        //nuove proposte
        List<Richiesta> nuoveProposte = new ArrayList();
        //richieste per cui il manutente ha già fatto una proposta e per cui attende che sia accettata
        List<Proposta> proposteInAttesa = new ArrayList();
        String categoria;
        String zona;
        Proposta p;
        
        //per ogni competenza del manutente
        for(Competenza c: competenze){
            categoria = c.getCategoria(); //categoria
            zona = c.getZonaDiCompetenza(); //zona di competenza
            richiesteCategorie.addAll(gestoreRichieste.cercaProposteLavoro(categoria, zona, email, Utilita.IN_ATTESA)); //richieste per una data competenza
        }
        
        //verifico che il manutente non abbia già avanzato una proposta per le varie richieste
        for(Richiesta r: richiesteCategorie){
            p = gestoreProposte.cercaPropostaRichiestaManutente(r.getId(), manutente);
            //se esiste già un proposta
            if(p!=null){
                proposteInAttesa.add(p);
            }
            //altrimenti è una nuova richiesta
            else {
                nuoveProposte.add(r);
            }
        }
        
        request.setAttribute("nuoveProposte", nuoveProposte);
        request.setAttribute("proposteInAttesa", proposteInAttesa);
        
        ctx.getRequestDispatcher("/jsp/nuoveProposte.jsp").forward(request, response);
        
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
