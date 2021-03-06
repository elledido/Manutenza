<%-- 
    Document   : leMieCompetenze
    Created on : 15-apr-2018, 15.52.19
    Author     : irene
--%>

<%@page import="it.unito.taass.manutenza.entities.Indirizzo"%>
<%@page import="it.unito.taass.manutenza.entities.Manutente"%>
<%@page import="it.unito.taass.manutenza.entities.Utente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>

<%
    //dati dell'utente in sessione
    Manutente utente = (Manutente) session.getAttribute("utente");
%>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Le mie competenze</title>

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
            <h2>Le mie competenze</h2>

            <div id="skillList" class="row">

                <c:forEach items="${utente.getListaCompetenze()}" var="competenza">
                    <div class="col-xs-6 col-md-3">
                        <div class="thumbnail">
                            <div class="text-center" style="background-color: #fffffa; height: 180px;">
                                <img src="images/${competenza.getCategoria()}.png" alt="${competenza.getCategoria()}">
                            </div>
                            <div class="caption">
                                <h3>${competenza.getCategoria()}</h3>
                                <p>${competenza.getZonaDiCompetenza()}</p>
                                <c:choose>
                                    <c:when test="${competenza.getTipo() == 'P'}">
                                        <p>Professionista</p>
                                        <p>${competenza.getPartitaIva()}</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p>Amatoriale</p>
                                        <p>...</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <!-- Aggiungi competenza -->
                <div id="addSkill" class="col-md-3 col-xs-6 text-center">
                    <div class="thumbnail">
                        <button type="button" class="btn btn-block btn-img" data-toggle="modal" data-target="#nuovaCompetenza">
                            <img class="img-responsive img-rounded" src="images/add_img.jpg">
                        </button>
                    </div>
                </div>

            </div>

            <!-- Form Aggiungi competenza -->
            <div class="modal fade" id="nuovaCompetenza" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <form class="form-horizontal" action="/Manutenza-web/MainController" method="post">
                            <div class="modal-header">
                                <h4 class="modal-title text-center">Aggiungi competenza</h4>
                            </div>
                            <div class="modal-body row">
                                <div class="col-xs-12" align="center">
                                    <!-- Competenza -->
                                    <div class="form-group">
                                        <label class="control-label col-xs-3" for="categoria">Competenza: </label>
                                        <div class="col-xs-8">
                                            <select class="form-control" id="categoria" name="categoria" required>
                                                <c:forEach items="${applicationScope.categorie}" var="categoria">
                                                    <option value="${categoria.getNome()}">${categoria.getNome()}</option>
                                                </c:forEach> 
                                            </select>
                                        </div>
                                    </div>
                                    <!-- Zona (momentaneamente solo le città in cui ha domicilio il manutente) -->
                                    <div class="form-group">
                                        <label class="control-label col-xs-3" for="zona">Zona di competenza: </label>
                                        <div class="col-xs-8">
                                            <select class="form-control" id="zona" name="zona" required>
                                                <c:forEach items="${utente.getListaIndirizzi()}" var="domicilio">
                                                    <option value="${domicilio.getCitta()}">${domicilio.getCitta()}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <!-- Tipo (amatoriale o professionista) -->
                                    <div class="form-group">
                                        <label class="control-label col-xs-2">Tipo: </label>
                                        <div class="col-xs-8">
                                            <label class="radio-inline">
                                                <input type="radio" name="tipo" value="A" required>Amatoriale
                                            </label>
                                            <label class="radio-inline">
                                                <input type="radio" name="tipo" value="P">Professionista
                                            </label>
                                        </div>
                                    </div>
                                    <!-- Partita IVA (solo professionista) -->
                                    <div class="form-group">
                                        <label class="control-label col-xs-3" for="partitaIVA">Partita IVA: </label>
                                        <div class="col-xs-8">
                                            <input class="form-control" id="partitaIVA" name="partitaIVA" type="text" disabled>
                                        </div>
                                    </div>
                                    <!-- Messaggio di errore per la partita IVA -->
                                    <div class="form-group">
                                        <label class="control-label col-xs-3"> </label>
                                        <div class="col-xs-8">
                                            <p class="errorMsg"> </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <div class="col-xs-3"></div>
                                <div class="col-xs-3">
                                    <button type="button" class="btn btn-block btn-annul" data-dismiss="modal">Annulla</button>
                                </div>
                                <div class="col-xs-3">
                                    <button type="submit" class="btn btn-block btn-ok" name="action" value="aggiungiCompetenza">Aggiungi</button>
                                </div>
                                <div class="col-xs-3"></div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>


            <!-- FOOTER -->
            <%@include file="/footer.txt"%>

        </div>

        <script type="text/javascript">

            $(document).ready(function () {

                //se professionista, abilita partita IVA
                $('input[value=P]').on('click', function () {
                    $('#partitaIVA').prop('disabled', false);
                    $('#partitaIVA').prop('required', true);
                });

                //se amatoriale, disabilita partita IVA
                $('input[value=A]').on('click', function () {
                    $('#partitaIVA').prop('disabled', true);
                    $('#partitaIVA').prop('required', false);
                });

                $('form').on('submit', function () {

                    //se è un professionista faccio il check della partita IVA
                    if ($('#partitaIVA').prop('disabled') === false) {

                        var partitaIVA = $('#partitaIVA').val(); //partita IVA inserita dall'utente

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

                        var valido;

                        //check della partita IVA
                        $.ajax(settings).done(function (response) {
                            if (response === 'false') {
                                valido = 'false';
                                $('p.errorMsg').append(document.createTextNode("Partita IVA non valida"));
                            }
                        });

                        if (valido === 'false') {
                            return false;
                        }
                    }

                });

            });

        </script>

    </body>

</html>
