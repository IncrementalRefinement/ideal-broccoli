package preprocess;

import java.io.*;

public class Preprocessor {

    private static final String RAW_FILE_LOCATION = "./access.log.fensi";
    private static final String RESULT_FILE_LOCATION = "./processed_log.csv";

    public static void main(String[] args) throws IOException {
        File rawLog = new File(RAW_FILE_LOCATION);
        PrintWriter resultFile = new PrintWriter(RESULT_FILE_LOCATION, "UTF-8");
        resultFile.println("ID,DATE,HOUR,MINUTE,URL");

        BufferedReader br = new BufferedReader(new FileReader(rawLog));
        String line;
        while ((line = br.readLine()) != null) {
            String[] splited = line.split("\\s+");
            if (splited[5].equals("\"HEAD") || splited[6].equals("400")) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(splited[0]);
            sb.append(',');
            String timeStamp = splited[3].substring(1);
            String date = timeStamp.substring(0, 11);
            String hour = timeStamp.substring(12, 14);
            String minute = timeStamp.substring(15, 17);
            sb.append(date);
            sb.append(',');
            sb.append(hour);
            sb.append(',');
            sb.append(minute);
            sb.append(',');
            sb.append(splited[6]);
            resultFile.println(sb.toString());
        }

        resultFile.close();
    }
}
