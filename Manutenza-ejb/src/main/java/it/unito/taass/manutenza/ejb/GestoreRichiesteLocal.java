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
 * Gestisce le operazioni CRUD in locale per l'entity Richiesta
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Local
public interface GestoreRichiesteLocal {
    
    /**
     * Rende persistente sul database l'entity Richiesta.
     * @param utente La persona che crea la richiesta
     * @param indirizzo L'indirizzo di domicilio dell'utente
     * @param titolo Il titolo della richiesta
     * @param descrizione La descrizione del problema per cui si genera la richiesta 
     * @param categoria La categoria professionale a cui la richiesta è rivolta
     * @param budget La quantità di denaro disponibile
     * @param listaFoto L'eventuale lista di foto che rende visibile il problema
     * @param dataCreazione La data di creazione della richiesta
     * @param dataCompletamento La data di completamento del lavoro e quindi chiusura della richiesta
     * @param stato Lo stato dei lavori
     */
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
    
    /**
     * Cerca e restituisce un elenco di richieste
     * @param utente La persona che ha inviato la/e richeista/e
     * @param stato Lo stato di completamento della/e richiesta/e
     * @return Una lista di richieste oppure un valore nullo
     */
    public List<Richiesta> cercaRichieste(Utente utente, String stato);
    
    /**
     * Cerca e restituisce una singola richiesta
     * @param id Il valore univoco associato ad una particolare richiesta 
     * @return Una richiesta oppure un valore nullo
     */
    Richiesta cercaRichiestaId(Long id);
    
    /**
     * Cerca e restituisce un elenco di richieste inviate ad un particolare manutente,
     * filtrate per categoria, zona di competenza e stato di completamento 
     * @param categoria La categoria professionale a cui le richieste fanno riferimento
     * @param zona La zona di competenza di un manutentean
     * @param email L'indirizzo email associato a ?
     * @param stato Lo stato di completamento del lavoro
     * @return Un elenco di richieste oppure un valore nullo
     */
    List<Richiesta> cercaProposteLavoro(String categoria, String zona, String email, String stato);

    /**
     * Aggiorna lo stato di una richiesta
     * @param richiesta La richiesta da aggiornare
     */
    void aggiornaRichiesta(Richiesta richiesta);
}
