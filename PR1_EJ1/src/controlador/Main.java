package controlador;

import modelo.Hilo;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Semaphore semaphore = new Semaphore(1);
        //Hilo hilo = new Hilo();

        System.out.println("Cuantos registros desea introducir --> ");
        int numeroRegistros = scanner.nextInt();

        System.out.println("Con cuantos hilos lo desea --> ");
        int numeroHilos = scanner.nextInt();

        int resto = numeroRegistros%numeroHilos;
        int incrementoBucle = (numeroRegistros/numeroHilos);
        int principio = 0;
        int fin = incrementoBucle;

        System.out.println(resto);

        for (int i = 1; i <= numeroHilos; i++) {

            if(i == numeroHilos){
                principio = incrementoBucle * i;
                fin = principio + incrementoBucle + resto;
            } else {
                principio = incrementoBucle * i;
                fin = principio + incrementoBucle;
            }

            Hilo hilo = new Hilo(principio, fin);

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
