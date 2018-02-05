package it.unito.taass.manutenza.ejb.impl;

import it.unito.taass.manutenza.ejb.GestoreIndirizzoLocal;
import it.unito.taass.manutenza.entities.Indirizzo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "GestoreIndirizzo")
public class GestoreIndirizzo implements GestoreIndirizzoLocal {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void aggiungiIndirizzo(String via, String citta, String cap, String provincia) 
    {
        Indirizzo indirizzo = new Indirizzo();
        indirizzo.setVia(via);
        indirizzo.setCitta(citta);
        indirizzo.setCap(cap);
        indirizzo.setProvincia(provincia);
        
        em.persist(indirizzo);
    }

}
