/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.manutenza.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author leonardo
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Ricerca per utente", query = "SELECT c FROM Chat c WHERE c.utente = :utente"),
    @NamedQuery(name = "Ricerca per manutente", query = "SELECT c FROM Chat c WHERE c.manutente = :manutente"),
    @NamedQuery(name = "Ricerca per utente e manutente", query = "SELECT c FROM Chat c WHERE c.utente = :utente AND c.manutente = :manutente"),
    @NamedQuery(name = "Ricerca per proposta", query = "SELECT c FROM Chat c WHERE c.proposta.id = :propostaId")
})
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Id
    @OneToOne
    private Proposta proposta;
    @OneToOne
    private Utente utente;
    @OneToOne
    private Manutente manutente;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Messaggio> listaMessaggi = new ArrayList<Messaggio>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Manutente getManutente() {
        return manutente;
    }

    public void setManutente(Manutente manutente) {
        this.manutente = manutente;
    }

    public List<Messaggio> getListaMessaggi() {
        return listaMessaggi;
    }

    public void setListaMessaggi(List<Messaggio> listaMessaggi) {
        this.listaMessaggi = listaMessaggi;
    }

}
