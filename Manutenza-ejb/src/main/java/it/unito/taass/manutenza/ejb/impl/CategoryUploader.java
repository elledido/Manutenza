package it.unito.taass.manutenza.ejb.impl;

import it.unito.taass.manutenza.ejb.GestoreCategorieLocal;
import it.unito.taass.manutenza.entities.Categoria;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.transaction.Transactional;

/**
 *
 * @author leonardo
 */
@Singleton
@Startup
@DataSourceDefinition(
    className = "org.apache.derby.jdbc.EmbeddedDataSource",
    name = "java:global/jdbc/sample",
    user = "app",
    password = "app",
    properties = {"connectionAttributes=;create=true"}
)

public class CategoryUploader {
    
    @EJB(beanName = "GestoreCategorie")
    private GestoreCategorieLocal gestoreCategorie;
   
    private Categoria categoria;
    private Categoria categoria2;
    //private List<Categoria> listaCategorie;

    @Transactional //Per via di un big(?) i metodi con PostConstruct vanno resi transactional
    @PostConstruct
    public void populateDb(){
        System.out.println("Inizio creazione nuova categoria");
        categoria = new Categoria();
        categoria.setNome("Idraulico");
        categoria2 = new Categoria();
        categoria2.setNome("Elettricista");
        
        System.out.println("Registro nuova categoria");
        gestoreCategorie.registraCategoria(categoria);
        gestoreCategorie.registraCategoria(categoria2);
    }
    
    public void upload() {
        List<Categoria> listaCategoria = gestoreCategorie.caricaListaCategorie();
        
        for(Categoria categoria : listaCategoria) {
            System.out.println("Categoria: " + categoria.getNome());
        }
    }
    
//    @PreDestroy
//    public void cleanDB() {
//        System.out.println("Elimino utente");
//        gestoreUtente.eliminaUtente(utente);
//    }
//    
}