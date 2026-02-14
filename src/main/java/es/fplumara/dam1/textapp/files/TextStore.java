package es.fplumara.dam1.textapp.files;

import es.fplumara.dam1.textapp.model.Message;

import java.io.IOException;

public interface TextStore {
     void save(Message message) throws IOException;
     String readAll();
     String readLast(int n);
}
