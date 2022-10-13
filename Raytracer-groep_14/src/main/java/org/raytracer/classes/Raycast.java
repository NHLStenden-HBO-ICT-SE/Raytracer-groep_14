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
    public BufferedImage castRays(float rayReach,Scene scene) {
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
            }
        }
        return renderPixelColors.finishFrame();
    }
}
