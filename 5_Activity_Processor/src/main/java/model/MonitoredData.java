package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MonitoredData {
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String activityLabel;

    /**
     * Constructor method. Sets the startTime and endTime variables by parsing the parameter Strings and the DateTimeFormatter
     * "yyyy-MM-dd HH:mm:ss" and the activityLabel with the third String given as parameter.
     * @param startTime String
     * @param endTime String
     * @param activityLabel String
     */
    public MonitoredData(String startTime, String endTime, String activityLabel) {
        this.startTime = LocalDateTime.parse(startTime, DATE_TIME_FORMATTER);
        this.endTime = LocalDateTime.parse(endTime, DATE_TIME_FORMATTER);
        this.activityLabel = activityLabel;
    }

    @Override
    public String toString() {
        return activityLabel + ": " + startTime.toLocalDate() + " " + startTime.toLocalTime() + " -> " +
                endTime.toLocalDate() + " " + endTime.toLocalTime();
    }

    public int getStartDayOfYear() {
        return startTime.getDayOfYear();
    }

    /**
     * Returns a distinct integer for each possible start day in the monitoring data. It uses the formula:
     * 31 * (month + year + dayOfMonth).
     * @return Integer
     */
    public int getDistinctStartDay() {
        return 31 * (getStartTime().getMonthValue() + getStartTime().getYear()) + startTime.getDayOfMonth();
    }

    /**
     * Returns a distinct integer for each possible end day in the monitoring data. It uses the formula:
     * 31 * (month + year + dayOfMonth).
     * @return Integer
     */
    public int getDistinctEndDay() {
        return 31 * (getEndTime().getMonthValue() + getEndTime().getYear()) + endTime.getDayOfMonth();
    }

    public long getDurationSec() {
        return Duration.between(startTime, endTime).getSeconds();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getActivityLabel() {
        return activityLabel;
    }
}
