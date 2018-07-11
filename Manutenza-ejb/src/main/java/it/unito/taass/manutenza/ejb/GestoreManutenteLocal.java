/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.manutenza.ejb;

import it.unito.taass.manutenza.entities.Manutente;
import javax.ejb.Local;

/**
 * Gestisce le operazioni CRUD per l'entity Manutente
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Local
public interface GestoreManutenteLocal {
    
    /**
     * Rende persistente nel database un oggetto manutente
     * @param manutente L'oggetto manutente da rendere persistente nel database
     */
    public void resgistraManutente(Manutente manutente);
    /**
     * Esegue la ricerca di un manutente nel database
     * @param email L'email relativa al manutente da cercare
     * @return Un manutente
     */
    public Manutente cercaManutente(String email);
    /**
     * Esegue l'aggiornamento di un manutente nel database
     * @param manutente Il manutente da aggiornare
     */
    public void aggiornaManutente(Manutente manutente);
    
}
