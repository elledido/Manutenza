package it.unito.taass.manutenza.ejb;

import it.unito.taass.manutenza.entities.Utente;
import java.util.Calendar;
import javax.ejb.Local;

/**
 * Gestisce le operazioni CRUD per l'entity Utente.
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Local
public interface GestoreUtenteLocal {
   /**
    * Rende persistente l'entity utente su database.
    * @param utente Un utente da registrare.
    */
    public void registraUtente(Utente utente);
    
    /**
     * Rende persistente l'entity utente sul database.
     * @param nome Il nome dell'utente.
     * @param cognome Il cognome dell'utente.
     * @param dataDiNascita La data di nascita dell'utente.
     * @param codiceFiscale Il codice fiscale dell'utente.
     * @param email L'indirizzo email dell'utente.
     * @param password La password dell'utente per l'accesso al sistema.
     */
    public void registraUtente(String nome, String cognome, Calendar dataDiNascita, 
            String codiceFiscale,String email, String password);
    
    /**
     * Elimina un utente dal database.
     * @param utente L'utente da eliminare.
     */
    public void eliminaUtente(Utente utente);
    /**
     * Cerca un utente nel database e lo carica nel sistema.
     * @param email L'email dell'utente utilizzata come parametro di ricerca.
     * @return L'utente associato all'indirizzo email utilizzato come parametro di ricerca; null altrimenti.
     */
    public Utente caricaUtente(String email);
    /**
     * Cerca un utente nel database e lo carica nel sistema.
     * @param id Un valore univocamente associato ad un utente nel database.
     * @return L'utente corrispondente all'id utilizzato come chiave di ricerca; null altrimenti.
     */
    public Utente caricaUtente(Long id);
    /**
     * Esegue l'update di un utente
     * @param utente L'utente da aggiornare.
     */
    public void aggiornaUtente(Utente utente);
}
