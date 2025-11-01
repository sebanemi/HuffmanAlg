package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HuffmanCompressor compressor = new HuffmanCompressor();
        HuffmanDecompressor decompressor = new HuffmanDecompressor();

        System.out.println("=== HUFFMAN ===");
        System.out.println("1. Comprimir archivo");
        System.out.println("2. Descomprimir archivo");
        System.out.print("Opción: ");
        int opcion = sc.nextInt();
        sc.nextLine();

        try {
            if (opcion == 1) {
                System.out.print("Archivo a comprimir: ");
                String input = sc.nextLine();
                System.out.print("Archivo comprimido: ");
                String output = sc.nextLine();
                compressor.compress(input, output);

            } else if (opcion == 2) {
                System.out.print("Archivo a descomprimir: ");
                String input = sc.nextLine();
                System.out.print("Archivo descomprimido: ");
                String output = sc.nextLine();
                decompressor.decompress(input, output);

            } else {
                System.out.println("Opción inválida.");
            }
        } catch (Exception e) {
            System.err.println("⚠️ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

