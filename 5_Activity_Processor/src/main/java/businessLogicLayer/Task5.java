package businessLogicLayer;

import model.MonitoredData;

import java.util.List;
import java.util.Map;

public interface Task5 {
    /**
     * Functional interface method. It is used to compute the duration of each activity during the monitoring period.
     * @param activities List<MonitoredData>
     * @return Map<String, Long>
     */
    public Map<String, Long> computeActivityDuration(List<MonitoredData> activities);
}
