package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionBBDD {

    //Metodo para conectarse a la base de datos.
    public Connection getConexion() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/BBDD_PSP_1", "DAM2020_PSP", "DAM2020_PSP");

        } catch (SQLException e) {
            System.out.println("Ha ocurrido un error al conectarse a la base de datos.");
        }
        return connection;
    }
}
