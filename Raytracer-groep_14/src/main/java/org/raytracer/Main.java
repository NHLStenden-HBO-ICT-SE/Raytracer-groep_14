package org.raytracer;

import org.raytracer.classes.objects.Color;
import org.raytracer.classes.gui.UICanvas;
import org.raytracer.classes.objects.Plane;
import org.raytracer.classes.objects.Sphere;
import org.raytracer.classes.vectors.Vector3;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        //create a new uiCanvas which will act as a viewport
        UICanvas uiCanvas = new UICanvas(900);
        //create a new scene withing the uiCanvas and add one blue object in it
        uiCanvas.addObjectToScene(new Sphere(new Vector3(-1,0,250), 1, Color.Blue, 0.5f, 1));
        uiCanvas.addObjectToScene(new Sphere(new Vector3(0,1,330), 0.5f, Color.Black, 0.6f, 1));
        uiCanvas.addObjectToScene(new Sphere(new Vector3(0,-2,150), 0.9f,  new Color(0.5f,0.4f,0.3f), 0.5f, 0));
        uiCanvas.addObjectToScene(new Sphere(new Vector3(1,-1,120), 1.3f, new Color(0.5f,0.4f,1), 0.5f, 0.2f));
        uiCanvas.addObjectToScene(new Sphere(new Vector3(-2,-2,120), 0.2f, new Color(1,0,0), 0.5f, 1f));
        uiCanvas.addObjectToScene(new Sphere(new Vector3(1,-3,350), 0.75f, new Color(1f,1f,0), 2, 10));
        uiCanvas.addObjectToScene(new Sphere(new Vector3(0,1,100), 0.6f, new Color(0,1,0.2f), 0,0));
        uiCanvas.addObjectToScene(new Plane(new Vector3(0,-3,1), new Color(0,1,0), 1,1));

        //make a frame
        uiCanvas.createNewFrame();
        for (int i = 0; i < 900; i++) {
            uiCanvas.startRaytracer();
            System.out.println("loop" + i);
        }
        
    }

}