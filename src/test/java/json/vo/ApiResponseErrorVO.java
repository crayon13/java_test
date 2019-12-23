package json.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponseErrorVO {
    private String type;
    private String reason;

    @JsonProperty("root_cause")
    private ApiResponseErrorVO[] rootCause;
}