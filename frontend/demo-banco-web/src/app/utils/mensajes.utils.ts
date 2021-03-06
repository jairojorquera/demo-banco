import Swal from "sweetalert2";

export class Mensajes {

    constructor() {

    };

    static loading() {
        Swal.fire({
            title: 'Cargando'
        });
        Swal.showLoading();
    }

    static stopLoading() {
        Swal.close();

    }


    public validacionesPendientes() {

        Swal.fire({
            title: 'Datos inválidos',
            icon: 'warning',
            timer: 10000,
            confirmButtonText: 'Cerrar',
            text: "Por favor corrija los valores indicados en el formulario"
        })

    }


    static warningOperacion(mensajes: string[]) {
        let htmlMensaje:string= "";
        mensajes.forEach(m => htmlMensaje += m + "<br/>");

        Swal.fire({
            title: "Problemas:",
            icon: 'warning',
            timer: 20000,
            confirmButtonText: 'Cerrar',
            html: htmlMensaje
        })

    }

    static errorOperacion(mensajes: string[]) {
        let htmlMensaje = "Lamentamos informar que la operación solicitada no se pudo realizar";
        if (mensajes.length === 1) {
            htmlMensaje += ": " + mensajes;
        } else if (mensajes.length > 1) {
            htmlMensaje += ": " + mensajes.map(function (mensaje) {
                return mensaje + "<br/>";
            });

        }

        Swal.fire({
            title: "Error:",
            icon: 'error',
            timer: 20000,
            confirmButtonText: 'Cerrar',
            html: htmlMensaje
        })

    }


}
