/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.manutenza.ejb.impl;

import it.unito.taass.manutenza.ejb.GestoreRichiesteLocal;
import it.unito.taass.manutenza.entities.Foto;
import it.unito.taass.manutenza.entities.Indirizzo;
import it.unito.taass.manutenza.entities.Richiesta;
import it.unito.taass.manutenza.entities.Utente;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author leonardo
 */
@Stateless(name = "GestoreRichieste")
public class GestoreRichieste implements GestoreRichiesteLocal {
    
    @PersistenceContext(unitName = "ManutenzaPU_postgres")
    private EntityManager em;

    @Override
    public void createRichiesta(
            Utente utente, 
            Indirizzo indirizzo, 
            String titolo, 
            String descrizione, 
            String categoria, 
            float budget, 
            List<Foto> listaFoto, 
            Calendar dataCreazione, 
            Calendar dataCompletamento, 
            String stato) 
    {
        System.out.println("creo nuova richiesta");
        Richiesta nuovaRichiesta = new Richiesta();
        nuovaRichiesta.setUtente(utente);
        nuovaRichiesta.setIndirizzo(indirizzo);
        nuovaRichiesta.setTitolo(titolo);
        nuovaRichiesta.setDescrizione(descrizione);
        nuovaRichiesta.setCategoria(categoria);
        nuovaRichiesta.setListaFoto(listaFoto);
        nuovaRichiesta.setBudget(budget);
        nuovaRichiesta.setDataDiCreazione(dataCreazione);
        nuovaRichiesta.setDataDiCompletamento(dataCompletamento);
        nuovaRichiesta.setStato(stato);
        
        //Salvo la nuova categoria nel db
        em.persist(nuovaRichiesta);
        
        System.out.println("Richiesta salvata su db.");
    }

    @Override
    public List<Richiesta> cercaRichieste(Utente utente, String stato) {
        List<Richiesta> listaRichieste = em.createNamedQuery("Richieste utente e stato", Richiesta.class)
                .setParameter("utente", utente)
                .setParameter("stato", stato)
                .getResultList();
        return listaRichieste;
    }

}
