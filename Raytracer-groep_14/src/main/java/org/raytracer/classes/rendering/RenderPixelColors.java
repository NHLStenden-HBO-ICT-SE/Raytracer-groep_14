package org.raytracer.classes.rendering;


import org.raytracer.classes.objects.Color;

import javax.swing.*;
import java.awt.image.BufferedImage;

//Todo Add raycast method
public class RenderPixelColors {


    private BufferedImage writeFrame;

    private void startThread(int x, int y, Color color){

        SwingWorker worker = new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                try {
                    writeFrame.setRGB(x, y, color.getRGB());
                } catch (Exception e) {
                    writeFrame.setRGB(x, y, Color.White.getRGB());
                }
                return null;
            }
        };
    }

    public RenderPixelColors(int widthAndHeight){

        constructNewImg(widthAndHeight);
    }

    public void constructNewImg(int widthAndHeight){
        writeFrame = new BufferedImage(widthAndHeight,widthAndHeight, BufferedImage.TYPE_INT_RGB);
    }
    public void writeFramePixel(int x, int y, Color color){
        try {
            writeFrame.setRGB(x, y, color.getRGB());
        } catch (Exception e) {
            writeFrame.setRGB(x, y, Color.White.getRGB());
        }
    }
    public void writeFramePixel(int x, int y, int color){
        try {
            writeFrame.setRGB(x, y, color);
        } catch (Exception e) {
            writeFrame.setRGB(x, y, Color.White.getRGB());
        }
    }
    public BufferedImage finishFrame(){
        return writeFrame;
    }
}
