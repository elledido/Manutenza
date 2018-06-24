/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.manutenza.ejb;

import it.unito.taass.manutenza.entities.Foto;
import it.unito.taass.manutenza.entities.Indirizzo;
import it.unito.taass.manutenza.entities.Richiesta;
import it.unito.taass.manutenza.entities.Utente;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author leonardo
 */
@Local
public interface GestoreRichiesteLocal {
    
    public void createRichiesta(
            Utente utente, 
            Indirizzo indirizzo, 
            String titolo,
            String descrizione,
            String categoria,
            float budget,
            List<Foto> listaFoto,
            Calendar dataCreazione,
            Calendar dataCompletamento,
            String stato
    );
    
    public List<Richiesta> cercaRichieste(Utente utente, String stato);

    Richiesta cercaRichiestaId(Long id);

    List<Richiesta> cercaProposteLavoro(String categoria, String zona, String email, String stato);

    void aggiornaRichiesta(Richiesta richiesta);
}
