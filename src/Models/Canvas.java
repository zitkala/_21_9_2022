package Models;

import Models.Polygon;
import raster.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
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
        //x = width/2;
        //y = height/2;
        raster = new RasterBufferedImage(width, height);
        lineRasterizer = new TrivialLineRasterizer(raster);
        polygonRasterizer = new PolygonRasterizer(lineRasterizer);

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
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                //super.mouseReleased(e);
                Point point = new Point(e.getX(), e.getY());
                polygon.addPoint(point);
                polygonRasterizer.rasterize(polygon);
                panel.repaint();
            }
        });


    }
}
//TODO rovnoramm. trojuh. y = kx + q   // k2=1/k1
//TODO k3 = k1