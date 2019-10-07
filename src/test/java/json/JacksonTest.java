package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class JacksonTest {
    @Test
    public void simpleTest() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.createObjectNode();

        JsonNode childNode1 = mapper.createObjectNode();

        ((ObjectNode) childNode1).put("name1", "val1");
        ((ObjectNode) childNode1).put("name2", "val2");

        ((ObjectNode) rootNode).set("obj1", childNode1);

        JsonNode childNode2 = mapper.createObjectNode();
        ((ObjectNode) childNode2).put("name3", "val3");
        ((ObjectNode) childNode2).put("name4", "val4");

        ((ObjectNode) rootNode).set("obj2", childNode2);

        JsonNode childNode3 = mapper.createObjectNode();
        ((ObjectNode) childNode3).put("name5", "val5");
        ((ObjectNode) childNode3).put("name6", "val6");

        ((ObjectNode) rootNode).set("obj3", childNode3);


        String jsonString = null;
        try {
            jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.debug(jsonString);
    }
}
