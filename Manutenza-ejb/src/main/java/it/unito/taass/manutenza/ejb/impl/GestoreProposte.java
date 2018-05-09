/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.manutenza.ejb.impl;

import it.unito.taass.manutenza.ejb.GestoreProposteLocal;
import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Proposta;
import it.unito.taass.manutenza.entities.Richiesta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author leonardo
 */
@Stateless
public class GestoreProposte implements GestoreProposteLocal {
    
    @PersistenceContext(name = "ManutenzaPU_postgres")
    private EntityManager em;

    @Override
    public void creaProposta(Manutente manutente, Richiesta richiesta, float prezzo) {
        Proposta p = new Proposta();
        p.setManutente(manutente);
        p.setRichiesta(richiesta);
        p.setPrezzo(prezzo);
        p.setAccettato(false);
        
        em.persist(p);
    }

    @Override
    public List<Proposta> cercaProposteAccettate(Manutente manutente, String stato) {
       List<Proposta> listaProposte = em.createNamedQuery("Ricerca proposte non completate", Proposta.class)
               .setParameter("manutente", manutente)
               .setParameter("stato", stato)
               .getResultList();
       return listaProposte;
    }

    @Override
    public List<Proposta> cercaProposteRichieste(Long richiestaId) {
        List<Proposta> listaProposte = em.createNamedQuery("Ricerca per richiesta", Proposta.class)
                .setParameter("richiestaId", richiestaId)
                .getResultList();
        return listaProposte;
    }
}