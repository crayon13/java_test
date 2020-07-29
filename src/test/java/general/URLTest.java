package general;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class URLTest {

    @Test
    public void allowProtocolTest() throws MalformedURLException {
        URL url = null;

        url = new URL("http://a.b.com");
        url = new URL("https://a.b.com");
        url =  new URL("ftp://a.b.com");
    }
}
