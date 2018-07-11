/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.manutenza.ejb;

import it.unito.taass.manutenza.entities.Chat;
import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Proposta;
import it.unito.taass.manutenza.entities.Utente;
import java.util.List;
import javax.ejb.Local;

/**
 * Gestisce le operazioni CRUD per l'entity Chat
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Local
public interface GestoreChatLocal {
    
    /**
     * Crea e rende persisente nel database un oggetto di tipo Chat
     * @param proposta La proposta a cui la chat è associata
     * @param utente L'utente che prende parte alla chat
     * @param manutente Il manutente che prende parte alla chat
     */
    public void creaChat(Proposta proposta, Utente utente, Manutente manutente);
    /**
     * Effettua un aggiornamento di una chat nel database
     * @param chat La chat da aggiornare
     */
    public void updateChat(Chat chat);
    /**
     * Esegue la ricerca di un elenco di chat associate ad un determinato utente
     * @param utente L'utente a cui la chat è associata
     * @return Una lista di chat oppure un valore nullo
     */
    public List<Chat> ricercaChatPerUtente(Utente utente);
    /**
     * Esegue la ricerca di un elenco di chat associate ad un determinato manutente
     * @param manutente Il manutente a cui la chat è associata
     * @return Una lista di chat oppure un valore nullo
     */
    public List<Chat> ricercaChatPerManutente(Manutente manutente);
    /**
     * esegue la ricerca di un elenco di chat associate ad un utente e a un manutente
     * @param utente L'utente a cui la chat è associata 
     * @param manutente Il manutente a cui la chat è associata 
     * @return Un elenco di chat oppure un valore nullo
     */
    public List<Chat> ricercaPerUtenteManutente(Utente utente, Manutente manutente);
    /**
     * Esegue la ricerca di una chat associata ad una determinata proposta
     * @param propostaId L'id della proposta a cui la chat è associata
     * @return Una chat oppure un valore nullo
     */
    public Chat ricercaPerProposta(Long propostaId);
    
}
