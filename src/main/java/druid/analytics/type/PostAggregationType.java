package druid.analytics.type;

import com.google.gson.annotations.SerializedName;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PostAggregationType {

    @SerializedName("+") PLUS,
    @SerializedName("-") MINUS,
    @SerializedName("*") MULTIPLY,
    @SerializedName("/") DIVISION,
    @SerializedName("quotient") QUOTIENT
    ;
}
