package org.raytracer;

import org.raytracer.classes.objects.Color;
import org.raytracer.classes.gui.UICanvas;
import org.raytracer.classes.objects.Plane;
import org.raytracer.classes.objects.Sphere;
import org.raytracer.classes.vectors.Vector3;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("Starting raytracer");
        
        //create a new uiCanvas which will act as a viewport
        UICanvas uiCanvas = new UICanvas();
        
        // Adds objects to scene
        uiCanvas.addObjectToScene(new Sphere(new Vector3(0, 0, 120), 0.5f, new Color(0.1f, 0, 1f), 0.5f, 1));
        uiCanvas.addObjectToScene(new Sphere(new Vector3(0, 0, 150), 1.2f, new Color(1, 0, 0), 0.6f, 1));
        uiCanvas.addObjectToScene(new Sphere(new Vector3(0, -2, 150), 1f, new Color(1f, 0, 0.1f), 0.5f, 0));
        uiCanvas.addObjectToScene(new Sphere(new Vector3(1, -1, 120), 1, new Color(1f, 0.1f, 0.1f), 0.5f, 0.2f));
        uiCanvas.addObjectToScene(new Sphere(new Vector3(-2, 1, 120), 1f, new Color(1, 0, 0), 0.5f, 1f));
        uiCanvas.addObjectToScene(new Sphere(new Vector3(1, -3, 350), 1f, new Color(1f, 1f, 0), 1, 1));
        uiCanvas.addObjectToScene(new Sphere(new Vector3(0, 1, 100), 1f, new Color(0, 1, 0.2f), 0, 0));
        uiCanvas.addObjectToScene(new Plane(new Vector3(0, -3, 0), new Color(0, 0.5f, 0), 1, 1));
        
        // Makes a frame to be colored in
        uiCanvas.createNewFrame();
        
        // After 20000 cycles the program ends
        for (int i = 0; i < 20000; i++) {
            // Raytracing magic
            uiCanvas.startRaytracer();
        }
        
        System.out.println("Raytracer is finished. Thank you for watching.");
    }
    
}