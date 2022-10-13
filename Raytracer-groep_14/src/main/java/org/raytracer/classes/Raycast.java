package org.raytracer.classes;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class Raycast {

    /**
     * Create a new image out of the data collected by the raytracer
     * @param rayReach
     * @param camera
     * @param width
     * @param height
     * @param scene
     * @return
     */
    public BufferedImage castRays(int rayReach, Camera camera, int width, int height, Scene scene) {
        RenderPixelColors renderPixelColors = new RenderPixelColors(height);
        SolidObject object = scene.GetSolidSceneObject();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Ray tempray = new Ray(camera, i, j);
                Intersection intersection = object.CalculaterIntersectionTemp(tempray);
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
}
