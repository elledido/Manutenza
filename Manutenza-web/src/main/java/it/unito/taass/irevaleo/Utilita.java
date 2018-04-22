/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.irevaleo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author leonardo
 */
public class Utilita {
    
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
    
}
