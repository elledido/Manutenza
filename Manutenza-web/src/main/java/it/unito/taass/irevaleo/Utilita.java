/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.irevaleo;

import it.unito.taass.manutenza.entities.Indirizzo;
import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Utente;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author leonardo
 */
public class Utilita {
    
    public static final String IN_ATTESA = "I";
    public static final String COMPLETATA = "C";
    public static final String VALUTATA = "V";
    public static final String ACCETTATA = "A";
    
    public static final String[] MESI = {
        "gennaio", "febbraio", "marzo", "aprile",
        "maggio", "giugno", "luglio", "agosto",
        "settembre", "ottobre", "novembre", "dicembre"
    };
    
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    
    public static Calendar getCalendar(java.sql.Date data) {
        Calendar calendario = new GregorianCalendar();
        calendario.setLenient(false);
        calendario.setTime(data);
        return calendario;
    }
    
    public static Calendar getDataDiNascita(String data) {
        Calendar dataDiNascita = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            java.util.Date date = sdf.parse(data);
            //dataDiNascita = Calendar.getInstance();
            dataDiNascita.setTime(date);
        } catch(ParseException e) {
            System.out.println("Problemi con il parsing della data.");
            return null;
        }
    return dataDiNascita;
    }
    
    public static Utente manutente2utente(Manutente manutente){
        Utente utente = new Utente();
        utente.setId(manutente.getId());
        utente.setCodiceFiscale(manutente.getCodiceFiscale());
        utente.setCognome(manutente.getCognome());
        utente.setDataDiNascita(manutente.getDataDiNascita());
        utente.setEmail(manutente.getEmail());
        utente.setNome(manutente.getNome());
        
        for(Indirizzo i : manutente.getListaIndirizzi()){
            utente.addIndirizzo(i);
        }
        
        return utente;
    }
    
    public static Manutente utente2manutente(Utente utente){
        Manutente manutente = new Manutente();
        manutente.setId(utente.getId());
        manutente.setCodiceFiscale(utente.getCodiceFiscale());
        manutente.setCognome(utente.getCognome());
        manutente.setDataDiNascita(utente.getDataDiNascita());
        manutente.setEmail(utente.getEmail());
        manutente.setNome(utente.getNome());
        manutente.setValutazioneComplessiva(0);
        manutente.setListaCompetenze(null);
        
        for(Indirizzo i : utente.getListaIndirizzi()){
            manutente.addIndirizzo(i);
        }
        return manutente;
    }
    
}
