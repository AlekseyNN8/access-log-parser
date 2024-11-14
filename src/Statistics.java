
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private final HashSet<String> pages = new HashSet<>();
    private final HashMap<String, Integer> osStat = new HashMap<>();

    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = null;
        this.maxTime = null;
    }



    public void addEntry(LogEntry entry) throws NullPointerException {
        this.totalTraffic += entry.getSize();
        if (entry.getCodeResponse() ==200) {
            pages.add(entry.getReferer());
        }

        String osType = entry.getUserAgent().getOsType();
        if (osType != null) {
            osStat.put(osType, osStat.getOrDefault(osType, 0) + 1);
        }


        LocalDateTime entryTime = entry.getDataStep();
        if (minTime == null || entryTime.isBefore(minTime)) {

            this.minTime = entryTime;
        }
        if (maxTime == null || entryTime.isAfter(maxTime)) {
            this.maxTime = entryTime;
        }


    }

    public HashMap<String, Double> getOsStatistics() {
        HashMap<String, Double> osStatistics = new HashMap<>();
        int totalOsCount = osStat.get("Linux") + osStat.get("Windows") + osStat.get("macOS");

        for (Map.Entry<String, Integer> entry : osStat.entrySet()) {
            String os = entry.getKey();
            int count = entry.getValue();
            osStatistics.put(os, (double) count / totalOsCount);
        }

        return osStatistics;
    }

    public double getTrafficRate() {
        if (minTime == null || maxTime == null || minTime.equals(maxTime)) {
            return 0.0;
        }

        long hours = Duration.between(minTime, maxTime).toHours();
        return hours > 0 ? (double) totalTraffic / hours : 0.0;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Statistics(int totalTraffic, LocalDateTime minTime, LocalDateTime maxTime) {
        this.totalTraffic = totalTraffic;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public HashMap<String, Integer> getOsStat() {
        return osStat;
    }

    public long getTotalTraffic() {
        return totalTraffic;
    }

    public LocalDateTime getMinTime() {
        return minTime;
    }

    public HashSet<String> getPages() {
        return pages;
    }


    public LocalDateTime getMaxTime() {
        return maxTime;
    }
}



