package org.example;

import java.io.OutputStream;

public interface BitWriter
{
    // establece el outputStream donde escribirá los bits
    public void using(OutputStream os);

    // escribe un bit
    public void writeBit(int bit);

    // completa el buffer con ceros y lo escribe
    public void flush();
}
