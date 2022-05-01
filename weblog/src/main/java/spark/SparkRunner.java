package spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;

public class SparkRunner {

    public static void main(String[] args) {
        // 创建 Spark Session
        SparkSession spark = SparkSession
                .builder()
                .appName("WeblogSpark")
                .config("spark.master", "local")
                .getOrCreate();

        // 将 csv 转换为 Dataset<Row>，从而支持 Spark SQL
        StructType schema = new StructType()
                .add("ID", "string")
                .add("DATE", "string")
                .add("HOUR", "string")
                .add("MINUTE", "string")
                .add("URL", "string");

        Dataset<Row> df = spark.read()
                .option("mode", "DROPMALFORMED")
                .schema(schema)
                .csv("./processed_log.csv");

        df.createOrReplaceTempView("web_log");

        // top10 IP
        Dataset<Row> top10Ip= spark.sql(
                "SELECT ID, COUNT(*) as frequency"
                        + " FROM web_log"
                        + " GROUP BY ID"
                        + " ORDER BY frequency DESC"
                        + " LIMIT 10");
        top10Ip.show();
        top10Ip.write().format("csv").save("top10Ip.csv");

        // top10 URL
        Dataset<Row> top10Url= spark.sql(
                "SELECT URL, COUNT(*) as frequency"
                        + " FROM web_log"
                        + " GROUP BY URL"
                        + " ORDER BY frequency DESC"
                        + " LIMIT 10");
        top10Url.show();
        top10Url.write().format("csv").save("top10url.csv");

        // 时间与访问频率的关系
        Dataset<Row> timeFrequency= spark.sql(
                "SELECT HOUR, COUNT(*) as frequency"
                        + " FROM web_log"
                        + " GROUP BY HOUR"
                        + " ORDER BY HOUR");
        timeFrequency.show();
        timeFrequency.write().format("csv").save("timeFrequency.csv");
    }
}
