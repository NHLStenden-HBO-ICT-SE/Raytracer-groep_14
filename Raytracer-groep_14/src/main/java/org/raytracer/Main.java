package org.raytracer;

import org.raytracer.classes.Color;
import org.raytracer.classes.UICanvas;
import org.raytracer.classes.Vector3;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        UICanvas uiCanvas = new UICanvas(900, 900);

        uiCanvas.setupScenes(new Vector3(1,0,10), new Color());
        uiCanvas.createNewFrame();
        uiCanvas.castRays();

    }

}