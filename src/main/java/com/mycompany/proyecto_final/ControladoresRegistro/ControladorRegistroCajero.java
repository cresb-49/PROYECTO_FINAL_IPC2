package com.mycompany.proyecto_final.ControladoresRegistro;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.proyecto_final.Entidades.Cajero;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelCajero;
import com.mycompany.proyecto_final.Models.ModelUsuarioSistema;

@WebServlet("/RegistroCajero")
public class ControladorRegistroCajero extends HttpServlet {

    private ModelUsuarioSistema modelUsuarioSistema = new ModelUsuarioSistema();
    private ModelCajero modelCajero = new ModelCajero();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = null;
        String dpi = null;
        String sexo = null;
        String direccion = null;
        String turno = null;
        String password = null;

        nombre = req.getParameter("nombreEntidad");
        sexo = req.getParameter("sexo");
        direccion = req.getParameter("direccion");
        dpi = req.getParameter("numeroDPI");
        turno = req.getParameter("TipoTurno");
        password = req.getParameter("passInicial");

        Cajero cajero = new Cajero(null, password, dpi, nombre, sexo, direccion, turno);

        System.out.println(cajero.toString());
        RegistroCajero(cajero, req, resp);
    }

    private void RegistroCajero(Cajero cajero, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        boolean banderaRegistro = false;
        String errores = "";
        try {
            Long temp = modelUsuarioSistema.RegistroUsuarioSistema((UsuarioDeSistema) cajero);
            cajero.setCodigo(temp);
            System.out.println("REgistro usuario sistema: " + cajero.toString());
            banderaRegistro = true;
        } catch (SQLException e) {
            System.out.println("Error registro usuario sistema: " + e.getMessage());
            errores = errores + "\n" + e.getMessage();
            banderaRegistro = false;
        }
        try {
            if (banderaRegistro) {
                modelCajero.RegistroCajeroCreado(cajero);
                banderaRegistro = true;
            }
        } catch (SQLException e) {
            System.out.println("Error registro cajero: " + e.getMessage());
            errores = errores + "\n" + e.getMessage();
            banderaRegistro = false;
        }
        try {
            if (!banderaRegistro) {
                modelUsuarioSistema.EliminarUsuarioSistema(cajero.getCodigo());
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
            errores = errores + "\n" + e.getMessage();
        }
        if (banderaRegistro) {
            req.setAttribute("success", 1);
            req.setAttribute("genCode", cajero.getCodigo());
            req.getRequestDispatcher("/Registros/RegistrarCajero.jsp").forward(req, resp);
        }else{
            req.setAttribute("success", 2);
            req.setAttribute("errores", errores);
            req.getRequestDispatcher("/Registros/RegistrarCajero.jsp").forward(req, resp);
        }

    }
}
