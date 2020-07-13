package presentation;

import businessLogicLayer.ClientBLL;
import businessLogicLayer.OrderBLL;
import businessLogicLayer.OrderItemBLL;
import businessLogicLayer.ProductBLL;
import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import model.Client;
import model.Ordert;
import model.OrderItem;
import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Parser {
    private File commandFile;
    private int billIndex = 0;
    private int clientReportInd = 0;
    private int productReportInd = 0;
    private int orderReportInd = 0;

    private ClientBLL clientBLL;
    private ProductBLL productBLL;
    private OrderBLL orderBLL;
    private OrderItemBLL orderItemBLL;

    /**
     * Constructor method. Initializes the commandFile with name given as parameter and the business logic layer objects.
     * @param fileName String
     */
    public Parser(String fileName) {
        commandFile = new File(fileName);
        clientBLL = new ClientBLL();
        productBLL = new ProductBLL();
        orderBLL = new OrderBLL();
        orderItemBLL = new OrderItemBLL();
    }

    /**
     * Insert client command with fields in the List given as parameter.
     * @param parameters List<String>
     */
    private void commandInsertClient(List<String> parameters) {
        try {
            if (parameters.size() == 2) {
                Client toInsert = new Client(parameters.get(0), parameters.get(1));
                clientBLL.insertClient(toInsert);
            }
            else throw new IllegalCommandException("Illegal insert client command");
        } catch(IllegalCommandException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert product command with fields in the List given as a parameter.
     * @param parameters List<String>
     */
    private void commandInsertProduct(List<String> parameters) {
        try {
            if (parameters.size() == 3) {
                // Check if product already in db
                Product product = productBLL.findProductByName(parameters.get(0));

                if (product == null) {
                    Product toInsert = new Product(parameters.get(0), Integer.parseInt(parameters.get(1)), Float.parseFloat(parameters.get(2)));
                    productBLL.insertProduct(toInsert);
                }
                else {
                    int newQuantity = product.getQuantity() + Integer.parseInt(parameters.get(1));
                    productBLL.updateProductQuantity(newQuantity, product.getId());
                }
            }
            else throw new IllegalCommandException("Illegal insert product command");
        } catch (IllegalCommandException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes the client having the fields in the List given as parameter.
     * @param parameters List<String>
     */
    private void commandDeleteClient(List<String> parameters) {
        try {
            if(parameters.size() == 2) {
                // Remove client
                Client toDelete = clientBLL.findClientByNameAndAddress(parameters.get(0), parameters.get(1));
                clientBLL.removeClientById(toDelete.getId());
            }
            else throw new IllegalCommandException("Illegal delete client command");
        } catch (NoSuchElementException | IllegalCommandException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes the product having the name given as parameter.
     * @param parameter String
     */
    private void commandDeleteProduct(String parameter) {
        try {
            Product toDelete = productBLL.findProductByName(parameter);
            if(toDelete == null) {
                throw new NoSuchElementException();
            }
            // Remove from product table
            productBLL.removeProduct(parameter);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    // TODO: 10-Apr-20 Check NoSuchElementException throws

    /**
     * Creates a new orderitem object with the fields in the List given as parameter and inserts it into the orderitem table.
     * It updates the ordert table and the ordered product's quantity and handles insufficient stock orders or orders for
     * clients or of products that aren't in their respective tables. It returns a String that is used to create the bill pdf.
     * @param parameters List<String>
     * @return String
     */
    private String commandOrder(List<String> parameters) {
        try {
            if(parameters.size() != 3) {
                throw new IllegalCommandException("Illegal order command");
            }

            // Find client's id
            Client client = clientBLL.findClientByName(parameters.get(0));
            if(client == null)
                return "Unexisting client";
            Product product = productBLL.findProductByName(parameters.get(1));
            if(product == null)
                return "Unexisting product";

            int amount = Integer.parseInt(parameters.get(2));
            float price = amount * product.getPrice();

            if(product.getQuantity() - amount < 0) {
                return "Insufficient stock";
            }

            // Check if client already ordered
            Ordert ordert = orderBLL.findOrderByClient(client.getId());
            if(ordert != null) {
                OrderItem orderItem = new OrderItem(ordert.getId(), product.getId(), amount, price);
                orderItemBLL.insertOrderItem(orderItem);

                float oldTotal = ordert.getTotalPrice();
                orderBLL.updateTotalForId(oldTotal + orderItem.getPrice(), ordert.getId());
            }
            else {
                // Insert into ordert table
                Ordert newOrdert = new Ordert(client.getId(), price);
                orderBLL.insertOrder(newOrdert);

                // Insert into orderItem table
                newOrdert = orderBLL.findOrderByClient(client.getId());
                OrderItem orderItem = new OrderItem(newOrdert.getId(), product.getId(), amount, price);
                orderItemBLL.insertOrderItem(orderItem);
            }

            // Update product quantity
            int newQuantity = product.getQuantity() - amount;
            productBLL.updateProductQuantity(newQuantity, product.getId());

            StringBuilder sb = new StringBuilder();
            sb.append("Client: " + client.getName() + "\n");
            sb.append("Product: " + product.getName() + ", amount: " + amount + "\n");
            sb.append("Total price: " + price);
            return sb.toString();
        } catch (NoSuchElementException | IllegalCommandException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates the content of the report specific to the table given as a parameter String.
     * @param parameter String, table's name
     * @return List<String>
     */
    private List<String> commandReport(String parameter) {
        List<String> report = new ArrayList<String>();

        if(parameter.equals("client")) {
            List<Client> clients = clientBLL.listClients();

            for(Client client : clients) {
                report.add(client.toString());
            }
        }
        else if(parameter.equals("product")) {
            List<Product> products = productBLL.listProducts();

            for(Product product : products) {
                report.add(product.toString());
            }
        }
        else if(parameter.equals("order")) {
            List<Ordert> orderts = orderBLL.listOrders();

            for (Ordert ordert : orderts) {
                report.add(ordert.toString());
            }
        }

        return report;
    }

    /**
     * Creates the bill pdf. It's content is specified in the String given as parameter.
     * @param bill String
     */
    private void createBill(String bill) {
        billIndex++;
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("bill" + billIndex + ".pdf"));
            document.open();
            document.add(new Paragraph(bill));
            document.close();

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the report pdf file with an appropriate name to know the table on which the report was made and the report
     * number, using the option char. The created report will contain the Strings in the list given as parameter separated
     * in paragraphs.
     * @param report List<String>
     * @param option Character
     */
    private void createReport(List<String> report, char option) {
        String pdfName = "report";

        if(option == 'c') {
            clientReportInd++;
            pdfName += "_client" + clientReportInd + ".pdf";
        }
        else if(option == 'p') {
            productReportInd++;
            pdfName += "_product" + productReportInd + ".pdf";
        }
        else if(option == 'o') {
            orderReportInd++;
            pdfName += "_order" + orderReportInd + ".pdf";
        }

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfName));
            document.open();

            if(report.size() != 0) {
                for (String s : report) {
                    document.add(new Paragraph(s));
                }
            }
            else document.add(new Paragraph("empty"));

            document.close();

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses a single command line given as parameter, by finding the command to execute and it's fields. Calls the specific command's
     * method with the given parameters. It ignores unknown commands. To parse the command line, it uses Guava String Splitter.
     * @param commandLine String
     */
    private void parseCommandLine(String commandLine) {
        Splitter lineSplitter = Splitter.on(':').omitEmptyStrings();
        Splitter commandSplitter = Splitter.on(' ').trimResults(CharMatcher.is(':')).omitEmptyStrings();
        Splitter parameterSplitter = Splitter.on(',').trimResults(CharMatcher.is(' ')).omitEmptyStrings();

        // Split the command line in two, with respect to the character ':'
        List<String> splitInTwo = lineSplitter.splitToList(commandLine);
        // Insert, Delete, Ordert
        if(splitInTwo.size() == 2) {
            // Split the actual command with respect to whitespace. Actual command i.e. "Delete client" or "Insert product", etc
            List<String> commandSplit = commandSplitter.splitToList(splitInTwo.get(0));

            if(commandSplit.get(0).equals("Insert")) {
                if(commandSplit.get(1).equals("client")) {
                    // Split the parameters of the command with respect to ',' character
                    List<String> parameters = parameterSplitter.splitToList(splitInTwo.get(1));
                    commandInsertClient(parameters);
                }
                else if(commandSplit.get(1).equals("product")) {
                    List<String> parameters = parameterSplitter.splitToList(splitInTwo.get(1));
                    commandInsertProduct(parameters);
                }
            }
            else if(commandSplit.get(0).equals("Delete")) {
                if(commandSplit.get(1).equals("client")) {
                    List<String> parameters = parameterSplitter.splitToList(splitInTwo.get(1));
                    commandDeleteClient(parameters);
                }
                else if(commandSplit.get(1).equals("product")) {
                    // Split here too, to get rid of the spaces
                    List<String> parameters = parameterSplitter.splitToList(splitInTwo.get(1));
                    commandDeleteProduct(parameters.get(0));
                }
            }
            else if(commandSplit.get(0).equals("Order")) {
                List<String> parameters = parameterSplitter.splitToList(splitInTwo.get(1));
                String bill = commandOrder(parameters);
                createBill(bill);
            }
        }
        // Reports
        else if(splitInTwo.size() == 1) {
            List<String> commandSplit = commandSplitter.splitToList(splitInTwo.get(0));
            if(commandSplit.get(0).equals("Report")) {
                List<String> reports = commandReport(commandSplit.get(1));
                if(commandSplit.get(1).equals("client"))
                    createReport(reports, 'c');
                else if(commandSplit.get(1).equals("product"))
                    createReport(reports, 'p');
                else if(commandSplit.get(1).equals("order"))
                    createReport(reports, 'o');
            }
        }
    }

    /**
     * Parses the whole file containing commands. Calls the parseCommandLine() method for each line.
     */
    public void parseCommands() {
        orderBLL.dropFK();
        orderItemBLL.dropFK();
        orderItemBLL.truncateTable();
        orderBLL.truncateTable();
        productBLL.truncateTable();
        clientBLL.truncateTable();
        orderBLL.addFK();
        orderItemBLL.addFK();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(commandFile));

            String command;
            while((command = br.readLine()) != null) {
                parseCommandLine(command);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
