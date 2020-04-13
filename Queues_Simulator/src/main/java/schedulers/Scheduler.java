package schedulers;

import data.Client;
import data.Queue;
import strategy.SelectionPolicy;
import strategy.ShortestTimeStrategy;
import strategy.Strategy;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Queue> queueList;
    private int nrClients;
    private int nrQueues;
    private Strategy strategy;

    /**
     * Constructor of Scheduler class. Initializes it's field, creates queue objects in queueList and starts the thread
     * with each such object.
     * @param nrClients Integer
     * @param nrQueues Integer
     * @param preferredStrategy SelectionPolicy
     */
    public Scheduler(int nrClients, int nrQueues, SelectionPolicy preferredStrategy) {
        queueList = new ArrayList<Queue>();
        this.nrClients = nrClients;
        this.nrQueues = nrQueues;

        for (int i = 0; i < nrQueues; i++) {
            Queue queue = new Queue(i + 1, nrClients);
            queueList.add(queue);
            Thread thread = new Thread(queueList.get(i));
            thread.start();
        }

        changeStrategy(preferredStrategy);
    }

    /**
     * Method to change the strategy of the simulation.
     * @param selectionPolicy SelectionPolicy
     */
    public void changeStrategy(SelectionPolicy selectionPolicy) {
        if(selectionPolicy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ShortestTimeStrategy();
        }
    }

    /**
     * Dispatches a task (i.e. a Client) using the set strategy's addTask method.
     * @param client Client
     */
    public void dispatchTask(Client client) {
        strategy.addTask(queueList, client);
    }

    /**
     * Returns total waited time of each finished client from every queue in the queue list.
     * @return Int
     */
    public int totalWaitTime() {
        int output = 0;

        for (Queue queue : queueList) {
            output += queue.getTotalServiceTime();
        }

        return output;
    }

    public int totalProcessedClients() {
        int output = 0;

        for(Queue queue : queueList) {
            output += queue.getNrProcessedClients();
        }

        return output;
    }

    public List<Queue> getQueueList() {
        return queueList;
    }
}
