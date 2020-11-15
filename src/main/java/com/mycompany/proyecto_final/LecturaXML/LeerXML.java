package com.mycompany.proyecto_final.LecturaXML;

import com.mycompany.proyecto_final.Entidades.*;
import com.mycompany.proyecto_final.LecturaXML.ParcersEntidades.ParserGerente;
import com.mycompany.proyecto_final.LecturaXML.ParcersEntidades.ParserCajero;
import com.mycompany.proyecto_final.LecturaXML.ParcersEntidades.ParserCliente;
import com.mycompany.proyecto_final.LecturaXML.ParcersEntidades.ParserTransaccion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class LeerXML {

    private ParserGerente parGerente = new ParserGerente();
    private ParserCajero parCajero = new ParserCajero();
    private ParserTransaccion parTransaccion = new ParserTransaccion();
    private ParserCliente parCliente = new ParserCliente();
    
    private List<Archivo> archivosEntrada = new ArrayList<>();

    /**
     * CONTRUCTOR POR DEFECTO DE LA CLASE LEERXML
     *
     * @param archivosEntrada
     */
    public LeerXML(List<Archivo> archivosEntrada) {
        this.archivosEntrada = archivosEntrada;
    }

    public Banco EmpaquetarInformacion() throws ParserConfigurationException, SAXException, IOException {
        Banco banco = new Banco();
        Archivo xmlEntrada = null;
        for (Archivo arch : archivosEntrada) {
            if (arch.getNombre().endsWith(".xml")) {
                xmlEntrada = arch;
                break;
            }
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(xmlEntrada.getDatos());
        
        banco.setGerentes(parGerente.gerentesBanco(documento));
        banco.setCajeros(parCajero.cajerosBanco(documento));
        banco.setTransaciones(parTransaccion.transaccionBanco(documento));
        banco.setClientes(parCliente.clientesBanco(documento));
        
        AsignarDocumentos asignar = new AsignarDocumentos(archivosEntrada);
        asignar.asignarFotocopia(banco.getClientes());
        
        for(Cliente cliente: banco.getClientes()){
            for(CuentaBancaria cuenta:cliente.getCuentas()){
                cuenta.setIdCliente(cliente.getCodigo());
            }
        }
        
        for (Gerente gerente : banco.getGerentes()) {
            System.out.println(gerente.toString());
        }
        for (Cajero cajero : banco.getCajeros()) {
            System.out.println(cajero.toString());
        }
        for (Transaccion tran : banco.getTransaciones()) {
            System.out.println(tran.toString());
        }
        for (Cliente cliente : banco.getClientes()) {
            System.out.println(cliente.toString());
        }
        
        return banco;
    }
}
