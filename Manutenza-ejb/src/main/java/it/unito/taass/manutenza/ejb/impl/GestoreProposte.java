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
import javax.persistence.NoResultException;
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
    public void aggiornaProposta(Proposta proposta) {
        em.merge(proposta);
    }

    @Override
    public List<Proposta> cercaProposteAccettate(Manutente manutente, String stato) {
       List<Proposta> listaProposte = em.createNamedQuery("Ricerca proposte accettate", Proposta.class)
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

    @Override
    public Proposta cercaPropostaAccettata(Long richiestaId) {
        try {
            Proposta proposta = em.createNamedQuery("Ricerca proposta accettata per richiesta", Proposta.class)
                .setParameter("richiestaId", richiestaId)
                .getSingleResult();
            return proposta;
        } catch(NoResultException e) {
            System.out.println("Non esiste alcuna proposta accettata per la richiesta " + richiestaId);
            return null;
        }
    }

    @Override
    public Proposta cercaPropostaRichiestaManutente(Long richiestaId, Manutente manutente) {
        try {
            Proposta proposta = em.createNamedQuery("Ricerca per richiesta e manutente", Proposta.class)
                    .setParameter("richiestaId", richiestaId)
                    .setParameter("manutente", manutente)
                    .getSingleResult();
            return proposta;
        } catch(NoResultException e) {
            System.out.println("Non esiste alcuna proposta fatta dal manutente per la richiesta " + richiestaId);
            return null;
        }
    }

    @Override
    public Proposta cercaPerId(Long propostaId) {
        try {
            Proposta proposta = em.createNamedQuery("Ricerca per id", Proposta.class)
                    .setParameter("propostaId", propostaId)
                    .getSingleResult();
            return proposta;
        } catch(NoResultException e) {
            System.out.println("Non esiste alcuna proposta con id " + propostaId);
            return null;
        }
    }
    
}
