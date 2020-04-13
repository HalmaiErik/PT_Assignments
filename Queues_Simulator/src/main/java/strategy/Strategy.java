package strategy;

import data.Client;
import data.Queue;

import java.util.List;

public interface Strategy {
    /**
     * Method to be implemented by a class that implements the Strategy interface. It is used to add a client to one of
     * the queues, in the queueList, using a desired strategy.
     * @param queueList List of queues
     * @param client Client to be added
     */
    public void addTask(List<Queue> queueList, Client client);
}
