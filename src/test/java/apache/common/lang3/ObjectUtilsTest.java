package apache.common.lang3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.Test;

@Slf4j
public class ObjectUtilsTest {

    @Test
    public void a(){
        String a = "1";
        String b = ObjectUtils.defaultIfNull(a, "abc");

        log.debug(b);

    }



}
