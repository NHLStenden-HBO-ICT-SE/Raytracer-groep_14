package org.raytracer.classes.RenderSupport;

/**
 * Uses code and is based on https://github.com/carl-vbn/pure-java-raytracer/blob/master/src/carlvbn/raytracing/pixeldata/PixelBuffer.java
 */
public class PixelBuffer {

    //an multidemsional array containing the pixels
    private PixelData [][] pixels;

    //dimensions of the screen
    private int screenWidth, screenHeight;


    public PixelBuffer(int screenWidth, int screenHeight){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.pixels = new PixelData[screenWidth][screenHeight];
    }

    /**
     * set a single pixel by adding the given data
     * @param x
     * @param y
     * @param pixeldata
     */
    public void setPixels(int x, int y, PixelData pixeldata){
        pixels[x][y] = pixeldata;
    }

    /**
     * get a single pixel from the buffer
     * @param x
     * @param y
     * @return
     */
    public PixelData getPixels(int x, int y) {
        return pixels[x][y];
    }


}
