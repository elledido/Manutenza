package it.unito.taass.irevaleo.controller;

import it.unito.taass.irevaleo.Utilita;
import it.unito.taass.manutenza.ejb.GestoreChatLocal;
import it.unito.taass.manutenza.ejb.GestoreManutenteLocal;
import it.unito.taass.manutenza.ejb.GestoreProposteLocal;
import it.unito.taass.manutenza.ejb.GestoreUtenteLocal;
import it.unito.taass.manutenza.entities.Chat;
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
 * Scarica dal DB i dati della chat relativi ad una certa proposta e cancella
 * dalla sessione i messaggi non letti per quella chat
 *
 * @author irene
 */
public class ApriChat extends HttpServlet {

    @EJB
    private GestoreChatLocal gestoreChat;

    @EJB
    private GestoreManutenteLocal gestoreManutente;

    @EJB
    private GestoreProposteLocal gestoreProposte;

    @EJB
    private GestoreUtenteLocal gestoreUtente;

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
        Manutente utenteSessione = (Manutente) s.getAttribute("utente");
        //email dell'utente in sessione
        String email = utenteSessione.getEmail();

        //dati della chat
        Long propostaId = Long.parseLong(request.getParameter("propostaId"));
        //dati dell'interlocutore
        Manutente interlocutore;

        //cerco la chat nel DB (ricerca per proopostaId)
        Chat chat = gestoreChat.ricercaPerProposta(propostaId);
        //se non esiste la creo
        if (chat == null) {
            String emailUtente = request.getParameter("emailUtente");
            String emailManutente = request.getParameter("emailManutente");
            gestoreChat.creaChat(gestoreProposte.cercaPerId(propostaId),
                    gestoreUtente.caricaUtente(emailUtente),
                    gestoreManutente.cercaManutente(emailManutente));

            chat = gestoreChat.ricercaPerProposta(propostaId);
        }
        
        //chi è l'interlocutore?
        if (email.equals(chat.getUtente().getEmail())) {
            interlocutore = chat.getManutente();
        } else {
            interlocutore = Utilita.utente2manutente(chat.getUtente());
        }
        
        //passo tutto l'oggetto chat
        request.setAttribute("chat", chat);
        request.setAttribute("interlocutore", interlocutore);
        
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
