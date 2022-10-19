package Models;

import Fill.Filler;
import Fill.SeedFiller;
import Models.Polygon;
import raster.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;

public class Canvas {
    private final JFrame frame;
    private final JPanel panel;
    private final Raster raster;
    private final LineRasterizer lineRasterizer;
    private final Polygon polygon = new Polygon();
    private final PolygonRasterizer polygonRasterizer;
    private int x, y;
    public Canvas(int width, int height) {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setTitle("PGRF");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        raster = new RasterBufferedImage(width, height);
        lineRasterizer = new TrivialLineRasterizer(raster);
        polygonRasterizer = new PolygonRasterizer(lineRasterizer);
        //((RasterBufferedImage)raster).clear();
        //raster.setPixel(x, y, 0xffffff);
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ((RasterBufferedImage)raster).present(g);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        panel.requestFocus();
        panel.requestFocusInWindow();
        start();
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                raster.clear();
                polygonRasterizer.rasterize(polygon);
                switch (e.getButton()){
                    case MouseEvent.BUTTON1:
                        if (polygon.getCount() > 3) { polygon.clearPoints(); }
                        raster.clear();
                        Point point = new Point(e.getX(), e.getY());
                        polygon.addPoint(point);
                        polygonRasterizer.rasterize(polygon);
                        break;
                    case MouseEvent.BUTTON3:
                        Filler filler = new SeedFiller(e.getX(),e.getY(), 0x00ff00, Color.black.getRGB(), raster);
                        filler.fill();
                        break;
                }
                panel.repaint();
            }
        });


    }
    public void start(){
        raster.clear();
        panel.repaint();
    }
}
//TODO rovnoramm. trojuh. y = kx + q   // k2=1/k1
//TODO k3 = k1