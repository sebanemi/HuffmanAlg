package org.example;

public class HuffmanInfo
{
    private int c;
    private int n;
    private HuffmanInfo left;
    private HuffmanInfo right;


    private int idAuxiliar = 0;



    public HuffmanInfo(Byte dato, int frecuencia) {
        this.c = Byte.toUnsignedInt(dato);
        this.n = frecuencia;
        this.left = null;
        this.right = null;
    }


    public HuffmanInfo(HuffmanInfo izquierdo, HuffmanInfo derecho) {

        this.n = izquierdo.n + derecho.n;


        this.c = 256 + idAuxiliar++;


        this.left = izquierdo;
        this.right = derecho;
    }


    public int compFrecuenciaHuffman(HuffmanInfo nodoArbol) {


        if (this.n != nodoArbol.n) {
            return Integer.compare(this.n, nodoArbol.n);
        }


        return Integer.compare(this.c, nodoArbol.c);
    }


    public boolean esHoja() {
        return this.left == null && this.right == null;
    }


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
