package it.unito.taass.irevaleo.controller;

import it.unito.taass.manutenza.ejb.GestoreManutenteLocal;
import it.unito.taass.manutenza.entities.Competenza;
import it.unito.taass.manutenza.entities.Manutente;
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
        String email = (String) request.getParameter("email");

        Manutente manutente = gestoreManutente.cercaManutente(email);

        //numero massimo di campi competenza
        int numCompetenze = ((List) ctx.getAttribute("categorie")).size();

        Competenza competenza;
        String categoria;
        String zona;
        String tipo;
        String partitaIva;

        for (int i = 1; i <= numCompetenze; i++) {

            //resetto i dati di competenza
            competenza = null;

            /* dati del form */
            categoria = request.getParameter("categoria" + i);
            zona = request.getParameter("zona" + i);
            tipo = request.getParameter("tipo" + i);
            partitaIva = request.getParameter("partitaIVA" + i);

            System.out.println("COMPETENZA " + i + ": " + categoria + " " + zona + " " + tipo + " " + partitaIva);

            //se ho inserito l'ultima competenza al passo precedente
            if (categoria == null) {
                i = numCompetenze + 1; //lo forzo ad uscire dal ciclo
            } //inserisco la competenza nel DB
            else {
                competenza = new Competenza();
                competenza.setCategoria(categoria);
                competenza.setZonaDiCompetenza(zona);
                competenza.setTipo(tipo);

                //se si tratta di un professionista
                if (tipo.equals("P")) {
                    competenza.setPartitaIva(partitaIva); //partita IVA
                }

                manutente.addCompetenza(competenza);
            }

        }

        /* AGGIORNAMENO DEL MANUTENTE*/
        gestoreManutente.aggiornaManutente(manutente);

        s.setAttribute("utente", manutente);
        s.setAttribute("ruolo", "manutente");

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
