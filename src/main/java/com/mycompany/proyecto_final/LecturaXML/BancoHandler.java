package com.mycompany.proyecto_final.LecturaXML;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import com.mycompany.proyecto_final.Conversiones.ConversionesVariables;
import com.mycompany.proyecto_final.Entidades.*;

public class BancoHandler extends DefaultHandler {

    private ConversionesVariables conv = new ConversionesVariables();
    private Banco banco = new Banco();
    private StringBuilder buffer = new StringBuilder();
    private Object objeto;
    private String presedencia = "";
    private ArrayList<String> errores = new ArrayList<>();

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        buffer.delete(0, buffer.length());
        switch (qName) {
            case "GERENTE":
                this.objeto = new Gerente();
                break;
            case "CAJERO":
                this.objeto = new Cajero();
                break;
            case "CLIENTE":
                this.objeto = new Cliente();
                break;
            case "TRANSACCION":
                this.objeto = new Transaccion();
                break;
            case "CUENTAS":
                this.presedencia = "CLIENTE";
                break;
            case "CUENTA":
                if (objeto instanceof Cliente) {
                    ((Cliente) objeto).getCuentas().add(new CuentaBancaria());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "GERENTE":
                this.banco.getGerentes().add((Gerente) objeto);
                this.objeto = null;
                break;
            case "CAJERO":
                this.banco.getCajeros().add((Cajero) objeto);
                this.objeto = null;
                break;
            case "CLIENTE":
                this.banco.getClientes().add((Cliente) objeto);
                this.objeto = null;
                break;
            case "TRANSACCION":
                this.banco.getTransaciones().add((Transaccion) objeto);
                this.objeto = null;
                break;
            case "CODIGO":
                asignarCodigo();
                break;
            case "NOMBRE":
                if (this.objeto instanceof Persona) {
                    ((Persona) this.objeto).setNombre(buffer.toString());
                }
                break;
            case "TURNO":
                asignarTurno();
                break;
            case "DPI":
                if (objeto instanceof Persona) {
                    ((Persona) objeto).setDpi(buffer.toString());
                }
                break;
            case "DIRECCION":
                if (this.objeto instanceof Persona) {
                    ((Persona) this.objeto).setDireccion(buffer.toString());
                }
                break;
            case "SEXO":
                if (this.objeto instanceof Persona) {
                    ((Persona) this.objeto).setSexo(buffer.toString());
                }
                break;
            case "BIRTH":
                if (objeto instanceof Cliente) {
                    ((Cliente) objeto).setFechaNacimiento(this.conv.stringToDate(buffer.toString()));
                }
                break;
            case "CUENTAS":
                this.presedencia = "";
                break;
            case "CUENTA":
                break;
            case "CREADA":
                if (presedencia.equals("CLIENTE")) {
                    if (objeto instanceof Cliente) {
                        int ultimaCuenta = ((Cliente) objeto).getCuentas().size();
                        if (ultimaCuenta != 0) {
                            ultimaCuenta = ultimaCuenta - 1;
                            ((Cliente) objeto).getCuentas().get(ultimaCuenta).setFechaApertura(this.conv.stringToDate(buffer.toString()));
                        }
                    }
                }
                break;
            case "CREDITO":
                if (presedencia.equals("CLIENTE")) {
                    if (objeto instanceof Cliente) {
                        int ultimaCuenta = ((Cliente) objeto).getCuentas().size();
                        if (ultimaCuenta != 0) {
                            ultimaCuenta = ultimaCuenta - 1;
                            ((Cliente) objeto).getCuentas().get(ultimaCuenta).setCredito(this.conv.stringToDouble(buffer.toString()));
                        }
                    }
                }
                break;
            case "DPI-PDF":
                if (objeto instanceof Cliente) {
                    ((Cliente) objeto).getFotocopiaDPI().setNombre(buffer.toString());
                }
                break;
            case "PASSWORD":
                if (objeto instanceof UsuarioDeSistema) {
                    ((UsuarioDeSistema) objeto).setPassword(buffer.toString());
                }
                break;
            case "CUENTA-ID":
                if (objeto instanceof Transaccion) {
                    ((Transaccion) objeto).setIdCuenta(this.conv.stringToLong(buffer.toString()));
                }
                break;
            case "FECHA":
                if (objeto instanceof Transaccion) {
                    ((Transaccion) objeto).setFechaTransaccion(this.conv.stringToDate(buffer.toString()));
                }
                break;
            case "HORA":
                if (objeto instanceof Transaccion) {
                    ((Transaccion) objeto).setHora(buffer.toString());
                }
                break;
            case "TIPO":
                if (objeto instanceof Transaccion) {
                    ((Transaccion) objeto).setTipo(buffer.toString());
                }
                break;
            case "MONTO":
                if (objeto instanceof Transaccion) {
                    ((Transaccion) objeto).setMonto(this.conv.stringToDouble(buffer.toString()));
                }
                break;
            case "CAJERO-ID":
                if (objeto instanceof Transaccion) {
                    ((Transaccion) objeto).setIdCajero(this.conv.stringToLong(buffer.toString()));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.out.println("Error "+buffer.toString()+" Columna: "+e.getColumnNumber()+ " Linea: "+e.getLineNumber());
        errores.add("Error "+buffer.toString()+" Columna: "+e.getColumnNumber()+ " Linea: "+e.getLineNumber());
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println("Error Fatal "+buffer.toString()+" Columna: "+e.getColumnNumber()+ " Linea: "+e.getLineNumber());
        errores.add("Error Fatal "+buffer.toString()+" Columna: "+e.getColumnNumber()+ " Linea: "+e.getLineNumber());
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.out.println("Warning "+buffer.toString()+" Columna: "+e.getColumnNumber()+ " Linea: "+e.getLineNumber());
        errores.add("Warning "+buffer.toString()+" Columna: "+e.getColumnNumber()+ " Linea: "+e.getLineNumber());
    }

    /**
     * RETORNA EL BANCO GENERADO EN BASE AL ARCHIVO DE LECTURA
     *
     * @return
     */
    public Banco getBanco() {
        return banco;
    }

    private void asignarCodigo() {
        if (presedencia.equals("CLIENTE")) {
            if (objeto instanceof Cliente) {
                int ultimaCuenta = ((Cliente) objeto).getCuentas().size();
                if (ultimaCuenta != 0) {
                    ultimaCuenta = ultimaCuenta - 1;
                    ((Cliente) objeto).getCuentas().get(ultimaCuenta).setCodigo(this.conv.stringToLong(buffer.toString()));
                }
            }
        } else {
            if (this.objeto instanceof UsuarioDeSistema) {
                ((UsuarioDeSistema) this.objeto).setCodigo(this.conv.stringToLong(buffer.toString()));
            }
            if (this.objeto instanceof Transaccion) {
                ((Transaccion) this.objeto).setCodigo(this.conv.stringToLong(buffer.toString()));
            }
        }
    }

    private void asignarTurno() {
        if (objeto instanceof Gerente) {
            ((Gerente) objeto).setTurno(buffer.toString());
        }
        if (objeto instanceof Cajero) {
            ((Cajero) objeto).setTurno(buffer.toString());
        }
    }

}
