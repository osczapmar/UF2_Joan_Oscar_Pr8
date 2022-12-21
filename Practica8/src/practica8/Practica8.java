package practica8;

public class Practica8 {

    public static void main(String[] args) {
        // TODO code application logic here
    }

    public static int Jugada(int[][] tablero, int columna, int jugador) {
        int fila = -1;
        boolean corte = true;
        try {
            for (int i = tablero.length - 1; i >= 0 && corte; --i) {
                if (tablero[i][columna - 1] == 0) {
                    tablero[i][columna - 1] = jugador;
                    fila = i + 1;
                    corte = false;
                }
            }
        } catch (Exception e) {
            fila = -1;
        }
        return fila;
    }

    public static void Dibuja(int[][] tablero) {
        for (int i = 0; i < tablero.length; ++i) {
            for (int j = 0; j < tablero[i].length; ++j) {
                System.out.print(tablero[i][j]);
            }
            System.out.println("");
        }
    }

}
