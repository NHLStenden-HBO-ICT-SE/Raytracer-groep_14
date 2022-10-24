package org.raytracer.classes;

import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.concurrent.*;

public class Raycast {

    /**
     * Create a new image out of the data collected by the raytracer
     * @param rayReach
     * @param scene
     * @return
     */
    public BufferedImage threadedCastRays(float rayReach,Scene scene){
        RenderPixelColors renderPixelColors = new RenderPixelColors(scene.getWidthAndHeight());
        SolidObject object = scene.GetSolidSceneObject();
        Future<BufferedImage> threadedImage = ThreadManager.executerService.submit(new Callable<BufferedImage>() {
            @Override
            public BufferedImage call() throws Exception {
                for (int i = 0; i < scene.getWidthAndHeight(); i++) {
                    for (int j = 0; j < scene.getWidthAndHeight(); j++) {
                        Intersection intersection = object.calculateIntersection(new Ray(scene.GetCamera(), i, j));
                        if(intersection != null){
                            renderPixelColors.writeFramePixel(i,j,intersection.getSolidObject().getColor());
                        }
                        else
                        {
                            renderPixelColors.writeFramePixel(i,j, Color.White);
                        }
                    }
                }
                return renderPixelColors.finishFrame();
            }
        });
        while (!threadedImage.isDone()){
            System.out.println("processing stay patient");
        }
        ThreadManager.executerService.shutdown();
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
                Intersection intersection = object.calculateIntersection(tempRay);
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
