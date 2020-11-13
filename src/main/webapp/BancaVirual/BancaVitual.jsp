<%-- 
    Document   : BancaVitual
    Created on : 12/11/2020, 15:16:48
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Banca Virtual</title>
    </head>
    <body>
        <div class="main">
            <header>
                <div class="container">
                    <br>
                    <h1>BANCA VIRTUAL EL BILLETON</h1>
                    <h5><a href="${pageContext.request.contextPath}/AccionCliente?action=0">Regresar al perfil</a></h5>
                    <br>
                    
                </div>
            </header>
            <c:if test="${success == 1}">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Transaccion Realizada</h5>
                        </div>
                        <div class="modal-body">
                            <p>Datos de la transaccion realizada:</p>
                            <p>----------------------------------</p>
                            <p>Codigo de Transaccion: ${transaccion.codigo}</p>
                            <p>Cuenta Destino: ${transaccion.idCuenta}</p>
                            <p>Fecha: ${transaccion.fechaTransaccion}</p>
                            <p>Hora: ${transaccion.hora}</p>
                            <p>Tipo: ${transaccion.tipo}</p>
                            <p>Monto: ${transaccion.monto}</p>
                            <p>Cajero: ${transaccion.idCajero}</p>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/AccionCajero?action=2">Realizar otro Retiro</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${success == 2}">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Error Banca Virtual:</h5>
                        </div>
                        <div class="modal-body">
                            <p></p>
                            <p>${errores}</p>
                        </div>
                        <div class="modal-footer">
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${success == 0}">
                <div class="container">
                    <br>
                    <form>
                        <div>
                            <br>
                            <h5>Cuentas Disponibles</h5>
                            <br>
                        </div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Codigo de Cuenta</th>
                                    <th scope="col">Saldo Disponible (Q.)</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${cuentas}" var="cuenta">
                                    <tr>
                                        <td>${cuenta.codigo}</td>
                                        <td>${cuenta.credito}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <br>
                        <div class="form-group">
                            <label for="exampleFormControlSelect1">Seleccione la cuenta para retirar:</label>
                            <select class="form-control" id="exampleFormControlSelect1">
                                <option>Seleccionar</option>
                                <c:forEach items="${cuentas}" var="cuenta">
                                    <option>${cuenta.codigo}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <br>
                            <h5>Seleccione Cuenta de destino</h5>
                            <br>
                        </div>
                        <div class="form-group">
                            <label for="exampleFormControlSelect1">Cuenta Propia: </label>
                            <select class="form-control" id="exampleFormControlSelect1">
                                <option>Seleccionar</option>
                                <c:forEach items="${cuentas}" var="cuenta">
                                    <option>${cuenta.codigo}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="exampleFormControlSelect1">Cuenta Asociada: </label>
                            <select class="form-control" id="exampleFormControlSelect1">
                                <option>Seleccionar</option>
                                <c:forEach items="${cuentasAsociadas}" var="cuenta">
                                    <option>${cuenta.codigo}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                    <br>
                </div>
            </c:if>
            <%@include file="../CabeceraPie/piePagina.jsp" %>
        </div>
    </body>
</html>
