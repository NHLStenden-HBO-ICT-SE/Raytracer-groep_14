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
        uiCanvas.addSphereToScene(new Vector3(-1,0,250), Color.Blue, 0.5f);
        uiCanvas.addSphereToScene(new Vector3(0,1,330), Color.Black, 0.6f);
        uiCanvas.addSphereToScene(new Vector3(0,-2,150), new Color(0.5f,0.4f,0.3f), 0.5f);
        uiCanvas.addSphereToScene(new Vector3(1,-1,120), new Color(0.5f,0.4f,1), 0.5f);
        uiCanvas.addSphereToScene(new Vector3(-2,-2,120), new Color(1,0,0), 0.5f);
        uiCanvas.addSphereToScene(new Vector3(1,-3,350), new Color(1f,1f,0), 2);
        //make a frame
        uiCanvas.createNewFrame();
        for (int i = 0; i < 900; i++) {
            uiCanvas.startRaytracer();
            System.out.println("loop" + i);
        }
        
    }

}