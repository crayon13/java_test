package general;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.junit.jupiter.api.Test;

import javax.validation.Valid;
import javax.validation.constraints.Min;

public class ValidationTest {

    @Test
    public void urlTest() {
        @Valid Vo vo = new Vo("abc.com", 0);
    }




    @Getter
    @Setter
    public static class Vo {
        @Valid
        public Vo(String url, long count) {
            this.url = url;
            this.count = count;
        }

        @URL(message = "url 오류")
        private String url;

        @Min(value = 1, message = "count 오류")
        private long count;
    }
}
