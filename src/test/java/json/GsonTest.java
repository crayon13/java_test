package json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class GsonTest {
    @Test
    public void simpleTest() {
        JsonObject rootNode = new JsonObject();

        JsonObject obj1 = new JsonObject();
        obj1.addProperty("name1","val1");
        obj1.addProperty("name2","val2");

        rootNode.add("obj1", obj1);

        JsonObject obj2 = new JsonObject();
        obj2.addProperty("name3","val3");
        obj2.addProperty("name4","val4");

        rootNode.add("obj2", obj2);

        JsonObject obj3 = new JsonObject();
        obj3.addProperty("name5","val5");
        obj3.addProperty("name6","val6");

        rootNode.add("obj3", obj3);


        log.debug(String.valueOf(rootNode));

    }

    @Test
    public void getJsonForString() {
        String jsonString = "{\n" +
            "  \"took\": 8,\n" +
            "  \"timed_out\": false,\n" +
            "  \"_shards\": {\n" +
            "    \"total\": 5,\n" +
            "    \"successful\": 5,\n" +
            "    \"failed\": 0\n" +
            "  },\n" +
            "  \"hits\": {\n" +
            "    \"total\": 596638,\n" +
            "    \"max_score\": null,\n" +
            "    \"hits\": [\n" +
            "      {\n" +
            "        \"_index\": \"good_search\",\n" +
            "        \"_type\": \"data\",\n" +
            "        \"_id\": \"1194967\",\n" +
            "        \"_score\": null,\n" +
            "        \"_source\": {\n" +
            "          \"ut\": \"2019-10-21 16:08:42\"\n" +
            "        },\n" +
            "        \"sort\": [\n" +
            "          1571674122000\n" +
            "        ]\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}";

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(jsonString);

        String updateDate = element.getAsJsonObject().get("hits")
            .getAsJsonObject().get("hits")
                .getAsJsonArray().get(0)
                    .getAsJsonObject().get("_source")
                        .getAsJsonObject().get("ut").getAsString();


        assertThat(updateDate).isEqualTo("2019-10-21 16:08:42");
    }

}
