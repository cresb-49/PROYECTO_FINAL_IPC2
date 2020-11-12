const exprecionesRegulares = {
    texto: /^([\sA-Za-z0-9])+$/,
    nDpi: /^[0-9]{13}$/,
    sexo: /^(([M][a][s][c][u][l][i][n][o])|([F][e][m][e][n][i][n][o]))$/,
    fecha: /^[0-9]{4}\-[0-9]{2}\-[0-9]{2}$/,
    turno: /^(([M][A][T][U][T][I][N][O])|([V][E][S][P][E][R][T][I][N][O]))$/,
    direc: /^([\sA-Za-z0-9-])+$/,
};

function validarAcualizacionCliente(){
    var nombre = document.getElementById("nombreEntidad").value;
    var sexo = document.getElementById("sexo").value;
    var direccion = document.getElementById("direccion").value;
    var dpi = document.getElementById("numeroDPI").value;
    var fechaNacimiento = document.getElementById("fechaNacimiento").value;

    var errores="";

    if(nombre ===""||sexo ===""||direccion === ""||dpi === ""||fechaNacimiento === ""){
        alert("Todos los campos son obligatorios");
        return false;
    }else{
        if(nombre.length>100){
            errores = errores + "\n- El nombre debe contener como maximo 100 caracteres";
        }
        if(!exprecionesRegulares.texto.test(nombre)){
            errores = errores + "\n- El nombre no es valido no debe contener simbolos como #$@&";
        }
        if(!exprecionesRegulares.sexo.test(sexo)){
            errores = errores + "\n- Debe de selecionar un sexo";
        }
        if(direccion.length>200){
            errores = errores + "\n- La dereccion debe contener como maximo 200 caracteres";
        }
        if(!exprecionesRegulares.direc.test(direccion)){
            errores = errores + "\n- La direccion no es valida no debe de contener simbolos como !!@#";
        }
        if (!exprecionesRegulares.nDpi.test(dpi)){
            errores = errores + "\n- El numero de DPI debe tener 13 digitos";
        }
        if (!exprecionesRegulares.fecha.test(fechaNacimiento)){
            errores = errores + "\n- La fecha introducida no es correcta";
        }
    }
    
    if(errores.length>0){
        alert(errores);
        return false;
    }

    return true;
}

function validarActualizacionTabajador(){

    var nombre = document.getElementById("nombreEntidad").value;
    var sexo = document.getElementById("sexo").value;
    var direccion = document.getElementById("direccion").value;
    var dpi = document.getElementById("numeroDPI").value;
    var tipoTurno = document.getElementById("TipoTurno").value;

    var errores="";

    if(nombre === "" ||sexo===""||direccion===""||dpi===""||tipoTurno===""){
        alert("Todos los campos son obligatorios");
        return false;
    }else{
        if(nombre.length>100){
            errores = errores + "\n- El nombre debe contener como maximo 100 caracteres";
        }
        if(!exprecionesRegulares.texto.test(nombre)){
            errores = errores + "\n- El nombre no es valido no debe contener simbolos como #$@&";
        }
        if(!exprecionesRegulares.sexo.test(sexo)){
            errores = errores + "\n- Debe de selecionar un sexo";
        }
        if(direccion.length>200){
            errores = errores + "\n- La dereccion debe contener como maximo 200 caracteres";
        }
        if(!exprecionesRegulares.direc.test(direccion)){
            errores = errores + "\n- La direccion no es valida no debe de contener simbolos como !!@#";
        }
        if (!exprecionesRegulares.nDpi.test(dpi)){
            errores = errores + "\n- El numero de DPI debe tener 13 digitos";
        }
        if (!exprecionesRegulares.turno.test(tipoTurno)){
            errores = errores + "\n- Debe de seleccionar un turno de trabajo";
        }
    }

    if(errores.length>0){
        alert(errores);
        return false;
    }

    return true;
}