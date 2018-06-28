/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.manutenza.ejb.impl;

import it.unito.taass.manutenza.ejb.GestoreChatLocal;
import it.unito.taass.manutenza.entities.Chat;
import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Proposta;
import it.unito.taass.manutenza.entities.Utente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author leonardo
 */
@Stateless
public class GestoreChat implements GestoreChatLocal {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void creaChat(Proposta proposta, Utente utente, Manutente manutente) {
        Chat chat = new Chat();
        chat.setProposta(proposta);
        chat.setUtente(utente);
        chat.setManutente(manutente);
        
        em.persist(chat);
    }

    @Override
    public void updateChat(Chat chat) {
        em.merge(chat);
    }

    @Override
    public List<Chat> ricercaChatPerUtente(Utente utente) {
       try{
           List<Chat> listaChat = em.createNamedQuery("Ricerca per utente", Chat.class)
                   .setParameter("utente", utente)
                   .getResultList();
        return listaChat;
       }catch(NoResultException nre) {
           System.out.println("Nessuna chat trovata per l'utente: " + utente.getEmail());
           return null;
       }
    }

    @Override
    public List<Chat> ricercaChatPerManutente(Manutente manutente) {
        try{
           List<Chat> listaChat = em.createNamedQuery("Ricerca per manutente", Chat.class)
                   .setParameter("manutente", manutente)
                   .getResultList();
        return listaChat;
       }catch(NoResultException nre) {
           System.out.println("Nessuna chat trovata per il manutente: " + manutente.getEmail());
           return null;
       }
    }

    @Override
    public List<Chat> ricercaPerUtenteManutente(Utente utente, Manutente manutente) {
        try{
           List<Chat> listaChat = em.createNamedQuery("Ricerca per utente e manutente", Chat.class)
                   .setParameter("utente", utente)
                   .setParameter("manutente", manutente)
                   .getResultList();
        return listaChat;
       }catch(NoResultException nre) {
           System.out.println("Nessuna chat trovata per l'utente " + utente.getEmail() + " e il manutente " + manutente.getEmail());
           return null;
       }
    }

    @Override
    public Chat ricercaPerProposta(Long propostaId) {
        try{
           Chat chat = em.createNamedQuery("Ricerca per proposta", Chat.class)
                   .setParameter("propostaId", propostaId)
                   .getSingleResult();
        return chat;
       }catch(NoResultException nre) {
           System.out.println("Nessuna chat trovata per la proposta " + propostaId);
           return null;
       }
    }
    
}
