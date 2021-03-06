<%-- 
    Document   : PerfilGerente
    Created on : 10/11/2020, 10:54:00
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gerente</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/css/bootstrap.min.css"/>
        <script src="${pageContext.request.contextPath}/Resources/js/jquery-3.5.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/Resources/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file="../CabeceraPie/CabeceraPerfil.jsp" %>
        <div class="container">
            <ul class="nav nav-tabs nav-fill">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="true">Actuazalizacion de datos</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ActualizarDatos?actualizar=1">Actualizacion Cliente</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ActualizarDatos?actualizar=2">Actualizacion Cajero</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ActualizarDatos?actualizar=3">Actualizar Mis Datos</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="true">Registro</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/RegistrosNuevos?registro=1">Registrar Cliente</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/RegistrosNuevos?registro=2">Registrar Cajero</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/RegistrosNuevos?registro=3">Registrar Gerente</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/CrearCuentaBancaria?action=1">Crear Cuenta Bancaria</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="true">Reportes</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ControladorSolicitudGerente?reporte=1">Clientes con transacciones mayores a un limite</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ControladorSolicitudGerente?reporte=2">Clientes con transacciones sumadas mayores a un limite</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ControladorSolicitudGerente?reporte=3">Los 10 clientes con mas dinero</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ControladorSolicitudGerente?reporte=5">Historial Transacciones Cliente</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ControladorSolicitudGerente?reporte=6">Cajero con mas transacciones en intervalo de tiempo</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/Logout">Cerrar sesion</a>
                </li>
            </ul>
        </div>
        <c:if test="${success == 0}">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Error de Accion:</h5>
                    </div>
                    <div class="modal-body">
                        <p></p>
                        <p>Solo puedes realizar esta accion en tu horario de trabajo</p>
                    </div>
                    <div class="modal-footer">
                    </div>
                </div>
            </div>
        </c:if>
        <%@include file="../CabeceraPie/piePagina.jsp" %>
    </body>
</html>
