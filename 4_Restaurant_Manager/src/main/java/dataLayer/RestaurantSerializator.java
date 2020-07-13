package dataLayer;

import businessLogic.Restaurant;

import java.io.IOException;

public class RestaurantSerializator {
    private static final String FILE_NAME = "restaurant.ser";
    private Restaurant restaurant;

    /**
     * Constructor method that sets the restaurant object.
     * @param restaurant Restaurant
     */
    public RestaurantSerializator(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * Writes the serialized restaurant's menu in a file with the name "restaurant.ser".
     * @throws IOException In case it can't create the file.
     */
    public void write() throws IOException {
        FileWr writer = new FileWr(this, FILE_NAME);
    }

    /**
     * Reads the serialized restaurant's menu from a file with the name "restaurant.ser"
     * @throws IOException In case it can't find the file.
     * @throws ClassNotFoundException In case it can't find the serializable class.
     */
    public void read() throws IOException, ClassNotFoundException {
        FileRd reader = new FileRd(this, FILE_NAME);
        restaurant.setRestaurantMenu(reader.read());
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
