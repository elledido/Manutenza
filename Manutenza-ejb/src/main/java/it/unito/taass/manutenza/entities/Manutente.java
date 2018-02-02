package it.unito.taass.manutenza.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Manutente extends Utente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "VALUTAZIONE_COMPLESSIVA")
    private int valutazioneComplessiva;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Competenza> listaCompetenze;

    public int getValutazioneComplessiva() {
        return valutazioneComplessiva;
    }

    public void setValutazioneComplessiva(int valutazioneComplessiva) {
        this.valutazioneComplessiva = valutazioneComplessiva;
    }
   
}
