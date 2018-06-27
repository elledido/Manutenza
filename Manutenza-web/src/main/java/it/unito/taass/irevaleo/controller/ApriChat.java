package it.unito.taass.irevaleo.controller;

import it.unito.taass.irevaleo.Utilita;
import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Utente;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Scarica dal DB i dati della chat relativi ad una certa proposta e cancella 
 * dalla sessione i messaggi non letti per quella chat
 * @author irene
 */
public class ApriChat extends HttpServlet {

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
	HttpSession s = request.getSession(); //sessione in corso
        
        /* Utente/Manutente che sta aprendo la chat */
        Manutente manutente = (Manutente) s.getAttribute("utente");
        Utente utente = Utilita.manutente2utente(manutente);
        
        //email dell'utente in sessione
        String email = utente.getEmail();
        
        //dati della chat
        String propostaId = request.getParameter("propostaId");
        String emailUtente = request.getParameter("mailUtente");
        String emailManutente = request.getParameter("mailManutente");
        
        //nome e cognome dell'interlocutore
        String interlocutore;
        
        //chi è l'interlocutore?
        if(email.equals(emailUtente)){
            //interlocutore = chat.getManutente().getCognome() + " " + chat.getManutente().getNome();
        }
        else if(email.equals(emailManutente)) {
            //interlocutore = chat.getUtente().getCognome() + " " + chat.getUtente().getNome();
        }
        
        //messaggi non letti per quella proposta (li devo eliminare dalla sessione)
        
        //cerco la chat nel DB (ricerca per proopostaId)
        //se non esiste la creo
        
        //passo tutto l'oggetto chat
        //request.setAttribute("chat", chat);
        //request.setAttribute("interlocutore", interlocutore);
        
        //vai alla pagina della chat
        ctx.getRequestDispatcher("/jsp/chat.jsp").forward(request, response);
        
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
