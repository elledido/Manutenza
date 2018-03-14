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
        System.out.println("Creo nuovo utente e inserisco i dati");
        Utente utente = new Utente();
        System.out.println("Creato nuovo utente " + utente);
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setDataDiNascita(dataDiNascita);
        utente.setCodiceFiscale(codiceFiscale);
        utente.setEmail(email);
        utente.setPassword(password);
        
        System.out.println("Registro utente" + utente.getNome() + " " + utente.getCognome());
        em.persist(utente);
        System.out.println("Utente registrato");
    }
    
    @Override
    public Utente caricaUtente(String email) {
        try{
            Utente utente = em.createNamedQuery("Utente.cercaPerEmail", Utente.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return utente;
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
