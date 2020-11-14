package com.mycompany.proyecto_final.Controladores.ControladoresTransacciones;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.proyecto_final.Conversiones.ConversionesVariables;
import com.mycompany.proyecto_final.Entidades.CuentaBancaria;
import com.mycompany.proyecto_final.Entidades.Transaccion;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Entidades.Cliente;
import com.mycompany.proyecto_final.Models.ModelCliente;
import com.mycompany.proyecto_final.Models.ModelCuentaBancaria;
import com.mycompany.proyecto_final.Models.ModelTransaccion;
import com.mycompany.proyecto_final.Transaccion.LogicaTransaccion;

@WebServlet("/RetiroDeCuenta")
public class ControladorRetiroBancario extends HttpServlet {
    private ConversionesVariables conv = new ConversionesVariables();
    private ModelCliente modelCliente = new ModelCliente();
    private ModelCuentaBancaria modelCuentaBancaria = new ModelCuentaBancaria();
    private LogicaTransaccion logicaTransaccion = new LogicaTransaccion();
    private ModelTransaccion modelTransaccion = new ModelTransaccion();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        }else{

            String numeroCuenta = req.getParameter("numeroCuenta");
            String numeroDPI = req.getParameter("numeroDPI");
            String monto = req.getParameter("montoDebitar");
    
            System.out.println(
                    "Datos de cuenta recibidos: numeroCuenta=" + numeroCuenta + ", DPI=" + numeroDPI + ", monto=" + monto);
    
            UsuarioDeSistema usuarioDeSistema = (UsuarioDeSistema) req.getSession().getAttribute("USER");
    
            java.time.LocalDate today = java.time.LocalDate.now();
            java.time.LocalTime hora = java.time.LocalTime.now();
            String time = hora.getHour() + ":" + hora.getMinute() + ":" + hora.getSecond();
            Transaccion transaccion = new Transaccion(null, this.conv.stringToLong(numeroCuenta),
                    this.conv.stringToDate(today.toString()), time, this.conv.stringToDouble(monto),
                    usuarioDeSistema.getCodigo(), "DEBITO");
    
            System.out.println("Transferencia creada: " + transaccion.toString());
    
            try {
                CuentaBancaria cuenta = modelCuentaBancaria.BuscarCuenta(numeroCuenta);
                if (cuenta == null) {
                    req.setAttribute("success", 1);
                    req.setAttribute("errores", "No existe una cuenta con el numero de cuenta introducido");
                    req.getRequestDispatcher("/Transacciones/RetiroBancario.jsp").forward(req, resp);
                } else {
                    Cliente cliente = modelCliente.ObtenerCliente(cuenta.getIdCliente().toString());
                    if (!(numeroDPI.equals(cliente.getDpi()))) {
                        req.setAttribute("success", 1);
                        req.setAttribute("errores", "El numero de DPI con coincide con el propietario de la cuenta");
                        req.getRequestDispatcher("/Transacciones/RetiroBancario.jsp").forward(req, resp);
                    } else {
                        if (transaccion.getMonto() > cuenta.getCredito()) {
                            req.setAttribute("success", 1);
                            req.setAttribute("errores",
                                    "No puede retirnar la catidad ingresada supera el saldo actual.\nSaldo disponible: "
                                            + cuenta.getCredito());
                            req.getRequestDispatcher("/Transacciones/RetiroBancario.jsp").forward(req, resp);
                        } else {
                            logicaTransaccion.Debito(transaccion, cuenta);
    
                            Long codigoTransaccion = modelTransaccion.RegistrarTransaccion(transaccion);
                            if (codigoTransaccion == -1) {
                                req.setAttribute("success", 1);
                                req.setAttribute("errores", "No se pudo registar la transaccion");
                                req.getRequestDispatcher("/Transacciones/RetiroBancario.jsp").forward(req, resp);
                            } else {
                                transaccion.setCodigo(codigoTransaccion);
                                modelCuentaBancaria.ActualizarCuenta(cuenta);
                                req.setAttribute("success", 2);
                                req.setAttribute("transaccion", transaccion);
                                req.getRequestDispatcher("/Transacciones/RetiroBancario.jsp").forward(req, resp);
                            }
                        }
                    }
                }
    
            } catch (SQLException e) {
                System.out.println("Error en busqueda de cuenta: " + e.getMessage());
                req.setAttribute("success", 1);
                req.setAttribute("errores", e.getMessage());
                req.getRequestDispatcher("/Transacciones/RetiroBancario.jsp").forward(req, resp);
            }
        }
    }
}
