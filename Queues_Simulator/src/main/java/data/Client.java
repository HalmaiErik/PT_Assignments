package data;

public class Client {
    private int id;
    private int arrivalTime;
    private int serviceTime;
    private int waitedTime;

    /**
     * Constructor of Client class. Sets id, arrivalTime and serviceTime of the Client object to the values given as
     * parameter and sets the waitedTime to 0.
     * @param id Integer
     * @param arrivalTime Integer
     * @param serviceTime Integer
     */
    public Client(int id, int arrivalTime, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        waitedTime = 0;
    }

    /**
     * Constructs the printable string representation of a client. This will be of the form: "(id,arrivalTime,serviceTime)"
     * @return String
     */
    public String toString() {
        return "(" + String.valueOf(id) + "," + String.valueOf(arrivalTime) + "," + String.valueOf(serviceTime) + ")";
    }

    /**
     * Increments the waitedTime of a client. Waited time is equal to the total time spent by the client in a queue.
     */
    public void incrementWaitedTime() {
        waitedTime++;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getWaitedTime() {
        return waitedTime;
    }
}
