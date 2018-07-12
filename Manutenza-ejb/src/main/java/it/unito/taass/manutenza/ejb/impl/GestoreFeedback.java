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
 * Gestisce le operazioni CRUD per l'entity Feedback
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Stateless
public class GestoreFeedback implements GestoreFeedbackLocal {
    
    @PersistenceContext
    private EntityManager em;

    /**
     * Rende persistente nel database un oggetto di tipo Feedback
     * @param manutente Il manutente a cui il feedback viene rilasciato
     * @param richiesta La richiesta relativa al lavoro svolto dal manutente
     * @param valutazioneComplessiva La valutazione complessiva dell'operato del manutente
     * @param professionalita La valutazione sulla professionalita' del manutente
     * @param comunicazione La valutazione circa la bontà della comunicazione con il manutente
     * @param commento Un commento sull'operato del manutente
     */
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

    /**
     * Esegue una ricerca nel database di un feedback
     * @param richiestaId Il valore dell'id associato alla richiesta per cui il feedback viene rilasciato
     * @return Un feedback oppure il valore nullo
     */
    @Override
    public Feedback cercaPerIdRichiesta(Long richiestaId) {
        try{ 
            Feedback feedback = em.createNamedQuery("Cerca feedback per proposta", Feedback.class)
                    .setParameter("richiestaId", richiestaId)
                    .getSingleResult();
            return feedback;
        }catch(NoResultException nre){
            System.out.println("Non esistono feedback per la richiesta indicata: " + richiestaId);
            return null;
        }
    }
}
