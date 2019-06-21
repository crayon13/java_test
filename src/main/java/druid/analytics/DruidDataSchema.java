package druid.analytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
public class DruidDataSchema {
    private String dataSource;
    private GranularitySpec granularitySpec;
    private DruidDataSchemaParser parser;
    private List<MetricsSpec> metricsSpec;

    @Data
    @Builder
    public static class GranularitySpec {
        private final String type = "uniform";
        private final boolean rollup = false;
        private SegmentGranularity segmentGranularity;
        private final String queryGranularity = "none";
        private List<String> intervals;


        @Data
        @RequiredArgsConstructor
        public static class SegmentGranularity {
            private final String type = "period";
            private @NonNull String period;
            private final String timeZone = "Asia/Seoul";
        }
    }

    @Data
    @AllArgsConstructor
    public static class MetricsSpec {
        private String type;
        private String name;
        private String fieldName;
    }
}
