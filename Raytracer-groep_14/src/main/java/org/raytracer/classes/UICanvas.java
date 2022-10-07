package org.raytracer.classes;

import javax.swing.*;

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
        raycaster.castLine(10, cam, Width,Height, activeScene);
    }



}
