import Swal from "sweetalert2";

export class Mensajes {
    mensajes: string[];

    constructor(mensajes: string[]) {
        this.mensajes = mensajes;

    };

    public errorOperacion() {
        let htmlMensaje = "Lamentamos informar que la operación no pudo ser realizada";
        if(this.mensajes.length === 1){
            htmlMensaje+= ": " + this.mensajes;
        }else if(this.mensajes.length > 1){            
            htmlMensaje+= ": " + this.mensajes.map(function(mensaje) {
                return  mensaje + "<br/>";
            }) ;

        }


        Swal.fire({
            title: 'Ha ocurrido un problema',
            icon: 'error',
            timer: 20000,
            confirmButtonText: 'Cerrar',
            html: htmlMensaje
        })

    }


}
