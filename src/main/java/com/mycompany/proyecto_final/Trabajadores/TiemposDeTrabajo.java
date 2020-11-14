package com.mycompany.proyecto_final.Trabajadores;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TiemposDeTrabajo {

    private SimpleDateFormat parcerHora = new SimpleDateFormat("HH:mm:ss");

    /**
     * CONTRUCTOR VACIO DE LA ENTIDAD
     */
    public TiemposDeTrabajo() {

    }

    /**
     * RETORNA LA HORA DE INICIO DE TURNO
     * 
     * @param tipoTurno
     * @return
     */
    public String obtenerTiempoInicio(String tipoTurno) {
        if (tipoTurno.equals("MATUTINO")) {
            return java.time.LocalTime.of(6, 0, 0).toString();
        } 
        if (tipoTurno.equals("VESPERTINO")) {
            return java.time.LocalTime.of(13, 0, 0).toString();
        }
        return null;
    }

    /**
     * RETRONA LA HORA DE FIN DE TURNO
     * 
     * @param tipoTurno
     * @return
     */
    public String obtenerTiempoFin(String tipoTurno) {
        if (tipoTurno.equals("MATUTINO")) {
            return java.time.LocalTime.of(14, 30, 0).toString();
        }
        if (tipoTurno.equals("VESPERTINO")) {
            return java.time.LocalTime.of(22, 0, 0).toString();
        }
        return null;
    }

    /**
     * VALIDA EL ACCESO SEGUN EL TIPO DE TURNO
     */
    public boolean validarAcceso(String tipoTurno) {
        java.time.LocalTime time = java.time.LocalTime.now();

        if (tipoTurno.equals("MATUTINO")) {
            java.time.LocalTime minimo = java.time.LocalTime.of(6, 0, 0);
            java.time.LocalTime maximo = java.time.LocalTime.of(14, 30, 0);
            if(minimo.isBefore(time) && maximo.isAfter(time)){
                return true;
            }
        }
        if (tipoTurno.equals("VESPERTINO")) {
            java.time.LocalTime minimo = java.time.LocalTime.of(13, 0, 0);
            java.time.LocalTime maximo = java.time.LocalTime.of(22, 0, 0);
            if(minimo.isBefore(time) && maximo.isAfter(time)){
                return true;
            }
        }
        return false;
    }
}
