<%-- 
    Document   : registraUtente
    Created on : 5-feb-2018, 14.33.48
    Author     : leonardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrazione Utente</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        
        <nav class="navbar navbar-inverse" style="border-radius: 0">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/Manutenza-web/MainController?action=home">ManUtenza</a>
                </div>
            </div>
        </nav>
        
        <div class="container">
            <div class="row">
                 
                <div class="col-md-8 col-md-offset-2">
                    <form role="form" action="?action=registraUtente" method="post">   
                        
                    <filedset>
                        <legend>Dati Utente</legend>
                    <div class="form-group col-md-6">
                        <label for="nome">Nome</label>
                        <input type="text" class="form-control" name="nome" placeholder="Inserisci il tuo nome" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="cognome">Cognome</label>
                        <input type="text" class="form-control" name="cognome" placeholder="Inserisci il tuo cognome" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="dataDiNascita">Data di Nascita</label>
                        <input type="date" class="form-control" name="dataDiNascita" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="codiceFiscale">Codice Fiscale</label>
                        <input type="text" class="form-control" name="codiceFiscale" placeholder="Inserisci il codice fiscale" required>
                    </div>
                    <div class="form-group col-md-12">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" name="email" placeholder="Inserisci indirizzo email" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" name="password" placeholder="Inserisci una password" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="confirmPassword">Conferma Password</label>
                        <input type="password" class="form-control" name="confirmPassword" placeholder="Conferma password" required>
                    </div>
                    </filedset>
                        
                        <legend>Dati Domicilio</legend>
                        
                        <div class="form-group col-md-8">
                            <label for="citta">Citta&acute;</label>
                            <input type="text" class="form-control" name="citta" placeholder="Inserisci la tua citta&acute;" required>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="provincia">Provincia</label>
                            <input type="text" class="form-control" name="provincia" placeholder="" required>
                        </div>
                        <div class="form-group col-md-8">
                            <label for="via">Indirizzo</label>
                            <input type="text" class="form-control" name="via" placeholder="Inserisci indirizzo" required>
                        </div>
                        <div class="form-group col-md-4">
                            <label for="cap">Cap</label>
                            <input type="text" class="form-control" name="cap" placeholder="" required>
                        </div>
                        
                        <div class="form-group">
                        <div class="col-md-12">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="" id="">
                                    Accetto <a href="#">termini e condizioni</a>.
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-12">
                            <button type="submit" class="btn btn-primary">
                                Registrami
                            </button>
                            <a href="#">Hai un account?</a>
                        </div>
                    </div>
                       
                </form>
                </div>
            </div>
        </div>
    </body>
</html>
