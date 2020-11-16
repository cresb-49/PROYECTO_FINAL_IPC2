const exprecionesRegulares = {
    nDpi: /^[0-9]{13}$/,
    numeroEntero:/^[0-9]+$/,
    numeroDecimal: /^([0-9]+[\.][0-9]+|[0-9]+)$/,
};

function validarConsulta1() {
    var monto = document.getElementById("limiteMonetario").value;

    var errores = "";

    if (monto === "") {
        alert("Debe de introducir un cantidad");
        return false;
    } else {
        if (!exprecionesRegulares.numeroDecimal.test(monto)) {
            errores = errores + "\n- El limite debe se un cantidad numerica decimal o entera";
        }
    }
    if (errores.length > 0) {
        alert(errores);
        return false;
    }
    return true;
}