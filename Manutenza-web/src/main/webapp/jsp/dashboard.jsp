<%-- 
    Document   : dashboard
    Created on : 2-feb-2018, 11.48.34
    Author     : irene
--%>

<%@page import="it.unito.taass.manutenza.entities.Manutente"%>
<%@page import="it.unito.taass.manutenza.entities.Utente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="utente" value="<%= session.getAttribute("utente")%>"/>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Dashboard</title>

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
        <div class="jumbotron"></div>

        <!-- MAIN CONTAINER -->
        <div class="container">
            <h2>Dashboard</h2>

            <div class="row">

                <div class="col-xs-6 col-md-4">
                    <div class="thumbnail">
                        <div class="text-center" style="background-color: #fffffa; height: 200px;">
                            <img src="images/workInProgress.png" alt="Richieste in corso">
                        </div>
                        <div id="requests" class="caption text-center">
                            <h3>Le tue richieste</h3>
                        </div>
                    </div>
                </div>

                <div class="col-xs-6 col-md-4">
                    <div class="thumbnail">
                        <div class="text-center" style="background-color: #fffffa; height: 200px;">
                            <img src="images/workHelmet.png" alt="Richieste in corso">
                        </div>
                        <div class="caption text-center">
                            <h3>Aumenta la tua visibilità!</h3>
                            <p>Tieni sempre aggiornate le tue competenze</p>
                        </div>
                    </div>
                </div>

                <div class="col-xs-6 col-md-4">
                    <div class="thumbnail">
                        <div class="text-center" style="background-color: #fffffa; height: 200px;">
                            <img src="images/workInProgress.png" alt="Richieste in corso">
                        </div>
                        <div id="works" class="caption text-center">
                            <h3>Proposte di lavoro</h3>
                        </div>
                    </div>
                </div>

            </div>

            <!-- FOOTER -->
            <%@include file="/footer.txt"%>

        </div>

        <script type="text/javascript">

            //scarica le chat non lette per un certo utente
            function checkRequestMessage(email) {

                var form = new FormData();
                form.append("utente", email);

                var settings = {
                    "async": true,
                    "crossDomain": true,
                    "url": "http://localhost:8080/checkMessageByUtente",
                    "method": "POST",
                    "headers": {
                        "Cache-Control": "no-cache",
                        "Postman-Token": "a12a025f-7630-4b2d-b55c-698df2965c64"
                    },
                    "processData": false,
                    "contentType": false,
                    "mimeType": "multipart/form-data",
                    "data": form
                };

                $.ajax(settings).done(function (response) {
                    //se ci sono chat con messaggi non letti
                    if (response.length > 0) {

                        var msgNonLetti = 0; //numero di messaggi non letti
                        var chat = JSON.parse(response); //chat non lette

                        var c; //chat
                        var m; //messaggi

                        for (c = 0; c < chat.length; c++) {
                            //lista di messaggi non letti per la chat c
                            var messaggi = chat[c].listaMessaggi;

                            for (m = 0; m < messaggi.length; m++) {
                                var emailMittente = messaggi[m].email;
                                //se non è stato l'utente ad inviare il messaggio
                                if (email !== emailMittente) {
                                    //aggiorno il contatore
                                    msgNonLetti++;
                                }
                            }
                        }

                        var content;

                        if (msgNonLetti > 0) {
                            content = '<p>Qualcuno ha trovato la tua proposta interessante!</p>' +
                                    '<p>Vai alla pagina delle <a class="termini-condizioni" href="/Manutenza-web/MainController?action=richiesteInCorso">richieste in corso</a></p>';
                        } else {
                            content = '<p>Hai bisogno di qualcosa? Fai una <a class="termini-condizioni" href="/Manutenza-web/MainController?action=nuovaRichiesta">nuova richiesta</a>!</p>' +
                                    '<p>Oppure vai alla pagina delle <a class="termini-condizioni" href="/Manutenza-web/MainController?action=richiesteInCorso">richieste in corso</a></p>';
                        }

                        $('#requests').append(content);

                    }
                });
            }

            //scarica le chat non lette per un certo manutente
            function checkWorkMessage(email) {

                var form = new FormData();
                form.append("manutente", email);

                var settings = {
                    "async": true,
                    "crossDomain": true,
                    "url": "http://localhost:8080/checkMessageByManutente",
                    "method": "POST",
                    "headers": {
                        "Cache-Control": "no-cache",
                        "Postman-Token": "e3677b4e-0ee6-4a48-aacd-a7cf025d3f11"
                    },
                    "processData": false,
                    "contentType": false,
                    "mimeType": "multipart/form-data",
                    "data": form
                };

                $.ajax(settings).done(function (response) {
                    //se ci sono chat con messaggi non letti
                    if (response.length > 0) {

                        var msgNonLetti = 0; //numero di messaggi non letti
                        var chat = JSON.parse(response); //chat non lette

                        var c; //chat
                        var m; //messaggi

                        for (c = 0; c < chat.length; c++) {
                            //lista di messaggi non letti per la chat c
                            var messaggi = chat[c].listaMessaggi;

                            for (m = 0; m < messaggi.length; m++) {
                                var emailMittente = messaggi[m].email;
                                //se non è stato l'utente ad inviare il messaggio
                                if (email !== emailMittente) {
                                    //aggiorno il contatore
                                    msgNonLetti++;
                                }
                            }
                        }

                        var content;

                        if (msgNonLetti > 0) {
                            content = '<p>Qualcuno ha risposto alla tua richiesta!</p>' +
                                    '<p>Vai alla pagina delle <a class="termini-condizioni" href="/Manutenza-web/MainController?action=nuoveProposte">nuove proposte</a></p>';
                        } else {
                            content = '<p>Vedi se ci sono <a class="termini-condizioni" href="/Manutenza-web/MainController?action=nuoveProposte">nuove proposte</a> di lavoro per te</p>' +
                                    '<p>Oppure vai alla pagina dei <a class="termini-condizioni" href="/Manutenza-web/MainController?action=proposteAccettate">lavori in corso</a></p>';
                        }

                        $('#works').append(content);

                    }
                });

            }

            $(document).ready(function () {

                var email = "${utente.getEmail()}";

                //verifico che ci siano messaggi non letti per le richieste in corso
                checkRequestMessage(email);

                //verifico che ci siano messaggi non letti per le proposte di lavoro
                checkWorkMessage(email);

            });


        </script>

    </body>

</html>
