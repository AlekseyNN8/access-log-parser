import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        String path = "C:\\Users\\AKozin\\Documents\\HM2\\AccessLogParser\\src\\main\\resources\\access.log";
        File file = new File(path);
        long lineCount = 0;
        int maxLength = 0;
        int minLength = Integer.MAX_VALUE;


        try {

            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                int length = line.length();
                lineCount++;
//                System.out.println("Длинна каждой строки : " + length);
                if(length > maxLength){
                    maxLength=length;
                } if (length < minLength){
                    minLength = length;
                } if (length > 1024) {
                    throw new LineTooLong("Строка длиной более 1024 символа : " + length);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("All lines: " + lineCount);
        System.out.println("Max length : " + maxLength);
        System.out.println("Min length : " + minLength);





    }

}