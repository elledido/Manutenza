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

        <!-- Paypal checkout JS -->
        <script src="https://www.paypalobjects.com/api/checkout.js"></script>

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

            <!-- Elenco delle proposte associate alla richiesta scelta -->
            <c:forEach items="${proposte}" var="proposta">
                <div class="form-box">
                    <form class="form-horizontal" action="/Manutenza-web/MainController?action=apriChat&propostaId=${proposta.getId()}" method="post">
                        <div class="row">
                            <!-- Foto -->
                            <div class="img-box col-md-2 col-xs-2">
                                <img class="profile-img" src="images/profile_img.png" alt="profile-img"/>
                            </div>
                            <!-- Dati richiesta -->
                            <div class="col-md-9 col-xs-8">
                                <p class="titolo">${proposta.getManutente().getCognome()} ${proposta.getManutente().getNome()}</p>

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
                                    <label class="control-label col-md-3 col-xs-3" for="budget">Prezzo proposto: </label>
                                    <div class="input-group budget col-md-2 col-xs-4">
                                        <span class="input-group-addon">€</span>
                                        <input class="form-control currency" id="budget" name="budget" type="number" readonly 
                                               value="${proposta.getPrezzo()}">
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
                                <!-- va alla chat -->
                            </div>
                            <!-- vai alla pagina del pagamento -->
                            <div class="col-md-2 col-xs-4" id="paypal-button${proposta.getId()}"></div>
                        </div>
                    </form>
                </div>

                <div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header text-center">
                                <h4 class="modal-title"><strong>Pagamento effettuato con successo</strong></h4>
                            </div>
                            <div class="modal-body row">
                                <div class="col-xs-12 text-center">
                                    <p>Hai accettato la proposta di ${proposta.getManutente().getNome()} ${proposta.getManutente().getCognome()}</p>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <div class="col-xs-4"></div>
                                <div class="col-xs-4">
                                    <a href="/Manutenza-web/MainController?action=richiesteInCorso" class="btn btn-block btn-ok" role="button">Ok</a>
                                </div>
                                <div class="col-xs-4"></div>
                            </div>
                        </div>

                    </div>
                </div>

                <script>
                    paypal.Button.render({
                        env: 'sandbox', // sandbox | production

                        style: {
                            label: 'pay'
                        },

                        // PayPal Client IDs - replace with your own
                        client: {
                            sandbox: 'Adq8RmO8uXLMtwlpVQZam250Or9ivFWBBf0ZECiWd86lES4SgUe08ACRoJmjjVG_iCeVbIqsEnBaogdl',
                            production: '<insert production client id>'
                        },

                        // Show the buyer a 'Pay Now' button in the checkout flow
                        commit: true,

                        // payment() is called when the button is clicked
                        payment: function (data, actions) {

                            // Make a call to the REST api to create the payment
                            return actions.payment.create({
                                payment: {
                                    transactions: [
                                        {
                                            amount: {total: '${proposta.getPrezzo()}', currency: 'EUR'}
                                        }
                                    ]
                                }
                            });
                        },

                        // onAuthorize() is called when the buyer approves the payment
                        onAuthorize: function (data, actions) {

                            // Make a call to the REST api to execute the payment
                            return actions.payment.execute().then(function () {
                                //aggiornamento stato della proposta ad accettato  
                                $.ajax({
                                    type: 'POST',
                                    url: "AccettaProposta",
                                    data: {
                                        propostaId: ${proposta.getId()},
                                        richiestaId: ${proposta.getRichiesta().getId()}
                                    },
                                    success: function (messaggio) {
                                        console.log(messaggio);
                                        //apro una modal che conferma all'utente il successo del pagamento
                                        $('#successModal').modal();
                                    },
                                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                                        console.log("some error");
                                    }
                                });

                            });
                        }

                    }, '#paypal-button${proposta.getId()}');
                </script>

            </c:forEach>

            <!-- FOOTER -->
            <%@include file="/footer.txt"%>

        </div>

    </body>
</html>
