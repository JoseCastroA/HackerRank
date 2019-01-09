package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Jose Castro Arias
 */
public class Reinas {

    // Tamaño inicial y final del tablero
    static int tamañoTablero = 8;
    static int inicioTablero = 0;
    
    // Matriz que representa el tablero de ajedrez
    static int[][] matriz = new int[tamañoTablero][tamañoTablero];

    /* 
        Variables que representan la cantidad de movimientos posibles de la reina.
        Se inicializan 10000 para validar que los valores iniciales no cambien el problema.
    */
    static int right = 10000, left = 10000, down = 10000, up = 10000;
    static int abajoDerecha = 10000, arribaDerecha = 10000, abajoIzquierda = 10000, arribaIzquierda = 10000;

    // Posición de la reina en el tablero, n = fila, m = columna.
    static int n, m;

    /* 
        Main, se carga la matriz de un archivo plano
        Los ceros representan las casillas vacías del tablero
        El uno representa la posición de la reina en el tablero
        Los dos representan los obstáculos dentro del tablero
    */
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/test/text.txt");
        Scanner scan = new Scanner(file);
        for (int i = 0; i < tamañoTablero; i++) {
            for (int j = 0; j < tamañoTablero; j++) {
                matriz[i][j] = scan.nextInt();
                if (matriz[i][j] == 1) {
                    n = i;
                    m = j;
                }
            }
        }
        verificacionInicial();

        for (int i = 0; i < tamañoTablero; i++) {
            for (int j = 0; j < tamañoTablero; j++) {
                if (matriz[i][j] == 2) {
                    verificarObstaculo(i, j);
                }
            }
        }

        System.out.println("------------------------");
        imprimirValores();
    }

    /*
        Imprime los valores de los posibles movimientos actuales de la reina
    */
    public static void imprimirValores() {
        System.out.println("Derecha:" + right);
        System.out.println("Izquierda:" + left);
        System.out.println("Arriba:" + up);
        System.out.println("Abajo:" + down);

        System.out.println("Abajo-derecha:" + abajoDerecha);
        System.out.println("Arriba-derecha:" + arribaDerecha);
        System.out.println("Abajo-izquierda:" + abajoIzquierda);
        System.out.println("Arriba-izquierda:" + arribaIzquierda);
    }

    /*
        Verifica los posibles movimientos de la reina dentro del tablero (sin obstáculos)
    */
    public static void verificacionInicial() {
        right = checkRight(n, m, tamañoTablero);
        left = checkLeft(n, m, inicioTablero);
        up = checkUp(n, m, inicioTablero);
        down = checkDown(n, m, tamañoTablero);

        abajoDerecha = Math.min(down, right);
        arribaDerecha = Math.min(up, right);
        abajoIzquierda = Math.min(down, left);
        arribaIzquierda = Math.min(up, left);

        imprimirValores();
    }

    /*
        Verifica los movimientos de la reina a la derecha
    */
    public static int checkRight(int n, int m, int tamaño) {
        return Math.min(right, (tamaño - 1) - m);
    }

    /*
        Verifica los movimientos de la reina a la izquierda
    */
    public static int checkLeft(int n, int m, int tamaño) {
        return Math.min(left, m - tamaño);
    }

    /*
        Verifica los movimientos de la reina hacia arriba
    */
    public static int checkUp(int n, int m, int tamaño) {
        return Math.min(up, n - tamaño);
    }

    /*
        Verifica los movimientos de la reina hacia abajo
    */
    public static int checkDown(int n, int m, int tamaño) {
        return Math.min(down, (tamaño - 1) - n);
    }

    /*
        Imprime el tablero de ajedrez
    */
    public static void print() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /*
        Verifica el valor final de los movimientos posibles de la reina en la diagonal arriba derecha
        @param num Número que representa los movimiento posibles de la verificación del obstáculo
    */
    public static void arribaDerecha(int num) {
        arribaDerecha = Math.min(arribaDerecha, num);
    }

    /*
        Verifica el valor final de los movimientos posibles de la reina en la diagonal abajo derecha
        @param num Número que representa los movimiento posibles de la verificación del obstáculo
    */
    public static void abajoDerecha(int num) {
        abajoDerecha = Math.min(abajoDerecha, num);
    }

    /*
        Verifica el valor final de los movimientos posibles de la reina en la diagonal arriba izquierda
        @param num Número que representa los movimiento posibles de la verificación del obstáculo
    */
    public static void arribaIzquierda(int num) {
        arribaIzquierda = Math.min(arribaIzquierda, num);
    }

    /*
        Verifica el valor final de los movimientos posibles de la reina en la diagonal abajo izquierda
        @param num Número que representa los movimiento posibles de la verificación del obstáculo
    */
    public static void abajoIzquierda(int num) {
        abajoIzquierda = Math.min(abajoIzquierda, num);
    }

    /*
        Verifica el obstáculo, hace la asignación de los nuevos valores de movimientos según esté ubicado el mismo
        @param i Representa la fila en la que está ubicado el obstáculo
        @param j Representa la columna en la que está ubicado el obstáculo
    */
    public static void verificarObstaculo(int i, int j) {
        if (i == n) {
            if (j > m) {
                right = checkRight(n, m, j);
            } else {
                left = checkLeft(n, m, j + 1);
            }
        }

        if (j == m) {
            if (i > n) {
                down = checkDown(n, m, i);
            } else {
                up = checkUp(n, m, i + 1);
            }
        }

        if (j - m == n - i) {
            if (j > m) {
                arribaDerecha(j - m - 1);
            }
        } else if (i - n == j - m) {
            if (j > m) {
                abajoDerecha(i - n - 1);
            }

        }

        if (n - i == m - j) {
            if (j < m) {
                arribaIzquierda(n - i - 1);
            }
        } else if (i - n == m - j) {
            if (j < m) {
                abajoIzquierda(i - n - 1);
            }
        }
    }

}
