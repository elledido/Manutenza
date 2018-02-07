<%-- 
    Document   : home
    Created on : 6-feb-2018, 11.38.38
    Author     : leonardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    
        <title>ManUtenza</title>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!-- Custom CSS -->
        <link href="home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <nav class="navbar">
          <div class="container-fluid">
            <div class="navbar-header">
              <a class="navbar-brand" href="#">ManUtenza</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="#">Cos'e&grave;</a></li>
              <li><a href="#">Servizi</a></li>
            </ul>
              <button id="btn-diventa-manutente" class="btn navbar-btn navbar-right">Diventa ManUtente</button>
            <ul class="nav navbar-nav navbar-right">
              <li><a href="#" data-toggle="modal" data-target="login-modal">Login</a></li>
              <li><a href="/Manutenza-web/MainController?action=nuovoUtente">Registrati</a></li>
          </ul>
          </div>
        </nav>
        
        <a href="#" data-toggle="modal" data-target="login-modal">Login</a>
        
        <!-- ### LOGIN FORM A COMPARSA ###-->
        <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    	    <div class="modal-dialog">
		<div class="loginmodal-container">
		    <h1>Login to Your Account</h1><br>
		    <form>
			<input type="text" name="user" placeholder="Username">
			<input type="password" name="pass" placeholder="Password">
			<input type="submit" name="login" class="login loginmodal-submit" value="Login">
		    </form>
		    <div class="login-help">
			<a href="#">Register</a> - <a href="#">Forgot Password</a>
		    </div>
		</div>
	    </div>
        </div>
        
    </body>
</html>
