package apache.common.lang3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ObjectUtilsTest {

    @Test
    public void a(){
        String a = "1";
        String b = ObjectUtils.defaultIfNull(a, "abc");

        log.debug(b);
    }

    @Test
    public void defaultIfNullTest() {
        Object testObject = null;
        assertThat(ObjectUtils.defaultIfNull(testObject, "1").toString()).isEqualTo("1");

        Integer integerObject = 2;
        assertThat(ObjectUtils.defaultIfNull(integerObject, "1").toString()).isEqualTo("2");
    }




}
