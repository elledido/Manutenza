<%-- 
    Document   : contatti
    Created on : 12-mar-2018, 11.42.26
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

        <title>Contatti</title>

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

            <h2>Contatti</h2>
            <div class="row">
                <!-- MAPPA -->
                <div class="col-sm-6 col-sm-push-6">
                    <section id="dove-siamo">
                        <h3>Dove siamo</h3>
                        <div id="google-maps">
                            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2816.7964646436635!2d7.657224514869105!3d45.08992036636128!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47886db2ac05e99b%3A0x53d868944a1e154b!2sVia+Pessinetto%2C+12%2C+10149+Torino!5e0!3m2!1sit!2sit!4v1491784233847" width="555" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
                        </div>
                    </section>
                </div>
                <!-- CONTATTI -->
                <div class="col-sm-6 col-sm-pull-6">
                    <section id="info-contatto">
                        <h3>Recapiti</h3>
                        <address>
                            <strong>Manutenza</strong><br>
                            Via Pessinetto, 12<br>
                            Torino, 10149, Italy<br>
                            Telefono/fax: 123 456-7890
                        </address>
                        <address>
                            <strong>E-mail</strong>
                            <a href="mailto:#">info@manutenza.it</a>
                        </address>
                    </section>
                </div>
            </div>
            
            <!-- FOOTER -->
            <%@include file="/footer.txt"%>
            
        </div>
        
        <!-- CHAT -->
        <%@include file="/chat.txt"%>
        
    </body>

</html>
