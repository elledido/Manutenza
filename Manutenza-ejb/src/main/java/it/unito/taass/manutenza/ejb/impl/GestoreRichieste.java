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
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 * Gestisce le operazioni CRUD in locale per l'entity Richiesta
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Stateless(name = "GestoreRichieste")
public class GestoreRichieste implements GestoreRichiesteLocal {
    
    @PersistenceContext(unitName = "ManutenzaPU_postgres")
    private EntityManager em;

    /**
     * Rende persistente sul database l'entity Richiesta.
     * @param utente La persona che crea la richiesta
     * @param indirizzo L'indirizzo di domicilio dell'utente
     * @param titolo Il titolo della richiesta
     * @param descrizione La descrizione del problema per cui si genera la richiesta 
     * @param categoria La categoria professionale a cui la richiesta è rivolta
     * @param budget La quantità di denaro disponibile
     * @param listaFoto L'eventuale lista di foto che rende visibile il problema
     * @param dataCreazione La data di creazione della richiesta
     * @param dataCompletamento La data di completamento del lavoro e quindi chiusura della richiesta
     * @param stato Lo stato dei lavori
     */
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
        
        try {
            em.persist(nuovaRichiesta);
        } catch(EntityExistsException entityExists) {
            System.out.println("While persisting richiesta: " + entityExists.getMessage());
        }
        
        
        System.out.println("Richiesta salvata su db.");
    }
    
    /**
     * Cerca e restituisce un elenco di richieste
     * @param utente La persona che ha inviato la/e richeista/e
     * @param stato Lo stato di completamento della/e richiesta/e
     * @return Una lista di richieste oppure un valore nullo
     */
    @Override
    public List<Richiesta> cercaRichieste(Utente utente, String stato) {
        List<Richiesta> listaRichieste = Collections.EMPTY_LIST;
        try {
            listaRichieste = em.createNamedQuery("Richieste utente e stato", Richiesta.class)
                    .setParameter("utente", utente)
                    .setParameter("stato", stato)
                    .getResultList();
        } catch(PersistenceException exception ) {
            System.out.println("While retrieving list of Richieste: " + exception.getMessage());
        }
    return listaRichieste;
    }

    /**
     * Cerca e restituisce una singola richiesta
     * @param id Il valore univoco associato ad una particolare richiesta 
     * @return Una richiesta oppure un valore nullo
     */
    @Override
    public Richiesta cercaRichiestaId(Long id) {
        try {
            Richiesta richiesta = em.createNamedQuery("Richiesta per id", Richiesta.class)
                .setParameter("id", id)
                .getSingleResult();
            return richiesta;
        } catch(NoResultException e) {
            System.out.println("Non esiste alcuna richiesta con id " + id);
            return null;
        }
    }

    /**
     * Cerca e restituisce un elenco di richieste inviate ad un particolare manutente,
     * filtrate per categoria, zona di competenza e stato di completamento 
     * @param categoria La categoria professionale a cui le richieste fanno riferimento
     * @param zona La zona di competenza di un manutentean
     * @param email L'indirizzo email associato a ?
     * @param stato Lo stato di completamento del lavoro
     * @return Un elenco di richieste oppure un valore nullo
     */
    @Override
    public List<Richiesta> cercaProposteLavoro(String categoria, String zona, String email, String stato) {
        List<Richiesta> listaRichieste = em.createNamedQuery("Proposte di lavoro per categoria e zona ad un manutente", Richiesta.class)
                .setParameter("categoria", categoria)
                .setParameter("zona", zona)
                .setParameter("email", email)
                .setParameter("stato", stato)
                .getResultList();
        return listaRichieste;
    }

    /**
     * Aggiorna lo stato di una richiesta
     * @param richiesta La richiesta da aggiornare
     */
    @Override
    public void aggiornaRichiesta(Richiesta richiesta) {
        em.merge(richiesta);
    }
}
