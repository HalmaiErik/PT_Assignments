package businessLogicLayer;

import model.MonitoredData;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class Solver {
    /**
     * Method used to split an input line in three parts: start_time, end_time and activity_label and store them in a
     * new MonitoredData.
     * @param data String - line
     * @return MonitoredData
     */
    public MonitoredData readData(String data) {
        Task1 task1 = data1 -> {
            String[] activity = data1.split("\t\t");
            return new MonitoredData(activity[0], activity[1], activity[2].trim());
        };
        return task1.readData(data);
    }

    /**
     * Method that returns the number of distinct days in the monitored data list. Uses the Task2 functional interface
     * and a HashSet. The getDistinctStartDay() and getDistinctEndDay() methods are used to add two distinct integers
     * for each activity in the List of MonitoredData's.
     * @param activities List<MonitoredData>
     * @return Integer
     */
    public int countDistinctDays(List<MonitoredData> activities) {
        Task2 task2 = activities1 -> {
            Set<Integer> distinctDays = new HashSet<Integer>();
            for(MonitoredData data : activities1) {
                distinctDays.add(data.getDistinctStartDay());
                distinctDays.add(data.getDistinctEndDay());
            }

            return distinctDays.size();
        };
        return task2.countDistinctDays(activities);
    }

    /**
     * Method that returns a Map<String, Long> structure that contains the number of time each activity appears in the
     * List of MonitoredData. The method uses the Task3 functional interface and stream processing on the activity list
     * given as parameter to create the needed structure.
     * @param activities List<MonitoredData>
     * @return Map<String, Long>
     */
    public Map<String, Long> countActivities(List<MonitoredData> activities) {
        Task3 task3 = activities1 -> activities1.stream()
                .collect(Collectors.groupingBy(MonitoredData::getActivityLabel, Collectors.counting()));
        return task3.countActivities(activities);
    }

    /**
     * Method that returns a Map<Integer, Map<String, Long>> structure containing the number of time each activity appears
     * for each distinct date from the monitoring data. The method uses the Task4 functional interface and stream processing
     * on the activity list given as parameter to create the needed structure.
     * @param activities List<MonitoredData>
     * @return Map<Integer, Map<String, Long>>
     */
    public Map<Integer, Map<String, Long>> countActivitiesPerDay(List<MonitoredData> activities) {
        Task4 task4 = activities1 -> activities1.stream()
                .collect(Collectors.groupingBy(MonitoredData::getStartDayOfYear,
                        Collectors.groupingBy(MonitoredData::getActivityLabel, Collectors.counting())));
        return task4.countActivitiesPerDay(activities);
    }

    /**
     * Method that returns a Map<String, Long> structure containing the total duration of each activity over the monitoring
     * period. The method uses the Task5 functional interface.
     * @param activities List<MonitredData>
     * @return Map<String, Long>
     */
    public Map<String, Long> computeActivityDuration(List<MonitoredData> activities) {
        Task5 task5 = activities1 -> {
            Map<String, Long> activityDuration = new HashMap<String, Long>();

            for (MonitoredData data : activities1) {
                Duration duration = Duration.between(data.getStartTime(), data.getEndTime());

                if (activityDuration.containsKey(data.getActivityLabel())) {
                    activityDuration.put(data.getActivityLabel(), activityDuration.get(data.getActivityLabel()) + duration.toMinutes());
                }
                else {
                    activityDuration.put(data.getActivityLabel(), duration.toMinutes());
                }
            }
            return activityDuration;
        };
        return task5.computeActivityDuration(activities);
    }

    /**
     * Method that returns a List<String> structure containing the activity label of the MonitoredData objects that have
     * more than 90% of the monitoring records with duration less than 5 minutes. The method uses the Task6 functional
     * interface and stream processing to create the needed structure.
     * @param activities List<MonitoredData>
     * @return List<String>
     */
    public List<String> filterFiveMinutes(List<MonitoredData> activities) {
        Task6 task6 = activities1 -> {
            // Get number of times each activity was below 5 minutes
            Map<String, Long> fiveMinuteActivities = activities1.stream().filter(x -> x.getDurationSec() < 5 * 60)
                    .collect(Collectors.groupingBy(MonitoredData::getActivityLabel, Collectors.counting()));
            Map<String, Long> countActivities = countActivities(activities1);

            // Get percentage of duration less than 5 minutes for every activity
            for (String key : fiveMinuteActivities.keySet()) {
                fiveMinuteActivities.put(key, fiveMinuteActivities.get(key) / countActivities.get(key) * 100);
            }

            List<String> filtered = fiveMinuteActivities.keySet().stream().filter(x -> fiveMinuteActivities.get(x) > 90)
                    .collect(Collectors.toList());
            return filtered;
        };
        return task6.filterFiveMinutes(activities);
    }
}
