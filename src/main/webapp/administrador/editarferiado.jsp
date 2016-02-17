<%-- 
    Document   : editarferiado
    Created on : 16-Feb-2016, 20:32:30
    Author     : Anderson Souza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerenciamento de Feriados</title>
        <!-- Bootstrap Core CSS -->
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link rel="icon" type="image/png" href="../logo.ico" />
        <!-- Custom CSS -->
        <link href="../css/modern-business.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="../font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">    
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script>
            $(function () {
                $(".busca").keyup(function () {
                    //pega o css da tabela 
                    var tabela = $(this).attr('alt');
                    if ($(this).val() !== "") {
                        $("." + tabela + " tbody > tr").hide();
                        $("." + tabela + " td:contains-ci('" + $(this).val() + "')").parent("tr").show();
                    } else {
                        $("." + tabela + " tbody>tr").show();
                    }
                });
            });
            $.extend($.expr[":"], {
                "contains-ci": function (elem, i, match, array) {
                    return (elem.textContent || elem.innerText || $(elem).text() || "").toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
                }
            });
        </script>

    </head>
    <body>
        <nav style="background: white" class="navbar navbar-default navbar-fixed-top" role="navigation">
            <img src="../img/logo.png" style="width: 250px; margin-top: 30px;margin-left: 100px;">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <!--<img src="../img/logo.png" style="width:20px;">-->
                    <br>
                    <h4 class="navbar-form">Sistema Sisloc</a><br><br>
                        <h4 class="navbar-form">Logado como ${sessionScope.user.role}</h4>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->

                <ul class="nav navbar-nav dropdown" style="margin-top: -40px;margin-left: 600px;">
                    <li>
                        <a class="dropdown-toggle" style="font-size: 20px; color: black" data-toggle="dropdown" href="">${sessionScope.user.name}
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Editar Perfil</a></li>
                            <li><a href="../front?command=logout">Sair</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <div class="container text-center">
            <div class="row">
                <div class="col-md-12">
                    <h1><b>Ediçao de Feriado</b></h1>
                </div>
            </div>
        </div>
        <br>
        <br>

        <div class="container">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <form role="form" action="../front?command=editHoliday&idHoliday=${sessionScope.holidayEdit.idHoliday}" method="post">
                        <div class="form-group">
                            <label for="description">Descriçao</label>
                            <input type="text" name="description" value="${sessionScope.holidayEdit.description}" class="form-control" id="description">
                            <input type="hidden" name="username" value="${sessionScope.holidayEdit.idHoliday}" class="form-control" id="username">
                        </div>
                        <div class="form-group">
                            <label for="date">Data</label>
                            <input type="date" name="date" value="${sessionScope.holidayEdit.date}" pattern="[0-9]{2}\/[0-9]{2}\/[0-9]{4}" title="dd/MM/AAAA" class="form-control" id="date">
                        </div>
                        <br>
                        <br>
                        <button type="submit" class="btn btn-success">Editar</button>
                    </form>
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>
                        <br>
                        <br>
                        <br>
                        <br>
                        <br><hr>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Sisloc 2016</p>
                </div>
            </div>
        </div>
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    </body>
</html>
