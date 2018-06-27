<%-- 
    Document   : nuovaRichiesta
    Created on : 3-feb-2018, 19.37.11
    Author     : irene
--%>

<%@page import="it.unito.taass.manutenza.entities.Manutente"%>
<%@page import="it.unito.taass.manutenza.entities.Utente"%>
<%@page import="it.unito.taass.manutenza.entities.Indirizzo"%>
<%@page import="it.unito.taass.manutenza.entities.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>

<%
    //dati dell'utente in sessione
    Manutente utente = (Manutente) session.getAttribute("utente");
%>

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

            <h2>Nuova richiesta</h2>

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
                                <c:forEach items="${applicationScope.categorie}" var="categoria">
                                    <option value="${categoria.getNome()}">${categoria.getNome()}</option>
                                </c:forEach>  
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

                    <!-- INDIRIZZO -->
                    <div class="form-group">
                        <label class="control-label col-md-3 col-xs-3" for="indirizzo">Indirizzo: </label>
                        <div class="col-md-8 col-xs-8">
                            <select class="form-control" id="indirizzo" name="indirizzo" required>
                                <c:forEach items="${utente.getListaIndirizzi()}" var="domicilio">
                                    <option value="${domicilio.getId()}">${domicilio.getVia()}, ${domicilio.getCitta()} (${domicilio.getProvincia()})</option>
                                </c:forEach>                                    
                            </select>
                        </div>
                    </div>

                    <!-- BUDGET MASSIMO -->
                    <div class="form-group">
                        <label class="control-label col-md-3 col-xs-3" for="budget">Budget massimo: </label>
                        <div class="input-group budget col-md-2 col-xs-3">
                            <span class="input-group-addon">€</span>
                            <input class="form-control currency" id="budget" name="budget" type="number" placeholder="0" min="0" required>
                        </div>
                    </div>

                    <!-- FOTO (l'utente inserisce il link di una foto caricata in rete) -->
                    <div id="photoGallery" class="row">
                        <div id="addImg" class="col-md-3 col-xs-4 text-center">
                            <button type="button" class="btn btn-block btn-img" data-toggle="modal" data-target="#nuovaFoto">
                                <img class="img-responsive img-rounded" src="images/add_img.jpg">
                            </button>
                        </div>
                    </div>
                    <div id="photoLinks" class="form-group"></div>

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
            <!-- Form Aggiungi foto -->
            <div class="modal fade" id="nuovaFoto" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Inserisci link foto</h4>
                        </div>
                        <div class="modal-body row">
                            <div class="col-xs-12" align="center">
                                <div class="col-md-1 col-xs-1"></div>
                                <form class="form-horizontal">
                                    <label class="control-label col-md-1 col-xs-1" for="url">URL: </label>
                                    <div class="col-md-9 col-xs-9">
                                        <input class="form-control" id="url" name="url" type="url" placeholder="http://www.myimagelink.com" required>
                                    </div>                                    
                                </form>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <div class="col-xs-3"></div>
                            <div class="col-xs-3">
                                <button type="button" class="btn btn-block btn-annul" data-dismiss="modal">Annulla</button>
                            </div>
                            <div class="col-xs-3">
                                <button id="addPhotoLink" type="button" class="btn btn-block btn-ok" data-dismiss="modal">Aggiungi</button>
                            </div>
                            <div class="col-xs-3"></div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- FOOTER -->
            <%@include file="/footer.txt"%>
            
        </div>

        <script type="text/javascript">
            $(document).ready(function () {

                var numPhotos = 0; //numero di attualmente inserite (all'inizio 0)
                var maxPhotos = 10; //numero di foto massime

                //funzione per aggiunge una foto al form
                $('#addPhotoLink').on("click", function () {
                    numPhotos++; //aumento il numero di foto
                    var link = $('#url').val(); //link della foto
                    var photoLinks = $('#photoLinks');
                    var photoGallery = $('#photoGallery');
                    var addImg = $('#addImg');

                    $('#url').val(" "); //pulisco il form
                    
                    //campo hidden per il link della foto
                    var photoField = document.createElement("input");
                    photoField.name = "photos";
                    photoField.type = "hidden";
                    photoField.value = link;

                    photoLinks.append(photoField); //aggiungo il campo hidden al form

                    //faccio visualizzare la foto appena inserita
                    var photoDiv = document.createElement("div");
                    photoDiv.className = "col-md-3 col-xs-4";

                    var photoImg = document.createElement("img");
                    photoImg.className = "img-responsive img-rounded photo-gallery";
                    photoImg.src = link;

                    //bottone per eliminare l'immagine
                    var closeBtn = document.createElement("button");
                    closeBtn.className = "btn close btn-close";
                    closeBtn.onclick = function (event) {
                        deleteImg($(this), event);
                    };

                    var closeIcon = document.createElement("i");
                    closeIcon.className = "fa fa-times fa-2x";
                    closeBtn.appendChild(closeIcon);

                    photoDiv.appendChild(photoImg);
                    photoDiv.appendChild(closeBtn);

                    $('#addImg').remove();
                    photoGallery.append(photoDiv);
                    //se non ho raggiunto il numero di foto massime
                    if (numPhotos !== maxPhotos) {
                        photoGallery.append(addImg);
                    }

                });

                //funzione per eliminare un'immagine dal form
                function deleteImg(element, event) {
                    event.preventDefault();

                    var photoDiv = element.parent(); //div contenente la foto
                    var photoIndex = photoDiv.index(); //posizione della foto nell'elenco
                    var photoField = $('#photoLinks').children().eq(photoIndex); //campo hidden della foto

                    photoDiv.remove(); //rimuovo la foto
                    photoField.remove(); //rimuovo il campo hidden

                    numPhotos--; //diminuisco il numero delle foto
                }

            });
        </script>

    </body>
</html>
