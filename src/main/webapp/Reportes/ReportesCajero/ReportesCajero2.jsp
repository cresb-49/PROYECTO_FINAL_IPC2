<%-- 
    Document   : ReportesCajero2
    Created on : 14/11/2020, 01:44:45
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de transaciones en un intervalo de tiempo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/css/presentacion.css"/>
        <script src="${pageContext.request.contextPath}/Resources/js/jquery-3.5.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/Resources/js/bootstrap.min.js"></script>
    </head>
    <body>
        <header>
            <div class="container">
                <br>
                <h1>Banco EL BILLETON</h1>
                <h5><a href="${pageContext.request.contextPath}/SolicitudReporteCajero?reporte=0">Regresar al perfil</a></h5>
                <br>
            </div>
        </header>
        <div class="main">
            <c:if test="${success == 1}">
                <div class="container">
                    <br/>
                    <form action="ControladorReporteCajero2" method="POST">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="diaBusqueda">Seleccione un dia</label>
                                <input type="date" class="form-control" max="${diaCurso}" id="diaBusqueda" name="diaBusqueda" required="">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="horaInical">Hora Inicial</label>
                                <input type="time" min="${initTime}" max="${endTime}" class="form-control" id="horaInical" name="horaInical" required="">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="horaFinal">Hora Final</label>
                                <input type="time" min="${initTime}" max="${endTime}" class="form-control" id="horaFinal" name="horaFinal" required="">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Generar Reporte</button>
                    </form>
                </div>
                <br/>
            </c:if>
            <c:if test="${success == 0}">
                <div class="container">
                    <br/>
                    <div class="row">
                        <div class="col-md-3">
                            <a class="btn btn-warning" href="${pageContext.request.contextPath}/ControladorReporteCajero2?generar=1&diaBusqueda=${diaBusqueda}&horaInical=${horaInical}&horaFinal=${horaFinal}" name="generar" id="generar">Exportar Reporte PDF</a>
                        </div>
                    </div>
                    <div>
                        <br>
                        <h5>Depositos y Retiros procesado en el turno</h5>
                        <br>
                    </div>
                    <table class="table table-striped table-bordered">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Codigo de Transaccion</th>
                                <th scope="col">Fecha</th>
                                <th scope="col">Hora</th>
                                <th scope="col">Tipo</th>
                                <th scope="col">Monto</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${transacciones}" var="transaccion">
                                <tr>
                                    <td>${transaccion.codigo}</td>
                                    <td>${transaccion.fechaTransaccion}</td>
                                    <td>${transaccion.hora}</td>
                                    <td>${transaccion.tipo}</td>
                                    <td>${transaccion.monto}</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td class="table-info text-center" colspan="4">BALANCE DE CAJA</td>
                                <td>${balance}</td>
                            </tr>
                        </tbody>
                    </table>
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
                            <p>${errores}</p>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/SolicitudReporteCajero?reporte=2">Regresar al formulario</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${success == 3}">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Fatal Error:</h5>
                        </div>
                        <div class="modal-body">
                            <p></p>
                            <p>${errores}</p>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/SolicitudReporteCajero?reporte=0">Regresar al perfil</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${success == 5}">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Informacion:</h5>
                        </div>
                        <div class="modal-body">
                            <p></p>
                            <p>${info}</p>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/SolicitudReporteCajero?reporte=0">Regresar al perfil</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <%@include file="../../CabeceraPie/piePagina.jsp" %>
        </div>
    </body>
</html>
