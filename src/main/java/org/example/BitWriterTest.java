package org.example;

import java.io.FileOutputStream;
import java.io.IOException;

public class BitWriterTest {
    public static void main(String[] args) {
        try{
            BitWriterImp writer = new BitWriterImp();
            writer.using(new FileOutputStream("bits.bin"));

            int[] bits = {1,0,1,0};
            for(int b: bits){
                writer.writeBit(b);
            }

            writer.flush();
            System.out.println("Archivo generado correctamente");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
