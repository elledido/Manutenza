/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.irevaleo.controller;

import it.unito.taass.manutenza.ejb.GestoreManutenteLocal;
import it.unito.taass.manutenza.ejb.GestoreUtenteLocal;
import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Utente;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author leonardo
 */
public class VerificaUtente extends HttpServlet {

    @EJB
    private GestoreManutenteLocal gestoreManutente;

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

        String messaggio;
        String email = request.getParameter("email");

        //cerco un manutente con questa mail
        Manutente manutente = gestoreManutente.cercaManutente(email);

        //se non esiste
        if (manutente == null) {
            messaggio = "nonEsiste";
        } 
        //esiste un manutente o utente con tale mail
        else {
            messaggio = "esiste";
            HttpSession s = request.getSession(); //creo la sessione

            //verifico che se sia effettivamente un manutente
            if (manutente.getListaCompetenze() == null) {
                //è un utente, ricarico i dati con il gestore apposito
                Utente utente = gestoreUtente.caricaUtente(email);
                s.setAttribute("utente", utente);
                s.setAttribute("ruolo", "U");
                System.out.println("+++++++ UTENTE +++++++");
            }
            else {
                //è un manutente
                s.setAttribute("utente", manutente);
                s.setAttribute("ruolo", "M");
                System.out.println("+++++++ MANUTENTE +++++++");
            }
        }

        response.setContentType("text");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write(messaggio);

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
