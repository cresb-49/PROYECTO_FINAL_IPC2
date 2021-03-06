package com.mycompany.proyecto_final.Controladores.ControladoresRegistro;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.proyecto_final.Entidades.Gerente;
import com.mycompany.proyecto_final.Entidades.UsuarioDeSistema;
import com.mycompany.proyecto_final.Models.ModelGerente;
import com.mycompany.proyecto_final.Models.ModelUsuarioSistema;

@WebServlet("/RegistroGerente")
public class ControladorRegistroGerente extends HttpServlet {

    private ModelUsuarioSistema modelUsuarioSistema = new ModelUsuarioSistema();
    private ModelGerente modelGerente = new ModelGerente();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        }else{

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
    
            Gerente gerente = new Gerente(null, nombre, turno, dpi, direccion, sexo, password);
    
            System.out.println(gerente.toString());
    
            RegistroGerente(gerente, req, resp);
        }
    }

    private void RegistroGerente(Gerente gerente, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        boolean banderaRegistro = false;
        String errores = "";
        try {
            Long temp = modelUsuarioSistema.RegistroUsuarioSistema((UsuarioDeSistema) gerente);
            gerente.setCodigo(temp);
            System.out.println("REgistro usuario sistema: " + gerente.toString());
            banderaRegistro = true;
        } catch (SQLException e) {
            System.out.println("Error registro usuario sistema: " + e.getMessage());
            errores = errores + "\n" + e.getMessage();
            banderaRegistro = false;
        }
        try {
            if (banderaRegistro) {
                modelGerente.RegistroGerente(gerente);
                banderaRegistro = true;
            }
        } catch (SQLException e) {
            System.out.println("Error registro gerente: " + e.getMessage());
            errores = errores + "\n" + e.getMessage();
            banderaRegistro = false;
        }
        try {
            if (!banderaRegistro) {
                modelUsuarioSistema.EliminarUsuarioSistema(gerente.getCodigo());
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
            errores = errores + "\n" + e.getMessage();
        }
        if (banderaRegistro) {
            req.setAttribute("success", 1);
            req.setAttribute("genCode", gerente.getCodigo());
            req.getRequestDispatcher("/Registros/RegistrarGerente.jsp").forward(req, resp);
        } else {
            req.setAttribute("success", 2);
            req.setAttribute("errores", errores);
            req.getRequestDispatcher("/Registros/RegistrarGerente.jsp").forward(req, resp);
        }
    }
}
