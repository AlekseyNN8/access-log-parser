import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        String path = "C:\\Users\\AKozin\\Documents\\HM2\\AccessLogParser\\src\\main\\resources\\access.log";
        Pattern pattern = Pattern.compile("\\(([^)]+)\\)");
        int totalRequests = 0;
        int yandexBotRequests = 0;
        int googleBotRequests = 0;


        try {


            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;


            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                int length = line.length();
                totalRequests++;
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
            int allReq = yandexBotRequests + googleBotRequests;

            if (totalRequests > 0) {
                double yandexBotRatio = (double) yandexBotRequests / allReq * 100;
                double googleBotRatio = (double) googleBotRequests / allReq * 100;

                System.out.printf("Доля YandexBot: %.2f%%%n", yandexBotRatio);
                System.out.printf("Доля Googlebot: %.2f%%%n", googleBotRatio);
            } else {
                System.out.println("Файл пуст или не содержит данных.");
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println("All lines: " + totalRequests);
    }

}