/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unito.taass.manutenza.ejb;

import it.unito.taass.manutenza.entities.Chat;
import it.unito.taass.manutenza.entities.Manutente;
import it.unito.taass.manutenza.entities.Proposta;
import it.unito.taass.manutenza.entities.Utente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author leonardo
 */
@Local
public interface GestoreChatLocal {
    
    public void creaChat(Proposta proposta, Utente utente, Manutente manutente);
    public void updateChat(Chat chat);
    public List<Chat> ricercaChatPerUtente(Utente utente);
    public List<Chat> ricercaChatPerManutente(Manutente manutente);
    public List<Chat> ricercaPerUtenteManutente(Utente utente, Manutente manutente);
    
}
