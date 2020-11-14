package com.mycompany.proyecto_final.Controladores.ControladoresTransacciones;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.protobuf.TextFormat;
import com.mycompany.proyecto_final.Conversiones.ConversionesVariables;
import com.mycompany.proyecto_final.Entidades.CuentaBancaria;
import com.mycompany.proyecto_final.Entidades.Transaccion;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelCuentaBancaria;
import com.mycompany.proyecto_final.Models.ModelTransaccion;
import com.mycompany.proyecto_final.Transaccion.LogicaTransaccion;

@WebServlet("/AbonoCuenta")
public class ControladorAbonoBancario extends HttpServlet {
    private ConversionesVariables conv = new ConversionesVariables();
    private ModelCuentaBancaria modelCuentaBancaria = new ModelCuentaBancaria();
    private LogicaTransaccion logicaTransaccion = new LogicaTransaccion();
    private ModelTransaccion modelTransaccion = new ModelTransaccion();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        }else{

            String numeroCuenta = req.getParameter("numeroCuenta");
            String monto = req.getParameter("montoDepositar");
    
            System.out.println("Datos de cuenta recibidos: numeroCuenta=" + numeroCuenta + ", monto=" + monto);
    
            UsuarioDeSistema usuarioDeSistema = (UsuarioDeSistema) req.getSession().getAttribute("USER");
    
            java.time.LocalDate today = java.time.LocalDate.now();
            java.time.LocalTime hora = java.time.LocalTime.now();
    
            String time = hora.getHour() + ":" + hora.getMinute() + ":" + hora.getSecond();
            Transaccion transaccion = new Transaccion(null, this.conv.stringToLong(numeroCuenta),
                    this.conv.stringToDate(today.toString()), time, this.conv.stringToDouble(monto),
                    usuarioDeSistema.getCodigo(), "CREDITO");
    
            System.out.println("Transferencia creada: " + transaccion.toString());
    
            try {
                CuentaBancaria cuenta = modelCuentaBancaria.BuscarCuenta(numeroCuenta);
                if (cuenta == null) {
                    req.setAttribute("success", 1);
                    req.setAttribute("errores", "No existe una cuenta con el numero de cuenta introducido");
                    req.getRequestDispatcher("/Transacciones/AbonoBancario.jsp").forward(req, resp);
                } else {
                    System.out.println("Cuenta recuperada: " + cuenta.toString());
                    logicaTransaccion.Credito(transaccion, cuenta);
                    Long codigoTransaccion = modelTransaccion.RegistrarTransaccion(transaccion);
                    if (codigoTransaccion == -1) {
                        req.setAttribute("success", 1);
                        req.setAttribute("errores", "No se pudo registar la transaccion");
                        req.getRequestDispatcher("/Transacciones/AbonoBancario.jsp").forward(req, resp);
                    } else {
                        transaccion.setCodigo(codigoTransaccion);
                        modelCuentaBancaria.ActualizarCuenta(cuenta);
                        req.setAttribute("success", 2);
                        req.setAttribute("transaccion", transaccion);
                        req.getRequestDispatcher("/Transacciones/AbonoBancario.jsp").forward(req, resp);
                    }
    
                }
    
            } catch (SQLException e) {
                System.out.println("Error get AbonoCuenta en busqueda de cuenta: " + e.getMessage());
                req.setAttribute("success", 1);
                req.setAttribute("errores", e.getMessage());
                req.getRequestDispatcher("/Transacciones/AbonoBancario.jsp").forward(req, resp);
                e.printStackTrace();
            }
        }
    }

}
