package search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ApiErrorResponseDTOTest {

    @Test
    public void test() {
        ApiErrorResponseDTO dto = ApiErrorResponseDTO.Error.builder()
                .code("1")
                .message("2")
                .userMessage("3").build().convert();


        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.debug(jsonString);
    }
}
