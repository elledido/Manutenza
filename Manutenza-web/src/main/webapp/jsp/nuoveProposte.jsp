<%-- 
    Document   : nuoveProposte
    Created on : 14-mar-2018, 16.14.32
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

        <title>Nuove proposte</title>

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

            <h2>Nuove proposte</h2>

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
                            <!-- Utente -->
                            <div class="form-group">
                                <label class="control-label col-md-3 col-xs-3" for="utente">Utente: </label>
                                <div class="col-md-6 col-xs-8">
                                    <input class="form-control" id="utente" name="utente" type="text" readonly value="Utente">
                                </div>
                            </div>
                            <!-- Budget proposto -->
                            <div class="form-group">
                                <label class="control-label col-md-3 col-xs-3" for="budget">Budget: </label>
                                <div class="input-group budget col-md-2 col-xs-4">
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
                        <button type="button" class="btn btn-block btn-ok" data-toggle="modal" data-target="#mostraInteresse">
                            Mostra interesse
                        </button>
                    </div>
                </div>
            </div>

            <!-- Mosta interesse -->
            <div class="modal fade" id="mostraInteresse" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                        <form class="form-horizontal" action="/Manutenza-web/MainController" method="post">
                            <div class="modal-body row">
                                <div class="col-xs-12" align="center">
                                    <p class="titolo">Titolo richiesta 1</p>
                                    <!-- Budget proposto -->
                                    <div class="form-group row">
                                        <div class="col-xs-1"></div>
                                        <label class="control-label col-xs-1" for="budget">Budget: </label>
                                        <div class="input-group budget col-xs-6">
                                            <span class="input-group-addon">€</span>
                                            <input class="form-control currency" id="budget" name="budget" type="number">
                                        </div>
                                    </div> 
                                </div>
                            </div>
                            <div class="modal-footer">
                                <div class="col-xs-1"></div>
                                <div class="col-xs-5">
                                    <button type="button" class="btn btn-block btn-annul" data-dismiss="modal">Annula</button>
                                </div>
                                <div class="col-xs-5">
                                    <button type="submit" class="btn btn-block btn-ok" name="action" value="mostraInteresse">Conferma</button>
                                </div>
                                <div class="col-xs-1"></div>
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
        
    </body>
</html>
