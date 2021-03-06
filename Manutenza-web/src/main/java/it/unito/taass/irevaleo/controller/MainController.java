package it.unito.taass.irevaleo.controller;

import it.unito.taass.manutenza.ejb.GestoreCategorieLocal;
import it.unito.taass.manutenza.entities.Categoria;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet principale che funge da dispatcher per i sotto controller
 * (uno per ogni funzionalità del backend J2EE)
 * 
 * @author irene
 */
public class MainController extends HttpServlet {

    @EJB(beanName = "GestoreCategorie")
    private GestoreCategorieLocal gestoreCategorie;

    /**
     * Salva l'elenco delle categorie nel contesto dell'applicazione
     * 
     * @param conf
     * @throws ServletException 
     */
    @Override
    public void init(ServletConfig conf) throws ServletException {
	
	super.init(conf); //metodo della superclasse
        
	//recupero l'elenco delle categorie dal db
        List<Categoria> listaCategorie = gestoreCategorie.caricaListaCategorie();
        
	//salva l'elenco delle categorie nel contesto dell'applicazione
	getServletContext().setAttribute("categorie", listaCategorie); 
	
    }
    
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
        
        /* ### AVVIO DELL'APPLICAZIONE ### */
	if(action == null){
            //vai alla homepage
	    ctx.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
	}
        
        /* ### HOMEPAGE ### */
        else if(action.equals("home")) {
            //vai alla homepage
           ctx.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
        }
                
        /* ### LOGIN e REGISTRAZIONE UTENTE ### */
        else if(action.equals("goRegistrati")){
            //va alla pagina di completamento della registrazione
            ctx.getNamedDispatcher("GoRegistrati").forward(request, response);
        }
        else if(action.equals("registraUtente")) {
            //registra un nuovo utente nel DB
            ctx.getNamedDispatcher("RegistraUtente").forward(request, response);
        }
        else if(action.equals("diventaManutente")) {
            //registra i dati anagrafici di un nuovo manutente nel DB
            ctx.getNamedDispatcher("DiventaManutente").forward(request, response);
        }
        else if(action.equals("registraManutente")) {
            //aggiunge i dati relativi alle competenze del nuovo manutente
            ctx.getNamedDispatcher("RegistraManutente").forward(request, response);
        }
        
        /* ### DASHBOARD ### */
        else if(action.equals("dashboard")){
            //vai alla dashboard
            ctx.getRequestDispatcher("/jsp/dashboard.jsp").forward(request, response);
        }
        
        /* ### VISUALIZZA DATI PROFILO ### */
        else if(action.equals("profilo")){
            //va alla pagina del profilo
            ctx.getRequestDispatcher("/jsp/profilo.jsp").forward(request, response);
        }
        
        /* ### NUOVA RICHIESTA ### */
        else if(action.equals("nuovaRichiesta")){
            //vai alla pagina nuova richiesta
            ctx.getRequestDispatcher("/jsp/nuovaRichiesta.jsp").forward(request, response);
        }
        else if(action.equals("inviaRichiesta")){
            //salva i dati della nuova richiesta sul DB
            ctx.getNamedDispatcher("InviaRichiesta").forward(request, response);
        }
        
        /* ### RICHIESTE IN CORSO ### */
        else if(action.equals("richiesteInCorso")){
            //visualizza richieste in corso (lavori che devono ancora essere svolti)
            ctx.getNamedDispatcher("RichiesteInCorso").forward(request, response);
        }
        else if(action.equals("visualizzaProposte")){
            //visualizza tutte le proposte dei manutenti per la determinata richiesta
            ctx.getNamedDispatcher("VisualizzaProposte").forward(request, response);
        }
        
        /* ### RICHIESTE PASSATE ### */
        else if(action.equals("richiestePassate")){
            //visualizza richieste passate (lavori commissionati e completati)
            ctx.getNamedDispatcher("RichiestePassate").forward(request, response);
        }
        else if(action.equals("valutaManutente")){
            //invia i dati della valutazione
            ctx.getNamedDispatcher("ValutaManutente").forward(request, response);
        }
        
        /* ### LE MIE COMPETENZE (solo Manutente) ### */
        else if(action.equals("competenze")){
            //visualizza competenze di un Manutente
            ctx.getRequestDispatcher("/jsp/leMieCompetenze.jsp").forward(request, response);
        }
        else if(action.equals("aggiungiCompetenza")){
            //aggiunge una nuova competenza
            ctx.getNamedDispatcher("AggiungiCompetenza").forward(request, response);
        }
        
        /* ### PROPOSTE DI LAVORO (solo ManUtente) ### */
        else if(action.equals("nuoveProposte")){
            //visualizza richieste in attesa in cui zona e categoria coincidono con quella del manutente
            ctx.getNamedDispatcher("NuoveProposte").forward(request, response);
        }
        /* ### MOSTRA INTERESSE (solo ManUtente) ### */
        else if(action.equals("mostraInteresse")){
            //invia una notifica all'Utente
            ctx.getNamedDispatcher("MostraInteresse").forward(request, response);
        }
        
        /* ### PROPOSTE DI LAVORO ACCETTATE (solo ManUtente) ### */
        else if(action.equals("proposteAccettate")){
            //visualizza proposte di lavoro accettate (lavori accettati dal Manutente ma che devono essere ancora fatti)
            ctx.getNamedDispatcher("ProposteAccettate").forward(request, response);
        }
        
        /* ### LAVORI COMPLETATI (solo ManUtente) ### */
        else if(action.equals("lavoriCompletati")){
            //visualizza lavori completati (lavori accettati e completati dal ManUtente)
            ctx.getNamedDispatcher("LavoriCompletati").forward(request, response);
        }
        
        /* ### CHAT ### */
        else if(action.equals("apriChat")){
            //apre la chat tra utente e manutente
            ctx.getNamedDispatcher("ApriChat").forward(request, response);
        }
        
        /* ### CONTATTI ### */
        else if(action.equals("contatti")) {
            //va alla pagina dei contatti
           ctx.getRequestDispatcher("/jsp/contatti.jsp").forward(request, response);
        }
        
        /* ### LOGOUT ### */
	else if(action.equals("logout") && s.getAttribute("utente")!=null){
	    s.invalidate(); //distrugge la sessione
	    ctx.getRequestDispatcher("/jsp/home.jsp").forward(request, response); //vai alla homepage
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
