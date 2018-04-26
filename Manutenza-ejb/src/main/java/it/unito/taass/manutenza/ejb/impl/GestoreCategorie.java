package it.unito.taass.manutenza.ejb.impl;

import it.unito.taass.manutenza.ejb.GestoreCategorieLocal;
import it.unito.taass.manutenza.entities.Categoria;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author leonardo
 */
@Stateless(name = "GestoreCategorie")
public class GestoreCategorie implements GestoreCategorieLocal {
    
    @PersistenceContext(unitName = "ManutenzaPU_postgres")
    private EntityManager em;

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

    @Override
    public void registraCategoria(Categoria categoria) {
        em.persist(categoria);
    }

    @Override
    public void eliminaCategoria(Categoria daEliminare) {
        em.remove(daEliminare);
    }
}
