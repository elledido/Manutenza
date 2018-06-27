<%-- 
    Document   : chat
    Created on : 27-giu-2018, 15.03.54
    Author     : irene
--%>

<%@page import="it.unito.taass.manutenza.entities.Manutente"%>
<%@page import="it.unito.taass.manutenza.entities.Utente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Chat</title>

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
        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
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
            <h2>Proposta Ciao - Mario Rossi</h2>

            <div class="row">
                <!-- chat -->
                <div class="col-xs-9">
                    <div class="panel">
                        <div id="chat">
                            <div class="panel-body">
                                <ul id="chat-list">
                                    <ul id="chat-list">
                                        <!-- Interlocutore -->
                                        <li class="left clearfix">
                                            <span class="pull-left">
                                                <img class="chat-img img-circle" src="images/profile_img.png" alt="profile-img"/>
                                            </span>
                                            <div class="chat-body clearfix">
                                                <div class="header">
                                                    <strong>OtherAccount</strong>
                                                    <small class="pull-right text-muted"><span class="glyphicon glyphicon-time"></span>10:06</small>
                                                </div>
                                                <p>
                                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
                                                    dolor, quis ullamcorper ligula sodales.
                                                </p>
                                            </div>
                                        </li>
                                        <!-- utente in sessione -->
                                        <li class="right clearfix">
                                            <span class="pull-right">
                                                <img class="chat-img img-circle" src="http://placehold.it/50/FA6F57/fff&text=ME" alt="profile-img"/>
                                            </span>
                                            <div class="chat-body clearfix">
                                                <div class="header">
                                                    <small class="text-muted"><span class="glyphicon glyphicon-time"></span>10:11</small>
                                                    <strong class="pull-right">Account</strong>
                                                </div>
                                                <p>
                                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
                                                    dolor, quis ullamcorper ligula sodales.
                                                </p>
                                                
                                            </div>
                                        </li>
                                        <li class="right clearfix">
                                            <span class="pull-right">
                                                <img class="chat-img img-circle" src="http://placehold.it/50/FA6F57/fff&text=ME" alt="profile-img"/>
                                            </span>
                                            <div class="chat-body clearfix">
                                                <div class="header">
                                                    <small class="text-muted"><span class="glyphicon glyphicon-time"></span>10:11</small>
                                                    <strong class="pull-right">Account</strong>
                                                </div>
                                                <p>
                                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
                                                    dolor, quis ullamcorper ligula sodales.
                                                </p>
                                                
                                            </div>
                                        </li>
                                    </ul>
                                </ul>
                            </div>
                            <div class="panel-footer">
                                <form id="sendMsgChat">
                                    <div class="input-group">
                                        <input class="form-control input-sm" name="msg" type="text" placeholder="Scrivi un messaggio..." required>
                                        <span class="input-group-btn">
                                            <button type="submit" class="btn btn-sm btn-send" id="btn-chat">Invia</button>
                                        </span>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- info sull'interlocutore -->
                <div class="col-xs-3 profile-box">
                    <div class="row">
                        <div class="col-xs-12 text-center">
                            <img class="profile-img" src="images/profile_img.png" alt="profile-img"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 text-center rating-star">
                            <!-- RATING STAR -->                            
                            <div class="container-fluid back-stars">
                                <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                            </div>

                            <div class="container-fluid front-stars">
                                <c:forEach var="i" begin="1" end="3">
                                    <i id="star<c:out value="${i}"/>" class="fa fa-star fa-2x" aria-hidden="true"></i>
                                </c:forEach>
                                <c:forEach var="i" begin="${3 + 1}" end="5">
                                    <i id="star<c:out value="${i}"/>" class="fa fa-star-o fa-2x" aria-hidden="true"></i>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <div class="line"></div>

                    <div class="row">
                        <div class="profile-info text-center">
                            <p>Nome e cognome</p>
                            <p>Email</p>
                            <div class="line"></div>
                            <p>Idraulico professionista</p>
                            <p>Falegname amatoriale</p>
                        </div>
                    </div>

                </div>
            </div>


            <!-- FOOTER -->
            <%@include file="/footer.txt"%>

        </div>

    </body>

</html>
