<%-- 
    Document   : proposte
    Created on : 14-mar-2018, 15.23.05
    Author     : irene
--%>

<%@page import="it.unito.taass.manutenza.entities.Manutente"%>
<%@page import="it.unito.taass.manutenza.entities.Proposta"%>
<%@page import="it.unito.taass.manutenza.entities.Utente"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title><% out.print(request.getAttribute("titolo")); %></title>

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

            <h2><% out.print(request.getAttribute("titolo"));%></h2>

            <!-- Se non ci sono proposte -->
            <c:if test="${empty proposte}">
                <div class="form-box">
                    <p class="titolo">Spiacenti, non ci sono ancora proposte per la tua richiesta.</p>
                </div>
            </c:if>
            
            <!-- Elenco delle proposte associate alla richiesta scelta (INDICARE SE IL MANUTENTE SIA P O A)-->
            <c:forEach items="${proposte}" var="proposta">
                <div class="form-box">
                    <div class="row">
                        <!-- Foto -->
                        <div class="img-box col-md-2 col-xs-2">
                            <img class="profile-img" src="images/profile_img.png" alt="profile-img"/>
                        </div>
                        <!-- Dati richiesta -->
                        <div class="col-md-9 col-xs-8">
                            <p class="titolo">${proposta.getManutente().getCognome()} ${proposta.getManutente().getNome()}</p>
                            <form class="form-horizontal">
                                <!-- Categoria -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="categoria">Categoria: </label>
                                    <div class="col-md-6 col-xs-8">
                                        <input class="form-control" id="categoria" name="categoria" type="text" readonly 
                                               value="${proposta.getRichiesta().getCategoria()}">
                                    </div>
                                </div>
                                <!-- Valutazione -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="valutazione">Valutazione: </label>
                                    <div class="col-md-6 col-xs-8 rating-star">
                                        <!-- RATING STAR -->                            
                                        <div class="container-fluid back-stars">
                                            <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                            <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                            <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                            <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                            <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                        </div>

                                        <div class="container-fluid front-stars">
                                            <c:forEach var="i" begin="1" end="${proposta.getManutente().getValutazioneComplessiva()}">
                                                <i id="star<c:out value="${i}"/>" class="fa fa-star fa-2x" aria-hidden="true"></i>
                                            </c:forEach>
                                            <c:forEach var="i" begin="${proposta.getManutente().getValutazioneComplessiva() + 1}" end="5">
                                                <i id="star<c:out value="${i}"/>" class="fa fa-star-o fa-2x" aria-hidden="true"></i>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                                <!-- Budget proposto -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="budget">Budget: </label>
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
                            <button type="button" class="btn btn-block btn-ok"><a href=#">Accetta proposta</a></button>
                            <!-- apre la chat -->
                        </div>
                    </div>
                </div>
            </c:forEach>

            <!-- FOOTER -->
            <%@include file="/footer.txt"%>

        </div>

        <!-- CHAT -->
        <%@include file="/chat.txt"%>

    </body>
</html>
