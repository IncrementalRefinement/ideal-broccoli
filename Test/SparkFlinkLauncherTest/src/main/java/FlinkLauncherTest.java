import org.apache.flink.client.deployment.StandaloneClusterId;
import org.apache.flink.client.program.*;
import org.apache.flink.client.program.rest.RestClusterClient;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.JobManagerOptions;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.runtime.jobgraph.JobGraph;

import java.io.File;

public class FlinkLauncherTest {

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        String clusterHost = "localhost";
        int clusterPort = 8081;

        conf.setString(JobManagerOptions.ADDRESS, clusterHost);
        conf.setInteger(RestOptions.PORT, clusterPort);


        PackagedProgram packagedProgram = PackagedProgram.newBuilder()
                .setJarFile(new File("/opt/apache-flink/examples/streaming/WordCount.jar"))
                .build();

        JobGraph jobGraph = PackagedProgramUtils.createJobGraph(packagedProgram, conf, 1, false);

        RestClusterClient<StandaloneClusterId> client = new RestClusterClient<>(conf, StandaloneClusterId.getInstance());
        client.submitJob(jobGraph).get();
    }
}
