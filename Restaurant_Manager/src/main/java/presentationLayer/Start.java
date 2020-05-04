package presentationLayer;

import businessLogic.Restaurant;
import dataLayer.RestaurantSerializator;
import presentationLayer.Menu.MenuController;
import presentationLayer.Menu.MenuFrame;

import java.io.IOException;

public class Start {
    public static void main(String[] args) {
        Restaurant model = new Restaurant();
        RestaurantSerializator serializator = new RestaurantSerializator(model);
        MenuFrame view = new MenuFrame();
        MenuController controller = new MenuController(model, view);
        view.setVisible(true);

        try {
            serializator.read();
        } catch (IOException e) {
            view.showInfo(new IOException("No input file found"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
