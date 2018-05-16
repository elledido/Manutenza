/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.manutenza.ejb;

import it.unito.taass.manutenza.entities.Manutente;
import javax.ejb.Local;

/**
 *
 * @author leonardo
 */
@Local
public interface GestoreManutenteLocal {
    
    public void resgistraManutente(Manutente manutente);
    public Manutente cercaManutente(String email);
    public void aggiornaManutente(Manutente manutente);
    
}
