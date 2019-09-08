package io;


import core.Utregning;

import java.io.IOException;

public interface KalkIOInterface {

    void save(String filename, Utregning tall) throws IOException;
    Utregning load(String filename) throws IOException;

}
