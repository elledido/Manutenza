<%-- 
    Document   : nuovaRichiesta
    Created on : 3-feb-2018, 19.37.11
    Author     : irene
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Nuova Richiesta</title>

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

            <!-- NAVBAR -->
            <%@include file="/navbar.txt"%>

            <!-- MAIN CONTENT -->
            <div id="content">
                <ul class="breadcrumb">
                    <li></li>
                    <li>Le mie richieste</li>
                    <li>Nuova richiesta</li>
                </ul>

                <div class="form-box">
                    <form class="form-horizontal" action="/Manutenza-web/MainController" method="post">

                        <!-- TITOLO -->
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3" for="titolo">Titolo: </label>
                            <div class="col-md-8 col-xs-8">
                                <input class="form-control" id="titolo" name="titolo" type="text" required>
                            </div>
                        </div>

                        <!-- CATEGORIA (l'elenco delle categorie dovrebbe essere fornito dalla servlet come array di stringhe) -->
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3" for="categoria">Categoria: </label>
                            <div class="col-md-8 col-xs-8">
                                <select class="form-control" id="categoria" name="categoria" required>
                                    <option value="cat1">Cat 1</option>
                                    <option value="cat2">Cat 2</option>
                                    <option value="cat3">Cat 3</option>
                                    <option>Cat 4</option>
                                    <option>Cat 5</option>
                                </select>
                            </div>
                        </div>

                        <!-- DESCRIZIONE -->
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3" for="descrizione">Descrizione: </label>
                            <div class="col-md-8 col-xs-8">
                                <textarea class="form-control noresize" id="descrizione" name="descrizione" rows="5" required></textarea>
                            </div>
                        </div>

                        <!-- INDIRIZZO (l'elenco degli indirizzi dovrebbe essere fornito dalla servlet come array di stringhe) -->
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3" for="indirizzo">Indirizzo: </label>
                            <div class="col-md-8 col-xs-8">
                                <select class="form-control" id="indirizzo" name="indirizzo" required>
                                    <option value="id1">Via Indirizzo, 1, Città1 (C1)</option>
                                    <option value="id2">Via Indirizzo, 2, Città2 (C2)</option>
                                </select>
                            </div>
                        </div>

                        <!-- BUDGET MASSIMO -->
                        <div class="form-group">
                            <label class="control-label col-md-3 col-xs-3" for="budget">Budget massimo: </label>
                            <div class="input-group col-md-2 col-xs-3">
                                <span class="input-group-addon">€</span>
                                <input class="form-control currency" id="budget" name="budget" type="number" placeholder="0" min="0" required>
                            </div>
                        </div>

                        <!-- FOTO (da vedere come gestirle) -->

                        <!-- SUBMIT -->
                        <div class="row">
                            <div class="col-md-8 col-xs-6"></div>
                            <div class="col-md-2 col-xs-3">
                                <button type="button" class="btn btn-block btn-annul"><a href="#">Annulla</a></button>
                            </div>
                            <div class="col-md-2 col-xs-3">
                                <button type="submit" class="btn btn-block btn-ok" name="action" value="inviaRichiesta">Invia richiesta</button>
                            </div>
                        </div>

                    </form>
                </div>

            </div>

        </div>
    </body>
</html>
