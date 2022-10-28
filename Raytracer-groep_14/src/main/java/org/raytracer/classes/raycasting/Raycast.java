package org.raytracer.classes.raycasting;

import org.raytracer.classes.objects.Color;
import org.raytracer.classes.objects.SolidObject;
import org.raytracer.classes.scenes.Scene;
import org.raytracer.classes.rendering.BufferedImageGenerator;
import org.raytracer.classes.vectors.Vector3;

import java.awt.image.BufferedImage;

public class Raycast {
    
    /**
     * get the closest intersection point
     *
     * @param scene
     * @param bufferedImageGenerator
     * @param i
     * @param j
     * @param lastPos
     */
    private void getClosestIntersection(Scene scene, BufferedImageGenerator bufferedImageGenerator, int i, int j, float lastPos) {
        Intersection closestIntersection = null;
        SolidObject closestItem = null;
        
        for (SolidObject item : scene.getObjectList()) {
            Intersection intersection = item.calculateIntersection(new Ray(scene.GetCamera(), i, j));
            
            if (intersection != null) {
                if (intersection.getDistanceToCameraOrigin(scene.GetCamera()) < lastPos) {
                    lastPos = intersection.getDistanceToCameraOrigin(scene.GetCamera());
                    closestIntersection = intersection;
                    closestItem = item;
                }
            } else {
                bufferedImageGenerator.writeFramePixel(i, j, new Color(0, 0, Math.min(j / 3, 255f)));
            }
            
            if (closestIntersection != null) {
                closestIntersection.setLightPosition(scene.MainLight.GetPosition());
                closestIntersection.calculateColor(scene.MainLight.getColor(), closestIntersection.getDistanceToLightSource());
                
                Vector3 normalizedIntersectionPosition = closestIntersection.getStartPosition();
                Vector3 normalizedObjectCenter = closestItem.getPosition(); //mogelijk probleem
                Vector3 intersectionNormal = normalizedIntersectionPosition.subtract(normalizedObjectCenter).normalize();
                
                
                // Angle of intersection to light source
                Vector3 normalizedLightPosition = scene.MainLight.GetPosition();
                Vector3 directionToLightSource = normalizedLightPosition.subtract(normalizedIntersectionPosition).normalize();
                
                
                float angleOfImpact = intersectionNormal.dot(directionToLightSource);
                
                Color renderableColor = closestIntersection.getColor().multiply(angleOfImpact);
                
                renderableColor.nerfColor();
                bufferedImageGenerator.writeFramePixel(i, j, renderableColor);
                //todo Castshadow
                
            }
        }
    }
    
    
    //todo create a way to give a sample size to the raytracer
    public BufferedImage castNormalForNow(float rayReach, Scene scene) {
        
        ThreadManager.runExecuter();
        BufferedImageGenerator bufferedImageGenerator = new BufferedImageGenerator(scene.getWidthAndHeight());
        convertRaytracingToImage(scene, bufferedImageGenerator);
        
        return bufferedImageGenerator.finishFrame();
    }
    
    private BufferedImage convertRaytracingToImage(Scene scene, BufferedImageGenerator bufferedImageGenerator) {
        for (int i = 0; i < scene.getWidthAndHeight(); i++) {
            for (int j = 0; j < scene.getWidthAndHeight(); j++) {
                float maximumDistance = 1000;
                getClosestIntersection(scene, bufferedImageGenerator, i, j, maximumDistance);
                
            }
        }
        return bufferedImageGenerator.finishFrame();
    }
}
