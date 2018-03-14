<%-- 
    Document   : completaProfilo
    Created on : 14-mar-2018, 14.37.39
    Author     : leonardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <nav class="navbar navbar-inverse" style="border-radius: 0">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/Manutenza-web/MainController?action=home">ManUtenza</a>
                </div>
            </div>
        </nav>
    
        <div class="col-md-8 col-md-offset-2">
        
        <form class="form-horizontal" role="form" action="?action=registraUtente" method="post">
<fieldset>

    <legend>Dati profilo</legend>
    
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="Name">Nome</label>  
  <div class="col-md-4">
  <input id="nome" name="nome" type="text" class="form-control input-md" value="<%out.print(request.getAttribute("nome"));%>" readonly>
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="cognome">Cognome</label>  
  <div class="col-md-4">
  <input id="cognome" name="cognome" type="text" class="form-control input-md" value="<%out.print(request.getAttribute("cognome"));%>" readonly>
    
  </div>
</div>

<!-- Data di nascita input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="dataDiNascita">Data di Nascita</label>
  <div class="col-md-4">
    <input id="dataDinascita" name="dataDiNascita" type="date" placeholder="Data di Nascita" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Codice Fiscale input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="codiceFiscale">Codice Fiscale</label>  
  <div class="col-md-4">
  <input id="codiceFiscale" name="codiceFiscale" type="text" placeholder="Codice Fiscale" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Codice Fiscale input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email</label>  
  <div class="col-md-4">
      <input id="email" name="email" type="text" class="form-control input-md" value="<%out.print(request.getAttribute("email"));%>" readonly >
    
  </div>
</div>

</fieldset>
            
<fieldset>
    <legend>Dati Domicilio</legend>

    <!-- Indirizzo input-->
    <div class="form-group">
        <label class="col-md-4 control-label" for="indirizzo">Indirizzo</label>  
        <div class="col-md-4">
            <input id="indirizzo" name="indirizzo" type="text" placeholder="Indirizzo" class="form-control input-md" required="">
        </div>
    </div>
    
    <!--Città input-->
    <div class="form-group">
        <label class="col-md-4 control-label" for="citta">Citt&aacute;</label>  
        <div class="col-md-4">
            <input id="citta" name="citta" type="text" placeholder="Citt&aacute;" class="form-control input-md" required="">
        </div>
    </div>
    
    <!-- Provincia input-->
    <div class="form-group">
        <label class="col-md-4 control-label" for="provincia">Provincia</label>  
        <div class="col-md-4">
            <input id="provincia" name="provincia" type="text" placeholder="Provincia" class="form-control input-md" required="">
        </div>
    </div>
    
    <!-- CAP input-->
    <div class="form-group">
        <label class="col-md-4 control-label" for="cap">Cap</label>  
        <div class="col-md-4">
            <input id="cap" name="cap" type="text" placeholder="Cap" class="form-control input-md" required="">
        </div>
    </div>
</fieldset>
            
<legend></legend>

<div class="form-group">
    <div class="checkbox">
        <label class="col-md-4 control-label" for="completa"></label>
        <label class="col-md-4">
            <input type="checkbox" value="" id="">
            Accetto <a href="#">termini e condizioni</a>.
        </label>
    </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="completa"></label>
  <div class="col-md-4">
    <button id="completa" name="" class="btn btn-primary">Registrami</button>
    <a href="#">Vuoi diventare Manutente?</a>
  </div>
</div>
            
</form>
        </div>
<div class="container">
	<div class="row">
	</div>
</div>
        
    </body>
</html>
