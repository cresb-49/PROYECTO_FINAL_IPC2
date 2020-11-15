package com.mycompany.proyecto_final.LecturaXML.ParsersEntidades;

import com.mycompany.proyecto_final.Conversiones.ConversionesVariables;
import com.mycompany.proyecto_final.Entidades.CuentaBancaria;
import com.mycompany.proyecto_final.Entidades.Gerente;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParserCuentaBancaria {
    
    private ConversionesVariables conv = new ConversionesVariables();
    /**
     * CONTRUCTOR POR DEFECTO DE LA ENTIDAD
     */
    public ParserCuentaBancaria() {
    }
    
    public List<CuentaBancaria> cuentaBancariasCliente(NodeList cuentas){
        List<CuentaBancaria> cuentaBancarias = new ArrayList<>();
        CuentaBancaria temp = null;
        for (int i = 0; i < cuentas.getLength(); i++) {
            Node node = cuentas.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) node;
                NodeList hijos = e.getChildNodes();
                temp = new CuentaBancaria() ;
                for (int j = 0; j < hijos.getLength(); j++) {
                    Node hijo = hijos.item(j);
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        switch (hijo.getNodeName()) {
                            case "CODIGO":
                                temp.setCodigo(this.conv.stringToLong(hijo.getTextContent()));
                                break;
                            case "CREADA":
                                temp.setFechaApertura(this.conv.stringToDate(hijo.getTextContent()));
                                break;
                            case "CREDITO":
                                temp.setCredito(this.conv.stringToDouble(hijo.getTextContent()));
                                break;
                            default:
                                break;
                        }
                    }
                }
                cuentaBancarias.add(temp);
                temp=null;
            }
        }
        return cuentaBancarias;
    }
}
