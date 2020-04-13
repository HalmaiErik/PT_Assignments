package schedulers;

import data.Client;
import data.Queue;
import strategy.SelectionPolicy;

import java.io.*;
import java.util.*;

public class SimulationManager implements Runnable {
    private int clientNr;
    private int queueNr;
    private int maxSimulationTime;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int minServiceTime;
    private int maxServiceTime;
    private SelectionPolicy selectionPolicy;

    // Queue manager and client distributor
    private Scheduler scheduler;

    // Pool of clients shopping in the store
    private List<Client> generatedClients = new ArrayList<Client>();

    // Output file
    File outputFile;
    FileWriter outputWriter;

    /**
     * Constructor method, that sets the corresponding fields of the SimulationManager to the ones given as parameter.
     * It also creates a new scheduler object with the selected strategy and the output file with the name given as
     * parameter and a FileWriter associated with the file.
     * @param clientNr Int
     * @param queueNr Int
     * @param maxSimulationTime Int
     * @param minArrivalTime Int
     * @param maxArrivalTime Int
     * @param minServiceTime Int
     * @param maxServiceTime Int
     * @param selectionPolicy SelectionPolicy
     * @param outputFileName String
     */
    public SimulationManager(int clientNr, int queueNr, int maxSimulationTime, int minArrivalTime, int maxArrivalTime,
                             int minServiceTime, int maxServiceTime, SelectionPolicy selectionPolicy, String outputFileName) {
        this.clientNr = clientNr;
        this.queueNr = queueNr;
        this.maxSimulationTime = maxSimulationTime;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.selectionPolicy = selectionPolicy;

        this.scheduler = new Scheduler(clientNr, queueNr, selectionPolicy);
        generateNRandomTasks(clientNr);

        setOutputFile(outputFileName);
        try {
            outputWriter = new FileWriter(outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sorts the generated clients with respect to their arrivalTime.
     */
    private void sortClients() {
        generatedClients.sort(new Comparator<Client>() {
            public int compare(Client o1, Client o2) {
                int arrivalTime1 = o1.getArrivalTime();
                int arrivalTime2 = o2.getArrivalTime();

                if(arrivalTime1 < arrivalTime2)
                    return -1;
                else if(arrivalTime1 > arrivalTime2)
                    return 1;
                else return 0;
            }
        });
    }

    /**
     * Generates N number of clients having their arrivalTime and serviceTime in the given min-max interval, then adds these
     * clients to the generatedClients list, sorts the list and sets the client's ids in the order that they will be served.
     * @param n Int
     */
    private void generateNRandomTasks(int n) {
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            // Generate random arrival and service times in the given interval
            int generatedArrivalTime = rand.nextInt((maxArrivalTime - minArrivalTime) + 1) + minArrivalTime;
            int generatedServiceTime = rand.nextInt((maxServiceTime - minServiceTime) + 1) + minServiceTime;

            generatedClients.add(new Client(i + 1, generatedArrivalTime, generatedServiceTime));
        }

        sortClients();
        for (int i = 0; i < generatedClients.size(); i++) {
            generatedClients.get(i).setId(i + 1);
        }
    }

    /**
     * Overriden run method.
     */
    public void run() {
        int simulationTime = 0;

        while(simulationTime <= maxSimulationTime) {
            // Increment waited time for every client is the the queues.
            for(Queue queue: scheduler.getQueueList()) {
                for (Client client : queue.getTasks()) {
                    client.incrementWaitedTime();
                }
            }

            // If any client finished shopping, then add it to the queue using the chosen strategy and remove it from the
            // waiting clients' list.
            for (Iterator<Client> iterator = generatedClients.iterator(); iterator.hasNext();) {
                Client client = iterator.next();
                if(client.getArrivalTime() == simulationTime) {
                    scheduler.dispatchTask(client);
                    iterator.remove();
                }
            }

            // Sleep 1 second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Printing
            try {
                outputWriter.append("Time " + simulationTime + "\n");

                if(generatedClients.size() != 0) {
                    outputWriter.append("Waiting clients: ");
                    for(Client client : generatedClients) {
                        outputWriter.append(client.toString() + "; ");
                    }
                    outputWriter.append("\n");
                }

                for(Queue queue : scheduler.getQueueList()) {
                    outputWriter.append(queue.toString() + "\n");
                }
                outputWriter.append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

            simulationTime++;
        }

        float averageWaitTime = scheduler.totalWaitTime() / scheduler.totalProcessedClients();

        try {
            outputWriter.append("\nAverage waiting: " + averageWaitTime);
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Queue queue: scheduler.getQueueList()) {
            queue.setExit(true);
        }

        System.out.println("FINISHED");
    }

    /**
     * Creates and opens the output file with the name given as parameter.
     * @param outputFileName String
     */
    private void setOutputFile(String outputFileName) {
        outputFile = new File(outputFileName);

        try {
            outputFile.createNewFile();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
