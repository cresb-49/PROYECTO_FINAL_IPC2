<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bienvenido</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/css/bootstrap.min.css"/>
        <script src="${pageContext.request.contextPath}/Resources/js/jquery-3.5.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/Resources/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="CabeceraPie/Cabecera.jsp" %>
        <div class="container">
            <section class="main row">
                <div class="container col-md-4">
                    <form action="ControladorLogin" method="POST">
                        <h2>Inicio sesion</h2>
                        <div class="form-group">
                            <label for="usuario">Usuario:</label>
                            <input class="form-control" id="usuario" type="text" name="usuario">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input class="form-control" id="password" type="password" name="password">
                        </div>
                        <div class="form-group">
                            <button class="btn btn-success" type="submit" name="enviar" value="Ingresar">Ingresar</button>
                        </div>
                    </form>
                </div>
            </section>
        </div>
        <%@include file="CabeceraPie/piePagina.jsp" %>
    </body>
</html>
