import org.apache.log4j.Logger;
import org.apache.spark.launcher.SparkLauncher;

import java.io.IOException;

public class SparkLauncherTest {

    final static Logger LOGGER = Logger.getLogger(SparkLauncherTest.class);

    final static String JAVA_HOME = "/usr/lib/jvm/java-11-openjdk";
    final static String SPARK_HOME = "/opt/apache-spark";
    final static String APP_RESOURCE = "/opt/apache-spark/examples/jars/spark-examples_2.12-3.2.0.jar";
    final static String MAIN_CLASS = "org.apache.spark.examples.SparkPi";

    public static void main(String[] args) throws IOException, InterruptedException {

        SparkLauncher sparkLauncher = new SparkLauncher()
                .setVerbose(true)
                .setJavaHome(JAVA_HOME)
                .setSparkHome(SPARK_HOME)
                .setAppResource(APP_RESOURCE)    // "/my/app.jar"
                .setMainClass(MAIN_CLASS)        // "my.spark.app.Main"
                .setMaster("local")
                .setConf(SparkLauncher.DRIVER_MEMORY, "1g");

        Process process = sparkLauncher.launch();

        // TODO: org.apache.spark.launcher.SparkLauncher.redirectError(java.io.File)
        //  AND org.apache.spark.launcher.SparkLauncher.redirectOutput(java.io.File) can replace this shit
        InputStreamReaderRunnable inputStreamReaderRunnable = new InputStreamReaderRunnable(process.getInputStream(), "input");
        Thread inputThread = new Thread(inputStreamReaderRunnable, "LogStreamReader input");
        inputThread.start();
        //
        InputStreamReaderRunnable errorStreamReaderRunnable = new InputStreamReaderRunnable(process.getErrorStream(), "error");
        Thread errorThread = new Thread(errorStreamReaderRunnable, "LogStreamReader error");
        errorThread.start();
        //
        LOGGER.info("Waiting for finish...");
        int exitCode = process.waitFor();
        LOGGER.info("Finished! Exit code:" + exitCode);
    }
}
