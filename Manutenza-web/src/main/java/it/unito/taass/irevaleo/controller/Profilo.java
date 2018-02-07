package it.unito.taass.irevaleo.controller;

import it.unito.taass.manutenza.ejb.GestoreUtenteLocal;
import it.unito.taass.manutenza.entities.Indirizzo;
import it.unito.taass.manutenza.entities.Utente;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Scarica i dati relativi al profilo dell'utente in sessione e li visualizza nella jsp corrispondente
 * @author irene
 */
public class Profilo extends HttpServlet {
    
    @EJB(beanName = "GestoreUtente")
    private GestoreUtenteLocal gestoreUtente;
    private Utente utente;

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
        
        ServletContext ctx = getServletContext(); //contesto dell'applicazione
        HttpSession s = request.getSession(); //sessione
        
        //Carico l'utente dal database
        utente = gestoreUtente.caricaUtente("giuseppeverdi@email.com", "giuseppeverdi");
      
        //Formatto la data di nascita e poi la trasformo in una stringa
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataDiNascita = sdf.format(utente.getDataDiNascita().getTime());
        
        //setto staticamente i dati da inviare alla jsp
        request.setAttribute("email", utente.getEmail()); //email
        request.setAttribute("nome", utente.getNome()); //nome
        request.setAttribute("cognome", utente.getCognome()); //cognome
        request.setAttribute("dataNascita", dataDiNascita); //data nascita
        request.setAttribute("cf", utente.getCodiceFiscale()); //codice fiscale
        
        //Prendo un indirizzo dalla lista degli indirizzi dell'utente
        //Al caricamento dell'utente dal db la sua listaIndirizzi è caricata automaticamente
        Indirizzo indirizzo = utente.getListaIndirizzi().get(0);
        
        //domicilio 1
        request.setAttribute("citta", indirizzo.getCitta()); //città
        request.setAttribute("provincia", indirizzo.getProvincia()); //provincia
        request.setAttribute("via", indirizzo.getVia()); //via
        request.setAttribute("cap", indirizzo.getCap()); //CAP
        
        ctx.getRequestDispatcher("/jsp/profilo.jsp").forward(request, response); //vai alla pagina del profilo
        
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
