package it.unito.taass.irevaleo.controller;

import it.unito.taass.manutenza.ejb.GestoreUtenteLocal;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        HttpSession session = request.getSession();
        
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String data = request.getParameter("dataDiNascita");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar dataDiNascita = null;
        try {
            Date date = sdf.parse(data);
            dataDiNascita = Calendar.getInstance();
            dataDiNascita.setTime(date);
        } catch (ParseException ex) {
            Logger.getLogger(RegistraUtente.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        String codiceFiscale = request.getParameter("codiceFiscale");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        System.out.println("Nome: " + nome);
        System.out.println("Cognome: " + cognome);
        System.out.println("Data di Nascita: " + data);
        System.out.println("Codice Fiscale: " + codiceFiscale);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        
        System.out.println("Richiamo funzione gestoreUtente.registraUtente");
        gestoreUtente.registraUtente(nome, cognome, dataDiNascita, codiceFiscale, email, password);
   
        ctx.getRequestDispatcher("/jsp/nuovoUtente.jsp").forward(request, response);
        
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
