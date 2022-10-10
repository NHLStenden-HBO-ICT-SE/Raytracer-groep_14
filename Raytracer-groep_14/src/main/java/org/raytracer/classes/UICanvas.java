package org.raytracer.classes;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UICanvas{


    JFrame canvasFrame = new JFrame("best frame ever");

    private int Height, Width;
    PixelData [][] pixelData;

    public Scene activeScene= new Scene();

    Camera cam;

    //todo create a camera object
    //todo set the pixels from the screen
    //todo try to save the pixels inside some class containing a multidemensional array
    //todo try to create a few empty raycasts
    //todo make a mockup pixelloop that will try to call raycasts through the camera, looping with x and y or u and v

    public UICanvas(int height, int width){

        pixelData = new PixelData[height][width];

        this.Height = height;
        this.Width = width;

        if(canvasFrame != null){
            createNewFrame();
        }
        canvasFrame.setSize(height, width);
        canvasFrame.setVisible(true);
        cam = new Camera();
        Raycast raycaster = new Raycast();
        raycaster.castLine(10, cam, width,height);
        this.Width = cam.getScreenWidth();
        this.Height = cam.getScreenHeight();

    }
    public boolean createNewFrame(){

        this.canvasFrame = new JFrame("best frame ever");
        return true;
    }
    public void setupScenes(Vector3 position, Color color){

        activeScene.setupScene(new Sphere(position, 10,color, 0, 1 ));
    }
    public void castRays(){

        Raycast raycaster = new Raycast();
        pixelData = raycaster.castLine(10, cam, Width,Height, activeScene);
        //raycaster.castLine(10, cam, Width,Height, activeScene);
        System.out.println("alldone");
        BufferedImage image = null;
        image = new BufferedImage(this.Width,this.Height, BufferedImage.TYPE_INT_RGB);

        Graphics2D gdraw = image.createGraphics();
        gdraw.setColor(java.awt.Color.BLACK);
        gdraw.fillRect(0,0,this.Width,this.Height);

        BufferedImage theImage = new BufferedImage(this.Width, this.Height,
                BufferedImage.TYPE_INT_RGB);
        float pixelSize = 1;
        for (int i = 0; i < this.Width; i++) {
            for (int j = 0; j < this.Height; j++) {
                PixelData data = pixelData[i][j];
                try {
                    gdraw.setColor(pixelData[i][j].getColor().toAWTColor());
                    image.setRGB(i,j,pixelData[i][j].getColor().getRGB());
                }
                catch (Exception e){
                    gdraw.setColor(new java.awt.Color(1,1,1));
                    image.setRGB(i,j, Color.Black.getRGB());
                }
                gdraw.fillRect((int)(i*pixelSize), (int)(j*pixelSize), (int)pixelSize+1, (int)pixelSize+1);
            }
        }
        //src\main\resources\rame.png
        File f = new File("rame");
        try
        {
            f = new File("rame.png");
            System.out.println("createdFile:"+f.getAbsolutePath());
            f.createNewFile();
            ImageIO.write(image, "png", f);
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }


    }



}
