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
 * Gestisce le operazioni CRUD per l'entity Chat
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Stateless
public class GestoreChat implements GestoreChatLocal {
    
    @PersistenceContext
    private EntityManager em;

    /**
     * Crea e rende persisente nel database un oggetto di tipo Chat
     * @param proposta La proposta a cui la chat è associata
     * @param utente L'utente che prende parte alla chat
     * @param manutente Il manutente che prende parte alla chat
     */
    @Override
    public void creaChat(Proposta proposta, Utente utente, Manutente manutente) {
        Chat chat = new Chat();
        chat.setProposta(proposta);
        chat.setUtente(utente);
        chat.setManutente(manutente);
        
        em.persist(chat);
    }

    /**
     * Effettua un aggiornamento di una chat nel database
     * @param chat La chat da aggiornare
     */
    @Override
    public void updateChat(Chat chat) {
        em.merge(chat);
    }

    /**
     * Esegue la ricerca di un elenco di chat associate ad un determinato utente
     * @param utente L'utente a cui la chat è associata
     * @return Una lista di chat oppure un valore nullo
     */
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

    /**
     * Esegue la ricerca di un elenco di chat associate ad un determinato manutente
     * @param manutente Il manutente a cui la chat è associata
     * @return Una lista di chat oppure un valore nullo
     */
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

    /**
     * esegue la ricerca di un elenco di chat associate ad un utente e a un manutente
     * @param utente L'utente a cui la chat è associata 
     * @param manutente Il manutente a cui la chat è associata 
     * @return Un elenco di chat oppure un valore nullo
     */
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

    /**
     * Esegue la ricerca di una chat associata ad una determinata proposta
     * @param propostaId L'id della proposta a cui la chat è associata
     * @return Una chat oppure un valore nullo
     */
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
