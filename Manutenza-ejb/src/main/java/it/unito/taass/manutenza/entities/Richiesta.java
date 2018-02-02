package it.unito.taass.manutenza.entities;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class Richiesta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Utente utente;
    @OneToOne
    private Indirizzo indirizzo;
    private String titolo;
    private String descrizione;
    private String categoria; //idraulico eccetera
    private float budget;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataDiCreazione;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataDiCompletamento;
    private String stato; //accettato, in attesa, annullato, completato, in lavorazione

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public Calendar getDataDiCreazione() {
        return dataDiCreazione;
    }

    public void setDataDiCreazione(Calendar dataDiCreazione) {
        this.dataDiCreazione = dataDiCreazione;
    }

    public Calendar getDataDiCompletamento() {
        return dataDiCompletamento;
    }

    public void setDataDiCompletamento(Calendar dataDiCompletamento) {
        this.dataDiCompletamento = dataDiCompletamento;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

}
