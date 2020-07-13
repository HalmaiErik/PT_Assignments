package businessLogicLayer;

import model.MonitoredData;

import java.util.List;
import java.util.Map;

public interface Task4 {
    /**
     * Functional interface method. It is used to count the number of times each activity appears during each day over
     * the monitoring period.
     * @param activities List<MonitoredData>
     * @return Map<Integer, Map<String, Long>>
     */
    public Map<Integer, Map<String, Long>> countActivitiesPerDay(List<MonitoredData> activities);
}
