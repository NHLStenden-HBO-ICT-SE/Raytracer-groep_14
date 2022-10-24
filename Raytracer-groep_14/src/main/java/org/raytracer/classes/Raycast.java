package org.raytracer.classes;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class Raycast {

    /**
     * Create a new image out of the data collected by the raytracer
     * @param rayReach
     * @param scene
     * @return
     */
    public BufferedImage castRays(float rayReach,Scene scene) {
        RenderPixelColors renderPixelColors = new RenderPixelColors(scene.getWidthAndHeight());
        SolidObject object = scene.GetSolidSceneObject();
        ThreadManager threadManager = new ThreadManager(5,renderPixelColors);

        //todo Use threadmanager to reserve a given amount of threads
        for (int i = 0; i < scene.getWidthAndHeight(); i++) {
            for (int j = 0; j < scene.getWidthAndHeight(); j++) {
                Ray tempRay = new Ray(scene.GetCamera(), i, j);
                //todo Check if there is a thread available
                //todo if there is no thread available wait for one to be done
                //todo if there is assign the task below to the the thread
                //threadManager.runThread(i,j, object, tempRay);
                while (!threadManager.runThread(i,j, object, tempRay)){

                }
                Intersection intersection = object.CalculaterIntersectionTemp(tempRay);

                if(intersection != null){
                    renderPixelColors.writeFramePixel(i,j,intersection.getSolidObject().getColor());
                }
                else
                {
                    renderPixelColors.writeFramePixel(i,j, Color.White);
                }
                //Go to the next itteration if the thread is working
            }
        }
        //todo Create check if all threads are done with their task
        return renderPixelColors.finishFrame();
    }

    /**
     * A fun method to cast an image line by line
     * @param rayReach
     * @param scene
     * @param canvas
     */
    public void castNOW(float rayReach, Scene scene, UICanvas canvas) {
        RenderPixelColors renderPixelColors = new RenderPixelColors(scene.getWidthAndHeight());
        SolidObject object = scene.GetSolidSceneObject();
        for (int i = 0; i < scene.getWidthAndHeight(); i++) {
            for (int j = 0; j < scene.getWidthAndHeight(); j++) {
                Ray tempRay = new Ray(scene.GetCamera(), i, j);
                Intersection intersection = object.CalculaterIntersectionTemp(tempRay);
                if(intersection != null){
                    renderPixelColors.writeFramePixel(i,j,intersection.getSolidObject().getColor());
                }
                else
                {
                    renderPixelColors.writeFramePixel(i,j, Color.White);
                }
                canvas.updateFrame(renderPixelColors.finishFrame());
            }
        }
    }
}
