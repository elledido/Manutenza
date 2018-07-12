package it.unito.taass.manutenza.ejb.impl;

import it.unito.taass.manutenza.ejb.GestoreCategorieLocal;
import it.unito.taass.manutenza.entities.Categoria;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Gestisce le operazioni CRUD per l'entity Categoria
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Stateless(name = "GestoreCategorie")
public class GestoreCategorie implements GestoreCategorieLocal {
    
    @PersistenceContext(unitName = "ManutenzaPU_postgres")
    private EntityManager em;

    /**
     * Esegue il retrieve di tutte le categorie presenti nel database
     * @return Una lista di categorie oppure un valore nullo
     */
    @Override
    public List<Categoria> caricaListaCategorie() {
        List<Categoria> listaCategorie = null;
        try {
            listaCategorie = em.createNamedQuery("Categoria.caricaAll", Categoria.class)
                    .getResultList();
        } catch(NoResultException re) {
            return null;
        }
    return listaCategorie;
    }

    /**
     * Rende persistente nel database un oggetto di tipo Categoria
     * @param categoria La categoria da rendere persistente nel database
     */
    @Override
    public void registraCategoria(Categoria categoria) {
        em.persist(categoria);
    }

    /**
     * Elimina una detreminata categoria dal database
     * @param daEliminare La categoria da eliminare
     */
    @Override
    public void eliminaCategoria(Categoria daEliminare) {
        em.remove(daEliminare);
    }
}
