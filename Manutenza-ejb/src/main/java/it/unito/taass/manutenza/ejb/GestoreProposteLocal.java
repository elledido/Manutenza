/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.manutenza.ejb;

import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Proposta;
import it.unito.taass.manutenza.entities.Richiesta;
import java.util.List;
import javax.ejb.Local;

/**
 * Gestisce le operazioni CRUD per l'entity Proposta
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Local
public interface GestoreProposteLocal {
    
    /**
     * Crea una proposta e la rende persistente nel database
     * @param manutente Il manutente associato ad una determinata proposta
     * @param richiesta La richiesta associata alla proposta
     * @param prezzo Il budget rinegoziato dal manutente
     */
    public void creaProposta(Manutente manutente, Richiesta richiesta, float prezzo);
    
    /**
     * Cerca e restituisce una lista di proposte legate ad un determinato stato e accettate da un certo manutente
     * @param manutente Il manutente associato 
     * @param stato Lo stato della proposta
     * @return Una lista di proposte oppure un valore nullo
     */
    public List<Proposta> cercaProposteAccettate(Manutente manutente, String stato);
    
    /**
     * Cerca e restituisce una lista di proposte sulla base dell'id di una richiesta
     * @param richiestaId Il valore di id associato ad una determinata richiesta
     * @return Una lista di proposte o un valore nullo
     */
    public List<Proposta> cercaProposteRichieste(Long richiestaId);
    
    /**
     * Cerca e restituisce una proposta accettata 
     * @param richiestaId Il valore identificativo di una richiesta
     * @return Una proposta oppure un valore nullo
     */
    Proposta cercaPropostaAccettata(Long richiestaId);

    /**
     * Cerca e restituisce una proposta per id della richeista e manutente
     * @param richiestaId L'id della richiesta
     * @param manutente Il manutente associato alla proposta
     * @return 
     */
    Proposta cercaPropostaRichiestaManutente(Long richiestaId, Manutente manutente);

    /**
     * Cerca per id e restituisce una proposta
     * @param propostaId L'id della proposta da cercare
     * @return 
     */
    Proposta cercaPerId(Long propostaId);

    /**
     * Aggiorna i dati della proposta
     * @param proposta Una proposta
     */
    void aggiornaProposta(Proposta proposta);
    
}
