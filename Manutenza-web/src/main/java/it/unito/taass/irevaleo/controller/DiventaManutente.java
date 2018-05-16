/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.irevaleo.controller;

import it.unito.taass.irevaleo.Utilita;
import it.unito.taass.manutenza.ejb.GestoreManutenteLocal;
import it.unito.taass.manutenza.entities.Indirizzo;
import it.unito.taass.manutenza.entities.Manutente;
import java.io.IOException;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author leonardo
 */
public class DiventaManutente extends HttpServlet {
    
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
        //HttpSession s = request.getSession();
        
        /* DATI UTENTE PRELEVATI DALLA FORM */
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        Calendar dataDiNascita = Utilita.getDataDiNascita(request.getParameter("dataDiNascita"));
        String codiceFiscale = request.getParameter("codiceFiscale");
        String email = request.getParameter("email");
        
        /* DATI DOMICILIO 1 */
        String citta1 = request.getParameter("citta1");
        String via1 = request.getParameter("indirizzo1");
        String provincia1 = request.getParameter("provincia1");
        String cap1 = request.getParameter("cap1");
        
        /* DATI DOMICILIO 2 */
        String citta2 = request.getParameter("citta2");
        String via2 = request.getParameter("indirizzo2");
        String provincia2 = request.getParameter("provincia2");
        String cap2 = request.getParameter("cap2");
        
        /* DATI DOMICILIO 3 */
        String citta3 = request.getParameter("citta3");
        String via3 = request.getParameter("indirizzo3");
        String provincia3 = request.getParameter("provincia3");
        String cap3 = request.getParameter("cap3");
        
        /*CREAZIONE NUOVO MANUTENTE */
        Manutente manutente = new Manutente();
        manutente.setNome(nome);
        manutente.setCognome(cognome);
        manutente.setDataDiNascita(dataDiNascita);
        manutente.setCodiceFiscale(codiceFiscale);
        manutente.setEmail(email);
        manutente.setValutazioneComplessiva(0);
        manutente.setListaCompetenze(null);
        
        /* CREAZIONE DOMICILIO 1 */
        Indirizzo indirizzo = new Indirizzo();
        indirizzo.setVia(via1);
        indirizzo.setCitta(citta1);
        indirizzo.setProvincia(provincia1);
        indirizzo.setCap(cap1);
        manutente.addIndirizzo(indirizzo);
        
        //se l'utente ha inserito un secondo domicilio
        if(citta2 != null) {
            /* CREAZIONE DOMICILIO 2 */
            Indirizzo indirizzo2 = new Indirizzo();
            indirizzo2.setVia(via2);
            indirizzo2.setCitta(citta2);
            indirizzo2.setProvincia(provincia2);
            indirizzo2.setCap(cap2);
            manutente.addIndirizzo(indirizzo2);
        }
        
        //se l'utente ha inserito un terzo domicilio
        if(citta3 != null) {
            /* CREAZIONE DOMICILIO 3 */
            Indirizzo indirizzo3 = new Indirizzo();
            indirizzo3.setVia(via3);
            indirizzo3.setCitta(citta3);
            indirizzo3.setProvincia(provincia3);
            indirizzo3.setCap(cap3);
            manutente.addIndirizzo(indirizzo3);
        }
        
        //SALVO MANUTENTE NEL DB
        gestoreManutente.resgistraManutente(manutente);
        
        // passo a parametro email e indirizzi del nuovo ManUtente
        request.setAttribute("emailManutenteDaCompletare", email);
        request.setAttribute("indirizzi", manutente.getListaIndirizzi());
        
        ctx.getRequestDispatcher("/jsp/aggiungiCompetenze.jsp").forward(request, response);
        
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
