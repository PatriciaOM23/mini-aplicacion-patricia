package es.fplumara.dam1.textapp.config;

import es.fplumara.dam1.textapp.exceptions.ConfigException;

import java.util.Properties;

//store.type=FILE
//messages.file=data/mensajes.txt
//messages.maxLength=200
public class AppConfig {
        Properties props = new Properties();
    public AppConfig() {
        // TAMBIÉN PODRIAMOS INDICARLE UN VALOR DEFAULT
           String storeType = props.getProperty("store.type");
           String messageFile = props.getProperty("messages.file");
           String messagesMaxLength = props.getProperty("messages.maxLength");
        if(storeType == null ||storeType.isBlank()){
            throw new ConfigException("No se ha encontrado el tipo de almacenaje");
        }
        if(messageFile == null || messageFile.isBlank()){
            throw new ConfigException("Directorio no encontrado.");
        }
        if(messagesMaxLength == null || messagesMaxLength.isBlank()){
            throw new ConfigException("Longitud máxima de mensajes no encontrada.");
        }

        }

        public String getStoreType(){
            return props.getProperty("store.type");
        }
        public String getMessagesFile(){
            return props.getProperty("messages.file");
        }
        public int getMaxLength(){
            return Integer.parseInt(props.getProperty("messages.maxLength","200"));
        }
}
