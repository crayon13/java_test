package druid.analytics;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DruidDataInsertObject {

    private final String type = "index_hadoop";
    private DruidDataSpec spec;
}
