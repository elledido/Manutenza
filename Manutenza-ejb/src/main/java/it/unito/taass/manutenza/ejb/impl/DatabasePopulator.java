//package it.unito.taass.manutenza.ejb.impl;
//
//import it.unito.taass.manutenza.ejb.GestoreIndirizzoLocal;
//import it.unito.taass.manutenza.ejb.GestoreUtenteLocal;
//import it.unito.taass.manutenza.entities.Indirizzo;
//import it.unito.taass.manutenza.entities.Utente;
//import java.util.GregorianCalendar;
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import javax.annotation.sql.DataSourceDefinition;
//import javax.ejb.EJB;
//import javax.ejb.Singleton;
//import javax.ejb.Startup;
//
///**
// *
// * @author leonardo
// */
//@Singleton
//@Startup
//@DataSourceDefinition(
//    className = "org.apache.derby.jdbc.EmbeddedDataSource",
//    name = "java:global/jdbc/sample",
//    user = "app",
//    password = "app",
//    properties = {"connectionAttributes=;create=true"}
//)
//public class DatabasePopulator {
//    
//    @EJB(beanName = "GestoreUtente")
//    private GestoreUtenteLocal gestoreUtente;
//    @EJB(beanName = "GestoreIndirizzo")
//    private GestoreIndirizzoLocal gestoreIndirizzo;
//    private Utente utente;
//    private Indirizzo indirizzo;
//
//    @PostConstruct
//    public void populateDb(){
//        System.out.println("Inizio creazione nuovo utente");
//        utente = new Utente();
//        utente.setNome("Paolo");
//        utente.setCognome("Rossi");
//        utente.setDataDiNascita(new GregorianCalendar(1987, 4, 27));
//        utente.setCodiceFiscale("PLORSS87");
//        utente.setEmail("paolorossi@email.com");
//        utente.setPassword("paolorossi");
//        System.out.println("Creato nuovo utente " + utente.getNome() + " " + utente.getCognome());
//        
//        System.out.println("Creo nuovo indirizzo");
//        indirizzo = new Indirizzo();
//        indirizzo.setCitta("Torino");
//        indirizzo.setVia("Via Inventata 19");
//        indirizzo.setCap("10165");
//        indirizzo.setProvincia("TO");
//        System.out.println("Creato nuovo indirizzo");
//        
//        utente.addIndirizzo(indirizzo);
//        System.out.println("Registro nuovo utente");
//        gestoreUtente.registraUtente(utente);    
//    }
//    
//    @PreDestroy
//    public void cleanDB() {
//        System.out.println("Elimino utente");
//        gestoreUtente.eliminaUtente(utente);
//    }
//    
//}
