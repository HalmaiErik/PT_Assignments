package data;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable {
    private int id;
    private BlockingQueue<Client> tasks;
    private AtomicInteger waitingPeriod;
    private int totalServiceTime;
    private int nrProcessedClients;
    private boolean exit;

    /**
     * Constructor of Queue class. Initializes the queue of tasks, waitingPeriod and totalServiceTime.
     * @param nrClients Integer
     */
    public Queue(int id, int nrClients) {
        this.id = id;
        tasks = new ArrayBlockingQueue<Client>(nrClients);
        waitingPeriod = new AtomicInteger();
        waitingPeriod.set(0);
        totalServiceTime = 0;
        nrProcessedClients = 0;
        exit = false;
    }

    /**
     * Method to add a new client to the queue. It adds the new client to the end of the BlockingQueue and sets the
     * waitingPeriod to the service time of the new client.
     * @param newClient Client
     */
    public void addClient(Client newClient) {
        tasks.add(newClient);
        waitingPeriod.addAndGet(newClient.getServiceTime());
    }

    /**
     * Overridden run method.
     */
    public void run() {
        while(!exit) {
            while (tasks.size() != 0) {
                try {
                    Client client = tasks.peek();
                    Thread.sleep((client.getServiceTime() + 1) * 1000);
                    waitingPeriod.addAndGet(-client.getServiceTime());
                    totalServiceTime += client.getWaitedTime();
                    nrProcessedClients++;
                    tasks.remove();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Constructs the printable string representation of a queue, enumerating all clients. This will be of the form:
     * "Queue id: (client1Id, client1ArrivalTime, client1ServiceTime); ..."
     * @return String
     */
    public String toString() {
        String str = "Queue " + id + ": ";

        if(tasks.size() == 0) {
            str = str + "closed";
        }
        else {
            for (Client client : tasks) {
                str = str + client.toString() + "; ";
            }
        }

        return str;
    }

    /**
     * Sets exit boolean variable of a queue to stop the thread.
     * @param exit Boolean
     */
    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public BlockingQueue<Client> getTasks() {
        return tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public int getTotalServiceTime() {
        return totalServiceTime;
    }

    public int getNrProcessedClients() {
        return nrProcessedClients;
    }
}
