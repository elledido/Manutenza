<%-- 
    Document   : proposte
    Created on : 14-mar-2018, 15.23.05
    Author     : irene
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Titolo richiesta</title>

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
                    <li>Titolo richiesta</li>
                </ul>

                <div class="form-box">
                    <div class="row">
                        <!-- Foto -->
                        <div class="img-box col-md-2 col-xs-2">
                            <img class="profile-img" src="images/profile_img.png" alt="profile-img"/>
                        </div>
                        <!-- Dati richiesta -->
                        <div class="col-md-9 col-xs-8">
                            <p class="titolo">Nome Manutente 1</p>
                            <form class="form-horizontal">
                                <!-- Categoria -->
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-xs-3" for="categoria">Categoria: </label>
                                    <div class="col-md-6 col-xs-8">
                                        <input class="form-control" id="categoria" name="categoria" type="text" readonly value="Categoria">
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
                                            <c:forEach var="i" begin="1" end="4">
                                                <i id="star<c:out value="${i}"/>" class="fa fa-star fa-2x" aria-hidden="true"></i>
                                            </c:forEach>
                                            <c:forEach var="i" begin="5" end="5">
                                                <i id="star<c:out value="${i}"/>" class="fa fa-star-o fa-2x" aria-hidden="true"></i>
                                            </c:forEach>
                                        </div>
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
                            <button type="button" class="btn btn-block btn-ok"><a href=#">Accetta proposta</a></button>
                            <!-- apre la chat -->
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>