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
        uiCanvas.addObjectToScene(new Sphere(new Vector3(2,0,120), 0.5f, new Color(0.1f,0,1f), 0.5f, 1));
        uiCanvas.addObjectToScene(new Sphere(new Vector3(0,0,140), 1f, new Color(1,0,0), 0.6f, 1));
        uiCanvas.addObjectToScene(new Sphere(new Vector3(0,-2,150), 1f,  new Color(0.5f,0,0.1f), 0.5f, 0));
        uiCanvas.addObjectToScene(new Sphere(new Vector3(1,-1,130), 1, new Color(0.7f,0.1f,0.3f), 0.5f, 0.2f));
        uiCanvas.addObjectToScene(new Sphere(new Vector3(-2,1,100), 1f, new Color(0,0,1), 0.5f, 1f));
//        uiCanvas.addObjectToScene(new Sphere(new Vector3(1,-3,350), 1f, new Color(0f,0f,0), 1, 1));
        uiCanvas.addObjectToScene(new Sphere(new Vector3(0,1,90), 1f, new Color(0,1,0.2f), 0,0));

//        uiCanvas.addObjectToScene(new Plane(new Vector3(0,-1,0), Color.Blue, 0,0));


        //make a frame
        uiCanvas.createNewFrame();
        for (int i = 0; i < 2000; i++) {
            uiCanvas.startRaytracer();
        }
        System.out.println("done");
        
    }

}