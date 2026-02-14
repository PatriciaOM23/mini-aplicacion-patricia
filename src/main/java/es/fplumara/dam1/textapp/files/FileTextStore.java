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
import java.util.List;

public class FileTextStore implements TextStore{
    AppConfig appConfig;

            Path path = Path.of(appConfig.getMessagesFile());
    public FileTextStore(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public void save(Message message) throws IOException {
        try {
            int msgLength = appConfig.getMaxLength();
            File file = new File(appConfig.getMessagesFile());
            if (!file.exists()) {
                Files.createDirectories(path.getParent());
            }
            String text = message.getText();
            if (text.length() > msgLength) {
                text = text.substring(0, msgLength);
            }
            Files.write(path,
                    List.of(text),
                    StandardOpenOption.CREATE,
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

        } catch (Exception e ){
            throw new StoreException("Error al leer el archivo");
        }
        return "";
    }
}
