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

/**
 * Rappresenta l'entità Richiesta per l'invio di richieste ai Manutenti
 * @author Leonardo Di Domenico
 * @version 1.0
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Richieste utente e stato", query = "SELECT r FROM Richiesta r WHERE r.utente = :utente AND r.stato = :stato"),
    @NamedQuery(name = "Richiesta per id", query = "SELECT r FROM Richiesta r WHERE r.id = :id"),
    @NamedQuery(name = "Proposte di lavoro per categoria e zona ad un manutente", 
            query = "SELECT r FROM Richiesta r WHERE r.categoria = :categoria AND r.indirizzo.citta = :zona AND r.utente.email != :email AND r.stato = :stato")
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

    /**
     * Restituisce l'id univoco associato alla richeista
     * @return Un id univoco associato alla richeista
     */
    public Long getId() {
        return id;
    }

    /**
     * Setta un id univoco per la richiesta
     * @param id Un identificatore univoco
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Restituisce l'utente che ha definito la richiesta
     * @return L'utente che ha definito la richiesta
     */
    public Utente getUtente() {
        return utente;
    }

    /**
     * Setta l'utente che ha definito la richiesta
     * @param utente L'utente che ha definito la richiesta
     */
    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    /**
     * Restituisce l'indirizzo di domicilio asscociato alla richiesta
     * @return Un indirizzo di domicilio
     */
    public Indirizzo getIndirizzo() {
        return indirizzo;
    }

    /**
     * Setta l'indirizzo di domicilio associato alla richiesta
     * @param indirizzo L'indirizzo associato alla richiesta
     */
    public void setIndirizzo(Indirizzo indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Restituisce il titolo che rappresenta la richiesta
     * @return Il titolo della richiesta
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Setta un titolo per la richeista
     * @param titolo Il titolo della richiesta
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * Restituisce la descrizione della richiesta
     * @return La descrizione della richiesta
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta una descrizione per la richiesta di servizio
     * @param descrizione La descrizione della richiesta
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Restituisce la categoria alla quale la richiesta è associata
     * @return La categoria associata alla richiesta
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Imposta la categoria professionale a cui la richiesta è associata
     * @param categoria La categoria professionale associata alla richiesta
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Restituisce il budget che l'utente ha a disposizione per la richiesta di servizio
     * @return Un budget disponibile
     */
    public float getBudget() {
        return budget;
    }

    /**
     * Setta il budget che l'utente ha a disposizione per la richeista di servizio
     * @param budget Un valore economico
     */
    public void setBudget(float budget) {
        this.budget = budget;
    }

    /**
     * Retsituisce la lista delle foto associate alla richiesta di servizio
     * @return Una lista di foto
     */
    public List<Foto> getListaFoto() {
        return listaFoto;
    }

    /**
     * Setta una lista di foto per la richiesta di servizio
     * @param listaFoto Una lista di foto
     */
    public void setListaFoto(List<Foto> listaFoto) {
        this.listaFoto = listaFoto;
    }

    /**
     * Restituisce la data di creazione della richiesta di servizio
     * @return Una data
     */
    public Calendar getDataDiCreazione() {
        return dataDiCreazione;
    }

    /**
     * Setta la data di creazione della richiesta di servizio
     * @param dataDiCreazione Una data
     */
    public void setDataDiCreazione(Calendar dataDiCreazione) {
        this.dataDiCreazione = dataDiCreazione;
    }

    /**
     * Restituisce la data di completamento della richiesta di servizio
     * @return Una data
     */
    public Calendar getDataDiCompletamento() {
        return dataDiCompletamento;
    }

    /**
     * Imposta la data di completamento della richiesta di servizio
     * @param dataDiCompletamento 
     */
    public void setDataDiCompletamento(Calendar dataDiCompletamento) {
        this.dataDiCompletamento = dataDiCompletamento;
    }

    /**
     * Restituisce lo stato di completamento della richiesta
     * @return Uno stato di completamento
     */
    public String getStato() {
        return stato;
    }

    /**
     * Setta lo stato di completamento della richiesta
     * @param stato Uno stato di completamento
     */
    public void setStato(String stato) {
        this.stato = stato;
    }
    
    /**
     * Aggiunge una foto alla lista di foto associate alla richeista di servizio
     * @param foto Una foto
     */
    public void aggiungiFoto(Foto foto) {
        this.listaFoto.add(foto);
    }
    
    /**
     * Elimina una foto dalla lista di foto associate alla richiesta di servizio
     * @param daEliminare Una foto
     */
    public void eliminaFoto(Foto daEliminare) {
        for(Foto daCercare : listaFoto) {
            if(daCercare.getId().equals(daEliminare.getId())) {
                this.listaFoto.remove(daEliminare);
            }
        }
    }

}
