package dataLayer;

import businessLogic.Restaurant;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FileWr {
    private String fileName;
    private FileOutputStream file;
    private ObjectOutputStream outputStream;

    /**
     * Constructor class that writes the serialized object in the file with the name given as parameter.
     * @param serializator RestaurantSerializator
     * @param fileName String
     * @throws IOException In case it can't write in the file.
     */
    public FileWr(RestaurantSerializator serializator, String fileName) throws IOException {
        file = new FileOutputStream(fileName);
        outputStream = new ObjectOutputStream(file);
        outputStream.writeObject(Restaurant.getRestaurantMenu());

        outputStream.close();
        file.close();
    }
}
