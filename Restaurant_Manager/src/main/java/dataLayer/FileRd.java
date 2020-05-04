package dataLayer;

import businessLogic.MenuItem;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class FileRd {
    private String fileName;
    private FileInputStream file;
    private ObjectInputStream inputStream;

    /**
     * Constructor method that sets the input streams and the name of the input file.
     * @param serializator RestaurantSerializator
     * @param fileName String
     * @throws IOException In case it can't open the file.
     */
    public FileRd(RestaurantSerializator serializator, String fileName) throws IOException {
        this.fileName = fileName;
        file = new FileInputStream(fileName);
        inputStream = new ObjectInputStream(file);
    }

    /**
     * Returns the read list of menu items from the input file.
     * @return List<MenuItem>
     * @throws IOException In case it can't read from the file.
     * @throws ClassNotFoundException In case it can't find the serializable class.
     */
    public List<MenuItem> read() throws IOException, ClassNotFoundException {
        return (List<MenuItem>) inputStream.readObject();
    }
}
