package classes;

import lombok.Data;

import java.util.Objects;


@Data
public class TestA {
    private String abc;
    private TestB testB;

    @Data
    static class TestB {
        private String def;

        Object d;


        int a = 2136098994;
        //int b = 2236098994;


    }
}