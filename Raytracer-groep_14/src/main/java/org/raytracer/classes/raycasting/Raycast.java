package org.raytracer.classes.raycasting;

import org.raytracer.classes.objects.Color;
import org.raytracer.classes.objects.SolidObject;
import org.raytracer.classes.scenes.Scene;
import org.raytracer.classes.gui.UICanvas;
import org.raytracer.classes.rendering.RenderPixelColors;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

import java.awt.image.BufferedImage;

public class Raycast {
    
    /**
     * Create a new image out of the data collected by the raytracer
     *
     * @param rayReach
     * @param scene
     * @return
     */
    public BufferedImage castRays(float rayReach, Scene scene) {
        RenderPixelColors renderPixelColors = new RenderPixelColors(scene.getWidthAndHeight());

        SolidObject sphere = scene.getFirstSolidObject(); // todo weghalen en de lijst aanroepen
        SolidObject plane = scene.GetSceneObject(1);
        for (int i = 0; i < scene.getWidthAndHeight(); i++) {
            for (int j = 0; j < scene.getWidthAndHeight(); j++) {
                Ray tempRay = new Ray(scene.GetCamera(), i, j);
                Intersection sphereIntersection = sphere.calculateIntersection(tempRay);
                Intersection planeIntersection =plane.calculateIntersection(tempRay);

                if (sphereIntersection != null || planeIntersection != null) {
                    //todo object dat in de lijst voorkomt gebruiken om kleur op te vragen.
                    renderPixelColors.writeFramePixel(i, j, sphere.getColor());
                    renderPixelColors.writeFramePixel(i,j,plane.getColor());
                } else {
                    renderPixelColors.writeFramePixel(i, j, Color.Blue);
                }
            }
        }
        return renderPixelColors.finishFrame();
    }
    public BufferedImage castThreadedRaysMultipleObjects(float rayReach,Scene scene){
        RenderPixelColors renderPixelColors = new RenderPixelColors(scene.getWidthAndHeight());
        List<SolidObject> objectList = scene.getObjectList();
        Future<BufferedImage> threadedImage = ThreadManager.executerService.submit(() -> {
            for (int i = 0; i < scene.getWidthAndHeight(); i++) {
                for (int j = 0; j < scene.getWidthAndHeight(); j++) {
                    float lastPos = 300;
                    SolidObject closestObject = null;
                    for (SolidObject item: objectList) {
                        Intersection intersection = item.calculateIntersection(new Ray(scene.GetCamera(), i, j));
                        if(intersection != null){
                            if (intersection.getDistanceToCameraOrigin() < lastPos){
                                lastPos = intersection.getDistanceToCameraOrigin();
                                closestObject = item;
                            }
                        }
                        if (closestObject != null){
                            renderPixelColors.writeFramePixel(i,j,closestObject.getColor());   //replacement code, needs a colour to return else all goes to hell
                        }
                        else {
                            renderPixelColors.writeFramePixel(i,j, new Color(0,0,Math.min(j,255f)));
                        }
                    }
                }
            }
            return renderPixelColors.finishFrame();
        });
        while (!threadedImage.isDone()){
            System.out.println("processing stay patient");
        }
        try {
            return threadedImage.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * A fun method to cast an image line by line
     *
     * @param rayReach
     * @param scene
     * @param canvas
     */
    public void castLine(float rayReach, Scene scene, UICanvas canvas) {
        RenderPixelColors renderPixelColors = new RenderPixelColors(scene.getWidthAndHeight());
        SolidObject object = scene.getFirstSolidObject();
        for (int i = 0; i < scene.getWidthAndHeight(); i++) {
            for (int j = 0; j < scene.getWidthAndHeight(); j++) {
                Ray tempRay = new Ray(scene.GetCamera(), i, j);
                Intersection intersection = object.calculateIntersection(tempRay);
                if (intersection != null) {
                    renderPixelColors.writeFramePixel(i, j, object.getColor());
                } else {
                    renderPixelColors.writeFramePixel(i, j, Color.White);
                }
                canvas.updateFrame(renderPixelColors.finishFrame());
            }
        }
    }
}
