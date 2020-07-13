package businessLogicLayer;

import model.MonitoredData;

import java.util.List;
import java.util.Map;

public interface Task3 {
    /**
     * Functional interface method. It is used to count the number of times each activity appeared over the monitoring
     * period.
     * @param activities List<MonitoredData>
     * @return Map<String, Long>
     */
    public Map<String, Long> countActivities(List<MonitoredData> activities);
}
