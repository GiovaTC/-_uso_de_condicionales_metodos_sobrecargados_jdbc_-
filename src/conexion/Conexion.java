package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/personas_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Tapiero123";

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            System.out.println("No se encontro el driver MYSQL");

            e.printStackTrace();
        } catch (SQLException e) {

            System.out.println("Error de conexion");

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
