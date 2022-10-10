package org.raytracer;

import org.raytracer.classes.Color;
import org.raytracer.classes.UICanvas;
import org.raytracer.classes.Vector3;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        //create a new uitcanvast wich will act as a viewport
        UICanvas uiCanvas = new UICanvas(900, 900);
        //create a new scene withing the uicanvas and add one blue object in it
        uiCanvas.setupScenes(new Vector3(1,0,9), Color.Blue);
        //make a frame
        uiCanvas.createNewFrame();
        //start casting rays
        uiCanvas.castRays();

    }

}