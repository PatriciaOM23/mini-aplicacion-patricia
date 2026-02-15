package es.fplumara.dam1.textapp;

import es.fplumara.dam1.textapp.config.AppConfig;
import es.fplumara.dam1.textapp.files.StoreType;
import es.fplumara.dam1.textapp.files.TextStore;
import es.fplumara.dam1.textapp.files.TextStoreFactory;
import es.fplumara.dam1.textapp.model.Message;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class App {
    public static void main() throws IOException {

        AppConfig appConfig = new AppConfig();
        Properties properties;
        List<StoreType > storeTypes = List.of(StoreType.values());
        TextStoreFactory textStoreFactory = new TextStoreFactory();
        TextStore textStore = textStoreFactory.createTextStore(appConfig);

        textStore.save(new Message("Ultimo mensaje 1: Hola mundo"));
        textStore.save(new Message("Ultimo mensaje 2: : Probando almacenamiento"));
        textStore.save(new Message("Ultimo mensaje 3: : Tercer mensaje"));
        textStore.save(new Message("Ultimo mensaje 4: : Otro mensaje más"));
        textStore.save(new Message("Ultimo mensaje 5: : Último mensaje"));
    System.out.println( textStore.readAll());
    System.out.println(textStore.readLast(5));
    }

}
