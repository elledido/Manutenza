/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.irevaleo.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
        
        //setto staticamente i dati da inviare alla jsp
        request.setAttribute("email", "example@mail.it"); //email
        request.setAttribute("nome", "Nome Account"); //nome
        request.setAttribute("cognome", "Cognome Account"); //cognome
        request.setAttribute("dataNascita", "01/01/1970"); //data nascita
        request.setAttribute("cf", "XXXXXX00X00X000X"); //codice fiscale
        
        //domicilio 1
        request.setAttribute("citta", "città"); //città
        request.setAttribute("provincia", "PR"); //provincia
        request.setAttribute("via", "via 1"); //via
        request.setAttribute("cap", "00000"); //CAP
        
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
