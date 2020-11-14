package com.mycompany.proyecto_final.Controladores.ControladoresGerente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.proyecto_final.Conversiones.ConversionesVariables;
import com.mycompany.proyecto_final.Entidades.Cliente;
import com.mycompany.proyecto_final.Entidades.Gerente;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelCliente;
import com.mycompany.proyecto_final.Models.ModelCuentaBancaria;
import com.mycompany.proyecto_final.Models.ModelGerente;
import com.mycompany.proyecto_final.Trabajadores.TiemposDeTrabajo;

@WebServlet("/CrearCuentaBancaria")
public class ControladorCrearCuentaBancaria extends HttpServlet {
    private ModelCliente modelCliente = new ModelCliente();
    private ModelGerente modelGerente = new ModelGerente();
    private ModelCuentaBancaria modelCuentaBancaria = new ModelCuentaBancaria();
    private TiemposDeTrabajo tiempoTrabajo = new TiemposDeTrabajo();
    private ConversionesVariables conv = new ConversionesVariables();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        }else{

            try {
                String action = req.getParameter("action");
                UsuarioDeSistema usuarioDeSistema = (UsuarioDeSistema) req.getSession().getAttribute("USER");
                Gerente gerente = modelGerente.ObtenerGerente(usuarioDeSistema.getCodigo().toString());
                if (!tiempoTrabajo.validarAcceso(gerente.getTurno())) {
                    action = "10";
                }
                switch (action) {
                    case "1":
                        req.setAttribute("success", 3);
                        req.getRequestDispatcher("/CuentaBancaria/CrearCuenta.jsp").forward(req, resp);
                        break;
                    case "2":
                        obtenerCliente(req, resp);
                        break;
                    case "10":
                        req.setAttribute("success", 0);
                        req.getRequestDispatcher("/Perfiles/PerfilGerente.jsp").forward(req, resp);
                        break;
                    default:
                        req.getRequestDispatcher("/Perfiles/PerfilGerente.jsp").forward(req, resp);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: "+e.getMessage());
                e.printStackTrace();
            }
        }

    }

    private void obtenerCliente(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codigoEntidad = req.getParameter("codigoEntidad");
        try {
            Cliente cliente = modelCliente.ObtenerCliente(codigoEntidad);
            if (cliente != null) {
                System.out.println("Cliente recuperado: " + cliente.toString());
                req.setAttribute("entidad", cliente);
                req.setAttribute("success", 0);
                req.getRequestDispatcher("/CuentaBancaria/CrearCuenta.jsp").forward(req, resp);
            } else {
                req.setAttribute("noData", 0);
                req.getRequestDispatcher("/CuentaBancaria/CrearCuenta.jsp").forward(req, resp);
            }

        } catch (SQLException e) {
            System.out.println("Error en GET crear cuenta bancaria: " + e.getMessage());
            req.setAttribute("success", 2);
            req.setAttribute("errores", e.getMessage());
            req.getRequestDispatcher("/CuentaBancaria/CrearCuenta.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        }else{

            String codigoEntidad = req.getParameter("codigoEntidad");
            try {
                Long codigoCuenta = modelCuentaBancaria.CrearCuenta(codigoEntidad);
                req.setAttribute("cuenta", codigoCuenta);
                req.setAttribute("success", 1);
                req.getRequestDispatcher("/CuentaBancaria/CrearCuenta.jsp").forward(req, resp);
    
            } catch (SQLException e) {
                System.out.println("Error en POST crear cuenta bancaria: " + e.getMessage());
                req.setAttribute("success", 2);
                req.setAttribute("errores", e.getMessage());
                req.getRequestDispatcher("/CuentaBancaria/CrearCuenta.jsp").forward(req, resp);
            }
        }

    }
}
