package it.unito.taass.manutenza.ejb.impl;

import it.unito.taass.manutenza.ejb.GestoreIndirizzoLocal;
import it.unito.taass.manutenza.entities.Indirizzo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Gestisce le operazioni CRUD per l'entity Indirizzo
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Stateless(name = "GestoreIndirizzo")
public class GestoreIndirizzo implements GestoreIndirizzoLocal {
    
    @PersistenceContext(unitName = "ManutenzaPU_postgres")
    private EntityManager em;

    /**
     * Rende persistente nel database un oggetto di tipo Indirizzo
     * @param via Il nome della via compreso di numero civico
     * @param citta Il nome della citta'
     * @param cap Il codice di avviamento postale
     * @param provincia Il nome completo o abbreviato della provincia 
     */
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
