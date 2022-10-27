package org.raytracer.classes.raycasting;

import org.raytracer.classes.objects.Color;
import org.raytracer.classes.objects.SolidObject;
import org.raytracer.classes.scenes.Scene;
import org.raytracer.classes.gui.UICanvas;
import org.raytracer.classes.rendering.RenderPixelColors;
import org.raytracer.classes.vectors.Vector3;

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
                        
                        // Color absorption
                        intersection.calculateColor(scene.MainLight.getColor(), intersection.getDistanceToLightSource());
    
                        // Intersection Normal
                        Vector3 normalizedIntersectionPosition = intersection.getStartPosition();
                        Vector3 normalizedObjectCenter = object.getPosition();
                        
                        
                        
                        Vector3 intersectionNormal = normalizedIntersectionPosition.subtract(normalizedObjectCenter).normalize();
                        Vector3 intersectionNormal1 =
                                normalizedObjectCenter.subtract(normalizedIntersectionPosition).normalize();
                        
                        // Angle of intersection to light source
                        Vector3 normalizedLightPosition = scene.MainLight.GetPosition();
                        Vector3 directionToLightSource = normalizedLightPosition.subtract(normalizedIntersectionPosition).normalize();
                        Vector3 directionToLightSource1 =
                                normalizedIntersectionPosition.subtract(normalizedLightPosition).normalize();
                        
                        // Angle of impact value = Dot product of Intersection and Normal
                        float angleOfImpact = intersectionNormal.dot(directionToLightSource);
                        
                        // Absorbed color * angle of impact
                        Color renderableColor = intersection.getColor().multiply(angleOfImpact);
                        
                        // Sets colors to a value of max 1 and min 0
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
}
