package it.unito.taass.manutenza.ejb.impl;

import it.unito.taass.manutenza.ejb.GestoreUtenteLocal;
import it.unito.taass.manutenza.entities.Utente;
import java.util.Calendar;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Gestisce le operazioni CRUD per l'entity Utente.
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Stateless(name = "GestoreUtente")
public class GestoreUtente implements GestoreUtenteLocal {
    
    @PersistenceContext(unitName = "ManutenzaPU_postgres")
    private EntityManager em;
    
    /**
    * Rende persistente l'entity utente su database.
    * @param utente Un utente da registrare.
    */
    @Override
    public void registraUtente(Utente utente) {
        assert(utente != null) : "Entity utente to be persisted cannot be null.";
        try{
            em.persist(utente);
        } catch(EntityExistsException | IllegalArgumentException exception) {
            System.out.println("While persisting entity utente: " + exception.getMessage());
        }
        
    }
    
    /**
     * Rende persistente l'entity utente sul database.
     * @param nome Il nome dell'utente.
     * @param cognome Il cognome dell'utente.
     * @param dataDiNascita La data di nascita dell'utente.
     * @param codiceFiscale Il codice fiscale dell'utente.
     * @param email L'indirizzo email dell'utente.
     * @param password La password dell'utente per l'accesso al sistema.
     */
    @Override
    public void registraUtente(String nome, String cognome, Calendar dataDiNascita, 
            String codiceFiscale, String email, String password) 
    {
        try {
            
            Utente utente = new Utente();
            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setDataDiNascita(dataDiNascita);
            utente.setCodiceFiscale(codiceFiscale);
            utente.setEmail(email);
            utente.setPassword(password);
        
            em.persist(utente);
        
        } catch(EntityExistsException | IllegalArgumentException exception) {
            System.out.println("While persisting entity utente: " + exception.getMessage());
        }
        
    }
    
    /**
     * Cerca un utente nel database e lo carica nel sistema.
     * @param id Un valore univocamente associato ad un utente nel database.
     * @return L'utente corrispondente all'id utilizzato come chiave di ricerca; null altrimenti.
     */
    @Override
    public Utente caricaUtente(Long id) {
        Utente utente = new Utente();
        try {
            utente = em.createNamedQuery("Utente.cercaPerId", Utente.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch(NoResultException nre) {
            System.out.println("Nessun utente da caricare. L'utente cercato non esiste.");
        }
    return utente;
    }
    
    /**
     * Cerca un utente nel database e lo carica nel sistema.
     * @param email L'email dell'utente utilizzata come parametro di ricerca.
     * @return L'utente associato all'indirizzo email utilizzato come parametro di ricerca; null altrimenti.
     */
    @Override
    public Utente caricaUtente(String email) {
        Utente utente = new Utente();
        try{
            utente = em.createNamedQuery("Utente.cercaPerEmail", Utente.class)
                    .setParameter("email", email)
                    .getSingleResult();
        }catch(NoResultException nre){
           System.out.println("Nessun utente da caricare. L'utente cercato non esiste.");
        } //Non catturo NonUniqueResultException perché l'email è chiave nel db
    return utente;
    }
    
    /**
     * Esegue l'update di un utente
     * @param utenteDaAggiornare L'utente da aggiornare
     */
    @Override
    public void aggiornaUtente(Utente utenteDaAggiornare) {
        Utente vecchioUtente = this.caricaUtente(utenteDaAggiornare.getId());
        if(vecchioUtente == null) {
            System.out.println("L'utente cercato non esiste");
        } else {
            vecchioUtente.setEmail(utenteDaAggiornare.getEmail());
            vecchioUtente.setPassword(utenteDaAggiornare.getPassword());
            em.merge(vecchioUtente);
            System.out.println("Utente aggiornato.");
        }
    }

    /**
     * Metodo per eliminare un utente dal db
     * @param utente 
     */
    @Override
    public void eliminaUtente(Utente utente) {
       try{
           Utente daEliminare = em.createNamedQuery("Utente.cercaPerId", Utente.class)
               .setParameter("id", utente.getId())
               .getSingleResult();
           em.remove(daEliminare);
       }catch(NoResultException nre){
           System.out.println("Nessun utente dal eliminare. L'utente cercato non esiste.");
       }
       
    }

}
