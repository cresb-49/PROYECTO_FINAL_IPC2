/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_final.LecturaXML.ParsersEntidades;

import com.mycompany.proyecto_final.Conversiones.ConversionesVariables;
import com.mycompany.proyecto_final.Entidades.Archivo;
import com.mycompany.proyecto_final.Entidades.Cajero;
import com.mycompany.proyecto_final.Entidades.Cliente;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author carlo
 */
public class ParserCliente {

    private ConversionesVariables conv = new ConversionesVariables();
    private ParserCuentaBancaria parcerCuentas = new ParserCuentaBancaria();

    /**
     * CONTRUCTOR POR DEFECTO DE LA ENTIDAD
     */
    public ParserCliente() {

    }

    public List<Cliente> clientesBanco(Document document) {

        List<Cliente> clientes = new ArrayList<>();
        NodeList cajerosXML = document.getElementsByTagName("CLIENTE");

        Cliente temp = null;

        for (int i = 0; i < cajerosXML.getLength(); i++) {
            Node node = cajerosXML.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) node;
                NodeList hijos = e.getChildNodes();
                temp = new Cliente();

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
                            case "DPI":
                                //System.out.println("DPI: " + hijo.getTextContent());
                                temp.setDpi(hijo.getTextContent());
                                break;
                            case "BIRTH":
                                //System.out.println("DPI: " + hijo.getTextContent());
                                temp.setFechaNacimiento(this.conv.stringToDate(hijo.getTextContent()));
                                break;
                            case "DIRECCION":
                                //System.out.println("Direccion: " + hijo.getTextContent());
                                temp.setDireccion(hijo.getTextContent());
                                break;
                            case "DPI-PDF":
                                //System.out.println("Direccion: " + hijo.getTextContent());
                                temp.setFotocopiaDPI(new Archivo(hijo.getTextContent(), null));
                                break;
                            case "SEXO":
                                //System.out.println("Sexo: " + hijo.getTextContent());
                                temp.setSexo(hijo.getTextContent());
                                break;
                            case "CUENTAS":
                                Element cuenta_ = (Element) hijo;
                                NodeList cuenta_hijos = cuenta_.getElementsByTagName("CUENTA");
                                temp.setCuentas(this.parcerCuentas.cuentaBancariasCliente(cuenta_hijos));
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
                clientes.add(temp);
                temp = null;
            }
        }

        return clientes;
    }

}
