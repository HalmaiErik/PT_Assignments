package businessLogicLayer;

import model.MonitoredData;

public interface Task1 {
    /**
     * Functional interface method. It is used to process the lines of the input file.
     * @param data String
     * @return MonitoredData
     */
    public MonitoredData readData(String data);
}
