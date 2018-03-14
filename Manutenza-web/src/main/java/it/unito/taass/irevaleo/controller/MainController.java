package it.unito.taass.irevaleo.controller;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet principale che funge da dispatcher per i sotto controller
 * (uno per ogni funzionalità del backend J2EE)
 * @author irene
 */
public class MainController extends HttpServlet {

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
	String action = request.getParameter("action"); //parametro action
	HttpSession s = request.getSession(); //se la sessione non esiste la crea
        
        System.out.println("ACTION = " + action);
        
        /* ### AVVIO DELL'APPLICAZIONE ### */
	if(action == null){
	    ctx.getRequestDispatcher("/index.html").forward(request, response); //vai alla index
	}
        
        else if(action.equals("home")) {
           ctx.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
        }
        
        else if(action.equals("registrati")) {
           ctx.getRequestDispatcher("/jsp/registratiFacebook.jsp").forward(request, response);
        }
        
        /* ### PAGINA REGISTRAZIONE UTENTE ### */
        else if(action.equals("nuovoUtente")) {
            ctx.getRequestDispatcher("/jsp/nuovoUtente.jsp").forward(request, response);
        }
        
        /* ### AZIONE REGISTRA NUOVO UTENTE ### */
        else if(action.equals("registraUtente")) {
            ctx.getNamedDispatcher("RegistraUtente").forward(request, response);
        }
        
        /* ### DASHBOARD ### */
        else if(action.equals("dashboard")){
            ctx.getRequestDispatcher("/jsp/dashboard.jsp").forward(request, response); //vai alla dashboard
        }
        
        /* ### VISUALIZZA DATI PROFILO ### */
        else if(action.equals("profilo")){
            ctx.getNamedDispatcher("Profilo").forward(request, response);
        }
        
        /* ### NUOVA RICHIESTA ### */
        else if(action.equals("nuovaRichiesta")){
            ctx.getRequestDispatcher("/jsp/nuovaRichiesta.jsp").forward(request, response); //vai alla pagina nuova richiesta
        }
        else if(action.equals("inviaRichiesta")){
            ctx.getNamedDispatcher("InviaRichiesta").forward(request, response);
        }
        
        else if(action.equals("goRegistrati")){
            ctx.getNamedDispatcher("GoRegistrati").forward(request, response);
        }
        
        /* ### RICHIESTE IN CORSO ### */
        else if(action.equals("richiesteInCorso")){
            //visualizza richieste in corso (lavori che devono ancora essere svolti)
            ctx.getRequestDispatcher("/jsp/richiesteInCorso.jsp").forward(request, response);
        }
        else if(action.equals("visualizzaProposte")){
            //visualizza tutte le proposte dei manutenti per la determinata richiesta
        }
        
        /* ### RICHIESTE PASSATE ### */
        else if(action.equals("richiestePassate")){
            //visualizza richieste passate (lavori commissionati e completati)
            ctx.getRequestDispatcher("/jsp/richiestePassate.jsp").forward(request, response);
        }
        else if(action.equals("valutaManutente")){
            //invia i dati della valutazione
            ctx.getNamedDispatcher("ValutaManutente").forward(request, response);
        }
        
        /* ### LE MIE COMPETENZE (solo ManUtente) ### */
        else if(action.equals("competenze")){
            //visualizza competenze di un ManUtente
        }
        
        /* ### PROPOSTE DI LAVORO (solo ManUtente) ### */
        else if(action.equals("nuoveProposte")){
            //visualizza nuove proposte di lavoro
        }
        
        /* ### PROPOSTE DI LAVORO ACCETTATE (solo ManUtente) ### */
        else if(action.equals("proposteAccettate")){
            //visualizza proposte di lavoro accettate (lavori accettati dal ManUtente ma che devono essere ancora fatti)
        }
        
        /* ### LAVORI COMPLETATI (solo ManUtente) ### */
        else if(action.equals("lavoriCompletati")){
            //visualizza lavori completati (lavori accettati e completati dal ManUtente)
        }
        
        /* ### AZIONI NON GESTITE -> ERRORE! ### */
	else {
	    ctx.getRequestDispatcher("/error.html").forward(request, response); //vai alla pagina di errore
	}
        
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
