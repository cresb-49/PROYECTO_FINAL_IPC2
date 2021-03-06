<%-- 
    Document   : RegistrarGerente
    Created on : 10/11/2020, 13:09:26
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Gerente</title>
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
                                <h5 class="modal-title" id="exampleModalLabel">Codigo generado</h5>
                            </div>
                            <div class="modal-body">
                                <p>El codigo generado para el gerente es el siguiente:</p>
                                <p>${genCode}</p>
                            </div>
                            <div class="modal-footer">
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/RegistrosNuevos?registro=3">Regresar registro de Gerente</a>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${success == 2}">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Errores de registro:</h5>
                            </div>
                            <div class="modal-body">
                                <p></p>
                                <p>${errores}</p>
                            </div>
                            <div class="modal-footer">
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/RegistrosNuevos?registro=3">Regresar registro de Gerente</a>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${success == 0}">
                    <div class="container">
                        <br>
                        <h3>Informacion del Gerente</h3>
                    </div>
                    <br>
                    <form class="container form-group" action="RegistroGerente" onsubmit="return validarTabajador();" method="POST">
                        <div class="form-row form-group">
                            <div class="container form-group col-md-6">
                                <div class="form-group">
                                    <label for="nombreEntidad" class="control-label">Nombre: </label>
                                    <div class="">
                                        <input class="form-control" id="nombreEntidad" type="text" name="nombreEntidad" placeholder="Nombre" required="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="sexo">Sexo: </label>
                                    <div class="">
                                        <select class="form-control" name="sexo" id="sexo">
                                            <option value="Seleccionar" selected>Seleccionar</option>
                                            <option value="Masculino">Masculino</option>
                                            <option value="Femenino">Femenino</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="direccion">Direccion: </label>
                                    <div class="">
                                        <input class="form-control" id="direccion" type="text" name="direccion" placeholder="Direccion"required="">
                                    </div>
                                </div>
                            </div>
                            <div class="container form-group col-md-6">
                                <div class="form-group">
                                    <label class="control-label" for="numeroDPI">DPI: </label>
                                    <div class="">
                                        <input class="form-control" id="numeroDPI" type="number" name="numeroDPI" placeholder="DPI" required="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="TipoTurno">Turno: </label>
                                    <div class="">
                                        <select class="form-control" name="TipoTurno" id="TipoTurno">
                                            <option value="Seleccionar" selected>Seleccionar</option>
                                            <option value="MATUTINO">Matutino</option>
                                            <option value="VESPERTINO">Vespertino</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="container form-group col-md-6">
                                <div class="form-group">
                                    <label class="control-label" for="passInicial">Password: </label>
                                    <div class="">
                                        <input class="form-control" id="passInicial" type="password" name="passInicial" required="">
                                    </div>
                                </div>
                            </div>
                            <div class="container form-group col-md-6">
                                <div class="form-group">
                                    <label class="control-label" for="passwordComparacion"> Confirmar Password: </label>
                                    <div class="">
                                        <input class="form-control" id="passwordComparacion" type="password" name="passwordComparacion" required="">
                                    </div>
                                </div>
                            </div>
                            <div class="container form-group col-md-12">
                                <div class="container" >
                                    <div class="form-group">
                                        <button class="btn btn-success" type="submit" name="ingresar" value="Ingresar">Registrar Gerente</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>   
                </c:if>
            </div>
            <%@include file="../CabeceraPie/piePagina.jsp" %>
        </div>
        <script src="${pageContext.request.contextPath}/Resources/js/validarFormulariosRegistro.js"></script>
    </body>
</html>
