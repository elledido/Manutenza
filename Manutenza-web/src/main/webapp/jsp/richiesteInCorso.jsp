<%-- 
    Document   : richiesteInCorso
    Created on : 8-feb-2018, 10.21.52
    Author     : irene
--%>

<%@page import="it.unito.taass.manutenza.entities.Foto"%>
<%@page import="it.unito.taass.manutenza.entities.Manutente"%>
<%@page import="it.unito.taass.manutenza.entities.Proposta"%>
<%@page import="it.unito.taass.manutenza.entities.Richiesta"%>
<%@page import="it.unito.taass.manutenza.entities.Utente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>

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
    </head>

    <body>
        <!-- NAVBAR -->
        <%@include file="/navbar.txt"%>
        <div class="jumbotron"></div>

        <!-- MAIN CONTAINER -->
        <div class="container">
            <h2>In corso</h2>

            <!-- RICHIESTE IN ATTESA: richieste per cui non è ancora stata accetta alcuna proposta -->
            <c:forEach items="${richiesteInAttesa}" var="richiesta">
                <div class="form-box">
                    <div class="row">
                        <!-- Foto -->
                        <div class="img-box text-center col-xs-3">
                            <c:choose> 
                                <c:when test="${empty richiesta.getListaFoto()}">
                                    <img src="images/${richiesta.getCategoria()}.png" alt="${richiesta.getCategoria()}">
                                </c:when>
                                <c:otherwise>
                                    <img class="img-box" src="${richiesta.getListaFoto().get(0).getLink()}">
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <!-- Dati richiesta -->
                        <div class="col-xs-8">
                            <p class="titolo">${richiesta.getTitolo()}</p>
                            <form class="form-horizontal">
                                <!-- Categoria -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="categoria">Categoria: </label>
                                    <div class="col-md-6 col-xs-8">
                                        <input class="form-control" id="categoria" name="categoria" type="text" readonly 
                                               value="${richiesta.getCategoria()}">
                                    </div>
                                </div>
                                <!-- Budget proposto -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="budget">Budget: </label>
                                    <div class="input-group budget col-md-2 col-xs-4">
                                        <span class="input-group-addon">€</span>
                                        <input class="form-control currency" id="budget" name="budget" type="number" readonly 
                                               value="${richiesta.getBudget()}">
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-10 col-xs-8"></div>
                        <div class="col-md-2 col-xs-4">
                            <!--<a href="/Manutenza-web/MainController?action=visualizzaProposte" class="btn btn-block btn-ok btn-badge" data-badge="10" role="button">Visualizza proposte</a>-->
                            <a href="/Manutenza-web/MainController?action=visualizzaProposte&richiesta=${richiesta.getId()}" class="btn btn-block btn-ok" role="button">Visualizza proposte</a>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <!-- RICHIESTE ACCETTATE: richieste accettate ma lavoro non ancora completo -->
            <c:forEach items="${proposteAccettate}" var="proposta">
                <div class="form-box">
                    <form class="form-horizontal" action="/Manutenza-web/MainController?action=apriChat&propostaId=${proposta.getId()}" method="post">
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
                            </div>
                        </div>

                        <input type="hidden" name="emailUtente" value="<%out.print(((Utente) session.getAttribute("utente")).getEmail());%>">
                        <input type="hidden" name="emailManutente" value="${proposta.getManutente().getEmail()}">

                        <div class="row">
                            <div class="col-md-8 col-xs-4"></div>
                            <div class="col-md-2 col-xs-4">
                                <button type="submit" class="btn btn-block btn-primary btn-open">Apri chat</button>
                            </div>
                            <div class="col-md-2 col-xs-4">
                                <button type="button" class="btn btn-block btn-ok" data-toggle="modal" data-target="#mostraQRcode${proposta.getId()}">
                                    Mostra QR code
                                </button>
                            </div>
                        </div>
                    </form>
                </div>

                <!-- Mosta QR code (solo id della proposta) -->
                <div class="modal fade" id="mostraQRcode${proposta.getId()}" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-sm" role="document">
                        <div class="modal-content">
                            <div class="modal-body row modal-qrcode">
                                <div class="col-xs-12 text-center">
                                    <img src="https://zxing.org/w/chart?cht=qr&chs=280x280&chld=L&choe=UTF-8&chl=${proposta.getId()}">
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
            </c:forEach>

            <!-- FOOTER -->
            <%@include file="/footer.txt"%>

        </div>

    </body>

</html>
