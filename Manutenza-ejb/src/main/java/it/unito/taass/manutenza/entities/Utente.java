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

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "Utente.carica", query = "SELECT u FROM Utente u WHERE u.email = :email AND u.password = :password"),
    @NamedQuery(name = "Utente.cercaPerId", query = "SELECT u FROM Utente u WHERE u.id = :id" ),
    @NamedQuery(name = "Utente.cercaPerEmail", query = "SELECT u FROM Utente u WHERE u.email = :email")
})
public class Utente implements Serializable { //l'interfaccia Serializable è necessaria per rendere persistenti gli oggetti

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Calendar getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Calendar dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }
    
    public void setDataDiNascita(int giorno, int mese, int anno) {
        this.dataDiNascita = new GregorianCalendar(anno, mese-1, giorno);
        this.dataDiNascita.setLenient(false);
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = sha256hash(password);
    }
    
    private void inizializzaListaIndirizzi() {
        this.listaIndirizzi = new ArrayList<>();
    }
   
    public void addIndirizzo(Indirizzo indirizzo) {
        if (this.listaIndirizzi == null) 
            this.inizializzaListaIndirizzi();
        this.listaIndirizzi.add(indirizzo);
    }
    
    public void removeIndirizzo(Indirizzo indirizzo) {
        for(Indirizzo daRimuovere : listaIndirizzi) {
            if(indirizzo.getId().equals(daRimuovere.getId()))
                this.listaIndirizzi.remove(indirizzo);
        }
    }
    
    public List<Indirizzo> getListaIndirizzi() {
        return this.listaIndirizzi;
    }

    public boolean isAutenticato() {
        return autenticato;
    }

    public void setAutenticato(boolean autenticato) {
        this.autenticato = autenticato;
    }
    
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
