package controlador;

import modelo.Hilo;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Semaphore semaphore = new Semaphore(1);

        System.out.println("Cuantos registros desea introducir --> ");
        int numeroRegistros = scanner.nextInt();

        System.out.println("Con cuantos hilos lo desea --> ");
        int numeroHilos = scanner.nextInt();

        //Declaracion de variables.
        int resto = numeroRegistros%numeroHilos;
        int incrementoBucle = (numeroRegistros/numeroHilos);
        int principio = 0;
        int fin = incrementoBucle;

        /**Realizamos un bucle for para crear tantos hilos como quiere el usuario. Usamos las condiciones if else para que
         * cuando llegue el ultimo hilo podamos añadirlo el resto de inserts que quedan por hacer
         (en caso de que el numeros de registros y el numero de hilos indicados por el usuario no sea una division exacta).*/


        for (int i = 1; i <= numeroHilos; i++) {

            if(i == numeroHilos){
                principio = incrementoBucle * i;
                fin = principio + incrementoBucle + resto;
            } else {
                principio = incrementoBucle * i;
                fin = principio + incrementoBucle;
            }

            Hilo hilo = new Hilo(principio, fin);

            //Necesario el uso de semaforos ya que sino los hilos moririan por inanición.
            try {
                semaphore.acquire();
                hilo.start();
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
