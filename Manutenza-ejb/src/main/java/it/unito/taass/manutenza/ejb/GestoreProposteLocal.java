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
 *
 * @author leonardo
 */
@Local
public interface GestoreProposteLocal {
    
    public void creaProposta(Manutente manutente, Richiesta richiesta, float prezzo);
    public List<Proposta> cercaProposteAccettate(Manutente manutente, String stato);
    public List<Proposta> cercaProposteRichieste(Long richiestaId);

    Proposta cercaPropostaAccettata(Long richiestaId);

    Proposta cercaPropostaRichiestaManutente(Long richiestaId, Manutente manutente);

    Proposta cercaPerId(Long propostaId);
    
}
