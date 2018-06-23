/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.irevaleo.controller;

import it.unito.taass.irevaleo.Utilita;
import it.unito.taass.manutenza.ejb.GestoreProposteLocal;
import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Proposta;
import java.io.IOException;
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
public class LavoriCompletati extends HttpServlet {

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
        
        ServletContext ctx = getServletContext(); //contesto della Servlet
	HttpSession s = request.getSession(); //sessione
        
        Manutente manutente = (Manutente) s.getAttribute("utente"); //manutente in sessione
        
        //lavori completati = proposte di lavoro completate ma non valutate dall'utente che le ha commissionate
        List<Proposta> lavoriCompletati = gestoreProposte.cercaProposteAccettate(manutente, Utilita.COMPLETATA);
        
        //lavori valutati = proposte di lavoro completate e valutare dall'utente che le ha commissionate
        List<Proposta> lavoriValutati = gestoreProposte.cercaProposteAccettate(manutente, Utilita.VALUTATA);
        
        request.setAttribute("lavoriCompletati", lavoriCompletati);
        request.setAttribute("lavoriValutati", lavoriValutati);
        
        ctx.getRequestDispatcher("/jsp/lavoriCompletati.jsp").forward(request, response);
        
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