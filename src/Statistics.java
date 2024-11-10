
import java.time.LocalDateTime;
import java.time.Duration;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = null;
        this.maxTime = null;
    }

    public void addEntry(LogEntry entry) throws NullPointerException {
        this.totalTraffic += entry.getSize();


        LocalDateTime entryTime = entry.getDataStep();
        if (minTime == null || entryTime.isBefore(minTime)) {

            this.minTime = entryTime;
        }
        if (maxTime == null || entryTime.isAfter(maxTime)) {
            this.maxTime = entryTime;
        }
    }

    public double getTrafficRate() {
        if (minTime == null || maxTime == null || minTime.equals(maxTime)) {
            return 0.0;
        }

        long hours = Duration.between(minTime, maxTime).toHours();
        return hours > 0 ? (double) totalTraffic / hours : 0.0;
    }

    public Statistics(int totalTraffic, LocalDateTime minTime, LocalDateTime maxTime) {
        this.totalTraffic = totalTraffic;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public long getTotalTraffic() {
        return totalTraffic;
    }

    public LocalDateTime getMinTime() {
        return minTime;
    }

    public LocalDateTime getMaxTime() {
        return maxTime;
    }
}



