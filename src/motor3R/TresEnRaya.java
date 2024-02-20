package motor3R;

import java.util.Random;

/**
 * La clase TresEnRaya representa el juego del tres en raya.
 * Dos jugadores (humano y máquina) compiten en un tablero 3x3.
 */
public class TresEnRaya {
    private char[][] tablero; // Tablero de juego
    private char humano; // Ficha del jugador (x)
    private char maquina; // Ficha de la máquina (o)
    private int puntosJugador; // Puntuación del jugador
    private int puntosMaquina; // Puntuación de la máquina
    private String ganador; // Ganador de la partida

    /**
     * Obtiene el símbolo de la máquina.
     * 
     * @return El símbolo de la máquina.
     */
    public char getMaquina() {
        return maquina;
    }

    /**
     * Obtiene el símbolo del humano.
     * 
     * @return El símbolo del humano.
     */
    public char getHumano() {
        return humano;
    }

    /**
     * Obtiene el ganador de la partida.
     * 
     * @return El ganador de la partida.
     */
    public String getGanador() {
        return ganador;
    }

    /**
     * Obtiene la puntuación del jugador.
     * 
     * @return La puntuación del jugador.
     */
    public int getPuntosJugador() {
        return puntosJugador;
    }

    /**
     * Obtiene la puntuación de la máquina.
     * 
     * @return La puntuación de la máquina.
     */
    public int getPuntosMaquina() {
        return puntosMaquina;
    }

    /**
     * Constructor de la clase TresEnRaya. Inicializa el tablero y asigna los
     * símbolos a los jugadores.
     * 
     * @param humano  El símbolo del jugador humano.
     * @param maquina El símbolo del jugador máquina.
     */
    public TresEnRaya(char humano, char maquina) {
        this.tablero = new char[3][3];
        this.humano = humano;
        this.maquina = maquina;
        this.maquina = maquina;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = '_';
            }
        }
    }

    /**
     * Muestra el estado del tablero de juego.
     * 
     * @return Una representación del tablero como una cadena de caracteres.
     */
    public String mostrarTablero() {
        String mostrarTablero = "";
        for (int i = 0; i < tablero.length; i++) {
            mostrarTablero += tablero[i][0] + " | " + tablero[i][1] + " | " + tablero[i][2] + "\n";
        }
        return mostrarTablero;
    }

    /**
     * Realiza el turno de la máquina, colocando su símbolo en una posición
     * aleatoria válida del tablero.
     */
    public void turnoMaquina() { // throws InterruptedException{
        Random rdm = new Random();
        int fila = rdm.nextInt(3);
        int col = rdm.nextInt(3);
        if (tablero[fila][col] == '_') {
            tablero[fila][col] = maquina;
        } else {
            turnoMaquina();
        }
    }

    /**
     * Inserta un símbolo en la posición especificada del tablero.
     * 
     * @param fila    La fila en la que se insertará el símbolo.
     * @param col     La columna en la que se insertará el símbolo.
     * @param jugador El símbolo del jugador (humano o máquina).
     */
    public void insertarFicha(int fila, int col, char jugador) {
        tablero[fila][col] = jugador;
    }

    /**
     * Comprueba si hay un ganador o un empate en el juego actual verificando las
     * filas, columnas, diagonales y si el tablero está completo.
     * 
     * @return true si hay un ganador o un empate, false de lo contrario.
     */
    public boolean comprobarGanador() {
        // Verificar filas
        for (int i = 0; i < tablero.length; i++) {
            String fila = "";
            for (int j = 0; j < tablero.length; j++) {

                fila += tablero[i][j] + " ";
                if (fila.equals("x x x ")) {
                    ganador = "Jugador";
                    puntosJugador++;
                    return true;
                } else if (fila.equals("o o o ")) {
                    ganador = "Máquina";
                    puntosMaquina++;
                    return true;
                }
            }
        }

        // Verificar columnas
        for (int i = 0; i < tablero.length; i++) {
            String columna = "";
            for (int j = 0; j < tablero.length; j++) {
                columna += tablero[j][i] + " ";
                if (columna.equals("x x x ")) {
                    ganador = "Jugador";
                    puntosJugador++;
                    return true;
                } else if (columna.equals("o o o ")) {
                    ganador = "Máquina";
                    puntosMaquina++;
                    return true;
                }
            }
        }

        // Verificar diagonales
        String diagonal1 = "";
        String diagonal2 = "";
        for (int i = 0; i < tablero.length; i++) {
            diagonal1 += tablero[i][i] + " ";
        }
        if (diagonal1.equals("x x x ")) {
            ganador = "Jugador";
            puntosJugador++;
            return true;
        } else if (diagonal1.equals("o o o ")) {
            ganador = "Máquina";
            puntosMaquina++;
            return true;
        }
        for (int i = 0; i < tablero.length; i++) {
            diagonal2 += tablero[i][tablero.length - i - 1] + " ";
        }
        if (diagonal2.equals("x x x ")) {
            ganador = "Jugador";
            puntosJugador++;
            return true;
        } else if (diagonal2.equals("o o o ")) {
            ganador = "Máquina";
            puntosMaquina++;
            return true;
        }

        // Si no hay ganador, verificar empate
        boolean tableroCompleto = true;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == '_') {
                    tableroCompleto = false;
                    break;
                }
            }
        }
        if (tableroCompleto) {
            ganador = "Empate";
            return true;
        }

        // Si no hay ganador ni empate
        return false;
    }

    /**
     * Actualiza la puntuación según el ganador.
     * 
     * @param ganador Símbolo del ganador de la partida ('x' o 'o').
     */
    public void actualizarPuntuacion(char ganador) {
        if (ganador == humano) {
            puntosJugador++;
        } else if (ganador == maquina) {
            puntosMaquina++;
        }
    }

}
