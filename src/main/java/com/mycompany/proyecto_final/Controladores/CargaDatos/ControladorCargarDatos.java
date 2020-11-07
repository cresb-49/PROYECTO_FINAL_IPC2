package com.mycompany.proyecto_final.Controladores.CargaDatos;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/CargarDatosSistema")
public class ControladorCargarDatos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Estoy en controlador carga datos");

        //Generacion de la escritura del archivo
        DiskFileItemFactory itemFactory = new DiskFileItemFactory();
        //Tamaño maximo de archivos de recepcion
        itemFactory.setSizeThreshold(1024);
        //Asignacion de carga del archivo xml
        ServletFileUpload upload = new ServletFileUpload(itemFactory);
        try {
            //Partes del archivo
            List<FileItem> partes = upload.parseRequest(req);
            partes.forEach(items -> {
                System.out.println("Nombre Archivo: "+items.getName());
            });
        } catch (FileUploadException ex) {
            System.out.println("Error carga de datos: " + ex.getMessage());
        }
    }
}
