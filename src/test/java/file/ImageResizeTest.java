package file;

import org.junit.Test;

public class ImageResizeTest {

    @Test
    public void test() {
        ImageResize
            .setImage("http://image.msscdn.net/images/prd_img/2017082812453800000030445.jpg")
            .compareLimit(300, 300)
            .resize();


    }
}
