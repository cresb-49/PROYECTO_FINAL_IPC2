<%-- 
    Document   : ReporteGerente6
    Created on : 15/11/2020, 21:18:30
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cajero con mas transacciones</title>
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
                    <h5><a href="${pageContext.request.contextPath}/ControladorSolicitudGerente?reporte=0">Regresar al perfil</a></h5>
                    <br>
                </div>
            </header>
            <c:if test="${success == 0}">
                <div class="container">
                    <br>
                    <h5>Introduce el intervalo de fechas</h5>
                </div>
                <br>
                <div class="container">
                    <form class="form-inline" action="ControladorReporteGerente6" method="POST">
                        <label class="control-label col-md-2" for="fechaMaxima">Fecha Minima: </label>
                        <div class="form-group">
                            <input class="form-control" id="fechaMinima" type="date" max="${fechaMax}" name="fechaMinima" required="">
                        </div>
                        <label class="control-label col-md-2" for="fechaMinima">Fecha Mexima: </label>
                        <div class="form-group">
                            <input class="form-control" id="fechaMaxima" type="date" max="${fechaMax}" name="fechaMaxima" required="">
                        </div>
                        <div class="form-group col-md-2">
                            <button class="btn btn-primary" type="submit" name="buscar" >Buscar</button>
                        </div>
                    </form>
                </div>
                <br>
            </c:if>

            <c:if test="${success == 1}">
                 <div class="container">    
                    <br>
                    <div class="row">
                        <div class="col-md-3">
                            <a class="btn btn-warning" value="1" name="generar" id="generar" href="${pageContext.request.contextPath}/ControladorReporteGerente6?fechaMinima=${fechaMinima}&fechaMaxima=${fechaMaxima}">Exportar Reporte PDF</a>
                        </div>
                        <div class="col-md-6">
                        </div>
                        <div class="col-md-3">
                            <a class="btn btn-info" value="1" name="generar" id="generar" href="${pageContext.request.contextPath}/ControladorSolicitudGerente?reporte=5">Regresar a la ventana anterior</a>
                        </div>
                    </div>
                    <div>
                        <br>
                        <h5>Cajero con mas Transacciones</h5>
                        <br>
                        <p>Entre las fechas ${fechaMinima} y ${fechaMaxima}</p>
                        <br/>
                    </div>
                    <table class="table table-striped table-bordered">
                        <thead class="thead-dark">
                        </thead>
                        <tbody>
                            <tr>
                                <td>Codigo: </td>
                                <td>${cajero.cajero.codigo}</td>
                            </tr>
                            <tr>
                                <td>Nombre: </td>
                                <td>${cajero.cajero.nombre}</td>
                            </tr>
                            <tr>
                                <td>Turno: </td>
                                <td>${cajero.cajero.turno}</td>
                            </tr>
                            <tr>
                                <td>DPI: </td>
                                <td>${cajero.cajero.dpi}</td>
                            </tr>
                            <tr>
                                <td>Sexo: </td>
                                <td>${cajero.cajero.sexo}</td>
                            </tr>
                            <tr>
                                <td>Direccion: </td>
                                <td>${cajero.cajero.direccion}</td>
                            </tr>
                            <tr>
                                <td>Cantidad de transacciones: </td>
                                <td>${cajero.cantidad}</td>
                            </tr>
                        </tbody>
                    </table>
                    <br>
                </div>
            </c:if>
               
                    
            <c:if test="${success == 2}">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Error:</h5>
                        </div>
                        <div class="modal-body">
                            <p></p>
                            <p>${error}</p>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/ControladorSolicitudGerente?reporte=0">Regresar al perfil</a>
                        </div>
                    </div>
                </div>
            </c:if>


            <c:if test="${success == 3}">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Error:</h5>
                        </div>
                        <div class="modal-body">
                            <p></p>
                            <p>${error}</p>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/ControladorSolicitudGerente?reporte=6">Volver al formulario</a>
                        </div>
                    </div>
                </div>
            </c:if>

            <%@include file="../../CabeceraPie/piePagina.jsp" %>
        </div>
    </body>
</html>
