package es.fplumara.dam1.textapp.files;

import es.fplumara.dam1.textapp.config.AppConfig;
import es.fplumara.dam1.textapp.exceptions.StoreException;
import es.fplumara.dam1.textapp.model.Message;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CsvTextStore implements TextStore{
    AppConfig appConfig;
    Path path;
    public CsvTextStore(AppConfig appConfig) {
        this.appConfig = appConfig;
        this.path = Path.of(appConfig.getMessagesFile());
    }

    @Override
    public void save(Message message) throws IOException {
        try {
            int maxLength = appConfig.getMaxLength();
            File file = new File(appConfig.getMessagesFile());
            if (!file.exists()) {
                Files.createDirectories(path.getParent());
                file.createNewFile();
            }
            String text = message.getText();
            if (text.length() > maxLength) {
                text = text.substring(0, maxLength);
            }
            try(BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                String timestamp = message.getTimestamp();
                int wordCount = message.getWordCount();
                writer.write(timestamp + "," + wordCount + "," + text + "\n");
            }
        } catch (Exception e){
                throw new StoreException("Error al guardar el archivo");
            }
    }

    @Override
    public String readAll() {
            try {
                if (!Files.exists(path)) {
                    throw new StoreException("El archivo no existe");
                }
                List<String> lines = Files.readAllLines(path);
                StringBuilder sb = new StringBuilder();
                for (String line : lines) { String[] parts = line.split(",", 3);
                    if (parts.length == 3) {
                        String timestamp = parts[0];
                        String wordCount = parts[1];
                        String text = parts[2];
                        sb.append("[").append(timestamp).append("] (").append(wordCount)
                                .append(" palabras): ")
                                .append(text)
                                .append("\n");
                    }
                } return sb.toString();
         } catch (Exception e){
            throw new StoreException("No se pudo leer el archivo");
        }
    }

    @Override
    public String readLast(int n) {
        try {
            if (!Files.exists(path)) {
                throw new StoreException("El archivo no existe");
            }
            List<String> lines = Files.readAllLines(path);
            int start = Math.max(0, lines.size() - n);
            StringBuilder sb = new StringBuilder();
            for (int i = start; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",", 3);
                if (parts.length == 3) {
                    String timestamp = parts[0];
                    String wordCount = parts[1];
                    String text = parts[2];
                    sb.append("[")
                            .append(timestamp)
                            .append("] (")
                            .append(wordCount)
                            .append(" palabras): ")
                            .append(text) .append("\n");
                }
            }
            return sb.toString();
        } catch (Exception e) {
            throw new StoreException("Error al leer el archivo");
        }
    }
}
