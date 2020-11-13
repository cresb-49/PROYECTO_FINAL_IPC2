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
import com.mycompany.proyecto_final.Entidades.CuentaBancaria;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelCuentaBancaria;
import com.mycompany.proyecto_final.Models.ModelSolicitudAsociacion;

@WebServlet("/ControladorBancaVitual")
public class ControladorBancaVirtual extends HttpServlet {
    private ModelCuentaBancaria modelCuentaBancaria = new ModelCuentaBancaria();
    private ModelSolicitudAsociacion modelSolicitud = new ModelSolicitudAsociacion();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsuarioDeSistema usuarioDeSistema = (UsuarioDeSistema) req.getSession().getAttribute("USER");

        try {
            List<CuentaBancaria> cuentas = modelCuentaBancaria.BuscarCuentas(usuarioDeSistema.getCodigo().toString());
            List<SolicitudAsociasion> solicitudes = modelSolicitud.BuscarSolicitudSolicitante(usuarioDeSistema.getCodigo().toString());
            List<SolicitudAsociasion> cuentasAsociadas = new ArrayList<>();

            for(SolicitudAsociasion sol: solicitudes){
                if(sol.getEstado().equals(SolicitudAsociasion.ESTADO_SOLICITUD_1)){
                    cuentasAsociadas.add(sol);
                }
            }


            if (cuentas.isEmpty()) {
                req.setAttribute("errores","No hay ninguna cuenta asociada a su persona dirijase al banco para aperturar una");
                req.setAttribute("success", 2);
                req.getRequestDispatcher("/BancaVirual/BancaVitual.jsp").forward(req, resp);

            } else {
                System.out.println("Cuentas recuperadas: " + cuentas.toString());
                System.out.println("Cuentas asociadas recuperadas: " + cuentasAsociadas.toString());
                req.setAttribute("success", 0);
                req.setAttribute("cuentas",cuentas);
                req.setAttribute("cuentasAsociadas",cuentasAsociadas);
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

    }
}
