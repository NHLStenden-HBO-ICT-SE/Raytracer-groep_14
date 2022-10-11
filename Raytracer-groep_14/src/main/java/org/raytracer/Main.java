package org.raytracer;

import org.raytracer.classes.Color;
import org.raytracer.classes.UICanvas;
import org.raytracer.classes.Vector3;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        //create a new uitcanvast wich will act as a viewport
        UICanvas uiCanvas = new UICanvas(400, 400);
        //create a new scene withing the uicanvas and add one blue object in it
        uiCanvas.setupScenes(new Vector3(0,0,100), Color.Blue);
        //make a frame
        uiCanvas.createNewFrame();
        for (int i = 0; i < 900; i++) {
            uiCanvas.castRays();
        }

    }

}