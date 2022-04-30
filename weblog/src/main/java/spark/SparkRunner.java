package spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;

public class SparkRunner {

    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .appName("WeblogSpark")
                .getOrCreate();

        StructType schema = new StructType()
                .add("ID", "string")
                .add("DATE", "string")
                .add("HOUR", "string")
                .add("MINUTE", "string")
                .add("URL", "string");

        Dataset<Row> df = spark.read()
                .option("mode", "DROPMALFORMED")
                .schema(schema)
                .csv("processed_log.csv");

        df.createOrReplaceTempView("web_log");

        // TODO: here
        Dataset<Row> sqlResult = spark.sql(
                "SELECT COUNT(*)"
                        + " FROM web_log");

        sqlResult.show(); //for testing
    }
}
