package strategy;

import data.Client;
import data.Queue;

import java.util.List;

public class ShortestTimeStrategy implements Strategy {

    /**
     * Implementation of the Strategy interface's addTask method. The method adds a client to the queue which has
     * minimal waiting period.
     * @param queueList List of queues
     * @param client Client to be added
     */
    public void addTask(List<Queue> queueList, Client client) {
        int minTimeIndex = 0;

        // Find queue with minimal waiting period
        for (int i = 0; i < queueList.size(); i++) {
            if(queueList.get(i).getWaitingPeriod().get() < queueList.get(minTimeIndex).getWaitingPeriod().get()) {
                minTimeIndex = i;
            }
        }

        // Add client to the queue with minimal waiting period
        queueList.get(minTimeIndex).addClient(client);
    }
}
