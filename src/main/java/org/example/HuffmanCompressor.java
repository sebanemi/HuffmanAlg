package org.example;

import java.io.*;
import java.util.*;

public class HuffmanCompressor {

    public void compress(String inputFile, String outputFile) throws IOException {
        // 1️⃣ Contar la frecuencia de cada byte
        int[] freq = new int[256];
        try (InputStream in = new FileInputStream(inputFile)) {
            int b;
            while ((b = in.read()) != -1) {
                freq[b]++;
            }
        }

        // 2️⃣ Crear la lista de nodos hoja
        List<HuffmanInfo> nodos = new ArrayList<>();
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                nodos.add(new HuffmanInfo((byte) i, freq[i]));
            }
        }

        // 3️⃣ Construir el árbol Huffman
        while (nodos.size() > 1) {
            // Ordenar la lista por frecuencia (usando compFrecuenciaHuffman)
            nodos.sort(HuffmanInfo::compFrecuenciaHuffman);

            HuffmanInfo left = nodos.remove(0);
            HuffmanInfo right = nodos.remove(0);
            HuffmanInfo parent = new HuffmanInfo(left, right);
            nodos.add(parent);
        }

        HuffmanInfo root = nodos.get(0);

        // 4️⃣ Generar la tabla de códigos Huffman
        HuffmanTreeMap codeMap = new HuffmanTreeMap(root);

        // 5️⃣ Escribir el archivo comprimido
        try (OutputStream os = new FileOutputStream(outputFile);
             DataOutputStream dos = new DataOutputStream(os);
             FileInputStream in = new FileInputStream(inputFile);
             BitWriterImp writer = new BitWriterImp()) {

            writer.using(os);

            // Guardamos la tabla de frecuencias para reconstruir después
            for (int f : freq) {
                dos.writeInt(f);
            }

            // Escribir los bits codificados
            int b;
            while ((b = in.read()) != -1) {
                String code = codeMap.get(b);
                for (char bit : code.toCharArray()) {
                    writer.writeBit(bit == '1' ? 1 : 0);
                }
            }

            writer.flush(); // completa el último byte con ceros
        }

        System.out.println("✅ Compresión completada: " + outputFile);
    }
}
