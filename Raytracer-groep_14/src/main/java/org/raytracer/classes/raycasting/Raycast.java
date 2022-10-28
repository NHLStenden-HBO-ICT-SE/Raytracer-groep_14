package org.raytracer.classes.raycasting;

import org.raytracer.classes.objects.Color;
import org.raytracer.classes.objects.SolidObject;
import org.raytracer.classes.scenes.Scene;
import org.raytracer.classes.gui.UICanvas;
import org.raytracer.classes.rendering.RenderPixelColors;
import org.raytracer.classes.vectors.Vector3;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Raycast {

    /**
     * get the closest intersection point
     * @param scene
     * @param renderPixelColors
     * @param objectList
     * @param i
     * @param j
     * @param lastPos
     */
    private void getClosestIntersection(Scene scene, RenderPixelColors renderPixelColors, List<SolidObject> objectList, int i, int j, float lastPos) {
        Intersection closestIntersection = null;
        SolidObject closestItem = null;
        List<SolidObject> viewPortList = new ArrayList<>();
        for (SolidObject item: objectList) {
            Intersection intersection = item.calculateIntersection(new Ray(scene.GetCamera(), i, j));
            if(intersection != null){
                viewPortList.add(item);
                if (intersection.getDistanceToCameraOrigin(scene.GetCamera()) < lastPos){
                    lastPos = intersection.getDistanceToCameraOrigin(scene.GetCamera());
                    closestIntersection = intersection;
                    closestItem = item;
                }
            }
            else {
                renderPixelColors.writeFramePixel(i, j, new Color(0,0,Math.min(j/3,255f)));
            }
            if (closestIntersection != null){
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
                boolean lightBlocked = false;

                for (SolidObject item2: objectList) {
                    if (!viewPortList.contains(item2)) {
                        if (item2.getsHitByRay(new Ray(closestIntersection.getStartPosition(), scene.MainLight.GetPosition()))) {

                            if (viewPortList.contains(item2)) {
                                lightBlocked = false;
                                renderPixelColors.writeFramePixel(i, j, renderableColor);
                            } else {
                                renderPixelColors.writeFramePixel(i, j, Color.Black);
                                lightBlocked = true;
                            }
                        }
                    }
                    else {
                        lightBlocked = false;
                    }
                }
                if (!lightBlocked){
                    renderPixelColors.writeFramePixel(i, j, renderableColor);
                }
                //todo Castshadow

            }
        }
    }

    private void getClosestIntersection2(Scene scene, RenderPixelColors renderPixelColors, List<SolidObject> objectList, int i, int j, float lastPos) {
        Intersection closestIntersection = null;
        SolidObject closestItem = null;
        for (SolidObject item: objectList) {
            Intersection intersection = item.calculateIntersectionExp(new Ray(scene.GetCamera(), i, j), objectList, scene.MainLight);
            if(intersection != null){
                if (intersection.getDistanceToCameraOrigin(scene.GetCamera()) < lastPos){
                    lastPos = intersection.getDistanceToCameraOrigin(scene.GetCamera());
                    closestIntersection = intersection;
                    closestItem = item;
                }
            }
            else {
                renderPixelColors.writeFramePixel(i, j, new Color(0,0,Math.min(j/3,255f)));
            }
            if (closestIntersection != null){
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
                renderPixelColors.writeFramePixel(i,j, renderableColor);

                //todo Castshadow

            }
        }
    }


    //todo create a way to give a sample size to the raytracer
    public BufferedImage castNormalForNow(float rayReach,Scene scene) {
        ThreadManager.runExecuter();
        RenderPixelColors renderPixelColors = new RenderPixelColors(scene.getWidthAndHeight());
        List<SolidObject> objectList = scene.getObjectList();
        raytraceToImg2(scene, renderPixelColors, objectList);

        return renderPixelColors.finishFrame();
    }
    private BufferedImage raytraceToImg2(Scene scene, RenderPixelColors renderPixelColors, List<SolidObject> objectList) {
            for (int i = 0; i < scene.getWidthAndHeight(); i++) {
                for (int j = 0; j < scene.getWidthAndHeight(); j++) {
                    float lastPos = 1000;
                    //getClosestIntersection(scene, renderPixelColors, objectList, i, j, lastPos);
                    getClosestIntersection(scene, renderPixelColors, objectList, i, j, lastPos);

                }
            }
            return renderPixelColors.finishFrame();
        }
    }
