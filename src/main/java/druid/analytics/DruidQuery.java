package druid.analytics;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class DruidQuery {

    @Data
    @Builder
    public static class DruidInsert {
        private String dataSource;
        private String ioDataGranularity;
        private String ioInputPath;
        private String ioFilePattern;
        private String ioPathFormat;

        private List<String> dataSchemaParserColumns;
        private List<String> dataSchemaParserDimensionsSpec;
        private DruidDataSchemaParser.ParseSpec.TimestampSpec dataSchemaParserTimestampSpec;
        private List<DruidDataSchema.MetricsSpec> dataSchemaMetricsSpecs;
        private String intervals;
        private DruidDataSchema.GranularitySpec.SegmentGranularity segmentGranularity;

        public DruidDataInsertObject toQuery() throws Exception {
            return DruidDataInsertObject.builder()
                                       .spec(
                                           DruidDataSpec.builder()
                                                        .ioConfig(getIoConfig(ioDataGranularity, ioInputPath, ioFilePattern, ioPathFormat))
                                                        .dataSchema(getDruidDataSchema(
                                                            dataSource
                                                            , dataSchemaParserColumns
                                                            , dataSchemaParserDimensionsSpec
                                                            , dataSchemaParserTimestampSpec
                                                            , dataSchemaMetricsSpecs
                                                            , intervals
                                                            , segmentGranularity
                                                            )
                                                        )
                                                        .build()
                                       )
                                       .build();
        }
    }







    private static DruidDataSpec.IoConfig getIoConfig(String dataGranularity, String inputPath, String filePattern, String pathFormat) {
        return DruidDataSpec.IoConfig.builder()
                                     .inputSpec(
                                         DruidDataSpec.IoConfig.InputSpec.builder()
                                                                         .dataGranularity(dataGranularity)
                                                                         .inputPath(inputPath)
                                                                         .filePattern(filePattern)
                                                                         .pathFormat(pathFormat)
                                                                         .build()
                                     )
                                     .build();
    }

    private static DruidDataSchema getDruidDataSchema(
        String dataSource
        , List<String> dataSchemaParserColumns
        , List<String> dataSchemaParserDimensionsSpec
        , DruidDataSchemaParser.ParseSpec.TimestampSpec dataSchemaParserTimestampSpec
        , List<DruidDataSchema.MetricsSpec> dataSchemaMetricsSpecs
        , String intervals
        , DruidDataSchema.GranularitySpec.SegmentGranularity segmentGranularity

    ) throws Exception {
        DruidDataSchemaParser dataSchemaParser
            = DruidDataSchemaParser.builder()
                                   .parseSpec(
                                       DruidDataSchemaParser.ParseSpec.builder()
                                                                      .columns(dataSchemaParserColumns)
                                                                      .dimensionsSpec(
                                                                          new DruidDataSchemaParser.ParseSpec.DimensionsSpec(dataSchemaParserDimensionsSpec)
                                                                      )
                                                                      .timestampSpec(dataSchemaParserTimestampSpec)
                                                                      .build()
                                   )
                                   .build();

        return DruidDataSchema.builder()
                              .dataSource(dataSource)
                              .granularitySpec(getGranularitySpec(intervals, segmentGranularity))
                              .parser(dataSchemaParser)
                              .metricsSpec(dataSchemaMetricsSpecs)
                              .build();
    }

    private static DruidDataSchema.GranularitySpec getGranularitySpec(String intervals, DruidDataSchema.GranularitySpec.SegmentGranularity segmentGranularity) throws Exception {
        return DruidDataSchema.GranularitySpec.builder()
                                              .intervals(
                                                  Lists.newArrayList(intervals)
                                              )
                                              .segmentGranularity(
                                                  (segmentGranularity != null) ? segmentGranularity : new DruidDataSchema.GranularitySpec.SegmentGranularity("P1D")

                                              )
                                              .build();
    }
}
