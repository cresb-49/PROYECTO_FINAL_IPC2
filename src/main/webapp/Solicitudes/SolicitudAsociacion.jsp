<%-- 
    Document   : SolicitudAsociacion
    Created on : 12/11/2020, 18:08:29
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solicitud Asociacion de Cuenta</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/css/bootstrap.min.css"/>
        <script src="${pageContext.request.contextPath}/Resources/js/jquery-3.5.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/Resources/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="main">
            <header>
                <div class="container">
                    <br>
                    <h1>Banco EL BILLETON</h1>
                    <h5><a href="${pageContext.request.contextPath}/AccionCliente?action=0">Regresar al perfil</a></h5>
                    <br>
                </div>
            </header>
            <c:if test="${success==0}">
                <div class="container">
                    <br>
                    <h5>Introduce los datos para realizar solicitud</h5>
                    <p>Solo posees 3 intentos por cuenta, cuando los superes no podras solicitar mas a esa cuenta</p>
                </div>
                <br>
                <div class="container">
                    <form class="form" action="ControladorSolicitud" onsubmit="return validarAso();" method="POST">
                        <div class="form-group row">
                            <label for="nombreCliente" class="col-md-2 col-form-label">Nombre: </label>
                            <div class="col-md-4">
                                <input type="text" class="form-control" id="nombreCliente" required="" name="nombreCliente">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="numeroCuenta" class="col-md-2 col-form-label">Numero de Cuenta: </label>
                            <div class="col-md-4">
                                <input type="number" class="form-control" id="numeroCuenta" required="" name="numeroCuenta">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="numeroDPI" class="col-md-2 col-form-label">DPI: </label>
                            <div class="col-md-4">
                                <input type="number" class="form-control" id="numeroDPI" required="" name="numeroDPI">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-3">
                                <button type="submit" class="btn btn-warning">Generar Solicitud</button>
                            </div>
                        </div>
                    </form>
                </div>
            </c:if>       
            <c:if test="${success==1}">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Error en Solicitud</h5>
                        </div>
                        <div class="modal-body">
                            <p></p>
                            <p>${errores}</p>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/AccionCliente?action=2">Regresar a solicitud de asociacion</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${success==2}">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Solicitud Enviada</h5>
                        </div>
                        <div class="modal-body">
                            <p></p>
                            <p>Se realizo con exito la solicitud de asociacion de cuenta</p>
                            <p>${intentos}</p>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/AccionCliente?action=2">Regresar a solicitud de asociacion</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <%@include file="../CabeceraPie/piePagina.jsp" %>
        </div>
        <script src="${pageContext.request.contextPath}/Resources/js/validarSolicitudAso.js"></script>
    </body>
</html>
