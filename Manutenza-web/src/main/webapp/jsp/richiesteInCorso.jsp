<%-- 
    Document   : richiesteInCorso
    Created on : 8-feb-2018, 10.21.52
    Author     : irene
--%>

<%@page import="it.unito.taass.manutenza.entities.Utente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Richieste in corso</title>

        <!-- Bootstrap CSS CDN -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="manutenzastyle.css" rel="stylesheet" type="text/css"/>
        <!-- Scrollbar Custom CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
        <!-- Font Awesome -->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

        <!-- jQuery CDN -->
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <!-- Bootstrap Js CDN -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Custom Scroll Js CDN -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>
        <!-- Custom JS -->
        <script src="manutenza.js" type="text/javascript"></script>
    </head>

    <body>
        <!-- MAIN CONTAINER -->
        <div class="container">

            <!-- NAVBAR E SIDEBAR -->
            <%@include file="/navbar.txt"%>

            <!-- MAIN CONTENT -->
            <div id="content">
                <ul class="breadcrumb">
                    <li></li>
                    <li>Le mie richieste</li>
                    <li>In corso</li>
                </ul>

                <!-- richiesta per cui non è ancora stata accetta alcuna proposta -->
                <div class="form-box">
                    <div class="row">
                        <!-- Foto -->
                        <div class="img-box col-md-2 col-xs-2">
                        </div>
                        <!-- Dati richiesta -->
                        <div class="col-md-9 col-xs-8">
                            <p class="titolo">Titolo richiesta 1</p>
                            <form class="form-horizontal">
                                <!-- Categoria -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="categoria">Categoria: </label>
                                    <div class="col-md-6 col-xs-8">
                                        <input class="form-control" id="categoria" name="categoria" type="text" readonly value="Categoria">
                                    </div>
                                </div>
                                <!-- Budget proposto -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="budget">Budget: </label>
                                    <div class="input-group col-md-2 col-xs-4">
                                        <span class="input-group-addon">€</span>
                                        <input class="form-control currency" id="budget" name="budget" type="number" readonly value="50">
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-10 col-xs-8"></div>
                        <div class="col-md-2 col-xs-4">
                            <!--<button type="button" class="btn btn-block btn-ok btn-badge" data-badge="10">
                                <a href="/Manutenza-web/MainController?action=visualizzaProposte">Visualizza proposte</a>
                            </button> -->
                            <a href="/Manutenza-web/MainController?action=visualizzaProposte" class="btn btn-block btn-ok btn-badge" data-badge="10" role="button">Visualizza proposte</a>
                        </div>
                    </div>
                </div>

                <!-- richiesta accettata ma lavoro non ancora completo -->
                <div class="form-box">
                    <div class="row">
                        <!-- Foto -->
                        <div class="img-box col-md-2 col-xs-2">
                        </div>
                        <!-- Dati richiesta -->
                        <div class="col-md-9 col-xs-8">
                            <p class="titolo">Titolo richiesta 2</p>
                            <form class="form-horizontal">
                                <!-- Categoria -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="categoria">Categoria: </label>
                                    <div class="col-md-6 col-xs-8">
                                        <input class="form-control" id="categoria" name="categoria" type="text" readonly value="Categoria">
                                    </div>
                                </div>
                                <!-- Manutente a cui è stato affidato il lavoro -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="manutente">Manutente: </label>
                                    <div class="col-md-6 col-xs-8">
                                        <input class="form-control" id="manutente" name="manutente" type="text" readonly value="Manutente">
                                    </div>
                                </div>
                                <!-- Costo pattuito -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="costo">Costo: </label>
                                    <div class="input-group col-md-2 col-xs-4">
                                        <span class="input-group-addon">€</span>
                                        <input class="form-control currency" id="costo" name="costo" type="number" readonly value="50">
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-10 col-xs-8"></div>
                        <div class="col-md-2 col-xs-4">
                            <button type="button" class="btn btn-block btn-ok" data-toggle="modal" data-target="#mostraQRcode">
                                Mostra QR code
                            </button>
                        </div>
                    </div>
                </div>

                <!-- Mosta QR code -->
                <div class="modal fade" id="mostraQRcode" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-body row">
                                <div class="col-xs-12" align="center">
                                    <img src="https://zxing.org/w/chart?cht=qr&chs=280x280&chld=L&choe=UTF-8&chl=Mario+Rossi+Luigi+Verdi+350+12">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <div class="col-xs-1"></div>
                                <div class="col-xs-10">
                                    <button type="button" class="btn btn-block btn-ok" data-dismiss="modal">Indietro</button>
                                </div>
                                <div class="col-xs-1"></div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </body>

</html>
