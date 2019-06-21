package druid.analytics;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class DruidDataSpec {
    private IoConfig ioConfig;
    private DruidDataSchema dataSchema;
    private final TuningConfig tuningConfig = new TuningConfig();


    @Data
    @Builder
    public static class IoConfig {
        private final String type = "hadoop";
        private InputSpec inputSpec;

        @Data
        @Builder
        public static class InputSpec {
            private final String type = "granularity";
            private String dataGranularity;
            private String inputPath;
            private String filePattern;
            private String pathFormat;
        }
    }

    @Data
    @NoArgsConstructor
    private class TuningConfig {
        private String type = "hadoop";
        private PartitionsSpec partitionsSpec = new PartitionsSpec();
        private JobProperties jobProperties = new JobProperties();

        @Data
        private class PartitionsSpec {
            private String type = "hashed";
            private long targetPartitionSize = 5000000;
        }

        @Data
        private class JobProperties {
            @SerializedName("mapreduce.user.classpath.first")
            private String key = "true";
        }
    }
}
