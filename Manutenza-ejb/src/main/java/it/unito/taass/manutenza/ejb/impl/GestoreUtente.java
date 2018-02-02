package it.unito.taass.manutenza.ejb.impl;

import it.unito.taass.manutenza.ejb.GestoreUtenteLocal;
import it.unito.taass.manutenza.entities.Utente;
import java.util.Calendar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class GestoreUtente implements GestoreUtenteLocal {
    
    @PersistenceContext(unitName = "ManutenzaPU")
    private EntityManager em;

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
            Utente utente = em.createNamedQuery("Utente.carica", Utente.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
            return utente;
        }catch(NoResultException nre){
           return null;
        }
    }

    
}
