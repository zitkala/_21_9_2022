package raster;

import static java.lang.Math.abs;

public class TrivialLineRasterizer extends LineRasterizer{

    public TrivialLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    protected void drawLine(int x1, int y1, int x2, int y2) {
        float k = (float)(y2 - y1)/ (float)(x2 - x1);
        float q = y1 - k * x1;

        if (abs(x1 - x2) > abs(y1 - y2)) {
            if (x1 <= x2) {
                setPixelCalculationX(x1, x2, k, q);
            } else {
                setPixelCalculationX(x2, x1, k, q);
            }
        } else {
            if (y1 <= y2) {
                setPixelCalculationY(y1, y2, k, q);
            } else {
                setPixelCalculationY(y2, y1, k, q);
            }
        }
    }
    private void setPixelCalculationX(int x1, int x2, float k, float q){
        for (int idx_x = x1; idx_x <= x2; idx_x++) {
            int temp_y = (int) (k * idx_x + q);
            raster.setPixel(idx_x, temp_y, 0xffffff);
        }
    }
    private void setPixelCalculationY(int y1, int y2, float k, float q){
        for (int idx_y = y1; idx_y <= y2; idx_y++) {
            int temp_x = (int) ((idx_y - q)/k);
            raster.setPixel(temp_x, idx_y, 0xffffff);
        }
    }
}
