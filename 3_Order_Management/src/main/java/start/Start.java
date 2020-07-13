package start;

import presentation.Parser;

public class Start {
    /**
     * Main method. Starts the execution of the program.
     * @param args String[]
     */
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Usage java -jar PT2020_30422_Erik_Halmai_Assignment_3.jar commands.txt");
            System.exit(1);
        }

        String commandFile = args[0];
        Parser parser = new Parser(commandFile);
        parser.parseCommands();
    }
}
