package com.mycompany.proyecto_final.Controladores.ControladorBancaVirtual;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Request;

import com.mycompany.proyecto_final.AsociarCuenta.SolicitudAsociasion;
import com.mycompany.proyecto_final.Conversiones.ConversionesVariables;
import com.mycompany.proyecto_final.Entidades.CuentaBancaria;
import com.mycompany.proyecto_final.Entidades.Transaccion;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelCuentaBancaria;
import com.mycompany.proyecto_final.Models.ModelSolicitudAsociacion;
import com.mycompany.proyecto_final.Models.ModelTransaccion;
import com.mycompany.proyecto_final.Transaccion.LogicaTransaccion;

@WebServlet("/ControladorBancaVitual")
public class ControladorBancaVirtual extends HttpServlet {
    private ModelCuentaBancaria modelCuentaBancaria = new ModelCuentaBancaria();
    private ModelSolicitudAsociacion modelSolicitud = new ModelSolicitudAsociacion();
    private ModelTransaccion modelTransaccion = new ModelTransaccion();
    private LogicaTransaccion logicaTransaccion = new LogicaTransaccion();
    private ConversionesVariables conv = new ConversionesVariables();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsuarioDeSistema usuarioDeSistema = (UsuarioDeSistema) req.getSession().getAttribute("USER");

        try {
            List<CuentaBancaria> cuentas = modelCuentaBancaria.BuscarCuentas(usuarioDeSistema.getCodigo().toString());
            List<SolicitudAsociasion> solicitudes = modelSolicitud
                    .BuscarSolicitudSolicitante(usuarioDeSistema.getCodigo().toString());
            List<SolicitudAsociasion> cuentasAsociadas = new ArrayList<>();

            for (SolicitudAsociasion sol : solicitudes) {
                if (sol.getEstado().equals(SolicitudAsociasion.ESTADO_SOLICITUD_1)) {
                    cuentasAsociadas.add(sol);
                }
            }

            if (cuentas.isEmpty()) {
                req.setAttribute("errores",
                        "No hay ninguna cuenta asociada a su persona dirijase al banco para aperturar una");
                req.setAttribute("success", 2);
                req.getRequestDispatcher("/BancaVirual/BancaVitual.jsp").forward(req, resp);

            } else {
                System.out.println("Cuentas recuperadas: " + cuentas.toString());
                System.out.println("Cuentas asociadas recuperadas: " + cuentasAsociadas.toString());
                req.setAttribute("success", 0);
                req.setAttribute("cuentas", cuentas);
                req.setAttribute("cuentasAsociadas", cuentasAsociadas);
                req.getRequestDispatcher("/BancaVirual/BancaVitual.jsp").forward(req, resp);
            }

        } catch (SQLException e) {
            req.setAttribute("errores",
                    "No hay ninguna cuenta asociada a su persona dirijase al banco para aperturar una");
            req.setAttribute("success", 2);
            req.getRequestDispatcher("/BancaVirual/BancaVitual.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cuentaOrigen = req.getParameter("codigoCuentaOrigen");
        String monto = req.getParameter("monto");
        String cuentaPropia = req.getParameter("codeCuentaPropia");
        String cuentaAsociada = req.getParameter("codeCuentaAsociada");

        String cuentaDestino = "";

        if (cuentaPropia.equals("Seleccionar")) {
            cuentaDestino = cuentaAsociada;
        } else if (cuentaAsociada.equals("Seleccionar")) {
            cuentaDestino = cuentaPropia;
        } else {
            req.setAttribute("errores", "Error en cuenta de destino verifica la configuracion de tu navegador");
            req.setAttribute("success", 2);
            req.getRequestDispatcher("/BancaVirual/BancaVitual.jsp").forward(req, resp);
        }

        System.out.println("Datos recuperdos: origen=" + cuentaOrigen + ", monto=" + monto + ", cuentaPropia="
                + cuentaPropia + ", cuentaAsociada=" + cuentaAsociada);

        try {
            CuentaBancaria cuentaDeOrigen = modelCuentaBancaria.BuscarCuenta(cuentaOrigen);
            CuentaBancaria cuentaDeDestino = modelCuentaBancaria.BuscarCuenta(cuentaDestino);
            if (cuentaDeOrigen == null || cuentaDeDestino == null) {
                req.setAttribute("errores", "Error en recuperacion de cuentas");
                req.setAttribute("success", 2);
                req.getRequestDispatcher("/BancaVirual/BancaVitual.jsp").forward(req, resp);
            } else {

                java.time.LocalDate today = java.time.LocalDate.now();
                java.time.LocalTime hora = java.time.LocalTime.now();
                String time = hora.getHour() + ":" + hora.getMinute() + ":" + hora.getSecond();

                Transaccion deOrigen = new Transaccion(null, cuentaDeOrigen.getCodigo(),
                        this.conv.stringToDate(today.toString()), time, this.conv.stringToDouble(monto), (long) 101,
                        "DEBITO");
                Transaccion deDestino = new Transaccion(null, cuentaDeDestino.getCodigo(),
                        this.conv.stringToDate(today.toString()), time, this.conv.stringToDouble(monto), (long) 101,
                        "CREDITO");
                if (deOrigen.getMonto() > cuentaDeOrigen.getCredito()) {
                    req.setAttribute("errores","La cuenta de origen no tiene el suficiente credito\nSaldo de la cuenta: "+ cuentaDeOrigen.getCredito());
                    req.setAttribute("success", 2);
                    req.getRequestDispatcher("/BancaVirual/BancaVitual.jsp").forward(req, resp);
                } else {
                    logicaTransaccion.Debito(deOrigen, cuentaDeOrigen);
                    Long code1 = modelTransaccion.RegistrarTransaccion(deOrigen);
                    if (code1 == -1) {
                        req.setAttribute("errores", "No se puede realizar el debito de la cuenta de origen");
                        req.setAttribute("success", 2);
                        req.getRequestDispatcher("/BancaVirual/BancaVitual.jsp").forward(req, resp);
                    } else {

                        logicaTransaccion.Credito(deDestino, cuentaDeDestino);
                        Long code2 = modelTransaccion.RegistrarTransaccion(deDestino);
                        if (code2 == -1) {
                            deOrigen.setTipo("CREDITO");
                            modelTransaccion.RegistrarTransaccion(deOrigen);
                            req.setAttribute("errores", "No se puede realizar el credito en la cuenta de destino");
                            req.setAttribute("success", 2);
                            req.getRequestDispatcher("/BancaVirual/BancaVitual.jsp").forward(req, resp);
                        } else {
                            modelCuentaBancaria.ActualizarCuenta(cuentaDeDestino);
                            modelCuentaBancaria.ActualizarCuenta(cuentaDeOrigen);
                            req.setAttribute("transaccion", deDestino);
                            req.setAttribute("success", 1);
                            req.getRequestDispatcher("/BancaVirual/BancaVitual.jsp").forward(req, resp);
                        }
                    }
                }
            }

        } catch (Exception e) {
            req.setAttribute("errores", e.getMessage());
            req.setAttribute("success", 2);
            req.getRequestDispatcher("/BancaVirual/BancaVitual.jsp").forward(req, resp);
        }

    }
}
