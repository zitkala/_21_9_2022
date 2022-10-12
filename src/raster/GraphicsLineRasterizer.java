package raster;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphicsLineRasterizer extends LineRasterizer{
    public GraphicsLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    protected void drawLine(int x1, int y1, int x2, int y2) {
        Graphics g = ((RasterBufferedImage)raster).getBufferedImage().getGraphics();
        g.drawLine(x1, y1, x2, y2);
    }
}
