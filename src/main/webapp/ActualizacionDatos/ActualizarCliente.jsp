<%-- 
    Document   : ActualizarCliente
    Created on : 11/11/2020, 19:47:59
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Atualizar Datos Cliente</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/css/presentacion.css"/>
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
                    <h5><a href="${pageContext.request.contextPath}/RegistrosNuevos?registro=0">Regresar al perfil</a></h5>
                    <br>
                </div>
            </header>

            <div class="container">
                <c:if test="${success == 1}">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Modificacion de Datos</h5>
                            </div>
                            <div class="modal-body">
                                <p>Se ha modificado con exito el cliente seleccionado</p>
                            </div>
                            <div class="modal-footer">
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/ActualizarDatos?actualizar=1">Modificar Otro Cliente</a>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${success == 2}">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Error de modificacion</h5>
                            </div>
                            <div class="modal-body">
                                <p></p>
                                <p>${errores}</p>
                            </div>
                            <div class="modal-footer">
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/ActualizarDatos?actualizar=1">Regresar al formulario de modificacion</a>
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:if test="${success == 0}">
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
                    <form class="form-inline" action="ActualizarDatosCliente" method="GET">
                        <label class="control-label col-md-2" for="codigoEntidad">Codigo del cliente: </label>
                        <div class="form-group">
                            <input class="form-control" id="codigoEntidad" type="text" name="codigoEntidad" placeholder="Codigo" required="">
                        </div>
                        <div class="form-group col-md-2">
                            <button class="btn btn-primary" type="submit" name="buscar" >Buscar</button>
                        </div>
                    </form>
                    <br>
                    <form class="container form-group" action="ActualizarDatosCliente" onsubmit="return validarAcualizacionCliente();" method="POST">
                        <div class="form-row form-group">
                            <div class="container form-group col-md-6">
                                <div class="form-group">
                                    <label for="nombreEntidad" class="control-label">Nombre: </label>
                                    <div class="">
                                        <input class="form-control" id="nombreEntidad" type="text" name="nombreEntidad" placeholder="Nombre" required="" value="${entidad.nombre}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="sexo">Sexo: </label>
                                    <div class="">
                                        <select class="form-control" name="sexo" id="sexo">
                                            <c:choose>
                                                <c:when test="${entidad.sexo == 'Masculino'}">
                                                    <option value="Seleccionar" >Seleccionar</option>
                                                    <option value="Masculino" selected>Masculino</option>
                                                    <option value="Femenino">Femenino</option>
                                                </c:when>
                                                <c:when test="${entidad.sexo == 'Femenino'}">
                                                    <option value="Seleccionar" >Seleccionar</option>
                                                    <option value="Masculino">Masculino</option>
                                                    <option value="Femenino" selected>Femenino</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="Seleccionar" selected>Seleccionar</option>
                                                    <option value="Masculino">Masculino</option>
                                                    <option value="Femenino">Femenino</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="direccion">Direccion: </label>
                                    <div class="">
                                        <input class="form-control" id="direccion" type="text" name="direccion" placeholder="Direccion" value="${entidad.direccion}" required="">
                                    </div>
                                </div>
                            </div>
                            <div class="container form-group col-md-6">
                                <div class="form-group">
                                    <label class="control-label" for="numeroDPI">DPI: </label>
                                    <div class="">
                                        <input class="form-control" id="numeroDPI" type="number" name="numeroDPI" placeholder="DPI" value="${entidad.dpi}" required="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="fechaNacimiento">Fecha de Nacimiento:</label>
                                    <div class="">
                                        <input class="form-control" id="fechaNacimiento" type="date" name="fechaNacimiento" placeholder="Fecha" value="${entidad.fechaNacimiento}" required="">
                                    </div>
                                </div>
                            </div>
                            <div class="container form-group col-md-12">
                                <div class="container" >
                                    <div class="form-group">
                                        <button class="btn btn-danger" type="submit" name="ingresar" value="Ingresar">Modificar Cliente</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </c:if>
            </div>
            <%@include file="../CabeceraPie/piePagina.jsp" %>
        </div>
        <script src="${pageContext.request.contextPath}/Resources/js/validarActulizacionEntidades.js"></script>
    </body>
</html>
