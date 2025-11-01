package org.example;

import java.io.*;
import java.util.*;

public class HuffmanCompressor {

    public void compress(String inputFile, String outputFile) throws IOException {

        int[] freq = new int[256];
        try (InputStream in = new FileInputStream(inputFile)) {
            int b;
            while ((b = in.read()) != -1) {
                freq[b]++;
            }
        }


        List<HuffmanInfo> nodos = new ArrayList<>();
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                nodos.add(new HuffmanInfo((byte) i, freq[i]));
            }
        }


        while (nodos.size() > 1) {

            nodos.sort(HuffmanInfo::compFrecuenciaHuffman);

            HuffmanInfo left = nodos.remove(0);
            HuffmanInfo right = nodos.remove(0);
            HuffmanInfo parent = new HuffmanInfo(left, right);
            nodos.add(parent);
        }

        HuffmanInfo root = nodos.get(0);


        HuffmanTreeMap codeMap = new HuffmanTreeMap(root);


        try (OutputStream os = new FileOutputStream(outputFile);
             DataOutputStream dos = new DataOutputStream(os);
             FileInputStream in = new FileInputStream(inputFile);
             BitWriterImp writer = new BitWriterImp()) {

            writer.using(os);


            for (int f : freq) {
                dos.writeInt(f);
            }


            int b;
            while ((b = in.read()) != -1) {
                String code = codeMap.get(b);
                for (char bit : code.toCharArray()) {
                    writer.writeBit(bit == '1' ? 1 : 0);
                }
            }

            writer.flush();
        }

        System.out.println("✅ Compresión ya completada: " + outputFile);
    }
}
