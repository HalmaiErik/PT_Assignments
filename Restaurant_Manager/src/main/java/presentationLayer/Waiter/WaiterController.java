package presentationLayer.Waiter;

import businessLogic.Order;
import businessLogic.Restaurant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WaiterController {
    private Restaurant model;
    private WaiterFrame waiterFrame;
    private int billInd;

    public WaiterController(Restaurant model, WaiterFrame view) {
        this.model = model;
        waiterFrame = view;

        waiterFrame.addOrderListener(new OrderListener());
        waiterFrame.addBillListener(new BillListener());
    }

    class OrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            NewOrderFrame orderFrame = new NewOrderFrame(Integer.parseInt(waiterFrame.getTablesCB().getSelectedItem().toString()));
            NewOrderController orderController = new NewOrderController(model, orderFrame);
            orderFrame.setVisible(true);
        }
    }

    class BillListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Order order = new Order(1, Integer.parseInt(waiterFrame.getTablesCB().getSelectedItem().toString()));

            try {
              String bill = model.generateBill(order);
              String billName = "bill" + new SimpleDateFormat("yyyyMMddHHmmss'.txt'").format(new Date());

              File file = new File(billName);
              file.createNewFile();
              FileWriter writer = new FileWriter(file);
              writer.append(bill);
              writer.close();
            } catch (IOException ex) {
                waiterFrame.showError(new IOException("Cannot create bill file"));
            } catch (NullPointerException ex) {
                waiterFrame.showError(new NullPointerException("The table hasn't ordered yet"));
            }
        }
    }
}
