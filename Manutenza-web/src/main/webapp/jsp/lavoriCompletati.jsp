<%-- 
    Document   : lavoriCompletati
    Created on : 23-giu-2018, 16.08.36
    Author     : irene
--%>

<%@page import="it.unito.taass.manutenza.entities.Foto"%>
<%@page import="it.unito.taass.manutenza.entities.Manutente"%>
<%@page import="it.unito.taass.manutenza.entities.Proposta"%>
<%@page import="it.unito.taass.manutenza.entities.Richiesta"%>
<%@page import="it.unito.taass.manutenza.entities.Utente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Lavori completati</title>

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
            <h2>Lavori completati</h2>

            <!-- Lavori che sono stati completati ma non ancora valutati dall'utente -->
            <c:forEach items="${lavoriCompletati}" var="proposta">
                <div class="form-box">
                    <div class="row">
                        <!-- Foto -->
                        <div class="img-box text-center col-xs-3">
                            <c:choose> 
                                <c:when test="${empty proposta.getRichiesta().getListaFoto()}">
                                    <img src="images/${proposta.getRichiesta().getCategoria()}.png" alt="${proposta.getRichiesta().getCategoria()}">
                                </c:when>
                                <c:otherwise>
                                    <img class="img-box" src="${proposta.getRichiesta().getListaFoto().get(0).getLink()}">
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <!-- Dati richiesta -->
                        <div class="col-xs-8">
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
                                <!-- Utente -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="utente">Utente: </label>
                                    <div class="col-md-6 col-xs-8">
                                        <input class="form-control" id="utente" name="utente" type="text" readonly 
                                               value="${proposta.getRichiesta().getUtente().getCognome()} ${proposta.getRichiesta().getUtente().getNome()}">
                                    </div>
                                </div>
                                <!-- Budget proposto -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="budget">Prezzo: </label>
                                    <div class="input-group budget col-md-2 col-xs-4">
                                        <span class="input-group-addon">€</span>
                                        <input class="form-control currency" id="budget" name="budget" type="number" readonly 
                                               value="${proposta.getPrezzo()}">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <!-- Variabile necessaria per mantenere la corrispondenza di indici tra proposte e feedback -->
            <c:set var="i" value="0"/>

            <!-- Lavori completati e valutati -->
            <c:forEach items="${lavoriValutati}" var="proposta">
                <div class="form-box">
                    <div class="row">
                        <!-- Foto -->
                        <div class="img-box text-center col-xs-3">
                            <c:choose> 
                                <c:when test="${empty proposta.getRichiesta().getListaFoto()}">
                                    <img src="images/${proposta.getRichiesta().getCategoria()}.png" alt="${proposta.getRichiesta().getCategoria()}">
                                </c:when>
                                <c:otherwise>
                                    <img class="img-box" src="${proposta.getRichiesta().getListaFoto().get(0).getLink()}">
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <!-- Dati richiesta -->
                        <div class="col-xs-8">
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
                                <!-- Utente -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="utente">Utente: </label>
                                    <div class="col-md-6 col-xs-8">
                                        <input class="form-control" id="utente" name="utente" type="text" readonly 
                                               value="${proposta.getRichiesta().getUtente().getCognome()} ${proposta.getRichiesta().getUtente().getNome()}">
                                    </div>
                                </div>
                                <!-- Budget proposto -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="budget">Prezzo: </label>
                                    <div class="input-group budget col-md-2 col-xs-4">
                                        <span class="input-group-addon">€</span>
                                        <input class="form-control currency" id="budget" name="budget" type="number" readonly 
                                               value="${proposta.getPrezzo()}">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-10 col-xs-8"></div>
                        <div class="col-md-2 col-xs-4">
                            <button type="button" class="btn btn-block btn-ok" data-toggle="modal" data-target="#mostraValutazione${richiesta.getId()}">
                                Mostra valutazione
                            </button>
                        </div>
                    </div>
                </div>

                <c:set var="feedback" value="${feedbackProposte.get(i)}" />
                <c:set var="i" value="${i+1}"/>

                <!-- Mostra valutazione -->
                <div class="modal fade" id="mostraValutazione${richiesta.getId()}" tabindex="-1" role="dialog" aria-hidden="true">
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
                                                <div class="col-xs-5 rating-star text-center">
                                                    <!-- RATING STAR -->                            
                                                    <div class="container-fluid back-stars">
                                                        <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                                        <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                                        <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                                        <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                                        <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                                    </div>

                                                    <div class="container-fluid front-stars">
                                                        <c:forEach var="i" begin="1" end="${feedback.getValutazioneComplessiva()}">
                                                            <i id="star<c:out value="${i}"/>" class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                        </c:forEach>
                                                        <c:forEach var="i" begin="${feedback.getValutazioneComplessiva() + 1}" end="5">
                                                            <i id="star<c:out value="${i}"/>" class="fa fa-star-o fa-2x" aria-hidden="true"></i>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Professionalità -->
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-xs-1"></div>
                                                <label class="control-label col-xs-5" for="professionalita">Professionalit&agrave;: </label>
                                                <div class="col-xs-5 rating-star text-center">
                                                    <!-- RATING STAR -->                            
                                                    <div class="container-fluid back-stars">
                                                        <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                                        <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                                        <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                                        <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                                        <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                                    </div>

                                                    <div class="container-fluid front-stars">
                                                        <c:forEach var="i" begin="1" end="${feedback.getProfessionalita()}">
                                                            <i id="star<c:out value="${i}"/>" class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                        </c:forEach>
                                                        <c:forEach var="i" begin="${feedback.getProfessionalita() + 1}" end="5">
                                                            <i id="star<c:out value="${i}"/>" class="fa fa-star-o fa-2x" aria-hidden="true"></i>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Comunicazione --> 
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-xs-1"></div>
                                                <label class="control-label col-xs-5" for="comunicazione">Comunicazione: </label>
                                                <div class="col-xs-5 rating-star text-center">
                                                    <!-- RATING STAR -->                            
                                                    <div class="container-fluid back-stars">
                                                        <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                                        <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                                        <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                                        <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                                        <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                                    </div>

                                                    <div class="container-fluid front-stars">
                                                        <c:forEach var="i" begin="1" end="${feedback.getComunicazione()}">
                                                            <i id="star<c:out value="${i}"/>" class="fa fa-star fa-2x" aria-hidden="true"></i>
                                                        </c:forEach>
                                                        <c:forEach var="i" begin="${feedback.getComunicazione() + 1}" end="5">
                                                            <i id="star<c:out value="${i}"/>" class="fa fa-star-o fa-2x" aria-hidden="true"></i>
                                                        </c:forEach>
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
                                                    <textarea class="form-control noresize" id="commento" name="commento" rows="5" readonly>${feedback.getCommento()}</textarea>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer text-center">
                                    <div class="col-xs-4"></div>
                                    <div class="col-xs-4">
                                        <button type="button" class="btn btn-block btn-annul" data-dismiss="modal">Chiudi</button>
                                    </div>
                                    <div class="col-xs-4"></div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </c:forEach>

            <!-- FOOTER -->
            <%@include file="/footer.txt"%>

        </div>

    </body>
</html>
