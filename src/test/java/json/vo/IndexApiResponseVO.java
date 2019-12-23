package json.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class IndexApiResponseVO {
    @Getter
    @Setter
    private Integer status;
    @Getter
    @Setter
    private boolean errors;
    @Getter
    @Setter
    @JsonProperty("error")
    private ApiResponseErrorVO errorVO;
    @Setter
    @JsonProperty("_shards")
    private Shard shard;

    public boolean isErrors() {
        return errorVO != null;
    }

    public Integer getTotal() {
        return shard.getTotal();
    }

    public Integer getSuccessful() {
        return shard.getSuccessful();
    }

    public Integer getFailed() {
        return shard.getFailed();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public class Shard {
        public Integer total;
        public Integer successful;

        public Integer failed;
    }
}
