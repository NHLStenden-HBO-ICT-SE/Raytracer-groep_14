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
    private SolidObject getClosestObject(Scene scene, RenderPixelColors renderPixelColors, List<SolidObject> objectList, int i, int j, float lastPos) {
        SolidObject closestObject = null;
        for (SolidObject item: objectList) {
            Intersection intersection = item.calculateIntersection(new Ray(scene.GetCamera(), i, j));
            Intersection closestIntersection = null;
            if(intersection != null){
                if (intersection.getDistanceToCameraOrigin(scene.GetCamera()) < lastPos){
                    lastPos = intersection.getDistanceToCameraOrigin(scene.GetCamera());
                    closestIntersection = intersection;
                    closestObject = item;
                }
            }
            else {
                renderPixelColors.writeFramePixel(i, j, new Color(0,0,Math.min(j,255f)));
            }
            if (closestIntersection != null){
                //renderPixelColors.writeFramePixel(i, j, closestObject.getColor());   //replacement code, needs a colour to return else all goes to hell
                closestIntersection.setLightPosition(scene.MainLight.GetPosition());

                //Color absorption
                closestIntersection.calculateColor(scene.MainLight.getColor(), closestIntersection.getDistanceToLightSource());


                // Intersection Normal
                Vector3 normalizedIntersectionPosition = closestIntersection.getStartPosition();
                Vector3 normalizedObjectCenter = item.getPosition();

                Vector3 intersectionNormal = normalizedIntersectionPosition.subtract(normalizedObjectCenter).normalize();

                Vector3 intersectionNormal1 =
                        normalizedObjectCenter.subtract(normalizedIntersectionPosition).normalize();

                // Angle of intersection to light source
                Vector3 normalizedLightPosition = scene.MainLight.GetPosition();
                Vector3 directionToLightSource = normalizedLightPosition.subtract(normalizedIntersectionPosition).normalize();

                Vector3 directionToLightSource1 =
                        normalizedIntersectionPosition.subtract(normalizedLightPosition).normalize();

                float angleOfImpact = intersectionNormal.dot(directionToLightSource);

                // Absorbed color * angle of impact
                Color renderableColor = intersection.getColor().multiply(angleOfImpact);

                // Sets colors to a value of max 1 and min 0
                renderableColor.nerfColor();

                // colors have to be converted to be max 1,1,1
                renderPixelColors.writeFramePixel(i, j, renderableColor);
                //todo Castshadow
            }
        }
        return closestObject;
    }
    private void getClosestIntersection(Scene scene, RenderPixelColors renderPixelColors, List<SolidObject> objectList, int i, int j, float lastPos) {
        Intersection closestIntersection = null;
        for (SolidObject item: objectList) {
            Intersection intersection = item.calculateIntersection(new Ray(scene.GetCamera(), i, j));
            if(intersection != null){
                if (intersection.getDistanceToCameraOrigin(scene.GetCamera()) < lastPos){
                    lastPos = intersection.getDistanceToCameraOrigin(scene.GetCamera());
                    closestIntersection = intersection;
                }
            }
            else {
                renderPixelColors.writeFramePixel(i, j, new Color(0,0,Math.min(j,255f)));
            }
            if (closestIntersection != null){
                closestIntersection.setLightPosition(scene.MainLight.GetPosition());
                closestIntersection.calculateColor(scene.MainLight.getColor(), closestIntersection.getDistanceToLightSource());
                Vector3 normalizedIntersectionPosition = closestIntersection.getStartPosition();
                Vector3 normalizedObjectCenter = item.getPosition();
                Vector3 intersectionNormal = normalizedIntersectionPosition.subtract(normalizedObjectCenter).normalize();


                // Angle of intersection to light source
                Vector3 normalizedLightPosition = scene.MainLight.GetPosition();
                Vector3 directionToLightSource = normalizedLightPosition.subtract(normalizedIntersectionPosition).normalize();


                float angleOfImpact = intersectionNormal.dot(directionToLightSource);

                Color renderableColor = closestIntersection.getColor().multiply(angleOfImpact);
                renderableColor.nerfColor();
                renderPixelColors.writeFramePixel(i, j, renderableColor);
                //todo Castshadow
            }
        }
    }


    //todo create a way to give a sample size to the raytracer
    public BufferedImage castThreadedRaysMultipleObjectsAntiAliasing(float rayReach,Scene scene) {
        ThreadManager.runExecuter();
        RenderPixelColors renderPixelColors = new RenderPixelColors(scene.getWidthAndHeight());
        List<SolidObject> objectList = scene.getObjectList();
        List<Future<BufferedImage>> threadImages = new ArrayList<Future<BufferedImage>>();
        for (int l = 0; l < 1; l++) {
            raytraceToImg(scene, renderPixelColors, objectList, threadImages);
        }
            List<BufferedImage> imageResultList = createNullList(0);
            boolean processing = true;
            while (processing){
                if (IntStream.of(0).allMatch(j -> checkIfThreadIsDone(threadImages, imageResultList, j))){
                    for(int i = 0; i < 1; i++){
                        if (imageResultList.get(i) == null){
                            checkIfThreadIsDone(threadImages,imageResultList,i);
                        }
                    }
                    processing = false;
                }
            }
        frameComparer(scene, renderPixelColors, imageResultList);
        ThreadManager.executerService.shutdown();
        return renderPixelColors.finishFrame();
    }

    public BufferedImage castNormalForNow(float rayReach,Scene scene) {
        ThreadManager.runExecuter();
        RenderPixelColors renderPixelColors = new RenderPixelColors(scene.getWidthAndHeight());
        List<SolidObject> objectList = scene.getObjectList();
        raytraceToImg2(scene, renderPixelColors, objectList);

        return renderPixelColors.finishFrame();
    }
    /**
     * create a empty list and fill it with null objects
     * @param emptyObjectsToAdd
     * @return
     */
    private List<BufferedImage> createNullList(int emptyObjectsToAdd){

        List<BufferedImage> listToFill = new ArrayList<>();
        for (int i = 0; i < emptyObjectsToAdd; i++) {
            listToFill.add(null);
        }
        return listToFill;
    }
    private List<BufferedImage> createNullList(int emptyObjectsToAdd, List<BufferedImage> listToFIll){

        for (int i = 0; i < emptyObjectsToAdd; i++) {
            listToFIll.add(null);
        }
        return listToFIll;
    }

    /**
     *
     * @param scene
     * @param renderPixelColors
     * @param imageResultList
     */
    private void frameComparer(Scene scene, RenderPixelColors renderPixelColors, List<BufferedImage> imageResultList) {
        for (int i = 0; i < scene.getWidthAndHeight(); i++){
            for (int j = 0; j < scene.getWidthAndHeight(); j++){
                int colorInt = 0;
                for (BufferedImage image: imageResultList) {
                    if (image == null){
                        System.out.println("heya");
                    }
                    else {
                        colorInt += image.getRGB(i,j);
                    }
                }
                renderPixelColors.writeFramePixel(i, j, colorInt/3);
            }
        }
    }

    /**
     *
     * @param threadImages
     * @param imageResultList
     * @param f
     * @return
     */
    private boolean checkIfThreadIsDone(List<Future<BufferedImage>> threadImages, List<BufferedImage> imageResultList, int f) {
        if (threadImages.get(f).isDone()){
            try {
                if (imageResultList.get(f) == null){
                    imageResultList.remove(f);
                    imageResultList.add(f, threadImages.get(f).get());
                }
                //imageResultList.add(threadImages.get(f).get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return threadImages.get(f).isDone();
    }

    /**
     *
     * @param scene
     * @param renderPixelColors
     * @param objectList
     * @param threadImages
     */
    private void raytraceToImg(Scene scene, RenderPixelColors renderPixelColors, List<SolidObject> objectList, List<Future<BufferedImage>> threadImages) {
        threadImages.add(ThreadManager.executerService.submit(() -> {
            for (int i = 0; i < scene.getWidthAndHeight(); i++) {
                for (int j = 0; j < scene.getWidthAndHeight(); j++) {
                    float lastPos = 1000;

                    //SolidObject closestObject = getClosestObject(scene, renderPixelColors, objectList, i, j, lastPos);;

                    getClosestIntersection(scene, renderPixelColors, objectList, i, j, lastPos);
                    //getClosestObject(scene, renderPixelColors, objectList, i, j, lastPos);
                    //todo grab the position of the closest object intersection and shoot a shadowray
                }
            }
            return renderPixelColors.finishFrame();
        }));
    }
    private BufferedImage raytraceToImg2(Scene scene, RenderPixelColors renderPixelColors, List<SolidObject> objectList) {
            for (int i = 0; i < scene.getWidthAndHeight(); i++) {
                for (int j = 0; j < scene.getWidthAndHeight(); j++) {
                    float lastPos = 1000;

                    //SolidObject closestObject = getClosestObject(scene, renderPixelColors, objectList, i, j, lastPos);;

                    getClosestIntersection(scene, renderPixelColors, objectList, i, j, lastPos);
                    //getClosestObject(scene, renderPixelColors, objectList, i, j, lastPos);
                    //todo grab the position of the closest object intersection and shoot a shadowray
                }
            }
            return renderPixelColors.finishFrame();
        }
    };

    /**
     *
     * @param scene
     * @param renderPixelColors
     * @param objectList
     * @param threadImages
     * @param aASupplement
     */
