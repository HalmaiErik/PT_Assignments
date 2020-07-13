package businessLogicLayer;

import model.MonitoredData;

import java.util.List;

public interface Task6 {
    /**
     * Functional interface method. It is used to filter the activities having 90% of the monitoring records with duration
     * less than 5 minutes and add them to a List of Strings that is than returned.
     * @param activities List<MonitoredData>
     * @return List<String>
     */
    public List<String> filterFiveMinutes(List<MonitoredData> activities);
}
