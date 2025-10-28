package org.example;

public interface BitReader
{
    // establece el inputStream desde donde leerá los bits
    public void using(InputStream is);

    // retorna 1 o 0, o -1 si es EOF
    public int readBit();

    // vacia el buffer
    public void flush();
}
