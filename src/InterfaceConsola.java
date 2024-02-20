import java.util.Scanner;

import motor3R.TresEnRaya;

public class InterfaceConsola {
    TresEnRaya tr = new TresEnRaya('x', 'o');
    Scanner sc = new Scanner(System.in);
    public int getPuntosJugador() {
        return tr.getPuntosJugador();
    }
    
    public int getPuntosMaquina() {
        return tr.getPuntosMaquina();
    }
    

    InterfaceConsola() throws InterruptedException {
        iniciarJuego();
     
        while (!tr.comprobarGanador()){ 
            turnoJugador();
            if (tr.comprobarGanador()) {
                break;
            }
            System.out.println("Juega la máquina: ");
            tr.turnoMaquina();
            Thread.sleep(1000);
            System.out.println(tr.mostrarTablero());

        }
        System.out.println("Ganador: "+tr.getGanador());
        
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        char respuesta = 'S';
        while (respuesta=='S') {
            new InterfaceConsola();
            System.out.println("¿Otra partida? (S/N)");
            respuesta = sc.nextLine().toUpperCase().charAt(0);
        }
  
    }

    void iniciarJuego() {
        System.out.println("Nueva partida. Tablero vacío:");
        tr.mostrarTablero();
    }

    void turnoJugador() {
        System.out.println("Jugador, indicar coordenadas de tu movimiento");
        System.out.print("Fila: ");
        int fila = sc.nextInt();
        System.out.print("Columna: ");
        int col = sc.nextInt();
        tr.insertarFicha(fila, col, tr.getHumano());
        System.out.println(tr.mostrarTablero());
    }

}
