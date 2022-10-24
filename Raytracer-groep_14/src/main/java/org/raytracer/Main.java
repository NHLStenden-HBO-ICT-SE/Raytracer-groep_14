package org.raytracer;

import org.raytracer.classes.objects.Color;
import org.raytracer.classes.gui.UICanvas;
import org.raytracer.classes.vectors.Vector3;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        //create a new uiCanvas which will act as a viewport
        UICanvas uiCanvas = new UICanvas(900);
        //create a new scene withing the uiCanvas and add one blue object in it
        uiCanvas.addSphereToScene(new Vector3(0,0,100), Color.Blue);
        uiCanvas.addSphereToScene(new Vector3(0,1,100), Color.Black);
        uiCanvas.addSphereToScene(new Vector3(0,-2,99), new Color(0.5f,0.4f,0.3f));
        uiCanvas.addSphereToScene(new Vector3(1,-1,105), new Color(0.5f,0.4f,1));
        //make a frame
        uiCanvas.createNewFrame();
        for (int i = 0; i < 900; i++) {
            uiCanvas.startRaytracer();
        }
        
    }

}