package druid.analytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DruidDataSchemaParser {
    private final String type = "hadoopyString";
    private ParseSpec parseSpec;

    @Data
    @Builder
    public static class ParseSpec {
        private final String format = "tsv";
        private List<String> columns;
        private DimensionsSpec dimensionsSpec;
        private TimestampSpec timestampSpec;

        @Data
        @AllArgsConstructor
        public static class DimensionsSpec {
            private List<String> dimensions;
        }

        @Data
        @AllArgsConstructor
        public static class TimestampSpec {
            private String format;
            private String column;
        }
    }
}
