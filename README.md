# -_uso_de_condicionales_metodos_sobrecargados_jdbc_- :.
# Proyecto Java 21:

<img width="1254" height="1254" alt="image" src="https://github.com/user-attachments/assets/6158755c-4c85-43bb-9d08-1c6180900b5c" />  

```
# Uso de Condicionales, Métodos Sobrecargados y JDBC:

## Descripcion:

Este proyecto desarrolla una aplicación en **Java 21** que utiliza **Database** mediante **JDBC**, permitiendo demostrar los conceptos fundamentales de programación orientada a objetos y estructuras de control.

El proyecto cumple con los siguientes requisitos:

- ✅ Utiliza tres estructuras condicionales (`if`, `if-else` y `switch`).
- ✅ Demuestra el comportamiento de un método de dos maneras diferentes:
  - Método sin parámetros.
  - Método con parámetros.
- ✅ Procesa la información utilizando una base de datos mediante JDBC.
- ✅ Guarda la información en una tabla.
- ✅ Consulta y muestra los datos almacenados.

---

# Estructura del proyecto

```text
Condicionales
│
├── src
│   ├── conexion
│   │      Conexion.java
│   │
│   ├── modelo
│   │      Persona.java
│   │
│   ├── dao
│   │      PersonaDAO.java
│   │
│   ├── util
│   │      Evaluador.java
│   │
│   └── Principal.java
│
└── mysql-connector-j-9.3.0.jar
```

---

# Script

```sql
-- ===========================================
-- Crear Base de Datos
-- ===========================================

CREATE DATABASE IF NOT EXISTS personas_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE personas_db;

-- ===========================================
-- Crear Tabla
-- ===========================================

CREATE TABLE personas (

    id INT NOT NULL AUTO_INCREMENT,

    nombre VARCHAR(100) NOT NULL,

    edad INT NOT NULL,

    genero VARCHAR(20) NOT NULL,

    mensaje VARCHAR(200),

    PRIMARY KEY(id)

);

-- ===========================================
-- Datos de prueba
-- ===========================================

INSERT INTO personas(nombre,edad,genero,mensaje)
VALUES
('Carlos',25,'Masculino','Mayor de edad'),
('Laura',17,'Femenino','Menor de edad'),
('Andrés',35,'Masculino','Adulto'),
('María',15,'Femenino','Estudiante');
```

---

# Clase Persona

```java
package modelo;

public class Persona {

    private int id;
    private String nombre;
    private int edad;
    private String genero;
    private String mensaje;

    public Persona() {
    }

