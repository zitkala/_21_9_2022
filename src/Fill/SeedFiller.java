package Fill;

import raster.Raster;

public class SeedFiller implements Filler{
    private final int x,y;
    private final Raster raster;
    private final int fillColor, backgroundColor;
    public SeedFiller(int x, int y, int fillColor, int backgroundColor, Raster raster){
        this.x = x;
        this.y = y;
        this.fillColor = fillColor;
        this.backgroundColor = backgroundColor;
        this.raster = raster;
    }

    @Override
    public void fill() {
        seedFill(x, y);
    }

    private void seedFill(int x, int y) {
        int pixel_color = raster.getPixel(x, y);
        if (pixel_color == backgroundColor) {
            raster.setPixel(x, y, fillColor);
            seedFill(x + 1, y);
            seedFill(x, y + 1);
            seedFill(x - 1, y);
            seedFill(x, y - 1);
        } else { return; }

        // nacist barvu z rasteru
        // porovnam nactenou barvu pixelu s barvou pozadi
        // kdyz barva pixelu != barva pozadi -> koncim

        //obarvim
        //pro 4 sousedy zavolam rekurzivne seedfill
    }
}
