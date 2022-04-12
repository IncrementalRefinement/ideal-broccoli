package spark.test;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;

import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.List;

public class SparkDemoApp {

    private static final String INPUT_FILE = "/home/xabi/Documents/Code/ideal-broccoli/FlinkSparkCompDemo/SparkDemo/spark_flink_comp_topic_test.csv";

    public static void main(String[] args) {
        SparkSession spark = SparkSession
                                    .builder()
                                    .appName("Simple Application")
                                    .getOrCreate();
        Dataset<String> logData = spark.read().textFile(INPUT_FILE).cache();
        Dataset<String> res = logData.map((MapFunction<String, String>) SparkDemoApp::processElement, Encoders.STRING());
        // res.show((int) res.count());
        System.out.println("===========================");
        System.out.println(res.count());
        System.out.println("===========================");
    }


    public static String processElement(String line) {
        List<String> items = Arrays.asList(line.split(";"));
        String value = items.get(1);
        long primeNumberIndex = Long.parseLong(value);
        long currentIndex = 0;
        long currentNumber = 0;
        while (currentIndex < primeNumberIndex) {
            currentNumber++;
            if (isPrime(currentNumber)) {
                currentIndex++;
            }
        }
        return String.valueOf(currentNumber);
    }

    private static boolean isPrime(long num) {
        boolean isPrime = true;
        if (num <= 0) {
            return false;
        }

        if (num == 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); ++i) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }

        return isPrime;
    }
}
