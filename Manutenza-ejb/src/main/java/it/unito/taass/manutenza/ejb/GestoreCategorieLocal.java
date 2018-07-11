/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.manutenza.ejb;

import it.unito.taass.manutenza.entities.Categoria;
import java.util.List;
import javax.ejb.Local;

/**
 * Gestisce le operazioni CRUD per l'entity Categoria
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Local
public interface GestoreCategorieLocal {
    
    /**
     * Rende persistente nel database un oggetto di tipo Categoria
     * @param categoria La categoria da rendere persistente nel database
     */
    public void registraCategoria(Categoria categoria);
    
    /**
     * Elimina una detreminata categoria dal database
     * @param daEliminare La categoria da eliminare
     */
    public void eliminaCategoria(Categoria daEliminare);
    
    /**
     * Esegue il retrieve di tutte le categorie presenti nel database
     * @return Una lista di categorie oppure un valore nullo
     */
    public List<Categoria> caricaListaCategorie();
    
}
