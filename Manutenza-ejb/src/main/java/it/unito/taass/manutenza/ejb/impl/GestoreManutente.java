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
import javax.persistence.PersistenceContext;

/**
 *
 * @author leonardo
 */
@Stateless(name = "GestoreManutente")
public class GestoreManutente implements GestoreManutenteLocal {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void resgistraManutente(Manutente manutente) {
        em.persist(manutente);
    }

    @Override
    public Manutente cercaManutente(String email) {
        Manutente manutente = em.createNamedQuery("Manutente.caricaPerEmail", Manutente.class)
                .setParameter("email", email)
                .getSingleResult();
        return manutente;
    }

    @Override
    public void aggiornaManutente(Manutente manutente) {
        em.merge(manutente);
    }

    
}
