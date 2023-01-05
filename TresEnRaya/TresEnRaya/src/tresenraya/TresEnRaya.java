package tresenraya;

import static tresenraya.Utils.*;
import java.util.Scanner;

/**
 * Juego del Tres en Raya
 *
 * @author ÓscarZapateroMarchal/JoanGuerreroSanchez
 */
public class TresEnRaya {

    public static Scanner scan = new Scanner(System.in);

    public static void Juego() {
        boolean newgame = true;
        while (newgame) {
            //Dimensionar tablero
            int filas, columnas;
            filas = LlegirInt("Dimensiones del tablero\nIntroduce el número de filas del tablero: ");
            while (filas < 4) {
                MostrarError("El tablero ha de tener un mínimo de 4 filas");
                filas = LlegirInt("Introduce el número de filas del tablero: ");
            }
            columnas = LlegirInt("Introduce el número de columnas del tablero: ");
            while (columnas < 4) {
                MostrarError("El tablero ha de tener un mínimo de 4 columnas");
                columnas = LlegirInt("Introduce el número de columnas del tablero: ");
            }

            int[][] tablero = new int[filas][columnas];

            final int NUMJUGADORES = 2;
            int ganador, fila, columna;
            String keepPlaying = "";
            ganador = 0;

            boolean raya = false;
            boolean newturn = true;

            while (newturn) {
                for (int i = 1; i <= NUMJUGADORES && raya == false; i++) {

                    columna = IntoRange("\nIntroduce la ficha en una columna, jugador " + i + ": ", "Fuera del lí­mite de columnas", 1, columnas);
                    fila = Jugada(tablero, columna - 1, i);
                    if (fila == -1) {
                        MostrarError("Columna llena, prueba de nuevo");
                        columna = IntoRange("Introduce la ficha en una columna: ", "Fuera del lí­mite de columnas", 1, columnas);
                        fila = Jugada(tablero, (columna - 1), i);

                    }
                    Dibuja(tablero);
                    raya = EnRaya(tablero, fila, (columna - 1));
                    if (raya == true) {
                        newturn = false;
                        ganador = i;
                        System.out.println("El jugador " + ganador + " ha hecho raya!");
                    }

                }

            }
            while (!(keepPlaying.equals("si") || keepPlaying.equals("no"))) {
                System.out.println("\n¿Quieres jugar otra partida?\n si/no");
                keepPlaying = scan.nextLine();
                if (keepPlaying.equals("no")) {
                    newgame = false;
                }
            }

        }
    }

    

    

    public static boolean EnRaya(int[][] tablero, int fila, int columna) {
        boolean limit = false;
        boolean raya = false;
        int columaux, filaux, dentroraya;
        columaux = columna;
        dentroraya = 4; //localizar la primera posición de una raya: un máximo de tres posiciones atrás
        filaux = fila;
        int jugador = tablero[fila][columna];
        //Comprobar fila en raya
        while (columaux >= 1 && tablero[fila][columaux - 1] == jugador && dentroraya > 1) {
            if (tablero[fila][columaux - 1] == jugador && dentroraya > 1) {
                columaux--;
                dentroraya--;
            }
            
        }
        if (columaux <= tablero[fila].length - 4) {
            if (tablero[fila][columaux] == jugador
                    && tablero[fila][columaux + 1] == jugador
                    && tablero[fila][columaux + 2] == jugador
                    && tablero[fila][columaux + 3] == jugador) {
                raya = true;
            }
        }
        //Comprobar columna en raya
        if (raya == false) {
            dentroraya = 3;
            limit = false;
            while (filaux >= 1 && tablero[filaux - 1][columna] == jugador) {
                if (tablero[filaux - 1][columna] == jugador && dentroraya > 1) {
                    filaux--;
                    dentroraya--;
                }
            }
            if (filaux <= tablero.length - 4) {
                if (tablero[filaux][columna] == jugador
                        && tablero[filaux + 1][columna] == jugador
                        && tablero[filaux + 2][columna] == jugador
                        && tablero[filaux + 3][columna] == jugador) {
                    raya = true;
                }
            }
        }
        return raya;
    }

    public static void Dibuja(int[][] tablero) {
        for (int i = 0; i < tablero.length; ++i) {
            for (int j = 0; j < tablero[i].length; ++j) {
                if (tablero[i][j] == 1) {
                    System.out.print(BLUE + tablero[i][j] + RESET);
                } else if (tablero[i][j] == 2) {
                    System.out.print(RED + tablero[i][j] + RESET);
                } else {
                    System.out.print(tablero[i][j]);
                }
            }
            System.out.println("");
        }
    }

    public static int Jugada(int[][] tablero, int columna, int jugador) {
        int filaCount = 0;
        boolean finTablero = false;
        if (tablero[filaCount][columna] == 0) {

            while (finTablero == false ) {
                if (tablero[filaCount + 1][columna] == 0 && filaCount == tablero.length - 2) {
                    filaCount += 1;
                    finTablero = true;
                } else if (tablero[filaCount + 1][columna] == 0) {
                    filaCount += 1;
                    if (tablero[filaCount + 1][columna] != 0) {
                        finTablero = true;
                    }
                }
                else {
                    finTablero = true;
                }

            }

            tablero[filaCount][columna] = jugador;
        } else {
            filaCount = -1;
        }
        return filaCount;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Juego();

    }

}
