import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Outer {
    private String name = "Outer";
    private Inner inner;

    public Outer() {
        log.debug(this.name);
        this.inner = new Inner();
    }

    public static class Inner {
        public String name = "Inner";

        public Inner() {
            log.debug(this.name);
        }

        public void dubug() {
            log.debug("debug " + name);
        }
    }
}
