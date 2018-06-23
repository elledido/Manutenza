package it.unito.taass.manutenza.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
    @NamedQuery(name = "Ricerca per richiesta", query = "SELECT p FROM Proposta p WHERE p.richiesta.id = :richiestaId"),
    @NamedQuery(name = "Ricerca per richiesta e manutente", query = "SELECT p FROM Proposta p WHERE p.richiesta.id = :richiestaId AND p.manutente = :manutente"),
    @NamedQuery(name = "Ricerca proposte accettate", query = "SELECT p FROM Proposta p WHERE p.manutente = :manutente AND p.accettato = TRUE AND p.richiesta.stato = :stato"),
    @NamedQuery(name = "Ricerca proposta accettata per richiesta", query = "SELECT p FROM Proposta p WHERE p.accettato = TRUE AND p.richiesta.id = :richiestaId")
})
public class Proposta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Manutente manutente;
    @OneToOne(cascade = CascadeType.MERGE)
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
