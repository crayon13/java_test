package druid.analytics.type;

import com.google.gson.annotations.SerializedName;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PeriodType {

    @SerializedName("PT1H") HOUR,
    @SerializedName("P1D") DAY,
    @SerializedName("P1W") WEEK,
    @SerializedName("P1M") MONTH
    ;
}
