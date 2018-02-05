package it.unito.taass.manutenza.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Proposta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Manutente manutente;
    @OneToOne
    private Richiesta richiesta;
    private float prezzo;
    private boolean accettato;

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

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public boolean isAccettato() {
        return accettato;
    }

    public void setAccettato(boolean accettato) {
        this.accettato = accettato;
    }

}