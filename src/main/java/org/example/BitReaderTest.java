package org.example;

import java.io.FileInputStream;
import java.io.IOException;

public class BitReaderTest {
    public static void main(String[] args) {
        try{
            BitReaderImp reader = new BitReaderImp();
            reader.using(new FileInputStream("bits.bin"));

            int[] bits = new int[8];

            for(int i = 0; i < bits.length; i++){
                bits[i] = reader.readBit();
            }

            reader.flush();
            for(int i = 0; i < bits.length; i++){
                System.out.print(bits[i]);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
