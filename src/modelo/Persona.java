package modelo;

public class Persona {

    private int id;
    private String nombre;
    private int edad;
    private String genero;
    private String mensaje;

    public Persona() {

    }

    public Persona(String nombre, int edad, String genero, String mensaje) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.mensaje = mensaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
