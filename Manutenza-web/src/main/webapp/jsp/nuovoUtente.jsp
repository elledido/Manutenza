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
        <!-- Custom CSS -->
        <link href="manutenzastyle.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Nuovo Utente</h1>
        
        <form action="?action=registraUtente" method="post">
            <div>
                <label for="nome">Nome: </label>
                <div>
                    <input name="nome" type="text" required />
                </div>
            </div>
            <div>
                <label for="cognome">Cognome: </label>
                <div>
                    <input name="cognome" type="text" required />
                </div>
            </div>
            <div>
                <label for="dataDiNascita">Data di nascita: </label>
                <div>
                    <input name="dataDiNascita" type="date" required />
                </div>
            </div>
            <div>
                <label for="codiceFiscale">Codice Fiscale: </label>
                <div>
                    <input name="codiceFiscale" type="text" required />
                </div>
            </div>
            <div>
                <label for="email">Email: </label>
                <div>
                    <input name="email" type="email" required />
                </div>
            </div>
            <div>
                <label for="password">Password: </label>
                <div>
                    <input name="password" type="password" required />
                </div>
            </div>
            <div>
                <input type="submit" value="Registrami" />
            </div>
        </form>
        
    </body>
</html>
