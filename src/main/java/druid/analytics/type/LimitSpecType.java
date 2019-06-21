package druid.analytics.type;

import com.google.gson.annotations.SerializedName;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum  LimitSpecType {
    @SerializedName("default") DEFAULT
    ;

    @RequiredArgsConstructor
    public enum LimitSpecColumnsType{
        @SerializedName("ascending") ASC,
        @SerializedName("descending") DESC
        ;
    }
}
