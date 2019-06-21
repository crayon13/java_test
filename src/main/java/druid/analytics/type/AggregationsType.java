package druid.analytics.type;

import com.google.gson.annotations.SerializedName;
import lombok.RequiredArgsConstructor;

/**
 * http://druid.io/docs/latest/querying/aggregations.html
 */
@RequiredArgsConstructor
public enum AggregationsType {
    @SerializedName("count") COUNT,
    @SerializedName("longSum") LONG_SUM,
    @SerializedName("doubleSum") DOUBLE_SUM,
    @SerializedName("floatSum") FLOAT_SUM,
    @SerializedName("doubleMin") DOUBLE_MIN,
    @SerializedName("doubleMax") DOUBLE_MAX,
    @SerializedName("floatMin") FLOAT_MIN,
    @SerializedName("floatMax") FLOAT_MAX,
    @SerializedName("longMin") LONG_MIN,
    @SerializedName("longMax") LONG_MAX,
    @SerializedName("doubleFirst") DOUBLE_FIRST,
    @SerializedName("doubleLast") DOUBLE_LAST,
    @SerializedName("floatFirst") FLOAT_FIRST,
    @SerializedName("floatLast") FLOAT_LAST,
    @SerializedName("longFirst") LONG_FIRST,
    @SerializedName("longLast") LONG_LAST
    ;

}
