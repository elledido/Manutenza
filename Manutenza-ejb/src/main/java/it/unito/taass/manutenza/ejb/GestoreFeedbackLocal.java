package it.unito.taass.manutenza.ejb;

import it.unito.taass.manutenza.entities.Feedback;
import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Richiesta;
import javax.ejb.Local;

/**
 *
 * @author leonardo
 */
@Local
public interface GestoreFeedbackLocal {
    
    public void createFeedback(
            Manutente manutente, 
            Richiesta richiesta, 
            short valutazioneComplessiva,
            short professionalita,
            short comunicazione,
            String commento
            );
    
    public Feedback cercaPerIdRichiesta(Long richiestaId);
}
