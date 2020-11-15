<%-- 
    Document   : ResultadoCargaDatos
    Created on : 14/11/2020, 22:58:20
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado de Carga de Datos</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/css/bootstrap.min.css"/>
        <script src="${pageContext.request.contextPath}/Resources/js/jquery-3.5.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/Resources/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="main">
            <%@include file="../CabeceraPie/Cabecera.jsp" %>

            <c:if test="${resultado == 0}">
                <br/>
                <div class="container">
                    <div class="alert alert-success" role="alert">
                        <div class="container">
                            TODOS LOS DATOS SE IMPORTARON DE MANERA CORRECTA!!!
                        </div>
                        <br/>
                        <div class="container">
                            <a class="btn btn-success" href="${pageContext.request.contextPath}/index.jsp">Regresar a Inicio de Sesion</a>
                        </div>

                    </div>
                </div>
            </c:if>
            <c:if test="${resultado == 1}">
                <br/>
                <div class="container">
                    <div class="alert alert-danger" role="alert">

                        <br/>
                        <div class="container">
                            <h5>Errores con Clientes</h5>
                            <c:forEach items="${erCliente}" var="error">
                                <p>${error}</p>
                            </c:forEach>
                        </div>

                        <br/>
                        <div class="container">
                            <h5>Errores con Gerentes</h5>
                            <c:forEach items="${erGerente}" var="error">
                                <p>${error}</p>
                            </c:forEach>
                        </div>

                        <br/>
                        <div class="container">
                            <h5>Errores con Cajeros</h5>
                            <c:forEach items="${erCajero}" var="error">
                                <p>${error}</p>
                            </c:forEach>
                        </div>

                        <br/>
                        <div class="container">
                            <h5>Errores con Transacciones</h5>
                            <c:forEach items="${erTransacciones}" var="error">
                                <p>${error}</p>
                            </c:forEach>
                        </div>
                        <br/>
                        <div class="container">
                            <a class="btn btn-success" href="${pageContext.request.contextPath}/index.jsp">Regresar a Inicio de Sesion</a>
                        </div>
                    </div>
                </div>
            </c:if>
            <%@include file="../CabeceraPie/piePagina.jsp" %>
        </div>
    </body>
</html>
