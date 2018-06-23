/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.irevaleo.controller;

import it.unito.taass.irevaleo.Utilita;
import it.unito.taass.manutenza.ejb.GestoreProposteLocal;
import it.unito.taass.manutenza.ejb.GestoreRichiesteLocal;
import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Proposta;
import it.unito.taass.manutenza.entities.Richiesta;
import it.unito.taass.manutenza.entities.Utente;
import java.io.IOException;
import java.util.ArrayList;
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
 * @author leonardo
 */
public class RichiesteInCorso extends HttpServlet {

    @EJB(beanName = "GestoreProposte")
    private GestoreProposteLocal gestoreProposte;
    
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
       
        HttpSession s = request.getSession();
        ServletContext ctx = getServletContext();
        
        Manutente manutente = (Manutente) s.getAttribute("utente");
        Utente utente = Utilita.manutente2utente(manutente);
        
        List<Richiesta> listaRichiesteInAttesa = gestoreRichieste.cercaRichieste(utente, Utilita.IN_ATTESA);
        List<Richiesta> listaRichiesteAccettate = gestoreRichieste.cercaRichieste(utente, Utilita.ACCETTATA);
        
        for(Richiesta r: listaRichiesteInAttesa){
            System.out.println(r.getId() + " " + r.getTitolo() + " " + r.getCategoria());
        }
        
        ArrayList<Proposta> listaProposteAccettate = new ArrayList();
        
        //cerco le proposte relative alle richieste accettate
        for(Richiesta r: listaRichiesteAccettate){
            Proposta p = gestoreProposte.cercaPropostaAccettata(r.getId());
            if(p != null){
                listaProposteAccettate.add(gestoreProposte.cercaPropostaAccettata(r.getId()));
            }
        }
        
        request.setAttribute("richiesteInAttesa", listaRichiesteInAttesa);
        request.setAttribute("proposteAccettate", listaProposteAccettate);
        
        ctx.getRequestDispatcher("/jsp/richiesteInCorso.jsp").forward(request, response);
        
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
