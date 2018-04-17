package it.unito.taass.manutenza.ejb;

import it.unito.taass.manutenza.entities.Utente;
import java.util.Calendar;
import javax.ejb.Local;

@Local
public interface GestoreUtenteLocal {
   
    public void registraUtente(Utente utente);
    public void registraUtente(String nome, String cognome, Calendar dataDiNascita, 
            String codiceFiscale,String email, String password);
    public void eliminaUtente(Utente utente);
    public Utente caricaUtente(String email);
    public Utente caricaUtente(Long id);
    public void aggiornaUtente(Utente daAggiornare);
}
