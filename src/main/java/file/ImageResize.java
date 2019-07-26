package file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@Slf4j
public final class ImageResize {

    private ImageResize() {

    }

    private String imagePath;
    private int limitWidth;
    private int limitHeight;
    private BufferedImage bufferedImage;
    private boolean resize = false;

    public static ImageResize read(String imagePath) throws IOException {
        ImageResize imageProcess = new ImageResize();
        imageProcess.imagePath = imagePath;
        imageProcess.bufferedImage = ImageIO.read(new URL(imagePath));
        return imageProcess;
    }

    public ImageResize compareLimit(int limitWidth, int limitHeight) {
        this.limitWidth = limitWidth;
        this.limitHeight = limitHeight;

        int realWidth = this.bufferedImage.getWidth();
        int realHeight = this.bufferedImage.getHeight();

        if (realWidth > limitWidth && realHeight > limitHeight) {
            this.resize = true;
        }
        return this;
    }

    public boolean resize() throws IOException {
        if (this.resize) {
            return this.resize(limitWidth, limitHeight);
        }
        return false;
    }

    public boolean resize(int resizeWidth, int resizeHeight) throws IOException {
        BufferedImage scaledImg = Scalr.resize(bufferedImage, Scalr.Mode.AUTOMATIC, resizeWidth, resizeHeight);
        return ImageIO.write(scaledImg, getFileExtension(), new File(this.imagePath));
    }

    private String getFileExtension() {
        return FilenameUtils.getExtension(this.imagePath);
    }
}
