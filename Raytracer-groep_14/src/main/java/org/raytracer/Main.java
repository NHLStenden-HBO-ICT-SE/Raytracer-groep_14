package org.raytracer;

import org.raytracer.classes.objects.Color;
import org.raytracer.classes.gui.UICanvas;
import org.raytracer.classes.vectors.Vector3;

public class Main {
    public static void main(String[] args) {
        
        //create a new uiCanvas which will act as a viewport
        UICanvas uiCanvas = new UICanvas(400);
        //create a new scene withing the uiCanvas and add one blue object in it
        uiCanvas.addSphereToScene(new Vector3(0,0,200), Color.Blue);
        // uiCanvas.addSphereToScene(new Vector3(3,0,400), new Color(0.5f,0.5f,1f));
        //make a frame
        uiCanvas.createNewFrame();
        for (int i = 0; i < 900; i++) {
            uiCanvas.startRaytracer();
        }
        
    }

}