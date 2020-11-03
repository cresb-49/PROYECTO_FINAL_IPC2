
package com.mycompany.proyecto_final.LecturaXML;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import com.mycompany.proyecto_final.Entidades.*;

public class BancoHandler extends DefaultHandler {

    private Banco banco = new Banco();
    private StringBuilder buffer = new StringBuilder();
    private Object objeto;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println("Poscicion caracter: " + start);
        buffer.append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "GERENTE":
                break;
            case "CODIGO":
                break;
            case "NOMBRE":
                break;
            case "TURNO":
                break;
            case "DPI":
                break;
            case "DIRECCION":
                break;
            case "SEXO":
                break;
            case "BIRTH":
                break;
            case "CUENTAS":
                break;
            case "CUENTA":
                break;
            case "CREADA":
                break;
            case "CREDITO":
                break;
            case "DPI-PDF":
                break;
            case "PASSWORD":
                break;
            case "CUENTA_ID":
                break;
            case "FECHA":
                break;
            case "HORA":
                break;
            case "TIPO":
                break;
            case "MONTO":
                break;
            case "CAJERO_ID":
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "GERENTE":
                break;
            case "CODIGO":
                break;
            case "NOMBRE":
                break;
            case "TURNO":
                break;
            case "DPI":
                break;
            case "DIRECCION":
                break;
            case "SEXO":
                break;
            case "BIRTH":
                break;
            case "CUENTAS":
                break;
            case "CUENTA":
                break;
            case "CREADA":
                break;
            case "CREDITO":
                break;
            case "DPI-PDF":
                break;
            case "PASSWORD":
                break;
            case "CUENTA_ID":
                break;
            case "FECHA":
                break;
            case "HORA":
                break;
            case "TIPO":
                break;
            case "MONTO":
                break;
            case "CAJERO_ID":
                break;
            default:
                break;
        }
    }
}
