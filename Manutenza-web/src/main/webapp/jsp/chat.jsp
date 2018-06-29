<%-- 
    Document   : chat
    Created on : 27-giu-2018, 15.03.54
    Author     : irene
--%>

<%@page import="it.unito.taass.manutenza.entities.Chat"%>
<%@page import="it.unito.taass.manutenza.entities.Competenza"%>
<%@page import="it.unito.taass.manutenza.entities.Manutente"%>
<%@page import="it.unito.taass.manutenza.entities.Messaggio"%>
<%@page import="it.unito.taass.manutenza.entities.Utente"%>
<%@page import="it.unito.taass.irevaleo.Utilita"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Chat</title>

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
    </head>

    <body>

        <!-- NAVBAR -->
        <%@include file="/navbar.txt"%>
        <div class="jumbotron"></div>

        <!-- MAIN CONTAINER -->
        <div class="container">
            <h2>${chat.getProposta().getRichiesta().getTitolo()}</h2>

            <c:set var="utente" value="<%= session.getAttribute("utente")%>"/>

            <div class="row">
                <!-- chat -->
                <div class="col-xs-9">
                    <div class="panel">
                        <div id="chat">
                            <div class="panel-body">
                                <ul id="chat-list">

                                    <c:if test="${empty chat.getListaMessaggi()}">
                                        <p id="noMsg"><em>Non ci sono messaggi. Inizia ora una conversazione con ${interlocutore.getNome()}</em></p>
                                    </c:if>

                                    <c:forEach items="${chat.getListaMessaggi()}" var="messaggio">
                                        <c:choose>
                                            <c:when test="${utente.getEmail().equals(messaggio.getMittente())}">
                                                <li class="right clearfix">
                                                    <span class="pull-right">
                                                        <img class="chat-img img-circle" src="http://placehold.it/50/FA6F57/fff&text=ME" alt="profile-img"/>
                                                    </span>
                                                    <div class="chat-body clearfix">
                                                        <div class="header">
                                                            <small class="text-muted"><span class="glyphicon glyphicon-time"></span>${messaggio.getHoursMinutes()}</small>
                                                            <strong class="pull-right">${utente.getNome()} ${utente.getCognome()}</strong>
                                                        </div>
                                                        <p>${messaggio.getTesto()}</p>
                                                    </div>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="left clearfix">
                                                    <span class="pull-left">
                                                        <img class="chat-img img-circle" src="images/profile_img.png" alt="profile-img"/>
                                                    </span>
                                                    <div class="chat-body clearfix">
                                                        <div class="header">
                                                            <strong>${interlocutore.getNome()} ${interlocutore.getCognome()}</strong>
                                                            <small class="pull-right text-muted"><span class="glyphicon glyphicon-time"></span>${messaggio.getHoursMinutes()}</small>
                                                        </div>
                                                        <p>${messaggio.getTesto()}</p>
                                                    </div>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </ul>
                            </div>
                            <div class="panel-footer">
                                <form id="sendMsgForm">
                                    <div class="input-group">
                                        <input class="form-control input-sm" name="msg" type="text" placeholder="Scrivi un messaggio..." required>
                                        <span class="input-group-btn">
                                            <button type="submit" class="btn btn-sm btn-send" id="btn-chat">Invia</button>
                                        </span>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- info sull'interlocutore -->
                <div class="col-xs-3 profile-box">
                    <div class="row">
                        <div class="col-xs-12 text-center">
                            <img class="profile-img" src="images/profile_img.png" alt="profile-img"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 text-center rating-star">
                            <!-- RATING STAR -->                            
                            <div class="container-fluid back-stars">
                                <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                            </div>

                            <div class="container-fluid front-stars">
                                <c:forEach var="i" begin="1" end="${interlocutore.getValutazioneComplessiva()}">
                                    <i id="star<c:out value="${i}"/>" class="fa fa-star fa-2x" aria-hidden="true"></i>
                                </c:forEach>
                                <c:forEach var="i" begin="${interlocutore.getValutazioneComplessiva() + 1}" end="5">
                                    <i id="star<c:out value="${i}"/>" class="fa fa-star-o fa-2x" aria-hidden="true"></i>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <div class="line"></div>

                    <c:set var="P" value="P"/>

                    <div class="row">
                        <div class="profile-info text-center">
                            <p><strong>${interlocutore.getNome()} ${interlocutore.getCognome()}</strong></p>
                            <c:if test="${chat.getProposta().isAccettato()}">
                                <p>interlocutore.getEmail()</p>
                            </c:if>
                            <c:forEach items="${interlocutore.getListaCompetenze()}" var="competenza">
                                <c:choose>
                                    <c:when test="${competenza.getTipo() eq P}">
                                        <p>${competenza.getCategoria()} professionista</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p>${competenza.getCategoria()} amatoriale</p>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </div>

                </div>
            </div>

            <!-- FOOTER -->
            <%@include file="/footer.txt"%>

        </div>

        <script type="text/javascript">

            //invia il messaggio al WS Spring
            function sendMsg(chat) {

                var settings = {
                    "async": true,
                    "crossDomain": true,
                    "url": "http://localhost:8080/sender/sendObjectMessage",
                    "method": "POST",
                    "headers": {
                        "Content-Type": "application/json",
                        "Cache-Control": "no-cache",
                        "Postman-Token": "a660e3be-c052-4a4b-8e59-53c5805208a5"
                    },
                    "processData": false,
                    "data": JSON.stringify(chat)
                };

                $.ajax(settings).done(function (response) {
                    console.log(response);
                });

            }

            //salva il messaggio sul DB
            function saveMsg(id, msg, mitt) {
                $.ajax({
                    type: 'POST',
                    url: "SalvaMessaggio",
                    data: {
                        propostaId: id,
                        testo: msg,
                        mittente: mitt
                    },
                    success: function (messaggio) {
                        console.log(messaggio);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log("some error");
                    }
                });
            }

            //scarica dalla coda i messaggi non letti per quella chat
            function checkMessage(propostaId, mittente, emailMittente) {
                
                var form = new FormData();
                form.append("idProposta", propostaId);

                var settings = {
                    "async": true,
                    "crossDomain": true,
                    "url": "http://localhost:8080/checkMessageById",
                    "method": "POST",
                    "headers": {
                        "Cache-Control": "no-cache"
                    },
                    "processData": false,
                    "contentType": false,
                    "mimeType": "multipart/form-data",
                    "data": form
                };

                $.ajax(settings).done(function (response) {
                    
                    //se ci sono messaggi non letti
                    if(response !== ""){
                        //lista di messaggi non letti
                        var messaggi = (JSON.parse(response)).listaMessaggi;
                        var m;
                        
                        for(m = 0; m < messaggi.length; m++){
                            var testo = messaggi[m].messaggio;
                            var timestamp = new Date(messaggi[m].timestamp);
                            
                            //stampo il messaggio a video
                            printMsg(mittente, testo, timestamp, "receive");
                        }
                    }
                });
            }

            //stampa a video il messaggio (type = "send" -> sto inviando, type = "receive" -> sto ricevendo)
            function printMsg(mittente, testo, timestamp, type) {

                var message;
                if (type === "send") {
                    message = '<li class="right clearfix">' +
                            '<span class="pull-right">' +
                            '<img class="chat-img img-circle" src="http://placehold.it/50/FA6F57/fff&text=ME" alt="profile-img"/>' +
                            '</span>' +
                            '<div class="chat-body clearfix">' +
                            '<div class="header">' +
                            '<small class="text-muted"><span class="glyphicon glyphicon-time"></span>' +
                            (timestamp.getHours() < 10 ? '0' : '') + timestamp.getHours() + ':' + (timestamp.getMinutes() < 10 ? '0' : '') + timestamp.getMinutes() + '</small>' +
                            '<strong class="pull-right">' + mittente + '</strong>' +
                            '</div>' +
                            '<p>' + testo + '</p>' +
                            '</div>' +
                            '</li>';
                } else {
                    message = '<li class="left clearfix">' +
                            '<span class="pull-left">' +
                            '<img class="chat-img img-circle" src="images/profile_img.png" alt="profile-img"/>' +
                            '</span>' +
                            '<div class="chat-body clearfix">' +
                            '<div class="header">' +
                            '<small class="text-muted"><span class="glyphicon glyphicon-time"></span>' +
                            (timestamp.getHours() < 10 ? '0' : '') + timestamp.getHours() + ':' + (timestamp.getMinutes() < 10 ? '0' : '') + timestamp.getMinutes() + '</small>' +
                            '<strong class="pull-right">' + mittente + '</strong>' +
                            '</div>' +
                            '<p>' + testo + '</p>' +
                            '</div>' +
                            '</li>';
                }

                //stampo il messaggio
                $('#chat-list').append(message).scrollTop($('#chat-list').prop('scrollHeight'));

            }

            $(document).ready(function () {
                
                //id della proposta
                var propostaId = <c:out value="${chat.getProposta().getId()}" />;
                //email del mittente del messaggio
                var emailMittente = "<c:out value="${utente.getEmail()}"/>";
                //nome e cognome del mittente del messaggio
                var mittente = "<c:out value="${utente.getNome()}"/> <c:out value="${utente.getCognome()}"/>";
                //email dell'utente
                var emailUtente = "<c:out value="${chat.getUtente().getEmail()}"/>";
                //email del manutente
                var emailManutente = "<c:out value="${chat.getManutente().getEmail()}"/>";
                
                checkMessage(String(propostaId), mittente, emailMittente);
                
                $('#sendMsgForm').on('submit', function (e) {
                    e.preventDefault();
                    //prendo il testo del messaggio
                    var msg = $("input[name='msg']").val();
                    //ora di invio del messaggio
                    var timestamp = new Date();
                    //oggetto chat da passare al WS Spring
                    var chat = {
                        "idProposta": propostaId,
                        "utenteEmail": emailUtente,
                        "manutenteEmail": emailManutente,
                        "listaMessaggi": [
                            {
                                "messaggio": msg,
                                "timestamp": timestamp.getTime()
                            }
                        ]
                    };

                    //invio del messaggio tramite WS Spring
                    sendMsg(chat);
                    //salvataggio del messaggio su DB tramite servlet
                    saveMsg(propostaId, msg, emailMittente);
                    //cancello l'avviso sulla non presenza di messaggi
                    $('#noMsg').remove();
                    //stampa a video del messaggio
                    printMsg(mittente, msg, timestamp, "send");
                    //cancello l'input
                    $("input[name='msg']").val("");
                });
                
            });
            

        </script>

    </body>

</html>
