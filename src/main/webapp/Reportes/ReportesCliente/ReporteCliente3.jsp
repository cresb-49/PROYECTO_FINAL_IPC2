<%-- 
    Document   : ReporteCliente3
    Created on : 13/11/2020, 16:19:46
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cuenta con mas dinero y transacciones</title>
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
                    <h5><a href="${pageContext.request.contextPath}/AccionCliente?action=0">Regresar al perfil</a></h5>
                    <br>

                </div>
            </header>
            <c:if test="${success == 0}">
                <div class="container">
                    <br>
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Cuenta con mayor dinero</h5>
                            </div>
                            <div class="modal-body">
                                <p>Codigo: ${cuenta.codigo}</p>
                                <p>Credito: ${cuenta.credito}</p>
                                <p>Fecha de apertura: ${cuenta.fechaApertura}</p>

                            </div>
                            <div class="modal-content container">
                                <br>
                                <p>Establecer Fecha de inicio de consulta</p>
                                <form class="form-inline" action="ControladorReporte3" method="GET">
                                    <div>
                                        <input style="display:none" value="${cuenta.codigo}" class="form-control mb-2 mr-sm-2" id="codigoCuenta" name="codigoCuenta">
                                        <input type="date" class="form-control mb-2 mr-sm-2" max="${fechaLimite}" id="fechaInferior" name="fechaInferior" required="">
                                        <button type="submit" class="btn btn-primary mb-2">Realizar Reporte</button>
                                    </div>
                                </form>
                                <br>
                            </div>
                        </div>
                    </div>
                    <br>
                </div>
            </c:if>
            <c:if test="${success == 1}">
                <div class="container">
                    <br>
                    <div class="row">
                        <div class="col-md-3">
                            <form action="ControladorReporte3" method="POST">
                                <button class="btn btn-warning" value="${codeCuenta}" name="codCuenta" id="codCuenta" type="submit">Exportar Reporte PDF</button>
                                <input style="display:none" value="${inferior}" id="infe" name="infe"/>
                            </form>    
                        </div>
                        <div class="col-md-6">
                        </div>
                        <div class="col-md-3">
                            <a class="btn btn-outline-info" href="${pageContext.request.contextPath}/ControladorReporte3">Regresar Ventana Anterior</a>
                        </div>
                    </div>
                    <div>
                        <br>
                        <h5>Transacciones de la Cuenta: ${codeCuenta}</h5>
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
                                <th scope="col">Codigo Cajero Intemediario</th>
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
                                    <td>${transaccion.idCajero}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <br>
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
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/AccionCliente?action=0">Regresar al perfil</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${success == 4}">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Error Reporte:</h5>
                        </div>
                        <div class="modal-body">
                            <p></p>
                            <p>${errores}</p>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/ControladorReporte3">Regresar a la pagina del reporte</a>
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
                            <a  class="btn btn-primary" href="${pageContext.request.contextPath}/AccionCliente?action=0">Regresar al perfil</a>
                        </div>
                    </div>
                </div>
            </c:if>



        </div>
    </body>
</html>
