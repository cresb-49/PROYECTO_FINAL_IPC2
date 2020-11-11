<%-- 
    Document   : CabeceraPerfil
    Created on : 10/11/2020, 10:54:39
    Author     : carlo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/css/presentacion.css"/>
    </head>
    <body>
        <header>
            <div class="container">
                <br/>
                <h1>Banco EL BILLETON</h1>
                <h5>BIENVENIDO: ${USER.codigo}</h5>
                <br/>
            </div>
        </header>    
    </body>
</html>
