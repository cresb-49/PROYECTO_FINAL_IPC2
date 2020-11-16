<%-- 
    Document   : ReporteGerente1
    Created on : 15/11/2020, 15:58:01
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transacciones mayores a un limite</title>
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
                    <h5><a href="${pageContext.request.contextPath}/ControladorSolicitudGerente?reporte=0">Regresar al perfil</a></h5>
                    <br>
                </div>
            </header>
            <c:if test="${success == 0}">
                <div class="container">
                    <br/>
                    <div>
                        <h5>Ingrese el limite de la consulta</h5>
                    </div>
                    <form action="ControladorReporteGerente1" onsubmit="return validarConsulta1()" method="POST">
                        <div class="form-row align-items-center">
                            <label class="form-check-label" for="limiteMonetario">Limite monetario para la busqueda  : </label>
                            <div class="col-sm-2 my-1">
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">Q.</div>
                                    </div>
                                    <input type="text" class="form-control" id="limiteMonetario" name="limiteMonetario" required="">
                                </div>
                            </div>
                            <div class="col-auto my-1">
                                <button type="submit" class="btn btn-success">Generar Reporte</button>
                            </div>
                        </div>
                    </form>
                    <br/>
                </div>
            </c:if>
            <c:if test="${success == 1}">
                <div class="container">
                    <br>
                    <div class="row">
                        <div class="col-md-3">
                            <h5>Clientes Encontrados</h5>
                        </div>
                        <div class="col-md-6">
                            <a class="btn btn-warning" value="1" name="generar" id="generar" href="${pageContext.request.contextPath}/ControladorReporteGerente1?limiteMonetario=${limiteMonetario}">Exportar Reporte PDF</a>
                        </div>
                        <div class="col-md-3">
                            <a class="btn btn-info" value="1" name="generar" id="generar" href="${pageContext.request.contextPath}/ControladorSolicitudGerente?reporte=1">Regresar a la ventana anterior</a>
                        </div>
                    </div>
                    <br/>
                    <table class="table table-striped table-bordered">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Codigo Cliente</th>
                                <th scope="col">Nombre Cliente</th>
                                <th scope="col">DPI Cliente</th>
                                <th scope="col">Direccion Cliente</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${clientes}" var="cliente">
                                <tr>
                                    <td>${cliente.codigo}</td>
                                    <td>${cliente.nombre}</td>
                                    <td>${cliente.dpi}</td>
                                    <td>${cliente.direccion}</td>
                                </tr>
                            </c:forEach>
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
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/ControladorSolicitudGerente?reporte=1">Volver al formulario</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <%@include file="../../CabeceraPie/piePagina.jsp" %>
        </div>
        <script src="${pageContext.request.contextPath}/Resources/js/validarConsultaGerente.js"></script>
    </body>
</html>
