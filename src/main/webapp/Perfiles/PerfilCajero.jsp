<%-- 
    Document   : PerfilCajero
    Created on : 10/11/2020, 10:54:12
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cajero</title>
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
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/AccionCajero?action=1">Transaccion Bancaria</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/AccionCajero?action=2">Retiro Bancario</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="true">Reportes</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/SolicitudReporteCajero?reporte=1">Listado de depositos y retiros del turno</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/SolicitudReporteCajero?reporte=2">Listado de transacciones en un intervalo de tiempo</a>
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
