<%-- 
    Document   : aggiungiCompetenze
    Created on : 13-mag-2018, 10.25.53
    Author     : irene
--%>

<%@page import="java.util.List"%>
<%@page import="it.unito.taass.manutenza.entities.Categoria"%>
<%@page import="it.unito.taass.manutenza.entities.Manutente"%>
<%@page import="it.unito.taass.manutenza.entities.Utente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Aggiungi competenze</title>

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

                    <fieldset id="newSkillList">

                        <legend>Le tue competenze</legend>

                        <input type="hidden" name="email" value="<% out.print(request.getAttribute("emailManutenteDaCompletare"));%>">

                        <!-- Categoria -->
                        <div id="ct1" class="form-group">
                            <label class="control-label col-md-3 col-xs-3" for="categoria1">Categoria: </label>
                            <div class="col-md-8 col-xs-8">
                                <select class="form-control" id="categoria1" name="categoria1" required>
                                    <c:forEach items="${applicationScope.categorie}" var="categoria">
                                        <option value="${categoria.getNome()}">${categoria.getNome()}</option>
                                    </c:forEach>  
                                </select>
                            </div>
                        </div>

                        <!-- Zona (momentaneamente solo le città in cui ha domicilio il manutente) -->
                        <div id="zn1" class="form-group">
                            <label class="control-label col-xs-3" for="zona1">Zona di competenza: </label>
                            <div class="col-xs-8">
                                <select class="form-control" id="zona1" name="zona1" required>
                                    <c:forEach items="${indirizzi}" var="domicilio">
                                        <option value="${domicilio.getCitta()}">${domicilio.getCitta()}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <!-- Tipo (amatoriale o professionista) -->
                        <div id="tp1" class="form-group">
                            <label class="control-label col-xs-3">Tipo: </label>
                            <div class="col-xs-8">
                                <label class="radio-inline">
                                    <input type="radio" name="tipo1" value="A" onclick="disablePI(1);" required>Amatoriale
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="tipo1" value="P" onclick="enablePI(1);">Professionista
                                </label>
                            </div>
                        </div>

                        <!-- Partita IVA (solo professionista) -->
                        <div id="pi1" class="form-group">
                            <label class="control-label col-xs-3" for="partitaIVA1">Partita IVA: </label>
                            <div class="col-xs-8">
                                <div class="row">
                                    <input class="form-control" id="partitaIVA1" name="partitaIVA1" type="text" disabled>
                                </div>
                                <div class="row">
                                    <!-- Messaggio di errore -->
                                </div>
                            </div>
                        </div>


                        <div class="col-xs-3">
                            <p class="add-skill" role="button" onclick="addSkill();"><span>+</span> Aggiungi competenza</p>
                        </div>

                    </fieldset>

                    <fieldset class="sign-in">
                        <div class="row">
                            <!-- SUBMIT -->
                            <div class="col-xs-3">
                                <button type="submit" class="btn btn-block btn-primary" name="action" value="registraManutente">Completa registrazione</button>
                            </div>
                        </div>
                    </fieldset>

                </form>

            </div>

            <!-- FOOTER -->
            <%@include file="/footer.txt"%>

        </div>

        <script type="text/javascript">

            var skills = 1;
            var maxSkills = <%= ((List) application.getAttribute("categorie")).size()%>;

            // funzione per aggiungere un campo per un nuovo domicilio
            function addSkill() {

                var newSkillList = $('#newSkillList'); //lista di domicili

                skills++; //incremento il numero di domicili

                //rimuovi i bottoni
                $('p.add-skill').parent().remove();
                $('p.remove-skill').parent().remove();

                //form per il nuovo domicilio
                var formGroup = '<div id="line' + skills + '" class="line"></div>' +
                                '<div id="ct' + skills + '" class="form-group">' +
                                    '<label class="control-label col-md-3 col-xs-3" for="categoria' + skills + '">Categoria: </label>' +
                                    '<div class="col-md-8 col-xs-8">' +
                                        '<select class="form-control" id="categoria' + skills + '" name="categoria' + skills + '" required>' +
                                            '<c:forEach items="${applicationScope.categorie}" var="categoria">' +
                                                '<option value="${categoria.getNome()}">${categoria.getNome()}</option>' +
                                            '</c:forEach>' +
                                        '</select>' +
                                    '</div>' +
                                '</div>' +
                                '<div id="zn' + skills + '" class="form-group">' +
                                    '<label class="control-label col-xs-3" for="zona' + skills + '">Zona di competenza: </label>' +
                                    '<div class="col-xs-8">' +
                                        '<select class="form-control" id="zona' + skills + '" name="zona' + skills + '" required>' +
                                            '<c:forEach items="${indirizzi}" var="domicilio">' +
                                                '<option value="${domicilio.getCitta()}">${domicilio.getCitta()}</option>' +
                                            '</c:forEach>' +
                                        '</select>' +
                                    '</div>' +
                                '</div>' +
                                '<div id="tp' + skills + '" class="form-group">' +
                                    '<label class="control-label col-xs-3">Tipo: </label>' +
                                    '<div class="col-xs-8">' +
                                        '<label class="radio-inline">' +
                                            '<input type="radio" name="tipo' + skills + '" value="A" onclick="disablePI(' + skills + ');" required>Amatoriale' +
                                        '</label>' +
                                        '<label class="radio-inline">' +
                                            '<input type="radio" name="tipo' + skills + '" value="P" onclick="enablePI(' + skills + ');">Professionista' +
                                        '</label>' +
                                    '</div>' +
                                '</div>' +
                                '<div id="pi' + skills + '" class="form-group">' +
                                    '<label class="control-label col-xs-3" for="partitaIVA' + skills + '">Partita IVA: </label>' +
                                    '<div class="col-xs-8">' +
                                        '<div class="row">' +
                                            '<input class="form-control" id="partitaIVA' + skills + '" name="partitaIVA' + skills + '" type="text" disabled>' +
                                        '</div>' +
                                        '<div class="row">' +
                                        '</div>' +
                                    '</div>' +
                                '</div>';


                //aggiungi il domicilio alla lista
                newSkillList.append(formGroup);

                //se non ho raggiunto il numero massimo di domicili
                if (skills < maxSkills) {
                    //bottone per aggiungere un nuovo campo per i domicili
                    var addBtn = '<div class="col-xs-3">' +
                            '<p class="add-skill" role="button" onclick="addSkill();"><span>+</span> Aggiungi competenza</p>' +
                            '</div>';
                    //reinserisco il bottone per l'aggiunta dei domicili
                    newSkillList.append(addBtn);
                }

                //bottone per rimuovere l'ultimo domicilio
                var removeBtn = '<div class="col-xs-3">' +
                        '<p class="remove-skill" role="button" onclick="removeSkill();"><span>-</span> Rimuovi competenza</p>' +
                        '</div>';

                //aggiungo il bottone per la rimozione dei domicili
                newSkillList.append(removeBtn);

            }

            // funzione per rimuovere il campo dell'ultimo domicilio
            function removeSkill() {

                var newSkillList = $('#newSkillList'); //lista di domicili

                //rimuovo la line
                $('#line' + skills).remove();

                //rimuovo il campo categoria
                $('#ct' + skills).remove();

                //rimuovo il campo zona
                $('#zn' + skills).remove();

                //rimuovo il campo tipo
                $('#tp' + skills).remove();

                //rimuovo il campo partita iva
                $('#pi' + skills).remove();

                //rimuovo i bottoni
                $('p.add-skill').parent().remove();
                $('p.remove-skill').parent().remove();

                skills--;

                //se avevo inserito tutti i domicili possibili
                if (skills < maxSkills)
                {
                    //inserisco nuovamente il bottone di aggiungi domicilio
                    var addBtn = '<div class="col-xs-3">' +
                            '<p class="add-skill" role="button" onclick="addSkill();"><span>+</span> Aggiungi competenza</p>' +
                            '</div>';

                    newSkillList.append(addBtn);
                }

                //se ci sono più campi domicilio
                if (skills > 1) {
                    //inserisco nuovamente il bottone per rimuovere i domicili
                    var removeBtn = '<div class="col-xs-3">' +
                            '<p class="remove-skill" role="button" onclick="removeSkill();"><span>-</span> Rimuovi competenza</p>' +
                            '</div>';

                    newSkillList.append(removeBtn);
                }

            }

            //se professionista, abilita partita IVA
            function enablePI(id) {
                $('#partitaIVA' + id).prop('disabled', false);
                $('#partitaIVA' + id).prop('required', true);
            }

            //se amatoriale, disabilita partita IVA
            function disablePI(id) {
                $('#partitaIVA' + id).prop('disabled', true);
                $('#partitaIVA' + id).prop('required', false);
            }

            //verifica la correttezza della partita IVA
            function verifyPartitaIVA(partitaIVA) {
                
                var valido;
                //dati da inviare al WS della partita IVA
                var form = new FormData();
                form.append("memberStateCode", "IT");
                form.append("number", partitaIVA);

                var settings = {
                    "async": false,
                    "crossDomain": true,
                    "url": "http://localhost:8080/IVA/check",
                    "method": "POST",
                    "headers": {
                        "Cache-Control": "no-cache",
                        "Postman-Token": "4f4c907f-e427-4eb1-b0cf-588821664a60"
                    },
                    "processData": false,
                    "contentType": false,
                    "mimeType": "multipart/form-data",
                    "data": form
                };

                //check della partita IVA
                $.ajax(settings).done(function (response) {
                    if (response === 'false') {
                        console.log('Partita IVA: ' + partitaIVA);
                        valido = false;
                    } else {
                        valido = true;
                    }
                });

                return valido;
            }

            $(document).ready(function () {
                //al submit del form
                $('form').on('submit', function () {
                    
                    var errorMsg = '<p class="errorMsg">Partita IVA non valida!</p>';
                    var i;
                    
                    //verifico ogni partita IVA inserita
                    for(i=1; i<=skills; i++){
                        
                        //row che contiene il messaggio di errore
                        var parent = $('#pi' + i + '>div.col-xs-8>div.row:last-child');
                        //rimuovo il messaggio d'errore
                        parent.children('p').remove();
                        
                        if($('#partitaIVA'+i).prop('disabled') === false) {
                            
                            var partitaIVA = $('#partitaIVA'+i).val(); //partita IVA inserita dall'utente
                            
                            //se almeno una ritorna false, segnalo l'errore e blocco l'operazione
                            if(verifyPartitaIVA(partitaIVA) === false){
                                //stampa un messaggio di errore
                                parent.append(errorMsg);
                                return false;
                            }
                        }
                        
                    }
                    
                });
            });

        </script>

    </body>
</html>

