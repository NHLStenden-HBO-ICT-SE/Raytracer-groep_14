package org.raytracer.classes.rendering;


import org.raytracer.classes.objects.Color;

import java.awt.image.BufferedImage;

//Todo Add ray cast method
public class RenderPixelColors {


    private BufferedImage writeFrame;


    public RenderPixelColors(int widthAndHeight){

        constructNewImg(widthAndHeight);
    }

    public void constructNewImg(int widthAndHeight){
        writeFrame = new BufferedImage(widthAndHeight,widthAndHeight, BufferedImage.TYPE_INT_RGB);
    }
    public void writeFramePixel(int x, int y, Color color){
        writeFrame.setRGB(x, y, color.getRGB());
    }
    public BufferedImage finishFrame(){
        return writeFrame;
    }
}
