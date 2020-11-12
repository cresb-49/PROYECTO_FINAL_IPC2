package com.mycompany.proyecto_final.Controladores.ControladoresActualizacionDatos;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.proyecto_final.Conversiones.ConversionesVariables;
import com.mycompany.proyecto_final.Entidades.Cliente;
import com.mycompany.proyecto_final.Models.ModelCliente;

@WebServlet("/ActualizarDatosCliente")
public class ControladorActualizarCliente extends HttpServlet {
    private ModelCliente modelCliente = new ModelCliente();
    private ConversionesVariables conv = new ConversionesVariables();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codigoEntidad = req.getParameter("codigoEntidad");
        try {
            Cliente cliente = modelCliente.ObtenerCliente(codigoEntidad);
            if (cliente != null) {
                System.out.println("Cliente recuperado: " + cliente.toString());
                req.setAttribute("entidad", cliente);
                req.setAttribute("success", 0);
                req.getSession().setAttribute("codigoEntidad",cliente.getCodigo());
                req.getRequestDispatcher("/ActualizacionDatos/ActualizarCliente.jsp").forward(req, resp);
            }
            else{
                req.setAttribute("noData", 0);
                req.setAttribute("success", 0);
                req.getRequestDispatcher("/ActualizacionDatos/ActualizarCliente.jsp").forward(req, resp);
            }

        } catch (SQLException e) {
            System.out.println("Error en GET ActualizarCliente: " + e.getMessage());
            req.setAttribute("success", 2);
            req.setAttribute("errores", e.getMessage());
            req.getRequestDispatcher("/ActualizacionDatos/ActualizarCliente.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long codigoEntidad = (Long) req.getSession().getAttribute("codigoEntidad");
        req.getSession().removeAttribute("codigoEntidad");

        if(codigoEntidad==null){
            req.setAttribute("success", 2);
            req.setAttribute("errores", "Debe de seleccionar un cliente para modificar");
            req.getRequestDispatcher("/ActualizacionDatos/ActualizarCliente.jsp").forward(req, resp);
        }

        String nombre=null;
        String dpi=null;
        String sexo=null;
        String direccion=null;
        Date fechaNacimiento=null;

        nombre = req.getParameter("nombreEntidad");
        System.out.println("Nombre: "+nombre);
        sexo = req.getParameter("sexo");
        direccion = req.getParameter("direccion");
        dpi = req.getParameter("numeroDPI");
        fechaNacimiento=this.conv.stringToDate(req.getParameter("fechaNacimiento"));

        Cliente clienteModificar = new Cliente(codigoEntidad, null, nombre, dpi, sexo, direccion, fechaNacimiento, null);

        System.out.println("Cliente a modificar: "+clienteModificar.toString());

        this.ActualizarCliente(clienteModificar, req, resp);
        
    }
    private void ActualizarCliente (Cliente cliente,HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        try {
            modelCliente.ModificarCliente(cliente);
            req.setAttribute("success", 1);
            req.getRequestDispatcher("/ActualizacionDatos/ActualizarCliente.jsp").forward(req, resp);
        } catch (SQLException e) {
            req.setAttribute("success", 2);
            req.setAttribute("errores", e.getMessage());
            req.getRequestDispatcher("/ActualizacionDatos/ActualizarCliente.jsp").forward(req, resp);
        }
    }
}
