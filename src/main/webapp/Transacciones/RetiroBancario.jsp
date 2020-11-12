<%-- 
    Document   : RetiroBancario
    Created on : 12/11/2020, 10:44:31
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Retiro Bancario</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/css/bootstrap.min.css"/>
        <script src="${pageContext.request.contextPath}/Resources/js/jquery-3.5.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/Resources/js/bootstrap.min.js"></script>
    </head>
    <body>
        <header>
            <div class="container">
                <br>
                <h1>Banco EL BILLETON</h1>
                <h5><a href="${pageContext.request.contextPath}/AccionCajero?action=4">Regresar al perfil</a></h5>
                <br>
            </div>
        </header>
        <div class="main" >
            <c:if test="${success==0}">
                <c:if test="${noData ==0}">
                    <br/>
                    <div class="alert alert-warning" role="alert">
                        No se encontro un numero de cuenta con el valor ingresado
                    </div>
                </c:if>
                <div class="container">
                    <br>
                    <h5>Introduce los datos para realizar la transaccion</h5>
                </div>
                <br>
                <div class="container">
                    <form class="form" action="RetiroDeCuenta" onsubmit=" return validarRetiroForm();" method="POST">
                        <div class="form-group row">
                            <label for="numeroCuenta" class="col-md-2 col-form-label">Numero de Cuenta: </label>
                            <div class="col-md-3">
                                <input type="number" class="form-control" id="numeroCuenta" required="" name="numeroCuenta">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="numeroDPI" class="col-md-2 col-form-label">DPI: </label>
                            <div class="col-md-3">
                                <input type="number" class="form-control" id="numeroDPI" required="" name="numeroDPI">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="montoDebitar" class="col-md-2 col-form-label">Monto a debitar: </label>
                            <div class="col-md-3">
                                <input type="number" class="form-control" id="montoDebitar" required="" name="montoDebitar">
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
                            <h5 class="modal-title" id="exampleModalLabel">Error en Transaccion</h5>
                        </div>
                        <div class="modal-body">
                            <p></p>
                            <p>${errores}</p>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/AccionCajero?action=2">Regresar a retiro Bancario</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${success==2}">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Transaccion Realizada</h5>
                        </div>
                        <div class="modal-body">
                            <p>Datos de la transaccion realizada:</p>
                            <p>----------------------------------</p>
                            <p>Codigo de Transaccion: ${transaccion.codigo}</p>
                            <p>Cuenta Remitente: ${transaccion.idCuenta}</p>
                            <p>Fecha: ${transaccion.fechaTransaccion}</p>
                            <p>Hora: ${transaccion.hora}</p>
                            <p>Tipo: ${transaccion.tipo}</p>
                            <p>Monto: ${transaccion.monto}</p>
                            <p>Cajero: ${transaccion.idCajero}</p>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/AccionCajero?action=2">Realizar otro Retiro</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${success==3}">

            </c:if>
            <%@include file="../CabeceraPie/piePagina.jsp" %>
        </div>
        <script src="${pageContext.request.contextPath}/Resources/js/validarRetiro.js"></script>
    </body>
</html>
