<%-- 
    Document   : richiestePassate
    Created on : 9-feb-2018, 19.14.53
    Author     : irene
--%>

<%@page import="it.unito.taass.manutenza.entities.Foto"%>
<%@page import="it.unito.taass.manutenza.entities.Manutente"%>
<%@page import="it.unito.taass.manutenza.entities.Proposta"%>
<%@page import="it.unito.taass.manutenza.entities.Richiesta"%>
<%@page import="it.unito.taass.manutenza.entities.Utente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>

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
        <link href='https://fonts.googleapis.com/css?family=Andika' rel='stylesheet'>
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
        <!-- NAVBAR -->
        <%@include file="/navbar.txt"%>
        <div class="jumbotron"></div>

        <!-- MAIN CONTAINER -->
        <div class="container">
            <h2>Storico richieste</h2>

            <!-- RICHIESTE COMPLETATE: richieste il cui lavoro è stato completato e che devono essere ancora valutate -->
            <c:forEach items="${lavoriCompletati}" var="proposta">
                <div class="form-box">
                    <div class="row">
                        <!-- Foto -->
                        <div class="img-box col-md-2 col-xs-2">
                            <c:choose> 
                                <c:when test="${empty richiesta.getListaFoto()}">
                                    <img class="img-box">
                                </c:when>
                                <c:otherwise>
                                    <img class="img-box" src="${richiesta.getListaFoto().get(0).getLink()}">
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <!-- Dati richiesta -->
                        <div class="col-md-9 col-xs-8">
                            <p class="titolo">${proposta.getRichiesta().getTitolo()}</p>
                            <form class="form-horizontal">
                                <!-- Categoria -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="categoria">Categoria: </label>
                                    <div class="col-md-6 col-xs-8">
                                        <input class="form-control" id="categoria" name="categoria" type="text" readonly 
                                               value="${proposta.getRichiesta().getCategoria()}">
                                    </div>
                                </div>
                                <!-- Manutente a cui è stato affidato il lavoro -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="manutente">Manutente: </label>
                                    <div class="col-md-6 col-xs-8">
                                        <input class="form-control" id="manutente" name="manutente" type="text" readonly 
                                               value="${proposta.getManutente().getCognome()} ${proposta.getManutente().getNome()}">
                                    </div>
                                </div>
                                <!-- Costo pattuito -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="costo">Costo: </label>
                                    <div class="input-group budget col-md-2 col-xs-4">
                                        <span class="input-group-addon">€</span>
                                        <input class="form-control currency" id="costo" name="costo" type="number" readonly value="${proposta.getPrezzo()}">
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-10 col-xs-8"></div>
                        <div class="col-md-2 col-xs-4">
                            <button type="button" class="btn btn-block btn-ok" data-toggle="modal" data-target="#feedback${proposta.getId()}">
                                Valuta
                            </button>
                        </div>
                    </div>
                </div>

                <!-- Valuta Manutente -->
                <div class="modal fade" id="feedback${proposta.getId()}" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <form class="form-horizontal" action="/Manutenza-web/MainController" method="post">
                                <div class="modal-body row">
                                    <div class="col-xs-12" align="center">

                                        <input type="hidden" name="richiestaId" value="${proposta.getId()}">
                                        <input type="hidden" name="manutenteEmail" value="${proposta.getManutente().getEmail()}"

                                        <!-- Valutazione complessiva --> 
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-xs-1"></div>
                                                <label class="control-label col-xs-5" for="val-complessiva">Valutazione complessiva: </label>
                                                <div class="col-xs-5">
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
                                                <label class="control-label col-xs-5" for="professionalita">Professionalità: </label>
                                                <div class="col-xs-5">
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
                                                <label class="control-label col-xs-5" for="comunicazione">Comunicazione: </label>
                                                <div class="col-xs-5">
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
                                        <button type="button" class="btn btn-block btn-annul" data-dismiss="modal">Annulla</button>
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
            </c:forEach>

            <!-- RICHIESTE VALUTATE: richieste il cui lavoro è stato completato e valutato (elenco di proposte a cui è associato un feedback in base alla posizione)-->
            <div class="form-box">
                <div class="row">
                    <!-- Foto -->
                    <div class="img-box col-md-2 col-xs-2">
                    </div>
                    <!-- Dati richiesta -->
                    <div class="col-md-9 col-xs-8">
                        <p class="titolo">Titolo 1</p>
                        <form class="form-horizontal">
                            <!-- Categoria -->
                            <div class="form-group">
                                <label class="control-label col-md-3 col-xs-3" for="categoria">Categoria: </label>
                                <div class="col-md-6 col-xs-8">
                                    <input class="form-control" id="categoria" name="categoria" type="text" readonly 
                                           value="Categoria">
                                </div>
                            </div>
                            <!-- Manutente a cui è stato affidato il lavoro -->
                            <div class="form-group">
                                <label class="control-label col-md-3 col-xs-3" for="manutente">Manutente: </label>
                                <div class="col-md-6 col-xs-8">
                                    <input class="form-control" id="manutente" name="manutente" type="text" readonly 
                                           value="Mario Rossi">
                                </div>
                            </div>
                            <!-- Costo pattuito -->
                            <div class="form-group">
                                <label class="control-label col-md-3 col-xs-3" for="costo">Costo: </label>
                                <div class="input-group budget col-md-2 col-xs-4">
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
                            Mostra Valutazione
                        </button>
                    </div>
                </div>
            </div>

            <!-- Mostra valutazione -->
            <div class="modal fade" id="feedback" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <form class="form-horizontal">
                            <div class="modal-body row">
                                <div class="col-xs-12" align="center">

                                    <!-- Valutazione complessiva --> 
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-xs-1"></div>
                                            <label class="control-label col-xs-5" for="val-complessiva">Valutazione complessiva: </label>
                                            <div class="col-xs-5">
                                                <div class="rating-star text-center">
                                                    <ul>
                                                        <li class="star selected">
                                                            <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                        </li>
                                                        <li class="star selected">
                                                            <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                        </li>
                                                        <li class="star selected">
                                                            <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                        </li>
                                                        <li class="star selected">
                                                            <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                        </li>
                                                        <li class="star">
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
                                            <label class="control-label col-xs-5" for="professionalita">Professionalità: </label>
                                            <div class="col-xs-5">
                                                <div class="rating-star text-center">
                                                    <ul>
                                                        <li class="star">
                                                            <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                        </li>
                                                        <li class="star">
                                                            <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                        </li>
                                                        <li class="star">
                                                            <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                        </li>
                                                        <li class="star">
                                                            <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                        </li>
                                                        <li class="star">
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
                                            <label class="control-label col-xs-5" for="comunicazione">Comunicazione: </label>
                                            <div class="col-xs-5">
                                                <div class="rating-star text-center">
                                                    <ul>
                                                        <li class="star">
                                                            <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                        </li>
                                                        <li class="star">
                                                            <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                        </li>
                                                        <li class="star">
                                                            <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                        </li>
                                                        <li class="star">
                                                            <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                        </li>
                                                        <li class="star">
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
                                                <textarea class="form-control noresize" id="commento" name="commento" rows="5" readonly>
                                                    Questo è un commento
                                                </textarea>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer text-center">
                                <div class="col-xs-4"></div>
                                <div class="col-xs-4">
                                    <button type="button" class="btn btn-block btn-annul" data-dismiss="modal">Annulla</button>
                                </div>
                                <div class="col-xs-4"></div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- FOOTER -->
            <%@include file="/footer.txt"%>

        </div>

        <!-- CHAT -->
        <%@include file="/chat.txt"%>

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

