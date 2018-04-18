<%--
    Document   : profilo
    Created on : 2-feb-2018, 12.23.11
    Author     : irene
--%>

<%@page import="it.unito.taass.manutenza.entities.Utente"%>
<%@page import="it.unito.taass.manutenza.entities.Indirizzo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>

<%
    //dati dell'utente in sessione
    Utente utente = (Utente) session.getAttribute("utente");
%>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Profilo</title>

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

            <h2>Profilo</h2>

            <div class="form-box">
                <!-- DATI DEL PROFILO -->
                <form class="form-horizontal" action="#">

                    <div class="row">
                        <div class="col-xs-2 profile-img-box">
                            <div class="text-center">
                                <img class="profile-img" src="images/profile_img.png" alt="profile-img"/>
                            </div>
                        </div>
                        <div class="col-xs-9">
                            <!-- EMAIL -->
                            <div class="form-group">
                                <label class="control-label col-xs-3" for="email">Email: </label>
                                <div class="col-xs-8">
                                    <input class="form-control" name="email" type="email" readonly 
                                           value="<%out.print(utente.getEmail());%>">
                                </div>
                            </div>
                            <!-- NOME -->
                            <div class="form-group">
                                <label class="control-label col-xs-3" for="nome">Nome: </label>
                                <div class="col-xs-8">
                                    <input class="form-control" name="nome" type="text" readonly value="<%out.print(utente.getNome());%>">
                                </div>
                            </div>
                            <!-- COGNOME -->
                            <div class="form-group">
                                <label class="control-label col-xs-3" for="cognome">Cognome: </label>
                                <div class="col-xs-8">
                                    <input class="form-control" name="cognome" type="text" readonly value="<%out.print(utente.getCognome());%>">
                                </div>
                            </div>
                            <!-- DATA DI NASCITA -->
                            <div class="form-group">
                                <label class="control-label col-xs-3" for="datanascita">Data di nascita: </label>
                                <div class="col-xs-8">
                                    <input class="form-control" name="datanascita" type="text" readonly value="<%out.print(request.getAttribute("dataNascita"));%>">
                                </div>
                            </div>
                            <!-- CODICE FISCALE -->
                            <div class="form-group">
                                <label class="control-label col-xs-3" for="cf">Codice fiscale: </label>
                                <div class="col-xs-8">
                                    <input class="form-control" name="cf" type="text" readonly value="<%out.print(utente.getCodiceFiscale());%>">
                                </div>
                            </div>
                        </div>

                    </div>

                    <!-- DOMICILII -->
                    <c:set var="i" value="${0}"/>

                    <c:forEach items="${utente.getListaIndirizzi()}" var="domicilio">
                        <c:set var="i" value="${i+1}"/>

                        <div class="line"></div>
                        <div>
                            <h3 class="domicilio">Domicilio ${i}</h3>
                            <!-- Città e provincia -->
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-xs-2" for="citta">Citt&agrave;: </label>
                                        <div class="col-md-8 col-xs-9">
                                            <input class="form-control" id="citta" name="citta" type="text" readonly value="${domicilio.getCitta()}">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <label class="control-label col-md-8 col-xs-2" for="provincia">Provincia: </label>
                                        <div class="col-md-4 col-xs-2">
                                            <input class="form-control" id="provincia" name="provincia" type="text" readonly value="${domicilio.getProvincia()}">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Via e CAP -->
                            <div class="row">
                                <div class="col-md-8 ">
                                    <div class="form-group">
                                        <label class="control-label col-md-3 col-xs-2" for="via">Via: </label>
                                        <div class="col-md-8 col-xs-9">
                                            <input class="form-control" id="via" name="via" type="text" readonly value="${domicilio.getVia()}">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3 ">
                                    <div class="form-group">
                                        <label class="control-label col-md-7 col-xs-2" for="cap">CAP: </label>
                                        <div class="col-md-5 col-xs-2">
                                            <input class="form-control" id="cap" name="cap" type="text" readonly value="${domicilio.getCap()}">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:forEach>

                </form>
            </div>

            <!-- FOOTER -->
            <%@include file="/footer.txt"%>
            
        </div>
        
        <!-- CHAT -->
        <%@include file="/chat.txt"%>

    </body>

</html>

