package file;

import org.junit.Test;

import java.io.IOException;

public class ImageResizeTest {

    @Test
    public void test() {
        try {
            ImageResize
                .read("http://image.msscdn.net/images/prd_img/2017082812453800000030445.jpg")
                .compareLimit(300, 300)
                .resize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
