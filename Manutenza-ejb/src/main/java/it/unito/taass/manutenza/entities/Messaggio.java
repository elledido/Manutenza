/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.manutenza.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author leonardo
 */
@Entity
public class Messaggio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String testo;
    @Column(name = "MESSAGGIO_TIMESTAMP")
    private Timestamp messaggioTimestamp;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Timestamp getMessaggioTimestamp() {
        return messaggioTimestamp;
    }

    public void setMessaggioTimestamp() {
        Calendar calendar = Calendar.getInstance();
        Date data = calendar.getTime();
        this.messaggioTimestamp = new Timestamp(data.getTime());
    }

}
