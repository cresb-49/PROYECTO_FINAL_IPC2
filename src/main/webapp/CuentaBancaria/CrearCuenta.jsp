<%-- 
    Document   : CrearCuenta
    Created on : 12/11/2020, 00:45:33
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Cuenta</title>
    </head>
    <body>
        <div class="main">
            <header>
                <div class="container">
                    <br>
                    <h1>Banco EL BILLETON</h1>
                    <h5><a href="${pageContext.request.contextPath}/RegistrosNuevos?registro=0">Regresar al perfil</a></h5>
                    <br>
                </div>
            </header>
            <div class="container">
                <c:if test="${success == 1}">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Cuenta bancaria Creada</h5>
                            </div>
                            <div class="modal-body">
                                <p>Se creo con exito la cuenta bancaria el codigo de la cuenta es:</p>
                                <p>Codigo: ${cuenta}</p>
                            </div>
                            <div class="modal-footer">
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/CrearCuentaBancaria?action=1">Crear Otra Cuenta</a>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${success == 2}">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Error creacion cuenta</h5>
                            </div>
                            <div class="modal-body">
                                <p></p>
                                <p>${errores}</p>
                            </div>
                            <div class="modal-footer">
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/CrearCuentaBancaria?action=1">Regresar al formulario de creacion de cuenta</a>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${success == 3}">
                    <c:if test="${noData ==0}">
                        <br/>
                        <div class="alert alert-warning" role="alert">
                            No se encontro un cliente con el codigo que ingreso
                        </div>
                    </c:if>
                    <div class="container">
                        <br>
                        <h5>Introduce el codigo para la busqueda del cliente</h5>
                    </div>
                    <br>
                    <form class="form-inline" action="CrearCuentaBancaria" method="GET">
                        <label class="control-label col-md-2" for="codigoEntidad">Codigo del cliente: </label>
                        <div class="form-group">
                            <input class="form-control" id="codigoEntidad" type="text" name="codigoEntidad" placeholder="Codigo" required="" >
                        </div>
                        <div class="form-group col-md-2">
                            <button class="btn btn-primary" type="submit" value="2" name="action" id="action" >Buscar</button>
                        </div>
                    </form>
                </c:if>
                <c:if test="${success == 0}">
                    <br>
                    <div class="container">
                        <form class="form" action="CrearCuentaBancaria" method="POST">
                            <div class="form-group row">
                                <p>El cliente con el nombre y codigo presentados solicita una cuenta bancaria</p>
                            </div>
                            <div class="form-group row">
                                <label for="codigoEntidad" class="col-md-2 col-form-label">Codigo Cliente</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="codigoEntidad" name="codigoEntidad" readonly="" value="${entidad.codigo}">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="nombre" class="col-md-2 col-form-label">Nombre</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="nombre" name="nombre" readonly="" value="${entidad.nombre}">
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-md-3">
                                    <button type="submit" class="btn btn-warning">Aprobar Creacion Cuenta</button>
                                </div>
                                <div class="col-md-3">
                                    <a type="button" class="btn btn-dark" href="${pageContext.request.contextPath}/CrearCuentaBancaria?action=1">Regresar</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </c:if>
                <br>
            </div>
        </div>
    </body>
    <%@include file="../CabeceraPie/piePagina.jsp" %>
</html>
