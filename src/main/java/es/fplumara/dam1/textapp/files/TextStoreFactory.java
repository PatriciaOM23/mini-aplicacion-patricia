package es.fplumara.dam1.textapp.files;

import es.fplumara.dam1.textapp.config.AppConfig;
import es.fplumara.dam1.textapp.exceptions.StoreException;

public class TextStoreFactory {
    public TextStoreFactory() {
    }
    public TextStore createTextStore(AppConfig appConfig){

        switch (appConfig.getStoreType()){
            case "FILE" -> {
                return new FileTextStore(appConfig);
            }
            case "CSV" -> {
                return new CsvTextStore(appConfig);
            }
            default -> throw new StoreException("Fallo en el tipo de archivo");
        }

        }
}
