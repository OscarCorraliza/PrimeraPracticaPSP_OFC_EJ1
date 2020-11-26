package modelo;


import com.github.javafaker.Faker;
import dao.ConexionBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Hilo extends Thread {
    private int principio, fin;
    ConexionBBDD conexionBBDD = new ConexionBBDD();
    PreparedStatement preparedStatementInsertar = null;
    Faker faker = null;

    //Le pasamos al hilo el principio y el fin del bucle con el número exacto de cuantos resgitros va a añadir cada hilo a la base de datos. 
    public Hilo (int principio, int fin) {
        this.principio = principio;
        this.fin = fin;
    }

    @Override
    public void run() {
        super.run();

        rellenarBBDD();
    }

    /**Método para rellenar la base de datos a partir de un numero creado aleatoriamente entre 10 y 1000 (tabla ingresos)
    y un e-mail generado aleatoriamente con la libreria faker (tabla e-mail) utilizando una prepared statement.*/
    public void rellenarBBDD(){

        for (int x = principio; x < fin; x++){

            faker = new Faker();
            String email = faker.internet().emailAddress();
            int ingreso = (int) ((Math.random() * (1000 - 10)) + 10);

            try{
                conexionBBDD.getConexion();
                Connection conexionAuxiliar = conexionBBDD.getConexion();
                preparedStatementInsertar = conexionAuxiliar.prepareStatement("INSERT INTO `empleados` (`EMAIL`, `INGRESOS`) VALUES (?,?)");

                preparedStatementInsertar.setString(1, email);
                preparedStatementInsertar.setInt(2, ingreso);

                preparedStatementInsertar.execute();
            }catch (SQLException e){

            }
        }

    }
}
