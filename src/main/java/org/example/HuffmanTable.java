package org.example;

public class HuffmanTable {
    private int n;
    private String cod;

    
    public HuffmanTable() {
        this.n = 0;
        this.cod = "";
    }

    public HuffmanTable(int n, String cod) {
        this.n = n;
        this.cod = cod;
    }


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


    public void incrementar() {
        this.n++;
    }

    @Override
    public String toString() {
        return "Freq=" + n + ", Code='" + cod + "'";
    }
}
