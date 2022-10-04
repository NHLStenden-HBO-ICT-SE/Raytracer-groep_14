package org.raytracer.classes;

import javax.swing.*;

public class UICanvas{

    JFrame canvasFrame = new JFrame("best frame ever");

    PixelData [][] pixelData;

    Camera cam;

    //todo create a camera object
    //todo set the pixels from the screen
    //todo try to save the pixels inside some class containing a multidemensional array
    //todo try to create a few empty raycasts
    //todo make a mockup pixelloop that will try to call raycasts through the camera, looping with x and y or u and v

    public UICanvas(int height, int width){

        pixelData = new PixelData[height][width];

        if(canvasFrame != null){
            createNewFrame();
        }
        canvasFrame.setSize(height, width);
        canvasFrame.setVisible(true);
        cam = new Camera();
        Raycast raycaster = new Raycast();
        raycaster.castLine(10, cam, width,height);

    }
    public boolean createNewFrame(){

        this.canvasFrame = new JFrame("best frame ever");
        return true;
    }



}
