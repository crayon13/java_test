package json.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class CommandApiResponseVO {
    private boolean acknowledged;

    @Getter
    @JsonProperty("error")
    private ApiResponseErrorVO errorVO;

    public boolean isErrors() {
        return !acknowledged || errorVO != null;
    }

}
