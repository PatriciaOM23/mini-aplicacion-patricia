package es.fplumara.dam1.textapp.model;

import es.fplumara.dam1.textapp.config.AppConfig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String text;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String formattedTimestamp;
    private int wordCount;


    public Message(String text) {
        this.text = text;
        this.formattedTimestamp = getTimestamp();
        this.wordCount = getWordCount();

    }

    public String getText(){
        //devuelve: el texto
        return this.text;
    }

    public String getTimestamp(){
        //devuelve: el timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(formatter);
    }

    public int getWordCount(){
        //devuelve: el número de palabras
        String[] words = text.replace("  "," ").split(" ");
       return wordCount = words.length;
    }
}
