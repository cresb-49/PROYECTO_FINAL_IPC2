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
import com.mycompany.proyecto_final.Models.ModelCliente;
import com.mycompany.proyecto_final.Models.ModelCuentaBancaria;

@WebServlet("/CrearCuentaBancaria")
public class ControladorCrearCuentaBancaria extends HttpServlet {
    private ModelCliente modelCliente = new ModelCliente();
    private ModelCuentaBancaria modelCuentaBancaria = new ModelCuentaBancaria();
    private ConversionesVariables conv = new ConversionesVariables();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "1":
            req.setAttribute("success", 3);
                req.getRequestDispatcher("/CuentaBancaria/CrearCuenta.jsp").forward(req, resp);
                break;
            case "2":
                obtenerCliente(req, resp);
                break;
            default:
                req.getRequestDispatcher("/Perfiles/PerfilGerente.jsp").forward(req, resp);
                break;
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
