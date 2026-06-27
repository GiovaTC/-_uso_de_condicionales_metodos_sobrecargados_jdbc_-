package dao;

import conexion.Conexion;
import modelo.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonaDAO {

    public  void guardar(Persona persona) {

        try (Connection cn = Conexion.conectar()) {

            String sql =
                    "INSERT INTO PERSONAS(NOMBRE,EDAD,GENERO,MENSAJE) VALUES(?,?,?,?)";

            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, persona.getNombre());
            ps.setInt(2, persona.getEdad());
            ps.setString(3, persona.getGenero());
            ps.setString(4, persona.getMensaje());

            ps.executeUpdate();

            System.out.println("Registro almacenado! ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  void listar() {
        try (Connection cn = Conexion.conectar()) {

            String sql = "SELECT * FROM PERSONAS";

            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                System.out.println("-----------------------");
                System.out.println("ID: "+rs.getInt("ID"));
                System.out.println("Nombre: "+rs.getString("NOMBRE"));
                System.out.println("Edad: "+rs.getInt("EDAD"));
                System.out.println("Genero: "+rs.getString("GENERO"));
                System.out.println("Mensaje: "+rs.getString("MENSAJE"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
