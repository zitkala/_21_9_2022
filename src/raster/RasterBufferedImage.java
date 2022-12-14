package raster;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RasterBufferedImage implements Raster {
    private final BufferedImage image;
    public RasterBufferedImage(int width, int height){
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }


    @Override
    public void setPixel(int x, int y, int color) {
        image.setRGB(x, y, color);
    }

    @Override
    public int getPixel(int x, int y) {
        return image.getRGB(x, y);
    }
    @Override
    public void clear(){
        Graphics gr = image.getGraphics();
        gr.setColor(Color.BLACK);
        gr.fillRect(0,0, image.getWidth(), image.getHeight());
    }
    public void present(Graphics g){
        g.drawImage(image, 0, 0, null);
    }

    public BufferedImage getBufferedImage(){
        return image;
    }
}
