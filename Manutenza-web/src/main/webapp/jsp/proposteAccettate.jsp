<%-- 
    Document   : proposteAccettate
    Created on : 23-giu-2018, 15.47.32
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

        <title>Proposte Accettate</title>

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
            <h2>Proposte accettate</h2>
            
            <c:forEach items="${proposteAccettate}" var="proposta">
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
                                    <label class="control-label col-md-3 col-xs-3" for="budget">Prezzo proposto: </label>
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

            <!-- FOOTER -->
            <%@include file="/footer.txt"%>

        </div>

    </body>
</html>

