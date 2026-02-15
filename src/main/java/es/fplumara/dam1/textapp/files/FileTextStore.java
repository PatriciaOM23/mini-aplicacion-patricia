package es.fplumara.dam1.textapp.files;

import es.fplumara.dam1.textapp.config.AppConfig;
import es.fplumara.dam1.textapp.exceptions.StoreException;
import es.fplumara.dam1.textapp.model.Message;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileTextStore implements TextStore{
    AppConfig appConfig;
            Path path;
    public FileTextStore(AppConfig appConfig) {
        this.appConfig = appConfig;
        this.path = Path.of(appConfig.getMessagesFile());
    }

    @Override
    public void save(Message message) throws IOException {
        try {
            int msgLength = appConfig.getMaxLength();
            File file = new File(appConfig.getMessagesFile());
            if (!file.exists()) {
                Files.createDirectories(path.getParent());
                file.createNewFile();
            }
            String text = message.getText();
            if (text.length() > msgLength) {
                text = text.substring(0, msgLength);
            }
            Files.write(path,
                    List.of(text),
                    StandardOpenOption.APPEND);
        } catch (Exception e){
            throw new StoreException("Error al guardar el archivo");
        }
    }

    @Override
    public String readAll() {
        try {
            if(!Files.exists(path)){
                throw new StoreException("Error al encontrar el archivo");
            }
        return  Files.readString(path);
        } catch (Exception e){
            throw new StoreException("Error al leer el archivo");
        }
    }

    @Override
    public String readLast(int n) {
        try(BufferedReader br = Files.newBufferedReader(path)){
        List <String> text = new ArrayList<>();
        String line;
        // Leer todas las líneas en la lista
         while ((line = br.readLine()) != null) {
             text.add(line);
         }
         //si tiene más de n lineas empieza a leer por text.size - n
         int readLines = Math.max(0,text.size() - n);
         StringBuilder sb = new StringBuilder();
         //lee a partir de readLines
         for(int i = readLines; i < text.size(); i++){
             sb.append(text.get(i)).append("\n");
         }
         return sb.toString();
        } catch (Exception e ){
            throw new StoreException("Error al leer el archivo");
        }

    }
}
