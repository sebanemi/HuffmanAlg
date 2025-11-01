package org.example;

import java.io.*;
import java.util.*;

public class HuffmanDecompressor {

    public void decompress(String inputFile, String outputFile) throws IOException {
        try (InputStream is = new FileInputStream(inputFile);
             DataInputStream dis = new DataInputStream(is);
             FileOutputStream os = new FileOutputStream(outputFile);
             BitReaderImp reader = new BitReaderImp()) {

            reader.using(is);


            int[] freq = new int[256];
            for (int i = 0; i < freq.length; i++) {
                freq[i] = dis.readInt();
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


            HuffmanInfo actual = root;
            int bit;
            while ((bit = reader.readBit()) != -1) {
                actual = (bit == 0) ? actual.getLeft() : actual.getRight();

                if (actual.esHoja()) {
                    os.write(actual.getC());
                    actual = root;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("✅ Descompresión completada: " + outputFile);
    }
}

