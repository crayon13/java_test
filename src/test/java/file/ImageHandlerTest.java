package file;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ImageHandlerTest {
    @Test
    public void urlImageWidhtHeightTest() {
        try {
            String imagePath = "/cat/19/2/7/4/7/5/7/MgzJW/17274757_dtl_1.PNG";
            URL url = new URL("http://i.011st.com" + imagePath);

            Image image = ImageIO.read(url);
            int width = image.getWidth(null);
            int height = image.getHeight(null);

            System.out.println("w : " + width + ", h : " + height);

            List<String> imageUrlList = getDimsImageUrlList(imagePath, width, height);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getImageCutNumberTest() {
        assertThat( getImageCutNumber(960, 1000), is(1));
        assertThat( getImageCutNumber(960, 1001), is(2));
    }

    private List<String> getDimsImageUrlList(String imagePath, int realWidth, int realHeight) {





        http://cdn.011st.com/11dims/resize/300x300/quality/75/crop/300x100+20+30/11src/pd/19/7/1/6/0/0/0/heGpl/169716000_L300.jpg
        return null;
    }

    private int getImageCutNumber(int realWidth, int realHeight) {
        final int MAX_HEIGHT = 1000;
        final int FIX_WIDTH = 960;

        double widthHeightRatio = (double)realHeight / (double)realWidth;
        double resizeWidth = Math.min(realWidth, FIX_WIDTH);
        double resizeHeight = (int) (resizeWidth * widthHeightRatio);

        double cuts =  resizeHeight / MAX_HEIGHT;
        if ( cuts == 0 || cuts - (int)cuts > 0 ) cuts++;

        return (int)cuts;
    }
}