    public Persona(String nombre,int edad,String genero,String mensaje){

        this.nombre=nombre;
        this.edad=edad;
        this.genero=genero;
        this.mensaje=mensaje;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre=nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad=edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero=genero;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje=mensaje;
    }

}
```

---

# Conexión bd

```java
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/personas_db?useSSL=false&serverTimezone=UTC";

    private static final String USER = "root";

    private static final String PASSWORD = "123456";

    public static Connection conectar() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {

            System.out.println("No se encontró el Driver MySQL");

            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Error de conexión");

            e.printStackTrace();

        }

        return null;
    }

    public static void cerrar(Connection conexion) {

        try {

            if (conexion != null) {

                conexion.close();

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}
```

---

# Clase Evaluador

En esta clase se implementan los **tres condicionales** solicitados y se demuestra el uso de **métodos sobrecargados**.

```java
package util;

public class Evaluador {

    // Método sin parámetros
    public void mostrarMensaje(){

        System.out.println("Método sin parámetros.");

    }

    // Método con parámetros
    public void mostrarMensaje(String nombre){

        System.out.println("Bienvenido "+nombre);

    }

    public String evaluarEdad(int edad){

        // Condicional 1

        if(edad>=18){

            return "Mayor de edad";

        }

        return "Menor de edad";

    }

    public String evaluarGenero(String genero){

        // Condicional 2

        if(genero.equalsIgnoreCase("M")){

            return "Masculino";

        }else{

            return "Femenino";

        }

    }

    public String categoriaEdad(int edad){

        // Condicional 3

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
                return "Adulto Mayor";

        }

    }

}
```

---

# DAO 

```java
package dao;

import conexion.Conexion;
import modelo.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonaDAO {

    public void guardar(Persona persona){

        try(Connection cn=Conexion.conectar()){

            String sql =
            "INSERT INTO PERSONAS(NOMBRE,EDAD,GENERO,MENSAJE) VALUES(?,?,?,?)";

            PreparedStatement ps=cn.prepareStatement(sql);

            ps.setString(1,persona.getNombre());
            ps.setInt(2,persona.getEdad());
            ps.setString(3,persona.getGenero());
            ps.setString(4,persona.getMensaje());

            ps.executeUpdate();

            System.out.println("Registro almacenado.");

        }catch(Exception e){

            e.printStackTrace();

        }

    }

    public void listar(){

        try(Connection cn=Conexion.conectar()){

            String sql="SELECT * FROM PERSONAS";

            PreparedStatement ps=cn.prepareStatement(sql);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                System.out.println("-----------------------");
                System.out.println("ID: "+rs.getInt("ID"));
                System.out.println("Nombre: "+rs.getString("NOMBRE"));
                System.out.println("Edad: "+rs.getInt("EDAD"));
                System.out.println("Genero: "+rs.getString("GENERO"));
                System.out.println("Mensaje: "+rs.getString("MENSAJE"));

            }

        }catch(Exception e){

            e.printStackTrace();

        }

    }

}
```

---

# Clase Principal

```java
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
```

---

# Resultado esperado

```text
Método sin parámetros.

Nombre:
Carlos

Bienvenido Carlos

Edad:
24

Genero(M/F):
M

Registro almacenado.

Datos registrados

----------------------
ID: 1

Nombre: Carlos

Edad: 24

Genero: M

Mensaje:
Mayor de edad - Masculino - Joven
```

---

# Requisitos cumplidos

| Requisito | Implementación |
|-----------|----------------|
| Método sin parámetros | `mostrarMensaje()` |
| Método con parámetros | `mostrarMensaje(String nombre)` |
| Condicional 1 | `if (edad >= 18)` |
| Condicional 2 | `if - else` para el género |
| Condicional 3 | `switch` para la categoría de edad |
| Base de datos | JDBC (`mysql-connector-j-9.3.0.jar`) |
| Inserción de datos | `INSERT INTO PERSONAS` |
| Consulta de datos | `SELECT * FROM PERSONAS` |
| Programación orientada a objetos | Clases `Persona`, `PersonaDAO`, `Evaluador` y `Conexion` |

---

# Tecnologías utilizadas

- Java 21
- Database
- JDBC
- JDBC Driver (`mysql-connector-j-9.3.0.jar`)
- Programación Orientada a Objetos (POO)

---

# Conceptos demostrados

Este proyecto permite practicar los siguientes conceptos:

- Sobrecarga de métodos.
- Métodos con y sin parámetros.
- Estructuras condicionales (`if`, `if-else`, `switch`).
- Encapsulamiento mediante clases.
- Programación Orientada a Objetos.
- Acceso a bases de datos con JDBC.
- Inserción y consulta de registros en bd.
- Uso de `PreparedStatement`.
- Manejo de `ResultSet`.
- Gestión de conexiones mediante `Connection`.

---

# Conclusión

Este ejemplo constituye una práctica académica completa para comprender la integración de **Java 21** con **Database** mediante **JDBC**, combinando 
programación orientada a objetos, sobrecarga de métodos, estructuras condicionales y persistencia de datos. Asimismo, sirve como base para proyectos de mayor 
complejidad que requieran almacenamiento y consulta de información en bases de datos relacionales . 
:. . / . 
