package org.example;

public class HuffmanTable {
    private int n;       // frecuencia del símbolo
    private String cod;  // código Huffman asignado

    // --- Constructores ---
    public HuffmanTable() {
        this.n = 0;
        this.cod = "";
    }

    public HuffmanTable(int n, String cod) {
        this.n = n;
        this.cod = cod;
    }

    // --- Getters y Setters ---
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    // --- Métodos útiles ---
    public void incrementar() {
        this.n++;
    }

    @Override
    public String toString() {
        return "Freq=" + n + ", Code='" + cod + "'";
    }
}
