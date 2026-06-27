package util;

public class Evaluador {

    // metodo sin parametros
    public void mostrarMensaje() {

        System.out.println("Metodo sin parametros. ");
    }

    // metodo con parametros .
    public void mostrarMensaje(String nombre) {

        System.out.println("Bienvenido" + nombre);
    }

    public String evaluarEdad(int edad) {
        // condicional 1 .

        if(edad>=18) {
            return "Mayor de edad";
        }

        return "Menor de edad";
    }

    public String evaluarGenero(String genero) {
        // condicional 2.

        if(genero.equalsIgnoreCase("M")) {
            return "MASCULINO";
        }else{
            return "FEMENINO";
        }
    }

    public String categoriaEdad(int edad) {
        // condicional 3 .

        switch(edad/10){
            case 0:
            case 1:
                return "Niño";
            case 2:
                return "Joven";

            case 3:
            case 4:
            case 5:
                return "Adulto";

            default:
                return "Adulto mayor";
        }
    }
}
