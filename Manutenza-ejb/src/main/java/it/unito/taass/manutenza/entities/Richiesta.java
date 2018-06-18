package it.unito.taass.manutenza.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
@NamedQueries({
    @NamedQuery(name = "Richieste utente e stato", query = "SELECT r FROM Richiesta r WHERE r.utente = :utente AND r.stato = :stato"),
    @NamedQuery(name = "Richiesta per id", query = "SELECT r FROM Richiesta r WHERE r.id = :id")
})
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
    @OneToMany(cascade = CascadeType.ALL)
    private List<Foto> listaFoto = new ArrayList<>();
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
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

    public List<Foto> getListaFoto() {
        return listaFoto;
    }

    public void setListaFoto(List<Foto> listaFoto) {
        this.listaFoto = listaFoto;
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
    
    public void aggiungiFoto(Foto foto) {
        this.listaFoto.add(foto);
    }
    
    public void eliminaFoto(Foto daEliminare) {
        for(Foto daCercare : listaFoto) {
            if(daCercare.getId().equals(daEliminare.getId())) {
                this.listaFoto.remove(daEliminare);
            }
        }
    }

}
