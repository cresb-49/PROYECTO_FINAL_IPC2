package com.mycompany.proyecto_final.ControladoresRegistro;

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
import java.io.InputStream;

import com.mycompany.proyecto_final.Conversiones.ConversionesVariables;
import com.mycompany.proyecto_final.Entidades.*;
import com.mycompany.proyecto_final.Models.*;

@WebServlet("/RegistroCliente")
@MultipartConfig(maxFileSize = 16177215)
public class ControladorRegistroCliente extends HttpServlet{

    private ConversionesVariables conv = new ConversionesVariables();

    private ModelUsuarioSistema modelUsuarioSistema = new ModelUsuarioSistema();
    private ModelCliente modelCliente = new ModelCliente();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
    
    private void RegistroCliente (Cliente cliente,HttpServletRequest req, HttpServletResponse resp){
        try {
            Long temp = modelUsuarioSistema.RegistroUsuarioSistema((UsuarioDeSistema)cliente); 
            cliente.setId(temp);
        } catch (SQLException e) {
            System.out.println("Error: "+e.getSQLState());
        }
        try {
            Long temp = modelCliente.RegistroClienteCreado(cliente);
        } catch (SQLException e) {
           System.out.println("Error: "+ e.getSQLState()); 
           try {
               modelUsuarioSistema.EliminarUsuarioSistema(cliente.getId());
           } catch (SQLException e1) {
               System.out.println("Error al eliminar usuario: "+e1.getSQLState());
           }
        }

    }

}
