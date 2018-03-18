<%-- 
    Document   : index
    Created on : 8-feb-2018, 22.34.27
    Author     : leonardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Facebook Login JavaScript Example</title>

        <meta charset="UTF-8">
    </head>
    <body>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
        <script>
            // This is called with the results from from FB.getLoginStatus().

            var controllo = false;

            function statusChangeCallback(response) {
                console.log('statusChangeCallback');
                console.log(response);
                // The response object is returned with a status field that lets the
                // app know the current login status of the person.
                // Full docs on the response object can be found in the documentation
                // for FB.getLoginStatus().
                if (response.status === 'connected') {
                    // Logged into your app and Facebook.
                    if (controllo)
                        testAPI();
                    //sendValues();
                    console.log("Sono entrato nell'if dopo testapi");


                } else {
                    // The person is not logged into your app or we are unable to tell.

                    document.getElementById('status').innerHTML = 'Please log ' +
                            'into this app.';
                }
            }

            // This function is called when someone finishes with the Login
            // Button.  See the onlogin handler attached to it in the sample
            // code below.
            function checkLoginState() {
                controllo = true;
                FB.getLoginStatus(function (response) {
                    statusChangeCallback(response);
                });
            }

            window.fbAsyncInit = function () {
                FB.init({
                    appId: '554374914912883',
                    cookie: false, // enable cookies to allow the server to access 
                    // the session
                    xfbml: true, // parse social plugins on this page
                    version: 'v2.8' // use graph api version 2.8
                });

                // Now that we've initialized the JavaScript SDK, we call 
                // FB.getLoginStatus().  This function gets the state of the
                // person visiting this page and can return one of three states to
                // the callback you provide.  They can be:
                //
                // 1. Logged into your app ('connected')
                // 2. Logged into Facebook, but not your app ('not_authorized')
                // 3. Not logged into Facebook and can't tell if they are logged into
                //    your app or not.
                //
                // These three cases are handled in the callback function.

                FB.getLoginStatus(function (response) {
                    statusChangeCallback(response);
                });

            };

            // Load the SDK asynchronously
            (function (d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id))
                    return;
                js = d.createElement(s);
                js.id = id;
                js.src = "https://connect.facebook.net/en_US/sdk.js";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));

            // Here we run a very simple test of the Graph API after login is
            // successful.  See statusChangeCallback() for when this call is made.
            function testAPI() {
                console.log('Welcome!  Fetching your information.... ');
                FB.api('/me?fields=last_name,first_name,email', function (response) {

                    //console.log('Successful login for: ' + response.name);
                    //document.getElementById('status').innerHTML =
                    //'Thanks for logging in, ' + response.email + '!';
                    sendValues(response);

                });

                function sendValues(response) {
                    $.ajax({
                        type: 'POST',
                        url: "VerificaUtente",
                        data: {
                            firstName: response.first_name,
                            lastName: response.last_name,
                            email: response.email
                        },
                        success: function (msg) {
                            //$("html").empty();
                            //$("html").append(msg);
                            //console.log('wow' + msg);

                            console.log("Name: " + response.first_name + " " + response.last_name);

                            if (msg === 'esiste') {
                                window.location.href = "/Manutenza-web/MainController?action=dashboard";
                            }
                            //l'utente non esiste nel nostro db
                            else {
                                var form = $('#sign-in-form');
                                form.append('<input type="hidden" name="email" value="' + response.email + '"/>');
                                form.append('<input type="hidden" name="lastName" value="' + response.last_name + '"/>');
                                form.append('<input type="hidden" name="firstName" value="' + response.first_name + '"/>');
                                form.submit();
                            }
                        },
                        error: function (XMLHttpRequest, textStatus, errorThrown) {
                            console.log("some error");
                        }
                    });
                }
                ;


            }
        </script>

        <!--
          Below we include the Login Button social plugin. This button uses
          the JavaScript SDK to present a graphical Login button that triggers
          the FB.login() function when clicked.
        -->

    <fb:login-button scope="public_profile,email" onlogin="checkLoginState();"></fb:login-button>

    <form id="sign-in-form" method="post" action="/Manutenza-web/MainController?action=goRegistrati"></form>

    <div id="status">
    </div>
</body>
</html>

