package org.example;

import java.io.IOException;
import java.io.OutputStream;

public class BitWriterImp implements BitWriter, AutoCloseable {
    private OutputStream os;
    private int currentByte = 0;
    private int numBitsFilled = 0;

    @Override
    public void using(OutputStream os){
        this.os = os;
    }

    @Override
    public void writeBit(int bit) {
        if(os == null){
            throw new IllegalStateException("Output stream es nulo");
        }
        if(bit != 0 && bit!= 1){
            throw new IllegalStateException("Invalid bit");
        }

        currentByte = (currentByte<<1) | bit;
        numBitsFilled++;

        if (numBitsFilled == 8) {
            try {
                os.write(currentByte);
            } catch (IOException e) {
                throw new RuntimeException("Error al escribir el byte: " + e.getMessage());
            }
            numBitsFilled = 0;
            currentByte = 0;
        }
    }

    @Override
    public void flush(){
        try{
            if(numBitsFilled > 0){
                currentByte = currentByte << (8 - numBitsFilled);
                os.write(currentByte);
                numBitsFilled = 0;
                currentByte = 0;
            }
            os.flush();
        }catch(IOException e){
            throw new RuntimeException("Error al hacer flush: " + e.getMessage());
        }
    }


    public void close() throws IOException {
        flush();
        if (os != null) os.close();
    }
}
