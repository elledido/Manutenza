package it.unito.taass.manutenza.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Manutente manutente;
    @OneToOne
    private Richiesta richiesta;
    private short valutazioneComplessiva;
    private short professionalita;
    private short comunicazione;
    private String commento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Manutente getManutente() {
        return manutente;
    }

    public void setManutente(Manutente manutente) {
        this.manutente = manutente;
    }

    public Richiesta getRichiesta() {
        return richiesta;
    }

    public void setRichiesta(Richiesta richiesta) {
        this.richiesta = richiesta;
    }

    public short getValutazioneComplessiva() {
        return valutazioneComplessiva;
    }

    public void setValutazioneComplessiva(short valutazioneComplessiva) {
        this.valutazioneComplessiva = valutazioneComplessiva;
    }

    public short getProfessionalita() {
        return professionalita;
    }

    public void setProfessionalita(short professionalita) {
        this.professionalita = professionalita;
    }

    public short getComunicazione() {
        return comunicazione;
    }

    public void setComunicazione(short comunicazione) {
        this.comunicazione = comunicazione;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

}
