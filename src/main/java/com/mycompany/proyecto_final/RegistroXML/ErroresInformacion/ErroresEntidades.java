package com.mycompany.proyecto_final.RegistroXML.ErroresInformacion;

public class ErroresEntidades {
    private int lineaError;
    private String codigo;
    private String entidad;
    
    private String busquedaEntidad = "<"+entidad+">";
    private String busquedaCode ="<CODIGO>"+codigo+"</CODIGO>";
    private String descripcion;

    public ErroresEntidades() {
    }

    public ErroresEntidades(int lineaError, String descripcion) {
        this.lineaError = lineaError;
        this.descripcion = descripcion;
    }

    public int getLineaError() {
        return lineaError;
    }

    public void setLineaError(int lineaError) {
        this.lineaError = lineaError;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getBusquedaEntidad() {
        return busquedaEntidad;
    }

    public void setBusquedaEntidad(String busquedaEntidad) {
        this.busquedaEntidad = busquedaEntidad;
    }

    public String getBusquedaCode() {
        return busquedaCode;
    }

    public void setBusquedaCode(String busquedaCode) {
        this.busquedaCode = busquedaCode;
    }

    @Override
    public String toString() {
        return "ErroresEntidades{" + "lineaError=" + lineaError + ", codigo=" + codigo + ", entidad=" + entidad + ", busquedaEntidad=" + busquedaEntidad + ", busquedaCode=" + busquedaCode + ", descripcion=" + descripcion + '}';
    }
}
