
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class LogEntry {
    final String ipAddress;
    final LocalDateTime dataStep;
    final HttpMethod method;
    final String pathRequest;
    final int codeResponse;
    final int size;
    final String referer;
    final UserAgent userAgent;

    public LogEntry(String string) throws FileNotFoundException, NumberFormatException {
        String[] parts = string.split(" ");
        this.ipAddress = parts[0];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        String dateString = string.substring(string.indexOf("[") + 1, string.indexOf("]"));
        LocalDateTime dataStep = null;

        try {
            dataStep = LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Failed to parse date: " + dateString);
        }
        this.dataStep = dataStep;

        HttpMethod detectedMethod = null;
        for (HttpMethod httpMethod : HttpMethod.values()) {
            if (httpMethod.toString().equals(parts[5].substring(1))) {
                detectedMethod = httpMethod;
                break;
            }
        }
        this.method = detectedMethod;

        this.pathRequest = parts[6];
        this.codeResponse = Integer.parseInt(parts[8]);
        this.size = Integer.parseInt(parts[9]);
        if (!parts[10].equals("\"-\"")) {
            this.referer = parts[10].substring(1, parts[10].length() - 1);
        } else {
            this.referer = "local";
        }
        this.userAgent = new UserAgent(string) ;


    }

    public enum HttpMethod {
        GET("GET"),
        POST("POST"),
        PUT("PUT"),
        DELETE("DELETE");

        private final String description;

        HttpMethod(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }

    }


    public int getSize() {
        return size;
    }

    public String getIpAddress() {

        return ipAddress;
    }

    public LocalDateTime getDataStep() {
        return dataStep;
    }


    public HttpMethod getMethod() {
        return method;
    }

    public String getPathRequest() {
        return pathRequest;
    }

    public int getCodeResponse() {
        return codeResponse;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }


}





/*
Создайте класс LogEntry со свойствами (полями), соответствующими компонентам строк лог-файла:
IP-адресу, дате и времени запроса, методу запроса, пути запроса, коду ответа, размеру отданных сервером данных, referer, а также User-Agent.
Возможные методы HTTP-запросов положите в enum. Типы остальных полей определите самостоятельно.
*/