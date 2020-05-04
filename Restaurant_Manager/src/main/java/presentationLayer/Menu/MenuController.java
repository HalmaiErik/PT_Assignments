package presentationLayer.Menu;

import businessLogic.Restaurant;
import presentationLayer.Admin.AdminController;
import presentationLayer.Admin.AdminFrame;
import presentationLayer.Chef.ChefFrame;
import presentationLayer.Waiter.WaiterController;
import presentationLayer.Waiter.WaiterFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController {
    private Restaurant model;
    private MenuFrame menuFrame;

    public MenuController(Restaurant model, MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
        this.model = model;

        menuFrame.addAdminListener(new AdminListener());
        menuFrame.addWaiterListener(new WaiterListener());
        menuFrame.addChefListener(new ChefListener());
    }

    class AdminListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdminFrame adminFrame = new AdminFrame();
            AdminController adminController = new AdminController(model, adminFrame);
            adminFrame.setLocation(menuFrame.getX(), menuFrame.getY());
            adminFrame.setVisible(true);
        }
    }

    class WaiterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            WaiterFrame waiterFrame = new WaiterFrame();
            WaiterController waiterController = new WaiterController(model, waiterFrame);
            waiterFrame.setLocation(menuFrame.getX(), menuFrame.getY());
            waiterFrame.setVisible(true);
        }
    }

    class ChefListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ChefFrame chefFrame = new ChefFrame();
            chefFrame.setLocation(menuFrame.getX(), menuFrame.getY());
            chefFrame.setVisible(true);
            model.addObserver(chefFrame);
        }
    }
}
