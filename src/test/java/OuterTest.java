import org.junit.jupiter.api.Test;

public class OuterTest {
    @Test
    public void OuterTest() {
        Outer outer = new Outer();
        Outer.Inner inner = outer.getInner();

        inner.dubug();
    }
}
