package file;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@Slf4j
public class ImageResize {
    private String imagePath;

    private int resizeWidth;
    private int resizeHeight;

    private int limitWidth;
    private int limitHeight;

    private BufferedImage bufferedImage;
    private boolean resize = false;


    public static ImageResize setImage(String imagePath) {
        ImageResize imageProcess = new ImageResize();

        imageProcess.imagePath = imagePath;

        log.debug("imagePath : " + imagePath);

        try {
            imageProcess.bufferedImage = ImageIO.read(new URL(imageProcess.imagePath));
        } catch (IOException e) {
            log.error(String.valueOf(e));
        }


        return imageProcess;
    }


    public ImageResize compareLimit(int limitWidth, int limitHeight) {
        this.limitWidth = limitWidth;
        this.limitHeight = limitHeight;

        int realWidth = this.bufferedImage.getWidth();
        int realHeight = this.bufferedImage.getHeight();


        if ( realWidth > limitWidth )  {
            log.debug("realWidth : " + realWidth + ", limitWidth : " + limitWidth);
            this.resize = true;
        }

        if ( realHeight > limitHeight )  {
            log.debug("realHeight : " + realHeight + ", limitHeight : " + limitHeight);
            this.resize = true;
        }

        return this;
    }

    public void resize() {
        this.resize(limitWidth, limitHeight) ;
    }

    private void resize(int resizeWidth, int resizeHeight) {
        this.resizeWidth = resizeWidth;
        this.resizeHeight = resizeHeight;

        if ( this.resize ) {
            // 여기서 실제 리사이징을 합니다.
            log.debug("리사이징!!!!");
        }
    }

}
