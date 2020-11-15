package com.mycompany.proyecto_final.Controladores.ControladoresRegistro;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mycompany.proyecto_final.Conversiones.ConversionesVariables;
import com.mycompany.proyecto_final.Entidades.*;
import com.mycompany.proyecto_final.Models.*;

@WebServlet("/RegistroCliente")
@MultipartConfig(maxFileSize = 16177215)
public class ControladorRegistroCliente extends HttpServlet{

    private ConversionesVariables conv = new ConversionesVariables();

    private ModelUsuarioSistema modelUsuarioSistema = new ModelUsuarioSistema();
    private ModelCliente modelCliente = new ModelCliente();
    private ModelFotocopiaDPF modelFotocopiaDPF = new ModelFotocopiaDPF();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("USER") == null) {
            resp.sendRedirect(req.getContextPath() + "/Logout");
        }else{

            String nombre=null;
            String dpi=null;
            String sexo=null;
            String direccion=null;
            Date fechaNacimiento=null;
            Archivo fotocopiaDPI=null;
            String password=null;
    
            nombre = req.getParameter("nombreEntidad");
            sexo = req.getParameter("sexo");
            direccion = req.getParameter("direccion");
            dpi = req.getParameter("numeroDPI");
            fechaNacimiento=this.conv.stringToDate(req.getParameter("fechaNacimiento"));
            password = req.getParameter("passInicial");
            try {
                Part filePart = req.getPart("fotocopiaDPI");
                if (filePart.getSize() > 0) {
                    fotocopiaDPI=new Archivo(filePart.getName(), filePart.getInputStream());
                }
            } catch (Exception ex) {
                System.out.println("Error de fichero: " + ex.getMessage());
            }
    
            Cliente cliente = new Cliente(null, password, nombre, dpi, sexo, direccion, fechaNacimiento, fotocopiaDPI);
            System.out.println(cliente.toString());
            RegistroCliente(cliente, req, resp);
        }
    }
    
    private void RegistroCliente (Cliente cliente,HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        boolean banderaRegistro=false;
        String errores = "";
        try {
            Long temp = modelUsuarioSistema.RegistroUsuarioSistema((UsuarioDeSistema)cliente); 
            cliente.setCodigo(temp);
            System.out.println("REgistro usuario sistema: "+cliente.toString());
            banderaRegistro=true;
        } catch (SQLException e) {
            System.out.println("Error registro usuario sistema: "+e.getMessage());
            errores = errores + "\n" + e.getMessage();
            banderaRegistro=false;
        }
        try {
            if(banderaRegistro){
                modelCliente.RegistroCliente(cliente);
                banderaRegistro=true;
            }
        } catch (SQLException e) {
            System.out.println("Error registro cliente: "+ e.getMessage());  
            errores = errores + "\n" + e.getMessage();
            banderaRegistro=false;
        }
        try {
            if(banderaRegistro){
                modelFotocopiaDPF.AgregarFotocopia(cliente);
                banderaRegistro=true;
            }
        } catch (SQLException e) {
            System.out.println("Error registro fotocopia: "+ e.getMessage());  
            errores = errores + "\n" + e.getMessage();
            banderaRegistro=false;
        }
        try {
            if(!banderaRegistro){
                modelUsuarioSistema.EliminarUsuarioSistema(cliente.getCodigo());
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: "+e.getMessage());
            errores = errores + "\n" + e.getMessage();
        }
        if (banderaRegistro) {
            req.setAttribute("success", 1);
            req.setAttribute("genCode", cliente.getCodigo());
            req.getRequestDispatcher("/Registros/RegistarCliente.jsp").forward(req, resp);
        }else{
            req.setAttribute("success", 2);
            req.setAttribute("errores", errores);
            req.getRequestDispatcher("/Registros/RegistarCliente.jsp").forward(req, resp);
        }
        
    }

}
