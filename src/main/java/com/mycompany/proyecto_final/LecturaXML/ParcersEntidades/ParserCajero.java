package com.mycompany.proyecto_final.LecturaXML.ParcersEntidades;

import com.mycompany.proyecto_final.Conversiones.ConversionesVariables;
import com.mycompany.proyecto_final.Entidades.Cajero;
import com.mycompany.proyecto_final.Entidades.Gerente;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParserCajero {
    private ConversionesVariables conv = new ConversionesVariables();
    public ParserCajero() {

    }

    public List<Cajero> cajerosBanco(Document document) {
        List<Cajero> cajeros = new ArrayList<>();
        NodeList cajerosXML = document.getElementsByTagName("CAJERO");
        Cajero temp = null;
        for (int i = 0; i < cajerosXML.getLength(); i++) {
            Node node = cajerosXML.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) node;
                NodeList hijos = e.getChildNodes();
                temp = new Cajero();
                for (int j = 0; j < hijos.getLength(); j++) {
                    Node hijo = hijos.item(j);
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        switch (hijo.getNodeName()) {
                            case "CODIGO":
                                //System.out.println("Codigo: " + hijo.getTextContent());
                                temp.setCodigo(this.conv.stringToLong(hijo.getTextContent()));
                                break;
                            case "NOMBRE":
                                //System.out.println("Nombre: " + hijo.getTextContent());
                                temp.setNombre(hijo.getTextContent());
                                break;
                            case "TURNO":
                                //System.out.println("Turno: " + hijo.getTextContent());
                                temp.setTurno(hijo.getTextContent());
                                break;
                            case "DPI":
                                //System.out.println("DPI: " + hijo.getTextContent());
                                temp.setDpi(hijo.getTextContent());
                                break;
                            case "DIRECCION":
                                //System.out.println("Direccion: " + hijo.getTextContent());
                                temp.setDireccion(hijo.getTextContent());
                                break;
                            case "SEXO":
                                //System.out.println("Sexo: " + hijo.getTextContent());
                                temp.setSexo(hijo.getTextContent());
                                break;
                            case "PASSWORD":
                                //System.out.println("Password: " + hijo.getTextContent());
                                temp.setPassword(hijo.getTextContent());
                                break;
                            default:
                                break;
                        }
                    }
                }
                cajeros.add(temp);
                temp = null;
            }
        }
        return cajeros;
    }

}
