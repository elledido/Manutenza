<%-- 
    Document   : richiestePassate
    Created on : 9-feb-2018, 19.14.53
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

        <title>Richieste passate</title>

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
                    <li>Passate</li>
                </ul>

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
                            <button type="button" class="btn btn-block btn-ok" data-toggle="modal" data-target="#feedback">
                                Valuta
                            </button>
                        </div>
                    </div>
                </div>

                <!-- Valuta Manutente -->
                <div class="modal fade" id="feedback" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <form class="form-horizontal" action="/Manutenza-web/MainController" method="post">
                                <div class="modal-body row">
                                    <div class="col-xs-12" align="center">
                                        
                                        <!-- Valutazione complessiva --> 
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-xs-1"></div>
                                                <label class="control-label col-xs-6" for="val-complessiva">Valutazione complessiva: </label>
                                                <div class="col-xs-4">
                                                    <input class="form-control" id="val-complessiva" name="val-complessiva" type="hidden" value="0">
                                                    <div class="rating-star text-center">
                                                        <ul id="stars">
                                                            <li class="star" data-value="1">
                                                                <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                            </li>
                                                            <li class="star" data-value="2">
                                                                <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                            </li>
                                                            <li class="star" data-value="3">
                                                                <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                            </li>
                                                            <li class="star" data-value="4">
                                                                <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                            </li>
                                                            <li class="star" data-value="5">
                                                                <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <!-- Professionalità --> 
                                        <div class="form-group"> 
                                            <div class="row">
                                                <div class="col-xs-1"></div>
                                                <label class="control-label col-xs-6" for="professionalita">Professionalità: </label>
                                                <div class="col-xs-4">
                                                    <input class="form-control" id="professionalita" name="professionalita" type="hidden" value="0">
                                                    <div class="rating-star text-center">
                                                        <ul id="stars">
                                                            <li class="star" data-value="1">
                                                                <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                            </li>
                                                            <li class="star" data-value="2">
                                                                <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                            </li>
                                                            <li class="star" data-value="3">
                                                                <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                            </li>
                                                            <li class="star" data-value="4">
                                                                <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                            </li>
                                                            <li class="star" data-value="5">
                                                                <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Comunicazione --> 
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-xs-1"></div>
                                                <label class="control-label col-xs-6" for="comunicazione">Comunicazione: </label>
                                                <div class="col-xs-4">
                                                    <input class="form-control" id="comunicazione" name="comunicazione" type="hidden" value="0">
                                                    <div class="rating-star text-center">
                                                        <ul id="stars">
                                                            <li class="star" data-value="1">
                                                                <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                            </li>
                                                            <li class="star" data-value="2">
                                                                <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                            </li>
                                                            <li class="star" data-value="3">
                                                                <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                            </li>
                                                            <li class="star" data-value="4">
                                                                <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                            </li>
                                                            <li class="star" data-value="5">
                                                                <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Commento -->
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-xs-1"></div>
                                                <label class="control-label col-xs-3" for="commento">Commento: </label>
                                            </div>
                                            <div class="row">
                                                <div class="col-xs-1"></div>
                                                <div class="col-xs-10">
                                                    <textarea class="form-control noresize" id="commento" name="commento" rows="5" required></textarea>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <div class="col-xs-3"></div>
                                    <div class="col-xs-3">
                                        <button type="button" class="btn btn-block btn-annul" data-dismiss="modal">Annula</button>
                                    </div>
                                    <div class="col-xs-3">
                                        <button type="submit" class="btn btn-block btn-ok" name="action" value="valutaManutente">Conferma</button>
                                    </div>
                                    <div class="col-xs-3"></div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <script type="text/javascript">
            $(document).ready(function () {

                $('#stars li').on('mouseover', function () {
                    var onStar = parseInt($(this).data('value'), 10); // The star currently mouse on

                    $(this).parent().children('li.star').each(function (e) {
                        if (e < onStar) {
                            $(this).addClass('hover');
                        } else {
                            $(this).removeClass('hover');
                        }
                    });
                }).on('mouseout', function () {
                    $(this).parent().children('li.star').each(function (e) {
                        $(this).removeClass('hover');
                    });
                });

                $('#stars li').on('click', function () {
                    var onStar = parseInt($(this).data('value'), 10); // The star currently selected
                    var stars = $(this).parent().children('li.star');

                    for (i = 0; i < stars.length; i++) {
                        $(stars[i]).removeClass('selected');
                    }

                    for (i = 0; i < onStar; i++) {
                        $(stars[i]).addClass('selected');
                    }

                    console.log($(this).parent().parent().prev());
                    $(this).parent().parent().prev().attr('value', onStar);

                });

            });

        </script>

    </body>

</html>

