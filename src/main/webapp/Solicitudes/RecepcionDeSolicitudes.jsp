<%-- 
    Document   : RecepcionDeSolicitudes
    Created on : 12/11/2020, 20:34:38
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recepcion de Solicitudes</title>
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
                    <h5><a href="${pageContext.request.contextPath}/AccionCliente?action=0">Regresar al perfil</a></h5>
                    <br>
                </div>
            </header>
            <c:if test="${success==0}">
                <div class="container">
                    <div>
                        <br>
                        <h5>Ingreso de Solicitudes</h5>
                        <br>
                    </div>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Id de solicitud</th>
                                <th scope="col">Codigo de Cuenta</th>
                                <th scope="col">Codigo Cliente Solicitante</th>
                                <th scope="col">Aceptar/Rechazar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${solicitudes}" var="solicitud">
                                <tr>
                                    <td>${solicitud.id}</td>
                                    <td>${solicitud.idCuenta}</td>
                                    <td>${solicitud.idClienteSolicitante}</td>
                                    <td><a href="AceptarRechazarSolicitud?action=1&id=${solicitud.id}">Aceptar</a>/<a href="AceptarRechazarSolicitud?action=2&id=${solicitud.id}">Rechazar</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <br>
                </div>
            </c:if>

            <c:if test="${success==1}">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Error en Solicitud</h5>
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
            <c:if test="${success==2}">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Accion Solicitud</h5>
                        </div>
                        <div class="modal-body">
                            <p></p>
                            <p>${mensaje}</p>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/AccionCliente?action=3">Regresar a recepcion de solicitudes</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${success==3}">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Mensaje</h5>
                        </div>
                        <div class="modal-body">
                            <p></p>
                            <p>No has recibido ninguna solicitud de asociacion</p>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/AccionCliente?action=0">Regresar al perfil</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <%@include file="../CabeceraPie/piePagina.jsp" %>
        </div>
    </body>
</html>
