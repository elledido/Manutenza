/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.manutenza.ejb.impl;

import it.unito.taass.manutenza.ejb.GestoreFeedbackLocal;
import it.unito.taass.manutenza.entities.Feedback;
import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Richiesta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author leonardo
 */
@Stateless
public class GestoreFeedback implements GestoreFeedbackLocal {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void createFeedback(Manutente manutente, Richiesta richiesta, short valutazioneComplessiva, short professionalita, short comunicazione, String commento) {
        Feedback feedback = new Feedback();
        feedback.setManutente(manutente);
        feedback.setRichiesta(richiesta);
        feedback.setValutazioneComplessiva(valutazioneComplessiva);
        feedback.setProfessionalita(professionalita);
        feedback.setComunicazione(comunicazione);
        feedback.setCommento(commento);
        
        em.persist(feedback);
    }

    @Override
    public Feedback cercaPerIdRichiesta(Richiesta richiesta) {
        try{ 
            Feedback feedback = em.createNamedQuery("Cerca feedback per proposta", Feedback.class)
                    .setParameter("richiestaId", richiesta.getId())
                    .getSingleResult();
            return feedback;
        }catch(NoResultException nre){
            System.out.println("Non esistono feedback per la richiesta indicata: " + richiesta.getId());
            return null;
        }
    }
}
