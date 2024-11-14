import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException{

        String path = "C:\\Users\\AKozin\\Documents\\HM2\\AccessLogParser\\src\\main\\resources\\access.log";
        Pattern pattern = Pattern.compile("\\(([^)]+)\\)");
        long totalRequests = 0;
        long yandexBotRequests = 0;
        long googleBotRequests = 0;
        FileReader fileReader = new FileReader(path);
        BufferedReader reader = new BufferedReader(fileReader);
        String line;

        try {
            Statistics statistics = new Statistics();
            while ((line = reader.readLine()) != null) {
                int count = 1;
                Matcher matcher = pattern.matcher(line);
                int length = line.length();
                totalRequests++;
                LogEntry le = new LogEntry(line);
                statistics.addEntry(le);


                while (matcher.find()) {
                    String contentInParentheses = matcher.group(1);
                    if (contentInParentheses.contains("YandexBot")) {
                        yandexBotRequests++;

                    }
                    if (contentInParentheses.contains("Googlebot")) {
                        googleBotRequests++;
                    } if (length > 1024) {
                        throw new LineTooLong("Строка длиной более 1024 символа : " + length);
                    }

                }


            }


            System.out.println("getTotalTraffic - " + statistics.getTotalTraffic());
            System.out.println("getMaxTime - " + statistics.getMaxTime());
            System.out.println("getMinTime - " + statistics.getMinTime());
            System.out.println("getTrafficRate - " + statistics.getTrafficRate());
            System.out.println("getPages " + statistics.getPages());
            System.out.println("getOsStatistics " + statistics.getOsStatistics());
            System.out.println("getOsStat " + statistics.getOsStat());
            System.out.println();



            long allReq = yandexBotRequests + googleBotRequests;

            if (totalRequests > 0) {
                double yandexBotRatio = (double) yandexBotRequests / allReq * 100;
                double googleBotRatio = (double) googleBotRequests / allReq * 100;

                System.out.printf("Доля YandexBot - %.2f%%%n", yandexBotRatio);
                System.out.printf("Доля Googlebot - %.2f%%%n", googleBotRatio);
                System.out.println(allReq);
            } else {
                System.out.println("Файл пуст или не содержит данных.");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}