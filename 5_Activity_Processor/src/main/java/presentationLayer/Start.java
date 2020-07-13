package presentationLayer;

public class Start {
    /**
     * Main method. Initializes a new Controller object with the input file given as parameter and calls the Controller's
     * run method.
     * @param args String[], arguments
     */
    public static void main(String[] args) {
        Controller controller = new Controller("Activities.txt");
        controller.run();
    }
}
