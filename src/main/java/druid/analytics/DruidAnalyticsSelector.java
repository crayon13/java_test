package druid.analytics;


import druid.analytics.type.AggregationsType;
import druid.analytics.type.HavingNumericType;
import druid.analytics.type.LimitSpecType;
import druid.analytics.type.PeriodType;
import druid.analytics.type.PostAggregationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
public class DruidAnalyticsSelector {

    private final String queryType = "groupBy";
    private String dataSource;
    private Qranularity granularity;
    private List<String> dimensions = null;
    private List<Aggregation> aggregations = null;
    private List<PostAggregations> postAggregations;
    private DruidAnalyticsFilter filter = null;
    private Having having;
    private LimitSpec limitSpec = null;
    private List<String> intervals;

    @Data
    @AllArgsConstructor
    public static class Aggregation {
        private AggregationsType type;
        private String name;
        private String fieldName;
    }

    @Data
    @AllArgsConstructor
    public static class Having {
        private HavingNumericType type;
        private String aggregation;
        private String value;
    }

    @Data
    @AllArgsConstructor
    public static class LimitSpec {
        private LimitSpecType type;
        private List<Columns> columns;

        @Data
        @AllArgsConstructor
        public static class Columns {
            private String dimension;
            private LimitSpecType.LimitSpecColumnsType direction;
        }
    }

    @Data
    @RequiredArgsConstructor
    public static class Qranularity {
        private String type = "period";
        @NonNull
        private PeriodType period;
        private String timeZone = "Asia/Seoul";
    }

    @Data
    @RequiredArgsConstructor
    public static class PostAggregations {
        private final String type = "arithmetic";
        private @NonNull String name;
        private @NonNull
        PostAggregationType fn;
        private @NonNull List<Fields> fields;


        @Data
        @RequiredArgsConstructor
        public static class Fields {
            private final String type = "fieldAccess";
            private @NonNull String fieldName;
        }
    }
}
