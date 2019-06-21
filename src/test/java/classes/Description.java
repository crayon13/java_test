package classes;

import lombok.Data;

import java.util.List;

@Data
public class Description {
    private String name;
    private List<Bold> bolds;


    @Data
    class Bold {
        private String type;


        @Data
        class Color {
            private String code;
        }
    }
}

