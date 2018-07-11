package it.unito.taass.manutenza.ejb;

import javax.ejb.Local;

/**
 * Gestisce le operazioni CRUD per l'entity Indirizzo
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Local
public interface GestoreIndirizzoLocal {
    
    /**
     * Rende persistente nel database un oggetto di tipo Indirizzo
     * @param via Il nome della via compreso di numero civico
     * @param citta Il nome della citta'
     * @param cap Il codice di avviamento postale
     * @param provincia Il nome completo o abbreviato della provincia 
     */
    public void aggiungiIndirizzo(String via, String citta, String cap, String provincia);
    
}
