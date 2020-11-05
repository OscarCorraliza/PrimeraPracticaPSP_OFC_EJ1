package modelo;


import com.github.javafaker.Faker;
import dao.ConexionBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Hilo extends Thread {
    private int principio, fin;
    ConexionBBDD conexionBBDD = new ConexionBBDD();
    PreparedStatement psInsertar = null;
    Faker faker = null;

    public Hilo (int principio, int fin) {
        this.principio = principio;
        this.fin = fin;
    }

    @Override
    public void run() {
        super.run();

        rellenarBBDD();
    }

    public void rellenarBBDD(){

        for (int x = principio; x < fin; x++){

            faker = new Faker();
            String email = faker.internet().emailAddress();
            int ingreso = (int) ((Math.random() * (1000 - 10)) + 10);

            try{
                conexionBBDD.getConexion();
                Connection conexionAuxiliar = conexionBBDD.getConexion();
                psInsertar = conexionAuxiliar.prepareStatement("INSERT INTO `empleados` (`EMAIL`, `INGRESOS`) VALUES (?,?)");

                psInsertar.setString(1, email);
                psInsertar.setInt(2, ingreso);

                psInsertar.execute();
            }catch (SQLException e){

            }
        }

    }
}
