package it.unito.taass.manutenza.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 * Rappresenta un utente del sistema
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "Utente.carica", query = "SELECT u FROM Utente u WHERE u.email = :email AND u.password = :password"),
    @NamedQuery(name = "Utente.cercaPerId", query = "SELECT u FROM Utente u WHERE u.id = :id" ),
    @NamedQuery(name = "Utente.cercaPerEmail", query = "SELECT u FROM Utente u WHERE u.email = :email")
})
public class Utente implements Serializable { 

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cognome;
    @Column(name = "DATA_DI_NASCITA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataDiNascita;
    @Column(name = "CODICE_FISCALE")
    private String codiceFiscale;
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Indirizzo> listaIndirizzi;
    @Transient
    private boolean autenticato;

    /**
     * Restituisce l'id univoco associato all'utente
     * @return Un numero identificativo
     */
    public Long getId() {
        return id;
    }

    /**
     * Associa all'utente un valore identificativo
     * @param id Un valore univoco da usare come identificativo
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce il nome dell'utente
     * @return Il nome dell'utente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Aggiunge il nome dell'utente
     * @param nome Il nome dell'utente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome dell'utente
     * @return Il cognome dell'utente
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Aggiunge il cognome all'utente
     * @param cognome Il cognome dell'utente
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Retistuisce la data di nascita dell'utente
     * @return La data di nascita dell'utente
     */
    public Calendar getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * Imposta la data di nascita per l'utente
     * @param dataDiNascita La data di nascita dell'utente
     */
    public void setDataDiNascita(Calendar dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }
    
    /**
     * Imposta la data di nascita per l'utente
     * @param giorno Il giorno di nascita dell'utente
     * @param mese Il mese di nascita dell'utente
     * @param anno L'anno di nascita dell'utente
     */
    public void setDataDiNascita(int giorno, int mese, int anno) {
        this.dataDiNascita = new GregorianCalendar(anno, mese-1, giorno);
        this.dataDiNascita.setLenient(false);
    }

    /**
     * Restituisce il codice fiscale dell'utente
     * @return Il codice fiscale dell'utente
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Imposta il codice fiscale dell'utente
     * @param codiceFiscale Il codice fiscale dell'utente
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * Restituisce l'indirizzo email associato all'utente
     * @return L'indirizzo email associato all'utente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'indirizzo email dell'utente
     * @param email L'indirizzo email dell'utente
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Restituisce l'hash della password dell'utente
     * @return Un hash della password dell'utente
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setta la password dell'utente come hash
     * @param password La password dell'utente
     */
    public void setPassword(String password) {
        this.password = sha256hash(password);
    }
    
    /**
     * Inizializza la lista degli indirizzi a lista vuota
     */
    private void inizializzaListaIndirizzi() {
        this.listaIndirizzi = new ArrayList<>();
    }
   
    /**
     * Aggiunge un indirizzo alla lista degli indirizzi
     * @param indirizzo Un indirizzo 
     */
    public void addIndirizzo(Indirizzo indirizzo) {
        if (this.listaIndirizzi == null) 
            this.inizializzaListaIndirizzi();
        this.listaIndirizzi.add(indirizzo);
    }
    
    /**
     * Rimuove un indirizzo dalla lista indirizzo
     * @param indirizzo Un indirizzo
     */
    public void removeIndirizzo(Indirizzo indirizzo) {
        for(Indirizzo daRimuovere : listaIndirizzi) {
            if(indirizzo.getId().equals(daRimuovere.getId()))
                this.listaIndirizzi.remove(indirizzo);
        }
    }
    
    /**
     * Restituisce la lista degli indirizzi
     * @return La lista degli indirizzi
     */
    public List<Indirizzo> getListaIndirizzi() {
        return this.listaIndirizzi;
    }

    public boolean isAutenticato() {
        return autenticato;
    }

    public void setAutenticato(boolean autenticato) {
        this.autenticato = autenticato;
    }
    
    /**
     * Verifica se la password formita coincide con quella memorizzata
     * @param password Una password
     */
    public void verifica(String password){
        String hashPasswordFornita = sha256hash(password);
        this.autenticato = this.password.equals(hashPasswordFornita);
    }
    
    private String sha256hash(String password) {
        String hashString = null;
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            hashString = "";
            for (int i = 0; i < hash.length; i++) {
                hashString += Integer.toHexString( 
                                  (hash[i] & 0xFF) | 0x100 
                              ).toLowerCase().substring(1,3);
            }
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return hashString;
    }

}
