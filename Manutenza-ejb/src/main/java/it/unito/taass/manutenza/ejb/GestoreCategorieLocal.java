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
 *
 * @author leonardo
 */
@Local
public interface GestoreCategorieLocal {
    
    public void registraCategoria(Categoria categoria);
    public List<Categoria> caricaListaCategorie();
    
}
