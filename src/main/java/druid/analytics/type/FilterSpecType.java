package druid.analytics.type;

import com.google.gson.annotations.SerializedName;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FilterSpecType {

    @SerializedName("and") AND,
    @SerializedName("or") OR,
    @SerializedName("selector") SELECTOR
    ;

}
