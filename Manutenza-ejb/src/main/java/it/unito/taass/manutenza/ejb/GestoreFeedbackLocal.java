package it.unito.taass.manutenza.ejb;

import it.unito.taass.manutenza.entities.Feedback;
import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Richiesta;
import javax.ejb.Local;

/**
 * Gestisce le operazioni CRUD per l'entity Feedback
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Local
public interface GestoreFeedbackLocal {
    
    /**
     * Rende persistente nel database un oggetto di tipo Feedback
     * @param manutente Il manutente a cui il feedback viene rilasciato
     * @param richiesta La richiesta relativa al lavoro svolto dal manutente
     * @param valutazioneComplessiva La valutazione complessiva dell'operato del manutente
     * @param professionalita La valutazione sulla professionalita' del manutente
     * @param comunicazione La valutazione circa la bontà della comunicazione con il manutente
     * @param commento Un commento sull'operato del manutente
     */
    public void createFeedback(
            Manutente manutente, 
            Richiesta richiesta, 
            short valutazioneComplessiva,
            short professionalita,
            short comunicazione,
            String commento
            );
    
    /**
     * Esegue una ricerca nel database di un feedback
     * @param richiestaId Il valore dell'id associato alla richiesta per cui il feedback viene rilasciato
     * @return Un feedback oppure il valore nullo
     */
    public Feedback cercaPerIdRichiesta(Long richiestaId);
}
