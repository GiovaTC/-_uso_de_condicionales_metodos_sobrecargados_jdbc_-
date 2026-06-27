import dao.PersonaDAO;
import modelo.Persona;
import util.Evaluador;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        Evaluador evaluador=new Evaluador();
        PersonaDAO dao=new PersonaDAO();

        evaluador.mostrarMensaje();

        System.out.print("Nombre: ");
        String nombre=sc.nextLine();

        evaluador.mostrarMensaje(nombre);

        System.out.print("Edad: ");
        int edad=sc.nextInt();
        sc.nextLine();

        System.out.print("Genero(M/F): ");
        String genero=sc.nextLine();

        String mensaje =
                evaluador.evaluarEdad(edad)
                        +" - "
                        +evaluador.evaluarGenero(genero)
                        +" - "
                        +evaluador.categoriaEdad(edad);

        Persona persona =
                new Persona(nombre,edad,genero,mensaje);

        dao.guardar(persona);

        System.out.println();
        System.out.println("Datos registrados");

        dao.listar();
    }
}