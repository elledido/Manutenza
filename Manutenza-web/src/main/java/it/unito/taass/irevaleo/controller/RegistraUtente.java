package it.unito.taass.irevaleo.controller;

import it.unito.taass.irevaleo.Utilita;
import it.unito.taass.manutenza.ejb.GestoreUtenteLocal;
import it.unito.taass.manutenza.entities.Indirizzo;
import it.unito.taass.manutenza.entities.Utente;
import java.io.IOException;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistraUtente extends HttpServlet {

    @EJB(beanName = "GestoreUtente")
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

        ServletContext ctx = getServletContext();

        /* DATI UTENTE PRELEVATI DALLA FORM */
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        Calendar dataDiNascita = Utilita.getDataDiNascita(request.getParameter("dataDiNascita"));
        String codiceFiscale = request.getParameter("codiceFiscale");
        String email = request.getParameter("email");
        //String password = request.getParameter("password");

        /* DATI INDIRIZZO PRELEVATI DALLA FORM */
        String citta = request.getParameter("citta");
        String via = request.getParameter("indirizzo");
        String provincia = request.getParameter("provincia");
        String cap = request.getParameter("cap");

        /* CREAZIONE NUOVO UTENTE */
        Utente utente = new Utente();
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setDataDiNascita(dataDiNascita);
        utente.setCodiceFiscale(codiceFiscale);
        utente.setEmail(email);
        //utente.setPassword(password);
        
        /*CREAZIONE NUOVO INDIRIZZO UTENTE */
        Indirizzo indirizzo = new Indirizzo();
        indirizzo.setVia(via);
        indirizzo.setCitta(citta);
        indirizzo.setProvincia(provincia);
        indirizzo.setCap(cap);
        utente.addIndirizzo(indirizzo);

        /* REGISTRO UTENTE SU DB */
        gestoreUtente.registraUtente(utente);

        //inizializzo la sessione
        HttpSession s = request.getSession(); //creo la sessione

        //salva i dati dell'utente in sessione
        s.setAttribute("utente", utente);

        ctx.getRequestDispatcher("/jsp/dashboard.jsp").forward(request, response);

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
