package it.unito.taass.manutenza.ejb.impl;

import it.unito.taass.manutenza.ejb.GestoreUtenteLocal;
import it.unito.taass.manutenza.entities.Utente;
import java.util.Calendar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless(name = "GestoreUtente")
public class GestoreUtente implements GestoreUtenteLocal {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void registraUtente(Utente utente) {
        em.persist(utente);
    }
    
   
    @Override
    public void registraUtente(String nome, String cognome, Calendar dataDiNascita, 
            String codiceFiscale, String email, String password) 
    {
        Utente utente = new Utente();
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setDataDiNascita(dataDiNascita);
        utente.setCodiceFiscale(codiceFiscale);
        utente.setEmail(email);
        utente.setPassword(password);
        
        em.persist(utente);
    }
    
    @Override
    public Utente caricaUtente(String email, String password) {
        try{
            Utente utente = em.createNamedQuery("Utente.cercaPerEmail", Utente.class)
                    .setParameter("email", email)
                    .getSingleResult();
            utente.verifica(password);
            if(!utente.isAutenticato()) return null;
            else return utente;
        }catch(NoResultException nre){
           return null;
        }
    }

    @Override
    public void eliminaUtente(Utente utente) {
       Utente daEliminare = em.createNamedQuery("Utente.cercaPerId", Utente.class)
               .setParameter("id", utente.getId())
               .getSingleResult();
       em.remove(daEliminare);
    }

}
