package it.unito.taass.manutenza.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "Manutente.caricaPerEmail", query = "SELECT m FROM Manutente m WHERE m.email = :email")
})
public class Manutente extends Utente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "VALUTAZIONE_COMPLESSIVA")
    private int valutazioneComplessiva;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Competenza> listaCompetenze = new ArrayList<>();

    public int getValutazioneComplessiva() {
        return valutazioneComplessiva;
    }

    public void setValutazioneComplessiva(int valutazioneComplessiva) {
        this.valutazioneComplessiva = valutazioneComplessiva;
    }

    public List<Competenza> getListaCompetenze() {
        return listaCompetenze;
    }

    public void setListaCompetenze(List<Competenza> listaCompetenze) {
        this.listaCompetenze = listaCompetenze;
    }
    
    public void addCompetenza(Competenza competenza) {
        if (this.getListaCompetenze() == null) this.listaCompetenze = new ArrayList<>();
        this.listaCompetenze.add(competenza);
    }
   
}
