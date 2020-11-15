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
import com.mycompany.proyecto_final.RegistroXML.RegistrarXMLDB;
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
            for (FileItem items : partes) {
                System.out.println("Nombre Archivo: " + items.getName());
                archivosRecuperdados.add(new Archivo(items.getName(), items.getInputStream()));
            }
            LeerXML lectura = new LeerXML(archivosRecuperdados);
            try {
                Banco banco = lectura.EmpaquetarInformacion();
                RegistrarXMLDB registrarXMLDB = new RegistrarXMLDB(banco);
                registrarXMLDB.realizarRegistro();
                Boolean bandera = true;

                if (!registrarXMLDB.getErroresCliente().isEmpty()) {
                    req.setAttribute("error1", 0);
                    req.setAttribute("erCliente", registrarXMLDB.getErroresCliente());
                    bandera = false;
                }
                if (!registrarXMLDB.getErroresCajero().isEmpty()) {
                    req.setAttribute("error3", 0);
                    req.setAttribute("erCajero", registrarXMLDB.getErroresCajero());
                    bandera = false;
                }
                if (!registrarXMLDB.getErroresGerente().isEmpty()) {
                    req.setAttribute("error2", 0);
                    req.setAttribute("erGerente", registrarXMLDB.getErroresGerente());
                    bandera = false;
                }
                if (!registrarXMLDB.getErroresTransacciones().isEmpty()) {
                    req.setAttribute("error4", 0);
                    req.setAttribute("erTransacciones", registrarXMLDB.getErroresTransacciones());
                    bandera = false;
                }
                if (bandera) {

                    req.setAttribute("resultado", 0);
                } else {
                    req.setAttribute("resultado", 1);
                }

                req.getRequestDispatcher("/CargaDeDatos/ResultadoCargaDatos.jsp").forward(req, resp);

                /*
                for(String er:registrarXMLDB.getErroresCliente()){
                    System.out.println(er);
                }
                for(String er:registrarXMLDB.getErroresCajero()){
                    System.out.println(er);
                }
                for(String er:registrarXMLDB.getErroresGerente()){
                    System.out.println(er);
                }
                for(String er:registrarXMLDB.getErroresTransacciones()){
                    System.out.println(er);
                }*/
            } catch (ParserConfigurationException ex) {
                System.out.println("Error estructura de datos: " + ex.getMessage());
            } catch (SAXException ex) {
                System.out.println("Error SAX: " + ex.getMessage());
            }
        } catch (FileUploadException ex) {
            System.out.println("Error carga de datos: " + ex.getMessage());
        } catch (IOException e) {
            System.out.println("Error carga de datos Archivo Corrupto: " + e.getMessage());
        }
    }
}
