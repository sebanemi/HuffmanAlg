package org.example;

import java.io.InputStream;
import java.io.IOException;

public class BitReaderImp implements BitReader{
    private InputStream in;
    private int currentByte = 0;
    private int numBitsRemaining = 0;

    @Override
    public void using(InputStream in){
        this.in=in;
    }

    @Override
    public int readBit(){
        if(in==null){
            throw new IllegalStateException("Input stream is null");
        }
        try{
            if(numBitsRemaining==0){
                currentByte = in.read();
                if(currentByte==-1){
                    return -1;
                }
                numBitsRemaining=8;
            }

            numBitsRemaining--;
            return(currentByte>>numBitsRemaining)&1;
        }catch(IOException e){
            throw new RuntimeException("Error al leer el bit",e);
        }
    }

    @Override
    public void flush(){
        try{
            if(in != null){
                in.close();
            }
        }catch(IOException e){
            throw new RuntimeException("Error al cerrar InputStream",e);
        }
    }
}
