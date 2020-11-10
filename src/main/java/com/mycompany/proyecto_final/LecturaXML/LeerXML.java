package com.mycompany.proyecto_final.LecturaXML;

import com.mycompany.proyecto_final.Entidades.Archivo;
import com.mycompany.proyecto_final.Entidades.Banco;
import com.mycompany.proyecto_final.Entidades.Cajero;
import com.mycompany.proyecto_final.Entidades.Cliente;
import com.mycompany.proyecto_final.Entidades.Gerente;
import com.mycompany.proyecto_final.Entidades.Transaccion;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class LeerXML {

    List<Archivo> archivosEntrada = new ArrayList<>();

    /**
     * CONTRUCTOR VACIO DE LA CLASE LEERXML
     *
     * @param archivosEntrada
     */
    public LeerXML(List<Archivo> archivosEntrada) {
        this.archivosEntrada = archivosEntrada;
    }

    public Banco EmpaquetarInformacion() throws ParserConfigurationException, SAXException, IOException {
        Archivo xmlEntrada = null;
        for (Archivo arch : archivosEntrada) {
            if (arch.getNombre().endsWith(".xml")) {
                xmlEntrada = arch;
                break;
            }
        }
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        BancoHandler bancoHandler = new BancoHandler();
        saxParser.parse(xmlEntrada.getDatos(), bancoHandler);
        
        AsignarDocumentos asignar = new AsignarDocumentos(archivosEntrada);
        asignar.asignarFotocopia(bancoHandler.getBanco().getClientes());
        
        for(Gerente gerente: bancoHandler.getBanco().getGerentes()){
            System.out.println(gerente.toString());
        }
        for(Cliente cliente: bancoHandler.getBanco().getClientes()){
            System.out.println(cliente.toString());
        }
        for(Cajero cajero: bancoHandler.getBanco().getCajeros()){
            System.out.println(cajero.toString());
        }
        for(Transaccion tran: bancoHandler.getBanco().getTransaciones()){
            System.out.println(tran.toString());
        }
        return bancoHandler.getBanco();
    }
}
