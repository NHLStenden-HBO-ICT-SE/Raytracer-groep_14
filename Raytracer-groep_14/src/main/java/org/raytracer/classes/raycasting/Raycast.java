package org.raytracer.classes.raycasting;

import org.raytracer.classes.objects.Color;
import org.raytracer.classes.objects.SolidObject;
import org.raytracer.classes.scenes.Scene;
import org.raytracer.classes.gui.UICanvas;
import org.raytracer.classes.rendering.RenderPixelColors;
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
                if (intersection.getDistanceToCameraOrigin() < lastPos){
                    lastPos = intersection.getDistanceToCameraOrigin();
                    closestIntersection = intersection;
                    closestObject = item;
                }
            }
            else {
                renderPixelColors.writeFramePixel(i, j, new Color(0,0,Math.min(j,255f)));
            }
            if (closestIntersection != null){
                renderPixelColors.writeFramePixel(i, j, closestObject.getColor());   //replacement code, needs a colour to return else all goes to hell
                closestIntersection.setLightPosition(scene.MainLight.GetPosition());

                closestIntersection.calculateColor(scene.MainLight.getColor(), intersection.getDistanceToLightSource());


                Color renderableColor = intersection.getColor();
                renderableColor.nerfColor();
                //todo Castshadow
            }
        }
        return closestObject;
    }
    private Intersection getClosestIntersection(Scene scene, RenderPixelColors renderPixelColors, List<SolidObject> objectList, int i, int j, float lastPos) {
        Intersection closestIntersection = null;
        for (SolidObject item: objectList) {
            Intersection intersection = item.calculateIntersection(new Ray(scene.GetCamera(), i, j));
            if(intersection != null){
                if (intersection.getDistanceToCameraOrigin() < lastPos){
                    lastPos = intersection.getDistanceToCameraOrigin();
                    closestIntersection = intersection;
                }
            }
            else {
                renderPixelColors.writeFramePixel(i, j, new Color(0,0,Math.min(j,255f)));
            }
            if (closestIntersection != null){
                closestIntersection.setLightPosition(scene.MainLight.GetPosition());
                closestIntersection.calculateColor(scene.MainLight.getColor(), closestIntersection.getDistanceToLightSource());
                Color renderableColor = closestIntersection.getColor();
                renderableColor.nerfColor();
                renderPixelColors.writeFramePixel(i, j, renderableColor);
                //todo Castshadow
            }
        }
        return closestIntersection;
    }

    //todo create a way to give a sample size to the raytracer
    public BufferedImage castThreadedRaysMultipleObjectsAntiAliasing(float rayReach,Scene scene) {
        ThreadManager.runExecuter();
        RenderPixelColors renderPixelColors = new RenderPixelColors(scene.getWidthAndHeight());
        List<SolidObject> objectList = scene.getObjectList();
        List<Future<BufferedImage>> threadImages = new ArrayList<Future<BufferedImage>>();
        for (int l = 0; l < 3; l++) {
            raytraceToImg(scene, renderPixelColors, objectList, threadImages);
        }
            List<BufferedImage> imageResultList = createNullList(3);
            boolean processing = true;
            while (processing){
                if (IntStream.of(0, 1, 2).allMatch(j -> checkIfThreadIsDone(threadImages, imageResultList, j))){
                    for(int i = 0; i < 3; i++){
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
                    float lastPos = 300;

                    //SolidObject closestObject = getClosestObject(scene, renderPixelColors, objectList, i, j, lastPos);;

                    getClosestIntersection(scene, renderPixelColors, objectList, i, j, lastPos);
                    //getClosestObject(scene, renderPixelColors, objectList, i, j, lastPos);
                    //todo grab the position of the closest object intersection and shoot a shadowray
                }
            }
            return renderPixelColors.finishFrame();
        }));
    }

    /**
     *
     * @param scene
     * @param renderPixelColors
     * @param objectList
     * @param threadImages
     * @param aASupplement
     */
    private void raytraceToImg(Scene scene, RenderPixelColors renderPixelColors, List<SolidObject> objectList, List<Future<BufferedImage>> threadImages, float aASupplement) {
        threadImages.add(ThreadManager.executerService.submit(() -> {
            for (int i = 0; i < scene.getWidthAndHeight(); i++) {
                for (int j = 0; j < scene.getWidthAndHeight(); j++) {
                    float lastPos = 300;
                    SolidObject closestObject = null;
                    getClosestObject(scene, renderPixelColors, objectList, i, j, lastPos);
                }
            }
            return renderPixelColors.finishFrame();
        }));
    }
}
