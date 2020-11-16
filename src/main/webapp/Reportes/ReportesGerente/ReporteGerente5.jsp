<%-- 
    Document   : ReporteGerente5
    Created on : 15/11/2020, 19:44:04
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Historial Transacciones Cliente</title>
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
                    <h5>Introduce el codigo para la busqueda del cliente</h5>
                </div>
                <br>
                <div class="container">
                    <form class="form-inline" action="#" method="POST">
                        <label class="control-label col-md-2" for="codigoEntidad">Codigo del cliente: </label>
                        <div class="form-group">
                            <input class="form-control" id="codigoEntidad" type="number" name="codigoEntidad" placeholder="Codigo" required="">
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
                            <form action="ControladorReporte1" method="POST">
                                <button class="btn btn-warning" value="${codeCuenta}" name="codCuenta" id="codCuenta" type="submit">Exportar Reporte PDF</button>
                            </form>    
                        </div>
                        <div class="col-md-6">
                        </div>
                        <div class="col-md-3">
                            <a class="btn btn-info" value="1" name="generar" id="generar" href="${pageContext.request.contextPath}/ControladorSolicitudGerente?reporte=5">Regresar a la ventana anterior</a>
                        </div>
                    </div>
                    <div>
                        <br>
                        <h5>Historial de transacciones del cliente</h5>
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

            <%@include file="../../CabeceraPie/piePagina.jsp" %>
        </div>
    </body>
</html>
