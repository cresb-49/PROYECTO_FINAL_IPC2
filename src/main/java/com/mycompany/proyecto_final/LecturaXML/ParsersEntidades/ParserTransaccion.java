package com.mycompany.proyecto_final.LecturaXML.ParsersEntidades;

import com.mycompany.proyecto_final.Conversiones.ConversionesVariables;
import com.mycompany.proyecto_final.Entidades.Cajero;
import com.mycompany.proyecto_final.Entidades.Transaccion;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParserTransaccion {

    private ConversionesVariables conv = new ConversionesVariables();

    /**
     * CONTRUCTOR POR DEFECTO DE LA CLASE
     */
    public ParserTransaccion() {

    }

    public List<Transaccion> transaccionBanco(Document document) {
        List<Transaccion> transacciones = new ArrayList<>();
        NodeList cajerosXML = document.getElementsByTagName("TRANSACCION");
        Transaccion temp = null;
        for (int i = 0; i < cajerosXML.getLength(); i++) {
            Node node = cajerosXML.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) node;
                NodeList hijos = e.getChildNodes();
                temp = new Transaccion();
                
                for (int j = 0; j < hijos.getLength(); j++) {
                    Node hijo = hijos.item(j);
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        switch (hijo.getNodeName()) {
                            case "CODIGO":
                                //System.out.println("Codigo: " + hijo.getTextContent());
                                temp.setCodigo(this.conv.stringToLong(hijo.getTextContent()));
                                break;
                            case "CUENTA-ID":
                                //System.out.println("Nombre: " + hijo.getTextContent());
                                temp.setIdCuenta(this.conv.stringToLong(hijo.getTextContent()));
                                break;
                            case "FECHA":
                                //System.out.println("Turno: " + hijo.getTextContent());
                                temp.setFechaTransaccion(this.conv.stringToDate(hijo.getTextContent()));
                                break;
                            case "HORA":
                                //System.out.println("DPI: " + hijo.getTextContent());
                                temp.setHora(hijo.getTextContent());
                                break;
                            case "TIPO":
                                //System.out.println("Direccion: " + hijo.getTextContent());
                                temp.setTipo(hijo.getTextContent());
                                break;
                            case "MONTO":
                                //System.out.println("Sexo: " + hijo.getTextContent());
                                temp.setMonto(this.conv.stringToDouble(hijo.getTextContent()));
                                break;
                            case "CAJERO-ID":
                                //System.out.println("Password: " + hijo.getTextContent());
                                temp.setIdCajero(this.conv.stringToLong(hijo.getTextContent()));
                                break;
                            default:
                                break;
                        }
                    }
                }
                transacciones.add(temp);
                temp = null;
            }
        }
        return transacciones;
    }
}
