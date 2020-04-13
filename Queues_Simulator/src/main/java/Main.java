import schedulers.SimulationManager;
import strategy.SelectionPolicy;
import util.WrongInputException;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Usage: java -jar PT2020_30422_Erik_Halmai_Assignment_2.jar <in.txt> <out.txt>");
            System.exit(0);
        }

        int clientNr;
        int queueNr;
        int maxSimulationTime;
        int minArrivalTime;
        int maxArrivalTime;
        int minServiceTime;
        int maxServiceTime;
        SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
        String outputFileName = args[1];

        String inputFileName = args[0];
        File file = new File(inputFileName);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String[] lines = new String[5];
            int i = 0;
            while(i < 5 && (lines[i] = br.readLine()) != null) {
                i++;
            }
            br.close();

            if(lines[0] == null || lines[1] == null || lines[2] == null || lines[3] == null || lines[4] == null) {
                System.out.println("Bad input file");
                System.exit(1);
            }

            clientNr = Integer.parseInt(lines[0]);
            if(clientNr <= 0)
                throw new WrongInputException("Please introduce valid input data");
            queueNr = Integer.parseInt(lines[1]);
            if(queueNr <= 0)
                throw new WrongInputException("Please introduce valid input data");
            maxSimulationTime = Integer.parseInt(lines[2]);

            String[] lineSplit = new String[2];
            lineSplit = lines[3].split(",");
            minArrivalTime = Integer.parseInt(lineSplit[0]);
            maxArrivalTime = Integer.parseInt(lineSplit[1]);
            if(minArrivalTime > maxArrivalTime)
                throw new WrongInputException("Please introduce valid input data");

            lineSplit = lines[4].split(",");
            minServiceTime = Integer.parseInt(lineSplit[0]);
            maxServiceTime = Integer.parseInt(lineSplit[1]);
            if(minServiceTime > maxServiceTime)
                throw new WrongInputException("Please introduce valid input data");

            SimulationManager sm = new SimulationManager(clientNr, queueNr, maxSimulationTime, minArrivalTime, maxArrivalTime,
                    minServiceTime, maxServiceTime, selectionPolicy, outputFileName);
            Thread t = new Thread(sm);
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}