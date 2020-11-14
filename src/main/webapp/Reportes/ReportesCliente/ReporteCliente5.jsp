<%-- 
    Document   : ReporteCliente5
    Created on : 13/11/2020, 18:20:14
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Historial de Solicitudes Realizadas</title>
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
                <h5><a href="${pageContext.request.contextPath}/AccionCliente?action=0">Regresar al perfil</a></h5>
                <br>

            </div>
        </header>
        <div class="main">
            <c:if test="${success == 0}">
                <div class="container">
                    <div>
                        <br>
                        <h5>Historial de Solicitudes Realizadas</h5>
                        <br>
                    </div>
                    <div class="container">
                        <form action="ControladorReporte5" method="POST">
                            <button class="btn btn-warning" name="codCuenta" id="codCuenta" type="submit">Exportar Reporte PDF</button>
                        </form> 
                    </div>
                    <br/>
                    <table class="table table-striped table-bordered">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">ID Solicitud</th>
                                <th scope="col">Cuenta Solicitada</th>
                                <th scope="col">Codigo Cliente Propietario</th>
                                <th scope="col">Estado</th>
                                <th scope="col">Intento</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${solicitudes}" var="solicitud">
                                <tr>
                                    <td>${solicitud.id}</td>
                                    <td>${solicitud.idCuenta}</td>
                                    <td>${solicitud.idClientePropietario}</td>
                                    <td>${solicitud.estado}</td>
                                    <td>${solicitud.intento}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
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
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/AccionCliente?action=0">Regresar al perfil</a>
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
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/AccionCliente?action=0">Regresar al perfil</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <%@include file="../../CabeceraPie/piePagina.jsp" %>
        </div>
    </body>
</html>
