package presentationLayer;

import businessLogicLayer.Solver;
import model.MonitoredData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private List<MonitoredData> activities;
    private Solver solver;
    private String fileName;

    /**
     * Constructor method. Initializes the activity list, the Solver object and sets the input file name to the String
     * given as parameter.
     * @param fileName String
     */
    public Controller(String fileName) {
        activities = new ArrayList<MonitoredData>();
        solver = new Solver();
        this.fileName = fileName;
    }

    /**
     * Run method. Executes the six implemented tasks.
     */
    public void run() {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
    }

    /**
     * Task1: Define a class MonitoredData with 3 fields: start time, end time and activity as string.Read the data
     * from the file Activity.txt using streams and split each line in 3 parts: start_time, end_time and activity_label,
     * and create a list of objects of type MonitoredData
     */
    private void task1() {
        try {
            Stream<String> stream = Files.lines(Paths.get(fileName));
            activities = stream.map(solver::readData).collect(Collectors.toList());

            File file = new File("Task1.txt");
            FileWriter writer = new FileWriter(file);
            for (MonitoredData data : activities) {
                writer.append(data.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Task2: Count the distinct days that appear in the monitoring data
     */
    private void task2() {
        try {
            int distinctDays = solver.countDistinctDays(activities);

            File file = new File("Task2.txt");
            FileWriter writer = new FileWriter(file);
            writer.append("Number of distinct days: " + distinctDays);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Task3: Count how many times each activity has appeared over the entire monitoring period. Return a structure of
     * type Map<String, Integer> representing the mapping of each distinct activity to the number of occurrences in the
     * log; therefore the key of the Map will represent a String object corresponding to the activity name, and the value
     * will represent an Integer object corresponding to the number of times the activity has appeared over the monitoring period.
     */
    private void task3() {
        try {
            Map<String, Long> activityFrequency = solver.countActivities(activities);

            File file = new File("Task3.txt");
            FileWriter writer = new FileWriter(file);
            for (String key : activityFrequency.keySet()) {
                writer.append(key + " appeared " + activityFrequency.get(key) + " times\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Task4: Count for how many times each activity has appeared for each day over the monitoring period.
     * Return a structure of type Map<Integer, Map<String, Integer>> that contains the activity count for each day of the log;
     * therefore the key of the Map will represent an Integer object corresponding to the number of the monitored
     * day, and the value will represent a Map<String, Integer> (in this map the key which is a String object corresponds
     * to the name of the activity, and the value which is an Integer object corresponds to the number of times that activity
     * has appeared within the day)
     */
    private void task4() {
        try {
            Map<Integer, Map<String, Long>> activitiesPerDay = solver.countActivitiesPerDay(activities);

            File file = new File("Task4.txt");
            FileWriter writer = new FileWriter(file);
            StringBuilder sb = new StringBuilder();
            for(Integer day : activitiesPerDay.keySet()) {
                sb.append("Day " + day + ":\n");
                for(String activity : activitiesPerDay.get(day).keySet()) {
                    sb.append("\t" + activity + " " + activitiesPerDay.get(day).get(activity) + " times\n");
                }
            }
            writer.append(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * For each activity compute the entire duration over the monitoring period.
     * Return a structure of type Map<String, LocalTime> in which the key of the Map will represent a String object
     * corresponding to the activity name, and the value will represent a LocalTime object corresponding to the entire
     * duration of the activity over the monitoring period.
     */
    private void task5() {
        try {
            Map<String, Long> activityDuration = solver.computeActivityDuration(activities);

            File file = new File("Task5.txt");
            FileWriter writer = new FileWriter(file);
            for(String label : activityDuration.keySet()) {
                writer.append(label + " had a total duration of: " + activityDuration.get(label) + " minutes\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Filter the activities that have more than 90% of the monitoring records with duration less than 5 minutes,
     * collect the results in a List<String> containing only the distinct activity names and return the list.
     */
    private void task6() {
        try {
            List<String> filtered = solver.filterFiveMinutes(activities);

            File file = new File("Task6.txt");
            FileWriter writer = new FileWriter(file);
            for(String label : filtered) {
                writer.append(label + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
