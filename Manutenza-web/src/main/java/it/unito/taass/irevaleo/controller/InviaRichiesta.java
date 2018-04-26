/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.irevaleo.controller;

import it.unito.taass.irevaleo.Utilita;
import it.unito.taass.manutenza.ejb.GestoreRichiesteLocal;
import it.unito.taass.manutenza.entities.Foto;
import it.unito.taass.manutenza.entities.Indirizzo;
import it.unito.taass.manutenza.entities.Utente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
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
public class InviaRichiesta extends HttpServlet {
    
    @EJB(beanName = "GestoreRichieste")
    private GestoreRichiesteLocal gestoreRichieste;

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
        HttpSession s = request.getSession();
        
        /* Utente che sta compilando la richiesta */
        Utente utente = (Utente)s.getAttribute("utente");
        
        /* campi del form */
        String titolo = request.getParameter("titolo");
        String categoria = request.getParameter("categoria");
        String descrizione = request.getParameter("descrizione");
        Indirizzo indirizzo = this.ceracIndirizzo(utente, request);
        float budget = Float.parseFloat(request.getParameter("budget"));
        List<Foto> listaFoto = this.caricaListaFoto(request);
        
        /*altri campi necessari per salvare la richiesta nel db */
        String statoCompletamento = Utilita.IN_ATTESA;
        Calendar dataDiCreazione = Calendar
                .getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
        Calendar dataDiCompletamento = null;
        
        //invia i dati al DB
        gestoreRichieste.createRichiesta(utente, 
            indirizzo, 
            titolo,
            descrizione,
            categoria,
            budget,
            listaFoto,
            dataDiCreazione,
            dataDiCompletamento,
            statoCompletamento);
                
        
        //vai alla pagina delle richieste
        
    }
 
    private Indirizzo ceracIndirizzo(Utente utente, HttpServletRequest request) {
        Long indirizzoId = Long.parseLong(request.getParameter("indirizzo"));
        Indirizzo daCercare = null;
        for(Indirizzo i : utente.getListaIndirizzi()) {
            if(i.getId().equals(indirizzoId)) {
                daCercare = i;
            }
        }
    return daCercare;
    }
    
    private List<Foto> caricaListaFoto(HttpServletRequest request) {
        String[] photos = request.getParameterValues("photos");
        List<Foto> listaFoto = new ArrayList<>();
        for(String photoLink : photos) {
            Foto foto = new Foto();
            foto.setLink(photoLink);
            listaFoto.add(foto);
        }
    return listaFoto;
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
