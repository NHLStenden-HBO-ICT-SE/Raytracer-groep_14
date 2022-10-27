package org.raytracer.classes.raycasting;

import org.raytracer.classes.objects.Color;
import org.raytracer.classes.objects.SolidObject;
import org.raytracer.classes.scenes.Scene;
import org.raytracer.classes.gui.UICanvas;
import org.raytracer.classes.rendering.RenderPixelColors;

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
        
        for (int i = 0; i < scene.getWidthAndHeight(); i++) {
            for (int j = 0; j < scene.getWidthAndHeight(); j++) {
                
                Ray tempRay = new Ray(scene.GetCamera(), i, j);
                
                // ray hits object
                for (SolidObject object : scene.getSolidObjectList()) {
                    if (object.getsHitByRay(tempRay)) {
                        
                        Intersection intersection = object.calculateIntersection(tempRay);
                        intersection.setLightPosition(scene.MainLight.GetPosition());
                        
                        intersection.calculateColor(scene.MainLight.getColor(), intersection.getDistanceToLightSource());
                        
                        
                        Color renderableColor = intersection.getColor();
                        renderableColor.nerfColor();
                        
                        
                        // colors have to be converted to be max 1,1,1
                        renderPixelColors.writeFramePixel(i, j, renderableColor);
                    } else { // Ray has not a hit with object
                        renderPixelColors.writeFramePixel(i, j, Color.White);
                    }
                }
            }
        }
        return renderPixelColors.finishFrame();
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
