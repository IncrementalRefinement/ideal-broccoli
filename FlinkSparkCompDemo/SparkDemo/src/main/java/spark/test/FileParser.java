package spark.test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class FileParser {

    public static void main(String[] args) throws IOException {
        File file = new File("spark_flink_comp_topic_test.csv");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        PrintWriter writer = new PrintWriter("parsedInput.txt", StandardCharsets.UTF_8);

        String line;
        int i = 0;
        while (i < 12775) {
            i++;
            line = bufferedReader.readLine();
            List<String> itemList = Arrays.asList(line.split(";"));
            writer.println(itemList.get(1));
        }

    }
}
