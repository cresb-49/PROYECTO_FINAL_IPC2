<%-- 
    Document   : ReporteGerente3
    Created on : 15/11/2020, 14:31:23
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>10 clientes con mas deinero</title>
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
                    <br>
                    <div class="row">
                        <div class="col-md-3">
                            <h5>Clientes</h5>
                        </div>
                        <div class="col-md-6">
                        </div>
                        <div class="col-md-3">
                            <a class="btn btn-warning" value="1" name="generar" id="generar" href="${pageContext.request.contextPath}/ControladorReporteGerente3?generar=1">Exportar Reporte PDF</a>
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
                                <th scope="col">Cantida de Dinero</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${clientes}" var="cliente">
                                <tr>
                                    <td>${cliente.cliente.codigo}</td>
                                    <td>${cliente.cliente.nombre}</td>
                                    <td>${cliente.cliente.dpi}</td>
                                    <td>${cliente.cliente.direccion}</td>
                                    <td>${cliente.cantidad}</td>
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
            <c:if test="${success == 1}">
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
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/ControladorReporteGerente3">Volver a la ventana anterior</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <%@include file="../../CabeceraPie/piePagina.jsp" %>
        </div>
    </body>
</html>
