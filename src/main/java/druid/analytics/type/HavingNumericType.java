package druid.analytics.type;

import com.google.gson.annotations.SerializedName;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum  HavingNumericType {
    @SerializedName("greaterThan") GREATER_THAN,        // >
    @SerializedName("equalTo") EQUAL_TO,                // =
    @SerializedName("lessThan") LESS_THAN               // <
    ;
}
