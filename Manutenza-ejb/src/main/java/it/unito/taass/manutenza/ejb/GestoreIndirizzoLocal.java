package it.unito.taass.manutenza.ejb;

import javax.ejb.Local;

@Local
public interface GestoreIndirizzoLocal {
    
    public void aggiungiIndirizzo(String via, String citta, String cap, String provincia);
    
}
