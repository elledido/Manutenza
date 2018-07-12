/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.manutenza.ejb.impl;

import it.unito.taass.manutenza.ejb.GestoreManutenteLocal;
import it.unito.taass.manutenza.entities.Manutente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Gestisce le operazioni CRUD per l'entity Manutente
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Stateless(name = "GestoreManutente")
public class GestoreManutente implements GestoreManutenteLocal {
    
    @PersistenceContext
    private EntityManager em;

    /**
     * Rende persistente nel database un oggetto manutente
     * @param manutente L'oggetto manutente da rendere persistente nel database
     */
    @Override
    public void resgistraManutente(Manutente manutente) {
        em.persist(manutente);
    }

    /**
     * Esegue la ricerca di un manutente nel database
     * @param email L'email relativa al manutente da cercare
     * @return Un manutente
     */
    @Override
    public Manutente cercaManutente(String email) {
        try {
            Manutente manutente = em.createNamedQuery("Manutente.caricaPerEmail", Manutente.class)
                .setParameter("email", email)
                .getSingleResult();
            return manutente;
        } catch(NoResultException e) {
            System.out.println("Non esiste alcun manutente con email " + email);
            return null;
        }
    }

    /**
     * Esegue l'aggiornamento di un manutente nel database
     * @param manutente Il manutente da aggiornare
     */
    @Override
    public void aggiornaManutente(Manutente manutente) {
        em.merge(manutente);
    }

    
}
