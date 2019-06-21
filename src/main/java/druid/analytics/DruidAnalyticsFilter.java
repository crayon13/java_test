package druid.analytics;

import druid.analytics.type.FilterSpecType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DruidAnalyticsFilter {
    private FilterSpecType type;
    private List<Field> fields;



    @Data
    public static class Field{
        private FilterSpecType type;
        private String dimension;
        private String value;
        private List<SubField> fields = null;

        public Field(FilterSpecType type, String dimension, String value){
            this.type = type;
            this.dimension = dimension;
            this.value = value;
        }

        public Field(FilterSpecType type, List<SubField> fields){
            this.type = type;
            this.fields = fields;
        }
    }

    @Data
    @AllArgsConstructor
    public static class SubField{
        private FilterSpecType type;
        private String dimension;
        private String value;
    }
}
