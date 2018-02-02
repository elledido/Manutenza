<%-- 
    Document   : dashboard
    Created on : 2-feb-2018, 11.48.34
    Author     : irene
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <title>Dashboard</title>

        <!-- Bootstrap CSS CDN -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="../manutenzastyle.css" rel="stylesheet" type="text/css"/>
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
    </head>
    
    <body>
        
        <!-- MAIN CONTAINER -->
        <div class="container">
            
            <!-- NAVIGATION BAR -->
            <nav id="topNav" class="navbar navbar-default navbar-fixed-top">
                <div class="container-fluid">
                    <!-- NAVIGATION HEADER -->
                    <div class="navbar-header pull-left">
                        <button type="button" id="sidebarCollapse" class="btn btn-default navbar-button">
                            <i class="fa fa-bars fa-2x" aria-hidden="true"></i>
                        </button>
                        <a class="navbar-brand" href="#">ManUtenza</a>
                    </div>
                    <!-- RIGHT-SIDE NAVBAR -->
                    <div class="navbar-header pull-right right-side-nav">
                        <ul class="nav navbar-nav">
                            <li><a href="#"><i class="fa fa-sign-out" aria-hidden="true"></i> Logout</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
            
            <!-- SIDEBAR -->
            <nav id="sidebar">

                <!-- PROFILE BOX -->
                <div class="container-fluid profile-box">
                    <div class="row">
                        <div class="col-md-12" align="center">
                            <img class="profile-img" src="https://cdn.onlinewebfonts.com/svg/img_215059.png" alt="profile-img">
                            <h4 class="account-name">Account Name</h4>
                            <div class="rating-star">
                                <!-- RATING STAR -->                            
                                <div class="container-fluid back-stars">
                                    <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                    <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                    <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                    <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                    <i class="fa fa-star fa-2x back-star" aria-hidden="true"></i>
                                </div>
                                <div class="container-fluid front-stars">
                                    <i id="star1" class="fa fa-star-o fa-2x" aria-hidden="true"></i>
                                    <i id="star2" class="fa fa-star-o fa-2x" aria-hidden="true"></i>
                                    <i id="star3" class="fa fa-star-o fa-2x" aria-hidden="true"></i>
                                    <i id="star4" class="fa fa-star-o fa-2x" aria-hidden="true"></i>
                                    <i id="star5" class="fa fa-star-o fa-2x" aria-hidden="true"></i>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

                <!-- MENU' VOICES -->
                <ul class="components list-unstyled">
                    <li class="active"><a href="dashboard.jsp">Dashboard</a></li>
                    <li><a href="profilo.jsp">Profilo</a></li>
                    <li>
                        <a href="#gestioneRichieste" data-toggle="collapse" aria-expanded="false">Le mie richieste</a>
                        <ul class="collapse list-unstyled" id="gestioneRichieste">
                            <li><a href="#">Nuova richiesta</a></li>
                            <li><a href="#">In corso</a></li>
                            <li><a href="#">Passate</a></li>
                        </ul>
                    </li>
                    <li><a href="#">Le mie competenze</a></li>
                    <li>
                        <a href="#gestioneProposte" data-toggle="collapse" aria-expanded="false">Proposte di lavoro</a>
                        <ul class="collapse list-unstyled" id="gestioneProposte">
                            <li><a href="#">Nuove proposte</a></li>
                            <li><a href="#">Accettate</a></li>
                            <li><a href="#">Completate</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
            
            <!-- MAIN CONTENT -->
            <div id="content">
                <h1>Dashboard</h1>
            </div>
            
        </div>
        
        <script type="text/javascript">

            function setRate(rate) {
                //set full stars
                for (i = 1; i <= rate; i++) {
                    $("#star" + i).addClass("fa-star");
                    $("#star" + i).removeClass("fa-star-o");
                }
                //set empty stars
                for (i = rate + 1; i <= 5; i++) {
                    $("#star" + i).addClass("fa-star-o");
                    $("#star" + i).removeClass("fa-star");
                }
            }

            $(document).ready(function () {
                setRate(4);

                $("#sidebar").mCustomScrollbar({
                    theme: "minimal"
                });

                $('#sidebarCollapse').on('click', function () {
                    // open or close navbar
                    $('#sidebar, #content').toggleClass('active');
                    // close dropdowns
                    $('.collapse.in').toggleClass('in');
                    // and also adjust aria-expanded attributes we use for the open/closed arrows
                    // in our CSS
                    $('a[aria-expanded=true]').attr('aria-expanded', 'false');

                });

            });

        </script>
        
    </body>
    
</html>
