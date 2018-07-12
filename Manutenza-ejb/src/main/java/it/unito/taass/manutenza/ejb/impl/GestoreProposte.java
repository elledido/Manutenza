/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.manutenza.ejb.impl;

import it.unito.taass.manutenza.ejb.GestoreProposteLocal;
import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Proposta;
import it.unito.taass.manutenza.entities.Richiesta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Gestisce le operazioni CRUD per l'entity Proposta
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Stateless
public class GestoreProposte implements GestoreProposteLocal {
    
    @PersistenceContext(name = "ManutenzaPU_postgres")
    private EntityManager em;

    /**
     * Crea una proposta e la rende persistente nel database
     * @param manutente Il manutente associato ad una determinata proposta
     * @param richiesta La richiesta associata alla proposta
     * @param prezzo Il budget rinegoziato dal manutente
     */
    @Override
    public void creaProposta(Manutente manutente, Richiesta richiesta, float prezzo) {
        Proposta p = new Proposta();
        p.setManutente(manutente);
        p.setRichiesta(richiesta);
        p.setPrezzo(prezzo);
        p.setAccettato(false);
        
        em.persist(p);
    }
    
    /**
     * Aggiorna i dati della proposta
     * @param proposta Una proposta
     */
    @Override
    public void aggiornaProposta(Proposta proposta) {
        em.merge(proposta);
    }

    /**
     * Cerca e restituisce una lista di proposte legate ad un determinato stato e accettate da un certo manutente
     * @param manutente Il manutente associato 
     * @param stato Lo stato della proposta
     * @return Una lista di proposte oppure un valore nullo
     */
    @Override
    public List<Proposta> cercaProposteAccettate(Manutente manutente, String stato) {
       List<Proposta> listaProposte = em.createNamedQuery("Ricerca proposte accettate", Proposta.class)
               .setParameter("manutente", manutente)
               .setParameter("stato", stato)
               .getResultList();
       return listaProposte;
    }

    /**
     * Cerca e restituisce una lista di proposte sulla base dell'id di una richiesta
     * @param richiestaId Il valore di id associato ad una determinata richiesta
     * @return Una lista di proposte o un valore nullo
     */
    @Override
    public List<Proposta> cercaProposteRichieste(Long richiestaId) {
        List<Proposta> listaProposte = em.createNamedQuery("Ricerca per richiesta", Proposta.class)
                .setParameter("richiestaId", richiestaId)
                .getResultList();
        return listaProposte;
    }

    /**
     * Cerca e restituisce una proposta accettata 
     * @param richiestaId Il valore identificativo di una richiesta
     * @return Una proposta oppure un valore nullo
     */
    @Override
    public Proposta cercaPropostaAccettata(Long richiestaId) {
        try {
            Proposta proposta = em.createNamedQuery("Ricerca proposta accettata per richiesta", Proposta.class)
                .setParameter("richiestaId", richiestaId)
                .getSingleResult();
            return proposta;
        } catch(NoResultException e) {
            System.out.println("Non esiste alcuna proposta accettata per la richiesta " + richiestaId);
            return null;
        }
    }

    /**
     * Cerca e restituisce una proposta per id della richeista e manutente
     * @param richiestaId L'id della richiesta
     * @param manutente Il manutente associato alla proposta
     * @return 
     */
    @Override
    public Proposta cercaPropostaRichiestaManutente(Long richiestaId, Manutente manutente) {
        try {
            Proposta proposta = em.createNamedQuery("Ricerca per richiesta e manutente", Proposta.class)
                    .setParameter("richiestaId", richiestaId)
                    .setParameter("manutente", manutente)
                    .getSingleResult();
            return proposta;
        } catch(NoResultException e) {
            System.out.println("Non esiste alcuna proposta fatta dal manutente per la richiesta " + richiestaId);
            return null;
        }
    }

    /**
     * Cerca per id e restituisce una proposta
     * @param propostaId L'id della proposta da cercare
     * @return 
     */
    @Override
    public Proposta cercaPerId(Long propostaId) {
        try {
            Proposta proposta = em.createNamedQuery("Ricerca per id", Proposta.class)
                    .setParameter("propostaId", propostaId)
                    .getSingleResult();
            return proposta;
        } catch(NoResultException e) {
            System.out.println("Non esiste alcuna proposta con id " + propostaId);
            return null;
        }
    }
    
}
