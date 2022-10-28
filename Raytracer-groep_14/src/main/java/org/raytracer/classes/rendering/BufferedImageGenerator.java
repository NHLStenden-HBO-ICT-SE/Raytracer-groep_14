package org.raytracer.classes.rendering;


import org.raytracer.classes.objects.Color;

import java.awt.image.BufferedImage;

//Todo Add ray cast method
public class BufferedImageGenerator {
    
    
    private BufferedImage writeFrame;
    
    
    public BufferedImageGenerator(int widthAndHeight) {
        
        constructNewImg(widthAndHeight);
    }
    
    public void constructNewImg(int widthAndHeight) {
        writeFrame = new BufferedImage(widthAndHeight, widthAndHeight, BufferedImage.TYPE_INT_RGB);
    }
    
    public void writeFramePixel(int x, int y, Color color) {
        writeFrame.setRGB(x, y, color.getRGB());
        //writeFrame.setRGB(x,y, color.getRGBBitshifted());
    }
    
    public void writeFramePixel(int x, int y, int color) {
        try {
            writeFrame.setRGB(x, y, color);
        } catch (Exception e) {
            writeFrame.setRGB(x, y, Color.White.getRGB());
        }
    }
    
    public BufferedImage finishFrame() {
        return writeFrame;
    }
}
