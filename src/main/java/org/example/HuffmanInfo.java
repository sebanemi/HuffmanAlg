package org.example;

public class HuffmanInfo
{
    private int c; // caracter o byte
    private int n; // cantidad de ocurrencias
    private HuffmanInfo left;  // putero al hijo izquierdo
    private HuffmanInfo right; // puntero  al hijo derecho

    // Contador para simular el 'i' de '*i' (Paso 3.c)
    private int idAuxiliar = 0;

    // Constructor para nodos HOJA (los que creás al inicio)
    public HuffmanInfo(Byte dato, int frecuencia) {
        this.c = Byte.toUnsignedInt(dato);
        this.n = frecuencia;
        this.left = null;
        this.right = null;
    }

    /**
     * Constructor para nodos INTERNOS (Paso 3.b: crear *1, *2, etc.)
     * Representa un "byte ficticio".
     */
    public HuffmanInfo(HuffmanInfo izquierdo, HuffmanInfo derecho) {
        // La frecuencia 'n' es la suma de los hijos (Paso 3.b)
        this.n = izquierdo.n + derecho.n;

        // El valor 'c' sigue la regla del Paso 3.c ("255+i")
        // Empezamos en 256 para que siempre sea > que cualquier hoja (0-255)
        this.c = 256 + idAuxiliar++;

        // Asignamos los hijos que nos pasaron
        this.left = izquierdo;
        this.right = derecho;
    }

    //Importante! Compara los nodos para que se ordenen en el arbol
    public int compFrecuenciaHuffman(HuffmanInfo nodoArbol) {

        // Criterio 1: Ordenar por 'n' (frecuencia) ascendente (Paso 2)
        if (this.n != nodoArbol.n) {
            return Integer.compare(this.n, nodoArbol.n);
        }

        // Criterio 2: Desempate por 'c' (valor numérico) ascendente
        // (Paso 2: "menor valor numérico")
        // (Paso 3.c: "valor numérico... 255+i")
        // Esto funciona para hojas (0-255) e internos (256+i)
        return Integer.compare(this.c, nodoArbol.c);
    }

    /**
     * Metodo para saber si es una HOJA (Paso 3, Figura 2.11)
     * Es una hoja si no tiene hijos.
     */
    public boolean esHoja() {
        return this.left == null && this.right == null;
    }

    /**
     * Getters para que HuffmanTree pueda leer los atributos
     * (Necesarios porque los atributos son privados).
     */
    public int getC() {
        return c;
    }

    public int getN() {
        return n;
    }

    public HuffmanInfo getLeft() {
        return left;
    }

    public HuffmanInfo getRight() {
        return right;
    }

    public int getIdAuxiliar() {
        return idAuxiliar;
    }
}
