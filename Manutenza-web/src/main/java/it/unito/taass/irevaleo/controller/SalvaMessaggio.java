/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.irevaleo.controller;

import it.unito.taass.manutenza.ejb.GestoreChatLocal;
import it.unito.taass.manutenza.entities.Chat;
import it.unito.taass.manutenza.entities.Messaggio;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Salva i messaggi inviati da una certa chat sul DB
 * 
 * @author irene
 */
public class SalvaMessaggio extends HttpServlet {

    @EJB
    private GestoreChatLocal gestoreChat;

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
                
        /* dati del messaggio */
        Long propostaId = Long.parseLong(request.getParameter("propostaId"));
        String testo = request.getParameter("testo");
        String mittente = request.getParameter("mittente");
        
        //creo l'oggetto messaggio
        Messaggio messaggio = new Messaggio();
        messaggio.setTesto(testo);
        messaggio.setMittente(mittente);
        messaggio.setMessaggioTimestamp();
        
        //recupero la chat dal DB
        Chat chat = gestoreChat.ricercaPerProposta(propostaId);
        //aggiungo il nuovo messaggio in coda
        chat.getListaMessaggi().add(messaggio);
        
        //aggiorno la chat sul DB
        gestoreChat.updateChat(chat);
        
        response.setContentType("text");
        response.setHeader("Cache-Control", "no-cache");
        response.getWriter().write("messaggio salvato su DB");
        
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
