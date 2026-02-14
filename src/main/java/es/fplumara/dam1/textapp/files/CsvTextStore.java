package es.fplumara.dam1.textapp.files;

import es.fplumara.dam1.textapp.model.Message;

import java.io.IOException;

public class CsvTextStore implements TextStore{
    @Override
    public void save(Message message) throws IOException {

    }

    @Override
    public String readAll() {
        return "";
    }

    @Override
    public String readLast(int n) {
        return "";
    }
}
