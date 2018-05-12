/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.irevaleo.controller;

import it.unito.taass.manutenza.ejb.GestoreManutenteLocal;
import it.unito.taass.manutenza.entities.Competenza;
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
 *
 * @author leonardo
 */
public class RegistraManutente extends HttpServlet {
    
    @EJB(beanName = "GestoreManutente")
    private GestoreManutenteLocal gestoreManutente;

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
        HttpSession s = request.getSession();
       
        /* RECUPERO DELL'UTENTE DAL DB */
        String email = request.getParameter("emailManutenteDaCompletare");
        Manutente manutente = gestoreManutente.cercaManutente(email);
       
        /* CREAZIONE PRIMA COMPETENZA */
        String categoria1 = request.getParameter("categoria1");
        String tipo1 = request.getParameter("tipo1");
        String partitaIva1 = request.getParameter("partitaIva1");
        String zonaDiCompetenza1 = request.getParameter("zonaDiCompetenza1");
        
        Competenza competenza1 = new Competenza();
        competenza1.setCategoria(categoria1);
        competenza1.setTipo(tipo1);
        competenza1.setPartitaIva(partitaIva1);
        competenza1.setZonaDiCompetenza(zonaDiCompetenza1);
          
        /* CREAZIONE SECONDA COMPETENZA */
        Competenza competenza2 = new Competenza();
        String categoria2 = request.getParameter("categoria2");
        String tipo2 = request.getParameter("tipo2");
        String partitaIva2 = request.getParameter("partitaIva2");
        String zonaDiCompetenza2 = request.getParameter("zonaDiCompetenza2");
        
        /* INSERT DELLE COMPETENZE NELLA LISTA COMPETENZE DEL MANUTENTE */
        manutente.addCompetenza(competenza1);
        manutente.addCompetenza(competenza2);
        
        /* AGGIORNAMENO DEL MANUTENTE*/
        gestoreManutente.aggiornaManutente(manutente);
        
        s.setAttribute("manutente", manutente);
        
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
