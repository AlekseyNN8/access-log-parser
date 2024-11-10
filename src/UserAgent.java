import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgent {
   private String osType;
   private String browser;

    public UserAgent(String string) {
        Pattern osPattern = Pattern.compile("Linux|Windows|Macintosh", Pattern.CASE_INSENSITIVE);
        Pattern browserPattern = Pattern.compile("Chrome|Edge|Firefox|Opera", Pattern.CASE_INSENSITIVE);
        Matcher osMatcher = osPattern.matcher(string);
        if (osMatcher.find()) {
            String osMatch = osMatcher.group();
            switch (osMatch.toLowerCase()) {
                case "linux":
                    this.osType = "Linux";
                    break;
                case "windows":
                    this.osType = "Windows";
                    break;
                case "macintosh":
                    this.osType = "macOS";
                    break;
            }
        }
        Matcher browserMatcher = browserPattern.matcher(string);
        if (browserMatcher.find()) {
            this.browser = "Chrome";
        }

    }

    @Override
    public String toString() {
        return "osType = '" + osType + '\'' +
                ", browser = '" + browser + '\'';
    }

    public String getOsType() {
        return osType;
    }

    public String getBrowser() {
        return browser;
    }
}
