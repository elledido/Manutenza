<%-- 
    Document   : completaProfilo
    Created on : 1-mag-2018, 18.45.30
    Author     : irene
--%>

<%@page import="it.unito.taass.manutenza.entities.Manutente"%>
<%@page import="it.unito.taass.manutenza.entities.Utente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Completa Profilo</title>

        <!-- Bootstrap CSS CDN -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href='https://fonts.googleapis.com/css?family=Andika' rel='stylesheet'>
        <link href="manutenzastyle.css" rel="stylesheet" type="text/css"/>
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
        <!-- Font Awesome -->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

        <!-- jQuery CDN -->
        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
        <!-- Bootstrap Js CDN -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Custom Scroll Js CDN -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>
        <!-- Custom JS -->
        <script src="manutenza.js" type="text/javascript"></script>
    </head>
    <body>

        <!-- NAVBAR -->
        <%@include file="/navbar.txt"%>

        <!-- MAIN CONTAINER -->
        <div class="container">

            <div class="form-box">

                <form class="form-horizontal" action="/Manutenza-web/MainController" method="post">

                    <!-- DATI DEL PROFILO -->
                    <fieldset>

                        <legend>Dati profilo</legend>

                        <!-- Email -->
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3" for="email">Email: </label>
                            <div class="col-md-8 col-xs-8">
                                <input class="form-control" id="email" name="email" type="text" value="<%out.print(request.getAttribute("email"));%>" readonly>
                            </div>
                        </div>

                        <!-- Nome -->
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3" for="nome">Nome: </label>
                            <div class="col-md-8 col-xs-8">
                                <input class="form-control" id="nome" name="nome" type="text" value="<%out.print(request.getAttribute("nome"));%>" readonly>
                            </div>
                        </div>

                        <!-- Cognome -->
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3" for="cognome">Cognome: </label>
                            <div class="col-md-8 col-xs-8">
                                <input class="form-control" id="cognome" name="cognome" type="text" value="<%out.print(request.getAttribute("cognome"));%>" readonly>
                            </div>
                        </div>

                        <!-- Data di nascita -->
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3" for="dataDiNascita">Data di nascita: </label>
                            <div class="col-md-8 col-xs-8">
                                <input class="form-control" id="dataDiNascita" name="dataDiNascita" type="date" required>
                            </div>
                        </div>

                        <!-- Codice fiscale -->
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3" for="codiceFiscale">Codice fiscale: </label>
                            <div class="col-md-8 col-xs-8">
                                <input class="form-control" id="codiceFiscale" name="codiceFiscale" type="text" placeholder="XXXXXX00X00X000X" required>
                            </div>
                        </div>

                    </fieldset>

                    <fieldset id="domicile-list">

                        <legend>Dati domicilio</legend>

                        <!-- Città e provincia -->
                        <div id="cp1" class="row">
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-2" for="citta1">Citt&agrave;: </label>
                                    <div class="col-md-8 col-xs-9">
                                        <input class="form-control" id="citta1" name="citta1" type="text" placeholder="Citt&agrave;" required>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="control-label col-md-8 col-xs-2" for="provincia1">Provincia: </label>
                                    <div class="col-md-4 col-xs-2">
                                        <input class="form-control text-center" id="provincia1" name="provincia1" type="text" placeholder="PR" required>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Indirizzo e CAP -->
                        <div id="ic1" class="row">
                            <div class="col-md-8 ">
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-2" for="indirizzo1">Indrizzo: </label>
                                    <div class="col-md-8 col-xs-9">
                                        <input class="form-control" id="indirizzo1" name="indirizzo1" type="text" placeholder="Via/Viale/Piazza" required>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 ">
                                <div class="form-group">
                                    <label class="control-label col-md-7 col-xs-2" for="cap1">CAP: </label>
                                    <div class="col-md-5 col-xs-2">
                                        <input class="form-control text-center" id="cap1" name="cap1" type="text" placeholder="00000" required>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-xs-3">
                            <p class="add-domicile" role="button" onclick="addDomicile();"><span>+</span> Aggiungi domicilio</p>
                        </div>

                    </fieldset>

                    <fieldset class="sign-in">
                        <div class="row">
                            <!-- SUBMIT -->
                            <div class="col-md-2 col-xs-3">
                                <button type="submit" class="btn btn-block btn-primary" name="action" value="registraUtente">Registrami</button>
                            </div>
                            <div class="col-md-2 col-xs-4">
                                <button type="submit" class="btn btn-block btn-ok sign-in" name="action" value="diventaManutente">Diventa Manutente</button>
                            </div>
                            <!-- TERMINI E CONDIZIONI D'USO -->
                            <div class="col-xs-4">
                                <div class="form-check">
                                    <input class="col-xs-1 form-check-input" type="checkbox" value="" id="termini" name="termini" required>
                                    <label class="col-xs-10 form-check-label" for="termini">
                                        Accetto <a class="termini-condizioni" href="#">termini e condizioni d'uso</a>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </fieldset>

                </form>
                            
            </div>

            <!-- FOOTER -->
            <%@include file="/footer.txt"%>

        </div>

        <script type="text/javascript">
            
            var domiciles = 1;
            var maxDomiciles = 3;
            
            // funzione per aggiungere un campo per un nuovo domicilio
            function addDomicile() {
                
                var domicileList = $('#domicile-list'); //lista di domicili
                
                domiciles++; //incremento il numero di domicili
                
                //rimuovi i bottoni
                $('p.add-domicile').parent().remove();
                $('p.remove-domicile').parent().remove();
                
                //form per il nuovo domicilio
                var formGroup = '<div id="line' + domiciles + '" class="line"></div>' +
                                '<div id="cp' + domiciles + '" class="row">' +
                                    '<div class="col-md-8">' +
                                        '<div class="form-group">' +
                                            '<label class="control-label col-md-3 col-xs-2" for="citta' + domiciles + '">Citt&agrave;: </label>' +
                                            '<div class="col-md-8 col-xs-9">' +
                                                '<input class="form-control" id="citta' + domiciles + '" name="citta' + domiciles + '" type="text" placeholder="Citt&agrave;" required>' +
                                            '</div>' +
                                        '</div>' +
                                    '</div>' +
                                    '<div class="col-md-3">' +
                                        '<div class="form-group">' +
                                            '<label class="control-label col-md-8 col-xs-2" for="provincia' + domiciles + '">Provincia: </label>' +
                                            '<div class="col-md-4 col-xs-2">' +
                                                '<input class="form-control text-center" id="provincia' + domiciles + '" name="provincia' + domiciles + '" type="text" placeholder="PR" required>' +
                                            '</div>' +
                                        '</div>' +
                                    '</div>' +
                                '</div>' +
                                '<div id="ic' + domiciles + '" class="row">' +
                                    '<div class="col-md-8 ">' +
                                        '<div class="form-group">' + 
                                            '<label class="control-label col-md-3 col-xs-2" for="indirizzo' + domiciles + '">Indrizzo: </label>' +
                                            '<div class="col-md-8 col-xs-9">' +
                                                '<input class="form-control" id="indirizzo' + domiciles + '" name="indirizzo' + domiciles + '" type="text" placeholder="Via/Viale/Piazza" required>' +
                                            '</div>' +
                                        '</div>' +
                                    '</div>' +
                                    '<div class="col-md-3 ">' +
                                        '<div class="form-group">' +
                                            '<label class="control-label col-md-7 col-xs-2" for="cap' + domiciles + '">CAP: </label>' +
                                            '<div class="col-md-5 col-xs-2">' +
                                                '<input class="form-control text-center" id="cap' + domiciles + '" name="cap' + domiciles + '" type="text" placeholder="00000" required>' +
                                            '</div>' +
                                        '</div>' +
                                    '</div>' +
                                '</div>';
                        
                        
                //aggiungi il domicilio alla lista
                domicileList.append(formGroup);

                //se non ho raggiunto il numero massimo di domicili
                if(domiciles < maxDomiciles){
                    //bottone per aggiungere un nuovo campo per i domicili
                    var addBtn = '<div class="col-xs-3">' +
                                    '<p class="add-domicile" role="button" onclick="addDomicile();"><span>+</span> Aggiungi domicilio</p>' +
                                '</div>';
                    //reinserisco il bottone per l'aggiunta dei domicili
                    domicileList.append(addBtn);
                }

                //bottone per rimuovere l'ultimo domicilio
                var removeBtn = '<div class="col-xs-3">' +
                                    '<p class="remove-domicile" role="button" onclick="removeDomicile();"><span>-</span> Rimuovi domicilio</p>' +
                                '</div>';
                
                //aggiungo il bottone per la rimozione dei domicili
                domicileList.append(removeBtn);
                
            }
            
            // funzione per rimuovere il campo dell'ultimo domicilio
            function removeDomicile() {
                
                var domicileList = $('#domicile-list'); //lista di domicili
                
                //rimuovo la line
                $('#line' + domiciles).remove();
                
                //rimuovo il campo Città-Provincia
                $('#cp' + domiciles).remove();
                
                //rimuovo il campo Indirizzo e CAP
                $('#ic' + domiciles).remove();
                
                //rimuovo i bottoni
                $('p.add-domicile').parent().remove();
                $('p.remove-domicile').parent().remove();
                
                domiciles--;
                
                //se avevo inserito tutti i domicili possibili
                if(domiciles < maxDomiciles)
                {
                    //inserisco nuovamente il bottone di aggiungi domicilio
                    var addBtn = '<div class="col-xs-3">' +
                                    '<p class="add-domicile" role="button" onclick="addDomicile();"><span>+</span> Aggiungi domicilio</p>' +
                                '</div>';
                    
                    domicileList.append(addBtn);
                }
                
                //se ci sono più campi domicilio
                if(domiciles > 1) {
                    //inserisco nuovamente il bottone per rimuovere i domicili
                    var removeBtn = '<div class="col-xs-3">' +
                                        '<p class="remove-domicile" role="button" onclick="removeDomicile();"><span>-</span> Rimuovi domicilio</p>' +
                                    '</div>';
                    
                    domicileList.append(removeBtn);
                }
                
            }
        
        </script>

    </body>
</html>

