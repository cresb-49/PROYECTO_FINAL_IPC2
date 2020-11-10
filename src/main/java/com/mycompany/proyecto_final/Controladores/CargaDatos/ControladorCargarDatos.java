package com.mycompany.proyecto_final.Controladores.CargaDatos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mycompany.proyecto_final.Entidades.*;
import com.mycompany.proyecto_final.LecturaXML.*;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.xml.sax.SAXException;

@WebServlet("/CargarDatosSistema")
public class ControladorCargarDatos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Generacion de la escritura del archivo
        DiskFileItemFactory itemFactory = new DiskFileItemFactory();
        //Tamaño maximo de archivos de recepcion
        itemFactory.setSizeThreshold(1024);
        //Asignacion de carga del archivo xml
        ServletFileUpload upload = new ServletFileUpload(itemFactory);
        try {
            //Lista de archivos recuperados luego de ser enviados
            List<Archivo> archivosRecuperdados = new ArrayList<>();
            //Partes del archivo
            List<FileItem> partes = upload.parseRequest(req);
            for(FileItem items: partes){
                System.out.println("Nombre Archivo: "+items.getName());
                archivosRecuperdados.add(new Archivo(items.getName(), items.getInputStream()));
            }
            LeerXML lectura = new LeerXML(archivosRecuperdados);
            try {
                Banco banco = lectura.EmpaquetarInformacion();
                
            } catch (ParserConfigurationException ex) {
                System.out.println("Error estructura de datos: "+ex.getMessage());
            } catch (SAXException ex) {
                System.out.println("Error SAX: "+ex.getMessage());
            }
        } catch (FileUploadException ex) {
            System.out.println("Error carga de datos: " + ex.getMessage());
        }catch (IOException e) {
            System.out.println("Error carga de datos Archivo Corrupto: " + e.getMessage());                    
        }
    }
}
