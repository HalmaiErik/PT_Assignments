package businessLogicLayer;

import model.MonitoredData;

import java.util.List;

public interface Task2 {
    /**
     * Functional interface method. It is used to count the number of distinct days from the list of activities.
     * @param activities List<MonitoredData>
     * @return Integer
     */
    public int countDistinctDays(List<MonitoredData> activities);
}
