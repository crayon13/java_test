package search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

public class ApiErrorResponseDTO {
    private Error error;

    public ApiErrorResponseDTO(Error error) {
        this.error = error;
    }

    @Builder
    public static class Error {
        private String code;
        private String message;
        @JsonProperty("usermessage")
        private String userMessage;

        public ApiErrorResponseDTO convert() {
            return new ApiErrorResponseDTO(this);
        }
    }
}
