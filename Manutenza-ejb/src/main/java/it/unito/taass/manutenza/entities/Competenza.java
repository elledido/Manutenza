package it.unito.taass.manutenza.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Competenza implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String categoria; //idraulico eccetera
    private String tipo; //amatoriale o professionista
    private String partitaIva;
    private String zonaDiCompetenza; //locale, regionale, nazionale eccetera

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getZonaDiCompetenza() {
        return zonaDiCompetenza;
    }

    public void setZonaDiCompetenza(String zonaDiCompetenza) {
        this.zonaDiCompetenza = zonaDiCompetenza;
    }

}
