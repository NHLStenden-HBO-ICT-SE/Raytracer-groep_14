package org.raytracer;

import org.raytracer.classes.objects.Color;
import org.raytracer.classes.gui.UICanvas;
import org.raytracer.classes.vectors.Vector3;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        //create a new uiCanvas which will act as a viewport
        UICanvas uiCanvas = new UICanvas(400);
        //create a new scene withing the uiCanvas and add one blue object in it
        uiCanvas.addSphereToScene(new Vector3(0,-3,100), new Color(1,0,0));
        //create a plane object
        uiCanvas.addPlaneToScene(new Vector3(0,-3,1),new Color(0,1,0));
        //make a frame
        uiCanvas.createNewFrame();
        for (int i = 0; i < 900; i++) {
            uiCanvas.startRaytracer();
        }
        
    }

}