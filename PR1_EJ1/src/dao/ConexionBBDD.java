package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionBBDD {
    PreparedStatement psInsertar = null;

    //Metodo para conectarse a la base de datos.
    public Connection getConexion() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/BBDD_PSP_1", "DAM2020_PSP", "DAM2020_PSP");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
