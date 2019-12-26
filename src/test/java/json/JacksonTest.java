package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import json.vo.IndexApiResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

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


    @Test
    public void stringToJson() {
        String testString = "{\n" +
                "  \"error\": {\n" +
                "    \"root_cause\": [\n" +
                "      {\n" +
                "        \"type\": \"index_not_found_exception\",\n" +
                "        \"reason\": \"no such index\",\n" +
                "        \"resource.type\": \"index_or_alias\",\n" +
                "        \"resource.id\": \"direct-contents1\",\n" +
                "        \"index_uuid\": \"_na_\",\n" +
                "        \"index\": \"direct-contents1\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"type\": \"index_not_found_exception\",\n" +
                "    \"reason\": \"no such index\",\n" +
                "    \"resource.type\": \"index_or_alias\",\n" +
                "    \"resource.id\": \"direct-contents1\",\n" +
                "    \"index_uuid\": \"_na_\",\n" +
                "    \"index\": \"direct-contents1\"\n" +
                "  },\n" +
                "  \"status\": 404\n" +
                "}";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            IndexApiResponseVO responseVO = objectMapper.readValue(testString, IndexApiResponseVO.class);

            assertThat(responseVO.isErrors()).isTrue();
            assertThat(responseVO.getStatus()).isEqualTo(404);
            assertThat(responseVO.getErrorVO().getType()).isEqualTo("index_not_found_exception");
            assertThat(responseVO.getErrorVO().getReason()).isEqualTo("no such index");

            log.debug(responseVO.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }


    @Test
    public void stringToJson2() {
        String testString = "{\n" +
                "  \"_shards\": {\n" +
                "    \"total\": 10,\n" +
                "    \"successful\": 5,\n" +
                "    \"failed\": 0\n" +
                "  }\n" +
                "}";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            IndexApiResponseVO responseVO = objectMapper.readValue(testString, IndexApiResponseVO.class);

            assertThat(responseVO.isErrors()).isFalse();
            assertThat(responseVO.getTotal()).isEqualTo(10);
            assertThat(responseVO.getSuccessful()).isEqualTo(5);
            assertThat(responseVO.getFailed()).isEqualTo(0);


            log.debug(responseVO.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }


    @Test
    public void stringToJsonNodeTest() {
        String given = "{\"actions\":[{\"remove\":{\"index\":\"index_1\",\"alias\":\"test_alias\"}},{\"remove\":{\"index\":\"index_1\",\"alias\":\"test_alias\"}}]}";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            ObjectNode jsonNode = (ObjectNode)objectMapper.readTree(given);
            assertThat(jsonNode.toString()).isEqualTo(given);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void asTextTest() {
        String given = "{\"index\":\"index_1\",\"alias\":\"test_alias\"}";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            JsonNode jsonNode = objectMapper.readTree(given);
            assertThat(jsonNode.get("index").asText()).isEqualTo("index_1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
